package springblog.persistence.dao.resetPassword.impl;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springblog.persistence.dao.resetPassword.ResetPasswordDao;
import springblog.persistence.entity.ResetPasswordToken;

@Repository
@Transactional
public class ResetPasswordDaoImpl implements ResetPasswordDao {
    private static final String TABLE_NAME = "ResetPasswordToken";

    private static final String SELECT_STMT = "FROM " + TABLE_NAME;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public ResetPasswordToken findByToken(String token) {
        StringBuilder stmt = new StringBuilder(SELECT_STMT);
        stmt.append(" WHERE token= :token");
        return this.sessionFactory.getCurrentSession().createQuery(stmt.toString(), ResetPasswordToken.class)
                .setParameter("token", token).getSingleResult();
    }

    @Override
    public void save(ResetPasswordToken reserPasswordToken) {
        this.sessionFactory.getCurrentSession().save(reserPasswordToken);
        
    }
    
    @Override
    public void update(ResetPasswordToken resetPasswordToken) {
        this.sessionFactory.getCurrentSession().merge(resetPasswordToken);
    }

    @Override
    public void delete(ResetPasswordToken resetPasswordToken) {
        this.sessionFactory.getCurrentSession().delete(resetPasswordToken);
    }
}
