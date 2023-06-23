package springblog.bl.services.user;

import java.util.List;

import springblog.bl.dto.UserDTO;
import springblog.web.form.UserForm;

public interface UserService {
	List<UserDTO> getAllUsers();
	
	List<UserDTO> getSearchUsers(String keyword);

	void saveUser(UserForm userForm);

	UserDTO getUserById(Long id);

	void updateUser(UserForm userForm);

	void deleteUserById(Long id);
}
