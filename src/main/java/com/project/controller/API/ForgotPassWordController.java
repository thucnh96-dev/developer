package com.project.controller.API;

import com.project.controller.AbtractController;
import com.project.entity.User;
import com.project.form.Mail;
import com.project.service.MailService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPassWordController extends AbtractController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping
    public String processForgotPasswordForm(@RequestParam String email, HttpServletRequest request) {


        String token = UUID.randomUUID().toString();

        User user = userService.findByPhone(email);
        if (user == null){
            return "forgot-password";
        }

        user.setExpiryTokenDate(5);
        // TODO save user ;

        Mail mail = new Mail();
        mail.setMailFrom("no-reply@memorynotfound.com");
        mail.setMailTo(user.getPhone());
        mail.setMailSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://memorynotfound.com");

        // url server
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        // url reset link
        model.put("resetUrl", url + "/reset-password?token=" + token);
        mail.setModel(model);
        mailService.sendMailTemplate(mail);
        return "redirect:/forgot-password?success";

    }
}
