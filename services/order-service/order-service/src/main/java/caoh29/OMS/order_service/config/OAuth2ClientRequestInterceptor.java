package caoh29.OMS.order_service.config;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
@AllArgsConstructor
public class OAuth2ClientRequestInterceptor implements ClientHttpRequestInterceptor {

    private OAuth2AuthorizedClientManager authorizedClientManager;

    private String clientRegistrationId;

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId(clientRegistrationId)
                .principal("order-service")
                .build();

        OAuth2AuthorizedClient authorizedClient =
                authorizedClientManager.authorize(authorizeRequest);

        if (authorizedClient != null && authorizedClient.getAccessToken() != null) {
            // Add the access token to the request headers
            String token = authorizedClient.getAccessToken().getTokenValue();
            request.getHeaders().setBearerAuth(token);
        }

        return execution.execute(request, body);
    }
}
