package springblog.bl.services.post;

import java.util.List;

import springblog.bl.dto.PostDTO;
import springblog.web.form.PostForm;


public interface PostService {
	List<PostDTO> getAllPosts();
	
	List<PostDTO> getSearchPosts(String keyword);
	
	void savePost(PostForm postForm);

	PostDTO getPostById(Long id);
	
	void updatePost(PostForm postForm);
	
	void deletePostById(Long id);
}
