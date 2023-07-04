package springblog.bl.services.resetPassword;

import springblog.persistence.entity.ResetPasswordToken;
import springblog.web.form.ResetPasswordForm;

public interface ResetPasswordService {
    ResetPasswordToken findByToken(String token);
    
    void update(ResetPasswordForm resetPasswordForm);
}
