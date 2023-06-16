package springblog.persistence.dao.post;

import java.util.List;

import springblog.persistence.entity.Post;

public interface PostDao {
	List<Post> getAllPosts();
}
