package springblog.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springblog.bl.services.resetPassword.ResetPasswordService;
import springblog.persistence.entity.ResetPasswordToken;
import springblog.web.form.ResetPasswordForm;

@Controller
public class ResetPasswordController {
    @Autowired
    ResetPasswordService resetPasswordService;

    @RequestMapping("/reset_password")
    public ModelAndView showResetPasswordForm(@RequestParam("token") String token) {
        ResetPasswordToken resetPasswordToken = resetPasswordService.findByToken(token);
        ModelAndView mv = new ModelAndView("resetPassword");
        mv.addObject("resetPasswordToken", resetPasswordToken);
        mv.addObject("resetPasswordForm", new ResetPasswordForm());
        return mv;
    }

    @RequestMapping(value = "/reset_password/update", method = RequestMethod.POST)
    public ModelAndView makeResetPassword(
            @ModelAttribute("resetPasswordForm") @Valid ResetPasswordForm resetPasswordForm, BindingResult bingResult) {
        ModelAndView mv = new ModelAndView();
        if (!isPasswordValid(resetPasswordForm.getPassword(), resetPasswordForm.getConfirmPassword())) {
            bingResult.rejectValue("confirmPassword", "error.resetPasswordForm", "Passwords do not match");
        }
        if (bingResult.hasErrors()) {
            ResetPasswordToken resetPasswordToken = resetPasswordService.findByToken(resetPasswordForm.getToken());
            mv.addObject("resetPasswordToken", resetPasswordToken);
            mv.setViewName("resetPassword");
            return mv;
        }

        this.resetPasswordService.update(resetPasswordForm);
        mv.setViewName("redirect:/home");
        return mv;
    }

    private boolean isPasswordValid(String password, String confirmPassword) {
        return password != null && password.equals(confirmPassword);
    }
}
