package springblog.bl.dto;

import springblog.persistence.entity.User;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDTO implements UserDetails {
	/**
     * <h2> serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String email;
    private String password;
    private Timestamp created_at;
    private List<PostDTO> posts; 
    private List<SimpleGrantedAuthority> roles;

    public UserDTO() {
    	super();
    }
    
    public UserDTO(User user) {
    	this.id = user.getId();
    	this.name = user.getName();
    	this.email = user.getEmail();
    	this.password = user.getPassword();
    	this.created_at = user.getCreated_at();
    	this.posts = user.getPosts().stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
    	this.roles = user.getRoles().stream().map(role -> {
            return new SimpleGrantedAuthority(role.getName());
        }).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
         return this.roles;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
