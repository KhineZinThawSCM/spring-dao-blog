package springblog.web.form;


import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {
	
	@NotBlank(message = "Title field is required")
	private String title;
	
	@NotBlank(message = "Description field is required")
	private String description;
}
