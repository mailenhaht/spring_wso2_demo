package hungthinh.htid.controller;

//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyController {
//    @GetMapping("/")
//    public String root() {return "index";}
//
//    @GetMapping("/user")
//    public String user() {return "user/index";}
//
//
//    @GetMapping("/user/register")
//    public String userRegister() { return "user/register"; }



//    @GetMapping("/")
//    public String getUserName(Model model, OAuth2AuthenticationToken token) throws JsonProcessingException {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        model.addAttribute("userName", currentPrincipalName);
//
//        //model.addAttribute("userName", token.getPrincipal().getName());
//
//        System.out.println("User has authorities: " + token.toString());
//        System.out.println("getDetails: " + authentication.getDetails());
//        System.out.println("getCredentials: " + authentication.getCredentials());
//        System.out.println("getPrincipal: " + authentication.getPrincipal());
//        System.out.println("getAuthorities: " + authentication.getAuthorities());
//        System.out.println("getClass: " + authentication.getClass());
//
//        return "index";
//    }


    @GetMapping("/")
    public String getUser(Authentication authentication, Model model) {
    if(authentication != null) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
        String username = userDetails.getName();
        System.out.println("userName" + userDetails.getName());
        model.addAttribute("userName", userDetails.getName());

        model.addAttribute("idtoken", userDetails.getClaims());
        System.out.println("idtoken" + userDetails);
    }
    return "index";

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
