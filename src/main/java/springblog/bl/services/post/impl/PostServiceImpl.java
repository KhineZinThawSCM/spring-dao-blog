package springblog.bl.services.post.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springblog.bl.dto.PostDTO;
import springblog.bl.services.post.PostService;
import springblog.persistence.dao.post.PostDao;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.Post;
import springblog.persistence.entity.User;
import springblog.web.form.PostForm;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = this.postDao.getAllPosts();
        List<PostDTO> postDTOList = posts.stream().map(post -> {
            PostDTO postDTO = new PostDTO(post);
            return postDTO;
        }).collect(Collectors.toList());
        return postDTOList;
    }

    @Override
    public void savePost(PostForm postForm) {
        User user = this.userDao.findById(postForm.getUserId());
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setDescription(postForm.getDescription());
        post.setUser(user);

        this.postDao.save(post);
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = this.postDao.findById(id);
        PostDTO postDTO = new PostDTO(post);
        return postDTO;
    }

    @Override
    public void updatePost(PostForm postForm) {
        Post post = this.postDao.findById(postForm.getId());
        User user = this.userDao.findById(postForm.getUserId());
        post.setTitle(postForm.getTitle());
        post.setDescription(postForm.getDescription());
        post.setUser(user);

        this.postDao.update(post);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = this.postDao.findById(id);
        this.postDao.delete(post);
    }
}
