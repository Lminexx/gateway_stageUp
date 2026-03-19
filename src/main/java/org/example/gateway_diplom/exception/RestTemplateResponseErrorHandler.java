package org.example.gateway_diplom.exception;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;


@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().is4xxClientError()
                || httpResponse.getStatusCode().is5xxServerError());

    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        Optional<String> requestBody = Optional.ofNullable(new BufferedReader(new InputStreamReader(httpResponse.getBody())).readLine());

        throw new WebClientException(httpResponse.getStatusCode(), requestBody.orElse("No body"));
    }
}
