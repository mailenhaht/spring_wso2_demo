package hungthinh.htid.controller;

//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;

import hungthinh.htid.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final UserService userService;

    public MyController(OAuth2AuthorizedClientService authorizedClientService, UserService userService) {
        this.authorizedClientService = authorizedClientService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String getUser(Authentication authentication, Model model) {
        if(authentication != null) {
            DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
            String username = userDetails.getName();
            //System.out.println("userName" + userDetails.getName());
            model.addAttribute("userName", userDetails.getName());

            model.addAttribute("idtoken", userDetails.getClaims());
            //System.out.println("idtoken" + userDetails);

            model.addAttribute("userResponse", userDetails);

        }
        return "index";
    }

    @GetMapping("/user")
    public String user(Model model) {
        System.out.println("AAAA = " + userService.getUser());
        return "user/index";
    }

    @GetMapping("/user/access_token")
    public String user_access_token(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken)
                securityContext.getAuthentication();
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(oauth2Token.getAuthorizedClientRegistrationId(),
                        oauth2Token.getName());

        if (client == null || client.getAccessToken() == null) {
            System.out.println("null");
            SecurityContextHolder.clearContext();
            return "redirect:/login";
        }

        String access_token = client.getAccessToken().getTokenValue();
        //System.out.println("Client = " + client.getRefreshToken().getTokenValue());
        model.addAttribute("access_token", access_token);
        model.addAttribute("refresh_token", client.getRefreshToken().getTokenValue());
        return "user/access_token";
    }


//    @GetMapping("/logouts")
//    public void logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        SecurityContextHolder.getContext().setAuthentication(null);
//    }
}
