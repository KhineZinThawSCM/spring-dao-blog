package springblog.bl.dto;



import java.sql.Timestamp;

import springblog.persistence.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	private int id;
	private String title;
	private String description;
	private Timestamp created_at;
	
	 public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.created_at = post.getCreated_at();
    }
}
