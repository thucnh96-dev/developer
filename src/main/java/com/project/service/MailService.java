package com.project.service;

import com.project.form.Mail;

public interface MailService {
    void sendMail(Mail mail);
    void sendMailTemplate(Mail mail);
}
