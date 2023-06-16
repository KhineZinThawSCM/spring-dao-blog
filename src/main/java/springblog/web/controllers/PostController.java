package springblog.web.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springblog.bl.dto.PostDTO;
import springblog.bl.services.post.PostService;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
	@RequestMapping("/posts/list")
	public ModelAndView getAllPosts() {
		System.out.println("Hello");
		List<PostDTO> postDtoList = this.postService.getAllPosts();
		ModelAndView mv = new ModelAndView("posts/list");
		mv.addObject("posts", postDtoList);
		return mv;
	}
}
