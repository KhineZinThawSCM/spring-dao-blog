package springblog.persistence.dao.user;

import java.util.List;

import springblog.persistence.entity.User;

public interface UserDao {
    List<User> getAllUsers();

    List<User> getSearchUsers(String keyword);

    void save(User user);

    void update(User user);

    void delete(User user);

    User findByEmail(String email);

    User findById(Long id);

    User findByName(String name);
}
