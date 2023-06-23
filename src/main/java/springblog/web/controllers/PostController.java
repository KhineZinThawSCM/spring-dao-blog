package springblog.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springblog.bl.dto.PostDTO;
import springblog.bl.dto.UserDTO;
import springblog.bl.services.post.PostService;
import springblog.bl.services.user.UserService;
import springblog.export.PostExcelExporter;
import springblog.export.UserExcelExporter;
import springblog.web.form.PostForm;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;

	@RequestMapping("/posts")
	public ModelAndView index() {
		List<PostDTO> posts = this.postService.getAllPosts();
		ModelAndView mv = new ModelAndView("postListView");
		mv.addObject("posts", posts);
		return mv;
	}
	
	@RequestMapping("/posts/search")
    public ModelAndView search(@RequestParam("keyword") String keyword) {
        List<PostDTO> posts = this.postService.getSearchPosts(keyword);
        ModelAndView mv = new ModelAndView("postListView");
        mv.addObject("posts", posts);
        return mv;
    }
	
	@RequestMapping("/posts/create")
	public ModelAndView create() {
		List<UserDTO> users = this.userService.getAllUsers();
		ModelAndView mv = new ModelAndView("postCreateView");
		mv.addObject("users", users);
		mv.addObject("postForm", new PostForm());
		return mv;
	}

	@RequestMapping(value = "/posts/store", method = RequestMethod.POST)
	public ModelAndView store(@ModelAttribute("postForm") @Valid PostForm postForm,
			BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			List<UserDTO> users = this.userService.getAllUsers();
			mv.addObject("users", users);
			mv.setViewName("postCreateView");
			return mv;
		}
		
		this.postService.savePost(postForm);
		mv.setViewName("redirect:/posts");
		return mv;
	}

	@RequestMapping("/posts/edit")
	public ModelAndView edit(@RequestParam("id") Long id) {
		PostDTO postDTO = this.postService.getPostById(id);
		List<UserDTO> users = this.userService.getAllUsers();
		ModelAndView mv = new ModelAndView("postEditView");
		mv.addObject("users", users);
		mv.addObject("postForm", new PostForm(postDTO));
		return mv;
	}
	
	@RequestMapping(value = "/posts/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("postForm") @Valid PostForm postForm,
			BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			List<UserDTO> users = this.userService.getAllUsers();
			mv.addObject("users", users);
			mv.setViewName("postEditView");
			return mv;
		}
		
		this.postService.updatePost(postForm);
		mv.setViewName("redirect:/posts");
		return mv;
	}
	
	@RequestMapping("/posts/destroy")
	public String destroy(@RequestParam("id") Long id) {
		this.postService.deletePostById(id);
		
		return "redirect:/posts";
	}
	
	@RequestMapping("/posts/excel/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=posts_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<PostDTO> posts = this.postService.getAllPosts();
         
        PostExcelExporter excelExporter = new PostExcelExporter(posts);
         
        excelExporter.export(response);    
    }  
}
