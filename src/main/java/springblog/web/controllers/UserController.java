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

import springblog.bl.dto.RoleDTO;
import springblog.bl.dto.UserDTO;
import springblog.bl.services.role.RoleService;
import springblog.bl.services.user.UserService;
import springblog.export.UserExcelExporter;
import springblog.web.form.UserForm;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/users")
    public ModelAndView index() {
        List<UserDTO> users = this.userService.getAllUsers();
        ModelAndView mv = new ModelAndView("userListView");
        String catalinaHome = System.getProperty("catalina.home");
        String filePath = catalinaHome + "/" + "webapps" + "/" + "springblog" + "/"
                + "user-photos" + "/";
        mv.addObject("users", users);
        mv.addObject("filePath", filePath.replace("\\", "/"));
        return mv;
    }

    @RequestMapping("/users/search")
    public ModelAndView search(@RequestParam("keyword") String keyword) {
        List<UserDTO> users = this.userService.getSearchUsers(keyword);
        ModelAndView mv = new ModelAndView("userListView");
        mv.addObject("users", users);
        return mv;
    }

    @RequestMapping("/users/create")
    public ModelAndView create() {
        List<RoleDTO> roles = this.roleService.getAllRoles();
        ModelAndView mv = new ModelAndView("userCreateView");
        mv.addObject("userForm", new UserForm());
        mv.addObject("roles", roles);
        return mv;
    }

    @RequestMapping(value = "/users/store", method = RequestMethod.POST)
    public ModelAndView store(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            List<RoleDTO> roles = this.roleService.getAllRoles();
            mv.setViewName("userCreateView");
            mv.addObject("roles", roles);
            return mv;
        }

        this.userService.saveUser(userForm);
        mv.setViewName("redirect:/users");
        return mv;
    }

    @RequestMapping("/users/edit")
    public ModelAndView edit(@RequestParam("id") Long id) {
        UserDTO userDTO = this.userService.getUserById(id);
        List<RoleDTO> roles = this.roleService.getAllRoles();
        ModelAndView mv = new ModelAndView("userEditView");
        mv.addObject("userForm", new UserForm(userDTO));
        mv.addObject("roles", roles);
        return mv;
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError());
            List<RoleDTO> roles = this.roleService.getAllRoles();
            mv.setViewName("userEditView");
            mv.addObject("roles", roles);
            return mv;
        }
        System.out.println("pass");
        this.userService.updateUser(userForm);
        mv.setViewName("redirect:/users");
        return mv;
    }

    @RequestMapping("/users/destroy")
    public String destroy(@RequestParam("id") Long id) {
        this.userService.deleteUserById(id);

        return "redirect:/users";
    }

    @RequestMapping("/users/excel/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<UserDTO> users = this.userService.getAllUsers();

        UserExcelExporter excelExporter = new UserExcelExporter(users);

        excelExporter.export(response);
    }
}
