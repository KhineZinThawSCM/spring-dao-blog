package springblog.bl.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springblog.persistence.entity.Post;
import springblog.persistence.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	private Long id;
	private String title;
	private String description;
	private Timestamp created_at;
	private User user;
	
	public PostDTO(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.description = post.getDescription();
		this.user = post.getUser();
		this.created_at = post.getCreated_at();
	}
}
