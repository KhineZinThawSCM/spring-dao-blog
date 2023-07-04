package springblog.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springblog.common.validation.EmailExist;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordForm {
    @NotBlank
    @EmailExist(message = "Email is not valid.")
    private String email;
}
