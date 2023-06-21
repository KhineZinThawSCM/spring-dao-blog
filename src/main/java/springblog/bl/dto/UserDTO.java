package springblog.bl.dto;

import springblog.persistence.entity.User;

import java.sql.Timestamp;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO{
	private Long id;
    private String name;
    private String email;
    private String password;
    private Timestamp created_at;
    private List<PostDTO> posts; 

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
    }
}
