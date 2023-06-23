package springblog.web.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springblog.bl.dto.PostDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {
	private Long id;

	@NotBlank(message = "Title field is required")
	private String title;
	
	@NotBlank(message = "Description field is required")
	private String description;
	
	@Min(value = 1, message = "User name field is required")
	private Long userId;
	
	public PostForm(PostDTO postDTO) {
		this.id = postDTO.getId();
		this.title = postDTO.getTitle();
		this.description = postDTO.getDescription();
		this.userId = postDTO.getUser().getId();
	}
}

