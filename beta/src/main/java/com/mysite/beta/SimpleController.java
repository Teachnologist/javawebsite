package com.mysite.beta;

import githubapi.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @GetMapping("/github")
    public String githubPage(Model model) {
        Consumeapi apiClass = new Consumeapi();
        System.out.print("a initial read\n");
        GithubUser user = apiClass.getGithubJSON();
        System.out.print(user.getLogin());


        model.addAttribute("username", user.getLogin());
        model.addAttribute("image_source", user.getAvatar_url());
        model.addAttribute("github_page", user.getHtml_url());


        GithubRate rate = apiClass.getGithubRateLimit();
        System.out.print("a rate object\n");

        GithubRateo parserate = rate.getRate();

        model.addAttribute("limit", parserate.getLimit());
        model.addAttribute("remaining", parserate.getRemaining());
        model.addAttribute("reset", parserate.getReset());

        Double percentage = 100*(parserate.getRemaining() / parserate.getLimit());
        System.out.print("percentage"+ percentage);

        Integer percentage_readable = (int) Math.round(percentage);

        model.addAttribute("percentage", percentage);
        model.addAttribute("percentage_readable", percentage_readable);


        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(parserate.getReset(), 0, ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm-dd-yyyy h:mm a", Locale.ENGLISH);
        String formattedDate = dateTime.format(formatter);

        model.addAttribute("formatteddate", formattedDate);
        System.out.print(parserate.getLimit());



        System.out.print("b rate object\n");



        List<GithubRepo> repos = apiClass.getGithubREPOS(user.getRepos_url());

        System.out.print("b initial read\n");
        System.out.print(repos.toString());
        System.out.print("d initial read\n");

        Integer percentage_of_last_repo = 40;

        Integer repo_size = (int) Math.round(repos.size()*(percentage_of_last_repo/100));
        repos.sort((o1, o2) -> o2.getUpdated_at().compareTo(o1.getUpdated_at()));

        for (int q=0;q<repos.size();q++){
            /*works - need to optimize, create a general function class*/
            Long dayssince = repos.get(q).convertDaystoMilliSeconds(30);
            Long repodate = repos.get(q).getDateAsMilliseconds(repos.get(q).getUpdated_at());

            if((repodate) > new Date().getTime() - dayssince) {
                System.out.print("SHOULD DISPLAY " + repos.get(q).getUpdated_at() + "\n");
            }else{
                System.out.print("NO DISPLAY " + repos.get(q).getUpdated_at() + "\n");
            }

        }
        System.out.print("map read\n");

        model.addAttribute("repo_list", repos);
        System.out.print("b map read\n");


        return "github";
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
