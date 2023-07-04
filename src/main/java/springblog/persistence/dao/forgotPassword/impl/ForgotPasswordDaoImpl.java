package springblog.persistence.dao.forgotPassword.impl;

import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springblog.persistence.dao.forgotPassword.ForgotPasswordDao;
import springblog.persistence.entity.ResetPasswordToken;

@Repository
@Transactional
public class ForgotPasswordDaoImpl implements ForgotPasswordDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ResetPasswordToken reserPasswordToken) {
        this.sessionFactory.getCurrentSession().save(reserPasswordToken);
        
    }
}
