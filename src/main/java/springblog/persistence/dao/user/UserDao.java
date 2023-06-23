package springblog.persistence.dao.user;

import java.util.List;

import springblog.persistence.entity.User;

public interface UserDao {
	List<User> getAllUsers();
	
	List<User> getSearchUsers(String keyword);
	
	void save(User user);
	
	User findById(Long id);
	
	void update(User user);
	
	void delete(User user);
}
