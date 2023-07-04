package springblog.bl.services.forgotPassword;

import springblog.web.form.ForgotPasswordForm;

public interface ForgotPasswordService {
    void sendForgotPasswordMail(ForgotPasswordForm forgotPasswordForm);
}
