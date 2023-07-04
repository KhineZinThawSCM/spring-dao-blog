package springblog.bl.services.resetPassword.impl;

import java.time.LocalDateTime;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import springblog.bl.services.resetPassword.ResetPasswordService;
import springblog.persistence.dao.resetPassword.ResetPasswordDao;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.ResetPasswordToken;
import springblog.persistence.entity.User;
import springblog.web.form.ResetPasswordForm;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
    @Autowired
    ResetPasswordDao resetPasswordDao;

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResetPasswordToken findByToken(String token) {
        try {
            ResetPasswordToken resetPasswordToken = this.resetPasswordDao.findByToken(token);
            // Check if the token has expired
            if (resetPasswordToken.getExpiryDate().isBefore(LocalDateTime.now())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reset password token has expired");
            }
            
            return resetPasswordToken;
        } catch (NoResultException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reset password token not found", e);
        }
    }

    @Override
    public void update(ResetPasswordForm resetPasswordForm) {
        ResetPasswordToken resetPasswordToken = this.resetPasswordDao.findByToken(resetPasswordForm.getToken());
        if (resetPasswordToken == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found");
        }

        User user = this.userDao.findByEmail(resetPasswordForm.getEmail());
        user.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword()));
        this.userDao.update(user);
        this.resetPasswordDao.delete(resetPasswordToken);
    }

}
