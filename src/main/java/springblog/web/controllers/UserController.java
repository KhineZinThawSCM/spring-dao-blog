package springblog.web.controllers;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springblog.bl.dto.UserDTO;
import springblog.bl.services.user.UserService;
import springblog.web.form.UserForm;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public ModelAndView index() {
		List<UserDTO> users = this.userService.getAllUsers();
		ModelAndView mv = new ModelAndView("userListView");
		mv.addObject("users", users);
		return mv;
	}

	@RequestMapping("/users/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("userCreateView");
		mv.addObject("userForm", new UserForm());
		return mv;
	}

	@RequestMapping(value = "/users/store", method = RequestMethod.POST)
	public ModelAndView store(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mv.setViewName("userCreateView");
			return mv;
		}

		this.userService.saveUser(userForm);
		mv.setViewName("redirect:/users");
		return mv;
	}

	@RequestMapping("/users/edit")
	public ModelAndView edit(@RequestParam("id") Long id) {
		UserDTO userDTO = this.userService.getUserById(id);
		ModelAndView mv = new ModelAndView("userEditView");
		mv.addObject("userForm", new UserForm(userDTO));
		return mv;
	}

	@RequestMapping(value = "/users/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mv.setViewName("userEditView");
			return mv;
		}

		this.userService.updateUser(userForm);
		mv.setViewName("redirect:/users");
		return mv;
	}

	@RequestMapping("/users/destroy")
	public String destroy(@RequestParam("id") Long id) {
		this.userService.deleteUserById(id);

		return "redirect:/users";
	}
}
