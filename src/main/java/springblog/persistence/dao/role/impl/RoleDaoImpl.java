package springblog.persistence.dao.role.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springblog.persistence.dao.role.RoleDao;
import springblog.persistence.entity.Role;
import springblog.persistence.entity.User;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
    private static final String TABLE_NAME = "Role";

    private static final String SELECT_STMT = "FROM " + TABLE_NAME;

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getAllRoles() {
        StringBuilder stmt = new StringBuilder(SELECT_STMT);
        return this.sessionFactory.getCurrentSession().createQuery(stmt.toString()).list();
    }

    @Override
    public Role findByName(String name) {
        StringBuilder stmt = new StringBuilder(SELECT_STMT);
        stmt.append(" WHERE name = :name");
        return this.sessionFactory.getCurrentSession().createQuery(stmt.toString(), Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Role findById(Long id) {
        StringBuilder stmt = new StringBuilder(SELECT_STMT);
        stmt.append(" WHERE id = :id");
        return this.sessionFactory.getCurrentSession().createQuery(stmt.toString(), Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
