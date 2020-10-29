package hungthinh.htid.rest;


import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@Configuration
public class ClientConfiguration {

//    private final OAuth2AuthorizedClientService authorizedClientService;
//
//    public ClientConfiguration(OAuth2AuthorizedClientService authorizedClientService) {
//        this.authorizedClientService = authorizedClientService;
//    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken)
//                securityContext.getAuthentication();
//        OAuth2AuthorizedClient client = authorizedClientService
//                .loadAuthorizedClient(oauth2Token.getAuthorizedClientRegistrationId(),
//                        oauth2Token.getName());

        //String access_token = client.getAccessToken().getTokenValue();

        return requestTemplate -> {
            String token = "access_token";
            String auth = "Bearer " + token;
            requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
            requestTemplate.header("Authorization", auth);
        };
    }
}
