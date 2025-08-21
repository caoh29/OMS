package caoh29.OMS.order_service.config;

import caoh29.OMS.order_service.clients.InventoryClient;
import caoh29.OMS.order_service.clients.ProductCatalogClient;
import io.micrometer.observation.ObservationRegistry;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {
    @Value("${app.services.inventory-service.base-url:http://inventory-service:3002}")
    private String inventoryServiceUrl;

    @Value("${app.services.product-catalog-service.base-url:http://product-catalog-service:3001}")
    private String productCatalogServiceUrl;

    @Value("${spring.security.oauth2.client.registration.order-service-client.client-id}")
    private String clientRegistrationId;

    private final ObservationRegistry observationRegistry;

    @Bean
    public OAuth2ClientRequestInterceptor oAuth2ClientRequestInterceptor(
            OAuth2AuthorizedClientManager authorizedClientManager) {
        return new OAuth2ClientRequestInterceptor(authorizedClientManager, clientRegistrationId);
    }

    @Bean
    public InventoryClient inventoryClient(OAuth2ClientRequestInterceptor oAuth2ClientRequestInterceptor) {
        RestClient restClient = RestClient.builder()
                .baseUrl(inventoryServiceUrl)
                .requestInterceptor(oAuth2ClientRequestInterceptor)
                .observationRegistry(observationRegistry)
                .build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(InventoryClient.class);
    }

    @Bean
    public ProductCatalogClient productCatalogClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(productCatalogServiceUrl)
                .observationRegistry(observationRegistry)
                .build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(ProductCatalogClient.class);
    }
}
