package com.mysite.beta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
@ComponentScan()
public class Email {

        private String email;
        private String content;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    @Autowired
        private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }



    public void sendEmail() {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                String address = "jason.triche@gmail.com";
                String name = "tester one";
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(address));
                mimeMessage.setFrom(new InternetAddress(address));
                mimeMessage.setText(
                        "Dear " + name
                                + ", thank you for placing order.");
            }
        };
        try {
            this.mailSender.send(preparator);
        } catch (
                MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }



}
