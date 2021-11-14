package com.euro.prospects.web;

import com.euro.prospects.dao.ProspectRepository;
import com.euro.prospects.model.Vendor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProspectController {

    @Autowired
    private ProspectRepository prospectRepository;
    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;


    @GetMapping(path = "/")
    public String index(){
        return "index";
    }

    @GetMapping(path = "/prospects")
    public String prospects(Model model){
        model.addAttribute("prospects", prospectRepository.findAll());
        return "prospects";
    }
    @GetMapping(path = "/prospect")
    public String getProspect(Model model){
        //model.addAttribute("prospect", prospectRepository.findById());
        return "prospect";
    }
    @GetMapping(path = "/vendors")
    public String vendors(Model model){
       PagedModel<Vendor> pageVendors = keycloakRestTemplate.getForObject("http://localhost:8095/vendors", PagedModel.class);
       model.addAttribute("vendors", pageVendors);
       return "vendors";
    }

    @GetMapping("/jwt")
    @ResponseBody
    public Map<String, String> map(HttpServletRequest request){
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext = principal.getKeycloakSecurityContext();
        Map<String, String> map=new HashMap<>();
        map.put("access_token", keycloakSecurityContext.getTokenString());
        return map;
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        model.addAttribute("errorMessage", HttpStatus.FORBIDDEN);
        return "errors";
    }

}
