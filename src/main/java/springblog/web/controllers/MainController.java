package springblog.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springblog.bl.services.user.UserService;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.User;

@Controller
public class MainController {
    @Autowired
    UserDao userDao;
    
    @RequestMapping( value = "/login", method = {RequestMethod.POST, RequestMethod.GET} )
    protected ModelAndView login() {
        return new ModelAndView("login");
    }
    
    @GetMapping("/home")
    public String home(Authentication authentication, HttpSession session) {
        User user = this.userDao.findByEmail(authentication.getName());
        session.setAttribute("user", user);
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {
            System.out.println("admin");
            return "redirect:/users";
        } else {
            System.out.println("user");
            return "redirect:/posts";
        }
    }
}
