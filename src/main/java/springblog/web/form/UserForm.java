package springblog.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import springblog.bl.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
	private Long id;

	@NotBlank(message = "The name field is required.")
	private String name;
	
	@NotBlank(message = "The email field is required.")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "The password field is required.")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
	
	public UserForm(UserDTO userDTO) {
		this.id = userDTO.getId();
		this.name = userDTO.getName();
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
	}
}
