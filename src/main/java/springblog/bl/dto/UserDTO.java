package springblog.bl.dto;

import springblog.persistence.entity.User;
import springblog.persistence.entity.UserProfile;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
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
    private static final long serialVersionUID = -2881453991706760715L;
    private Long id;
    private String name;
    private String email;
    private String password;
    private Timestamp created_at;
    private List<PostDTO> posts;
    private List<SimpleGrantedAuthority> roles;
    private UserProfile userProfile;

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
        this.userProfile = user.getUserProfile();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
