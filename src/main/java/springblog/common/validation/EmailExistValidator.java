package springblog.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.User;

public class EmailExistValidator implements ConstraintValidator<EmailExist, String> {

    @Autowired
    private UserDao userDao;

    @Override
    public void initialize(EmailExist constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        User user = userDao.findByEmail(email);
        return user != null;
    }
}
