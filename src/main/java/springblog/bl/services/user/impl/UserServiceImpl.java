package springblog.bl.services.user.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springblog.bl.dto.UserDTO;
import springblog.bl.services.user.UserService;
import springblog.persistence.dao.role.RoleDao;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.User;
import springblog.web.form.UserForm;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired 
	PasswordEncoder passwordEncoder;

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
	}

	@Override
	public void deleteUserById(Long id) {
		User user = this.userDao.findById(id);
		this.userDao.delete(user);
	}
}
