package com.mysite.beta;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;

@Controller
@ComponentScan()
public class Email {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;


   public String fromemail = "jason.triche@gmail.com";

    public String getFromemail() {
        return fromemail;
    }

    public void setFromemail(String fromemail) {
        this.fromemail = fromemail;
    }

    public void setId(String fromemail) {
        this.fromemail = fromemail;
    }



    public String content = "You did that. Remember to remove email credentials and post to git";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toemail = "trichejason@gmail.com";


    public void sendEmail() {

            //create sender

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        //create mail instance
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromemail);
        message.setTo(toemail);
        message.setSubject("Hello World!");
        message.setText(content);

//actually send it
        mailSender.send(message);

    }



}
