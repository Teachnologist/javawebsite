package com.mysite.beta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import javax.validation.*;
import org.json.JSONObject;

@Controller
@ComponentScan()
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;
    private JSONObject outputJsonObj;

    @GetMapping("/")
    public String indexPage(Model model) {
      /*  model.addAttribute("appName", appName);
        model.addAttribute("email", new Email());*/
        model.addAttribute("email", new Email());
        return "index";
    }

    @GetMapping("/error")
    public String errorPage(Model model) {
        return "error";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("email", new Email());
        return "home";
    }

    @GetMapping("/emailsuccess")
    public String emailSuccess(Model model) {
        return "emailsuccess";
    }

    @PostMapping("/email")
    public String emailSubmit(@Valid @ModelAttribute Email email, BindingResult result) {
        System.out.println("POSTURL /email");
        System.out.println("HAS ERRORS: "+result.hasErrors());
        System.out.print(result);

        REGEXValidation valid = new REGEXValidation();

        if(!result.hasErrors() && valid.validateEmail(email.fromemail) && valid.validateString(email.content)){
            Boolean sent = email.sendEmail();
            if(!sent) {
                return "error";
            }else{
                return "emailsuccess";
            }
        }else{
            return "error";
        }


    }

    @RequestMapping(value = "/ajax/email", method = RequestMethod.POST)
    @ResponseBody
    @Valid
    public String emailAjaxSubmit(@Valid Email email, BindingResult result) {
        JSONObject outputJsonObj = new JSONObject();
        outputJsonObj.put("fromemail",email.fromemail);
        outputJsonObj.put("content",email.content);
        outputJsonObj.put("success",false);
        outputJsonObj.put("message","Check for correct email and message format");

        REGEXValidation valid = new REGEXValidation();

        if(!valid.validateEmail(email.fromemail)){
            outputJsonObj.put("message", "Invalid Email Address");
            return outputJsonObj.toString();
        }

        if(!valid.validateString(email.content)){
            outputJsonObj.put("message", "Invalid Message Content");
            return outputJsonObj.toString();
        }


        if(!result.hasErrors()){
            Boolean sent = email.sendEmail();
            if(!sent) {
                outputJsonObj.put("message", "Error on send");
            }else{
                outputJsonObj.put("success",true);
                outputJsonObj.put("message", "Message sent");
            }

            return outputJsonObj.toString();
        }else{
            return outputJsonObj.toString();
        }
    }

    /**DEC 19**/
 /*   @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("email", new Email());
        return "contact";
    }*/



   /* @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }*/

    /**DEC 19**/


/*
    @GetMapping("/two")
    public String twoPage(Model model) {
        Consumeapi api = new Consumeapi();
        String data = api.consume();

        model.addAttribute("first", "I am #1");
        model.addAttribute("second", "Chicago");
        model.addAttribute("third", data);
        return "two";
    }*/
}
