package springblog.persistence.dao.userProfile.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springblog.persistence.dao.userProfile.UserProfileDao;
import springblog.persistence.entity.User;
import springblog.persistence.entity.UserProfile;

@Repository
@Transactional
public class UserProfileDaoImpl implements UserProfileDao {
    private static final String TABLE_NAME = "UserProfile";

    private static final String SELECT_STMT = "FROM " + TABLE_NAME;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(UserProfile userProfile) {
        this.sessionFactory.getCurrentSession().save(userProfile);
    }
    
    @Override
    public void update(UserProfile userProfile) {
        this.sessionFactory.getCurrentSession().update(userProfile);
    }

    @SuppressWarnings("unchecked")
    @Override
    public UserProfile findByUserId(Long userId) {
        StringBuilder stmt = new StringBuilder(SELECT_STMT);
        stmt.append(" WHERE user_Id= :userId");
        List<UserProfile> userProfile = this.sessionFactory.getCurrentSession().createQuery(stmt.toString()).setParameter("userId", userId).list();
        return (!userProfile.isEmpty()) ? userProfile.get(0) : null;
    }
}
