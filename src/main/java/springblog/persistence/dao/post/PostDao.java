package springblog.persistence.dao.post;

import java.util.List;

import springblog.persistence.entity.Post;

public interface PostDao {
	List<Post> getAllPosts();
	
	void save(Post post);
	
	Post findById(Long id);
	
	void update(Post post);
	
	void delete(Post post);
}
