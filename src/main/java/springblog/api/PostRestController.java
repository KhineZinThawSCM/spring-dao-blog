package springblog.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springblog.bl.dto.PostDTO;
import springblog.bl.services.post.PostService;

@RestController
public class PostRestController {
    @Autowired
    PostService postService;
    
    @GetMapping(value = "/api/posts")
    public List<PostDTO> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return posts;
    }
}
