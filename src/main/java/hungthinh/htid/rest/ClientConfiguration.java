package hungthinh.htid.rest;


import feign.Logger;
import feign.RequestInterceptor;
import feign.okhttp.OkHttpClient;
import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;


public class ClientConfiguration {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public ClientConfiguration(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    private static final String AUTHORIZATION_HEADER="Authorization";
    private static final String TOKEN_TYPE = "Bearer";


    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken)
                securityContext.getAuthentication();
        String access_token = "";
        if (oauth2Token != null) {
            OAuth2AuthorizedClient client = authorizedClientService
                    .loadAuthorizedClient(oauth2Token.getAuthorizedClientRegistrationId(),
                            oauth2Token.getName());
            access_token = client.getAccessToken().getTokenValue();
        }
        String auth = "Bearer " + access_token;
        System.out.println("auth " + auth);
        return requestTemplate -> {
            requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
            requestTemplate.header("Authorization", auth);
        };
    }
}
