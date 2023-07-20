package springblog.bl.services.user.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springblog.bl.dto.UserDTO;
import springblog.bl.services.user.UserService;
import springblog.persistence.dao.role.RoleDao;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.dao.userProfile.UserProfileDao;
import springblog.persistence.entity.User;
import springblog.persistence.entity.UserProfile;
import springblog.web.form.UserForm;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserProfileDao userProfileDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HttpSession session;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userDao.getAllUsers();
        List<UserDTO> userDTOList = users.stream().map(user -> {
            UserDTO userDTO = new UserDTO(user);
            return userDTO;
        }).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    public List<UserDTO> getSearchUsers(String keyword) {
        List<User> users = this.userDao.getSearchUsers(keyword);
        List<UserDTO> userDTOList = users.stream().map(user -> {
            UserDTO userDTO = new UserDTO(user);
            return userDTO;
        }).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    public void saveUser(UserForm userForm) {
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.getRoles().add(this.roleDao.findById(userForm.getRoleId()));
        this.userDao.save(user);

        UserProfile userProfile = new UserProfile();
        
        // String catalinaHome = System.getProperty("catalina.home");
        // String filePath = catalinaHome + File.separator + "webapps" + File.separator + "springblog" +
        //         File.separator + "user-photos"  + File.separator + userForm.getPhoto().getOriginalFilename();
        String filePath = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"
                + File.separator + "image" + File.separator + userForm.getPhoto().getOriginalFilename();
        try {
            FileOutputStream fileout = new FileOutputStream(filePath);
            fileout.write(userForm.getPhoto().getBytes());
            fileout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        userProfile.setUser(user);
        userProfile.setPhoto(userForm.getPhoto().getOriginalFilename());
        this.userProfileDao.save(userProfile);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = this.userDao.findById(id);
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }

    @Override
    public void updateUser(UserForm userForm) {
        User user = this.userDao.findById(userForm.getId());
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.getRoles().add(this.roleDao.findById(userForm.getRoleId()));
        this.userDao.update(user);

        UserProfile userProfile = this.userProfileDao.findByUserId(user.getId());
        String filePath = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"
                + File.separator + "image" + File.separator + userForm.getPhoto().getOriginalFilename();
        try {
            FileOutputStream fileout = new FileOutputStream(filePath);
            fileout.write(userForm.getPhoto().getBytes());
            fileout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        userProfile.setPhoto(userForm.getPhoto().getOriginalFilename());
        this.userProfileDao.update(userProfile);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = this.userDao.findById(id);
        this.userDao.delete(user);
    }
}
