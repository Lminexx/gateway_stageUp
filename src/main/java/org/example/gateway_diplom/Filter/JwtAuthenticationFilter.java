package org.example.gateway_diplom.Filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Если заголовка нет, отдаем дальше (Spring Security сам выдаст 403, если эндпоинт защищен)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        HttpServletRequest requestToForward = request;

        try {
            if (jwtUtil.isTokenExpired(jwt)) {
                sendUnauthorizedError(response, "Token is expired");
                return;
            }

            Claims claims = jwtUtil.getClaims(jwt);
            String username = claims.get("username", String.class);
            String role     = claims.get("role", String.class);

            // ВАЖНО: убедись, что в JWT поле называется именно "id", а не "userId" или "sub"
            String userId   = claims.get("id", String.class);

            if (userId == null) {
                sendUnauthorizedError(response, "User ID claim is missing in token");
                return;
            }

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    username, null, Collections.singletonList(authority)
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);

            // Оборачиваем запрос, чтобы добавить заголовок
            MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
            mutableRequest.putHeader("X-User-Id", userId);

            requestToForward = mutableRequest;

        } catch (Exception e) {
            // Теперь мы ловим ТОЛЬКО ошибки парсинга JWT
            System.out.println("JWT Parsing Error: " + e.getMessage());
            sendUnauthorizedError(response, "Invalid JWT Token");
            return;
        }

        // Вызываем контроллер ОДИН РАЗ, и он находится вне блока try-catch!
        filterChain.doFilter(requestToForward, response);
    }

    private void sendUnauthorizedError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}