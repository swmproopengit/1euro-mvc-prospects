package com.euro.prospects.web;

import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.facade.SimpleHttpFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

    @Autowired
    private AdapterDeploymentContext adapterDeploymentContext;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String changePassword(RedirectAttributes attributes, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpFacade facade = new SimpleHttpFacade(request, response);
        KeycloakDeployment deployment = adapterDeploymentContext.resolveDeployment(facade);
        /*attributes.addAttribute("referrer", deployment.getResourceName());
        attributes.addAttribute("referrer_uri", request.getHeader("referer"));
        System.out.println("*******************");
        System.out.println("referrer: "+deployment.getResourceName());
        System.out.println("*******************");
        System.out.println("*******************");
        System.out.println("referrer_uri: "+request.getHeader("referer"));
        System.out.println("*******************");
        System.out.println("*******************");
        System.out.println("URL: "+deployment.getAccountUrl());
        System.out.println("*******************");*/
        //http://localhost:8080/auth/realms/1-EURO-SERVICE/account?referrer=PROSPECTS-APP&referrer_uri=http://localhost:8097/vendors
        //http://localhost:8080/auth/realms/1-EURO-SERVICE/account/?referrer=PROSPECTS-APP
        return "redirect:" + deployment.getAccountUrl()+"?referrer="+deployment.getResourceName()+"&referrer_uri="+request.getHeader("referer");
    }
}
