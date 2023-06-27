package springblog.bl.services.auth;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springblog.bl.dto.UserDTO;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.User;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserDao userDao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.findByEmail(username);
        UserDTO userDTO = new UserDTO(user);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }

        return  org.springframework.security.core.userdetails.User.builder()
                .username(userDTO.getEmail())
                .password(userDTO.getPassword())
                .authorities(userDTO.getRoles())
                .build();
    }
}