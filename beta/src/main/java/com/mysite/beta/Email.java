package com.mysite.beta;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import java.io.PrintStream;

@Controller
@ComponentScan()
public class Email {

    EmailConfig emailConfig = new EmailConfig();

    private String username = emailConfig.getUsername();
    private String password = emailConfig.getPassword();
    private String host = emailConfig.getHost();
    private Integer port = emailConfig.getPort();


    public String fromemail = "";

    public String getFromemail() {
        return fromemail;
    }

    public void setFromemail(String fromemail) {
        this.fromemail = fromemail;
    }


    public String content = "You did that. Remember to remove email credentials and post to git";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




    public void sendEmail() {
        System.out.println("HOST: "+host+" PORT: "+port);

            //create sender

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        //create mail instance
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromemail);
        message.setTo(username);
        message.setSubject("Incoming Message");
        message.setText(content);

//actually send it
        mailSender.send(message);

    }



}
