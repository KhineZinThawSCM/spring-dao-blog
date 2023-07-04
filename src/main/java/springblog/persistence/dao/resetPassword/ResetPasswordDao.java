package springblog.persistence.dao.resetPassword;

import springblog.persistence.entity.ResetPasswordToken;

public interface ResetPasswordDao {
    ResetPasswordToken findByToken(String token);
    
    void save(ResetPasswordToken resetPasswordToken);
    
    void update(ResetPasswordToken resetPasswordToken);
    
    void delete(ResetPasswordToken resetPasswordToken);
}
