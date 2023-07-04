package springblog.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springblog.bl.services.forgotPassword.ForgotPasswordService;
import springblog.web.form.ForgotPasswordForm;

@Controller
public class ForgotPasswordController {
    @Autowired
    ForgotPasswordService forgotPasswordService;
    
    @RequestMapping("/forgot_password")
    public ModelAndView showForgotPasswordForm() {
        ModelAndView mv = new ModelAndView("forgotPassword");
        mv.addObject("forgotPasswordForm", new ForgotPasswordForm());
        System.out.println("hello");
        return mv;
    }
    
    @RequestMapping(value = "/forgot_password/store", method = RequestMethod.POST)
    public ModelAndView processForgotPassword(@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
            BindingResult bindingResult) {
        System.out.println("reach");
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("forgotPassword");
            return mv;
        }
        this.forgotPasswordService.sendForgotPasswordMail(forgotPasswordForm);
        mv.setViewName("redirect:/forgot_password");
        return mv;
    }
}
