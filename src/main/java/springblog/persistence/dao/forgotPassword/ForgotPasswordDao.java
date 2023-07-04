package springblog.persistence.dao.forgotPassword;

import springblog.persistence.entity.ResetPasswordToken;

public interface ForgotPasswordDao {
    void save(ResetPasswordToken reserPasswordToken);
}
