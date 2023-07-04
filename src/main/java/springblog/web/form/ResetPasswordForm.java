package springblog.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordForm {
    private Long id;
    @NotBlank
    private String token;

    @NotBlank
    private String email;
    
    @NotBlank
    private String password;
    
    @NotBlank
    private String confirmPassword;
}
