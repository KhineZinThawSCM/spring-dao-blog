package springblog.persistence.dao.post.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springblog.persistence.dao.post.PostDao;
import springblog.persistence.entity.Post;

@Repository
@Transactional
public class PostDaoImpl implements PostDao {
    private static final String TABLE_NAME = "Post";

    private static final String SELECT_STMT = "FROM " + TABLE_NAME;

    @Autowired
    private SessionFactory sessionFactory;
 
    @SuppressWarnings("unchecked")
	@Override
	public List<Post> getAllPosts() {
    	 StringBuilder stmt = new StringBuilder(SELECT_STMT);
         return this.sessionFactory.getCurrentSession().createQuery(stmt.toString()).list();
	}
    
    @Override
    public void save(Post post) {
        this.sessionFactory.getCurrentSession().save(post);
    }
    
    @Override
    public Post findById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Post.class, id);
    }
    
    @Override
    public void update(Post post) {
        Post oldPost = this.findById(post.getId());
        if (post.getUser() == null) {
            post.setUser(oldPost.getUser());
        }
        this.sessionFactory.getCurrentSession().merge(post);
    }
    
    @Override
    public void delete(Post post) {
        this.sessionFactory.getCurrentSession().delete(post);
    }
}
