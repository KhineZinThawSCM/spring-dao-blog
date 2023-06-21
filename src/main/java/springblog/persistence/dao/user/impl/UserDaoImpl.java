package springblog.persistence.dao.user.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    private static final String TABLE_NAME = "User";

    private static final String SELECT_STMT = "FROM " + TABLE_NAME;

    @Autowired
    private SessionFactory sessionFactory;
 
    @SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
    	 StringBuilder stmt = new StringBuilder(SELECT_STMT);
         return this.sessionFactory.getCurrentSession().createQuery(stmt.toString()).list();
	}
    
    @Override
    public User findById(Long id) {
        return this.sessionFactory.getCurrentSession().get(User.class, id);
    }
    
    @Override
    public void save(User user) {
        this.sessionFactory.getCurrentSession().save(user);
    }
    
    @Override
    public void update(User user) {
        this.sessionFactory.getCurrentSession().merge(user);
    }
    
    @Override
    public void delete(User user) {
        this.sessionFactory.getCurrentSession().delete(user);
    }
}
