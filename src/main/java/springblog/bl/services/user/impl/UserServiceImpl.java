package springblog.bl.services.user.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springblog.bl.dto.UserDTO;
import springblog.bl.services.user.UserService;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.User;
import springblog.web.form.UserForm;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

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
	public void saveUser(UserForm userForm) {
		User user = new User();
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
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
		user.setPassword(userForm.getPassword());
		this.userDao.update(user);
	}

	@Override
	public void deleteUserById(Long id) {
		User user = this.userDao.findById(id);
		this.userDao.delete(user);
	}
}
