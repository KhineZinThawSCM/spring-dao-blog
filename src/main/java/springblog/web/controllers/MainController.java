package springblog.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping( value = "/login", method = {RequestMethod.POST, RequestMethod.GET} )
    protected ModelAndView login() {
        return new ModelAndView("login");
    }
    
    @GetMapping("/home")
    public String home(Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {
            System.out.println("admin");
            return "redirect:/users";
        } else {
            System.out.println("user");
            return "redirect:/posts";
        }
    }
}
