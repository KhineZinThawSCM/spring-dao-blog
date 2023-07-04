package springblog.bl.services.forgotPassword.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springblog.bl.services.forgotPassword.ForgotPasswordService;
import springblog.common.util.EmailUtil;
import springblog.persistence.dao.resetPassword.ResetPasswordDao;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.ResetPasswordToken;
import springblog.persistence.entity.User;
import springblog.web.form.ForgotPasswordForm;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
    @Autowired
    UserDao userDao;
    
    @Autowired 
    ResetPasswordDao resetPasswordDao;
    
    @Autowired
    EmailUtil emailUtil;
    
    private final int EXPIRATION = 60 * 24;

    @Override
    public void sendForgotPasswordMail(ForgotPasswordForm forgotPasswordForm) {
        User user = userDao.findByEmail(forgotPasswordForm.getEmail());
        String token = UUID.randomUUID().toString();
        String resetPasswordUrl = "http://localhost:8080/springblog/reset_password?token=" + token;
        String subject = "Password Reset";
        String body = "Dear User,\n\nPlease click on the link below to reset your password:\n\n"
                + resetPasswordUrl + "\n\nKind regards,\nThe Support Team";

        try {
            this.emailUtil.sendEmail(forgotPasswordForm.getEmail(), subject, body);
            ResetPasswordToken resetPasswordToken = user.getResetPasswordToken() != null ? user.getResetPasswordToken() : new ResetPasswordToken();
            resetPasswordToken.setToken(token);
            resetPasswordToken.setUser(user);
            resetPasswordToken.setExpiryDate(calculateExpiryDate());
            if (user.getResetPasswordToken() != null) {
                this.resetPasswordDao.update(resetPasswordToken);
            } else {
                this.resetPasswordDao.save(resetPasswordToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LocalDateTime calculateExpiryDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.plus(EXPIRATION, ChronoUnit.MINUTES);
    }
}
