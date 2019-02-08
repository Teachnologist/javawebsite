package githubapi;

/*import org.springframework.web.client.RestTemplate;*/
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import githubapi.GithubRate;
import githubapi.GithubRepo;
import githubapi.GithubUser;
import org.springframework.web.client.RestTemplate;
import triche.date.methods.General;

import java.lang.reflect.Array;

/*importt org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;*/
import java.util.List;
import java.util.*;


public class Consumeapi {

    final String url_prefix = "https://api.github.com/users/";
    final String rate_uri = "https://api.github.com/rate_limit";

    public GithubUser getGithubJSON(String username){
        String uri = url_prefix+username;

        try {
            RestTemplate restTemplate = new RestTemplate();
            String jsonString = restTemplate.getForObject(uri, String.class);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            System.out.println("Trying....");
            GithubUser result = mapper.readValue(jsonString, GithubUser.class);
            return result;
        } catch (Exception e) {
            System.out.println("t Invalid results");
            return null;
          /*  System.out.print(e);
            System.out.println("b Invalid results");*/

        }



    }



    public GithubRate getGithubRateLimit(){
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(rate_uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GithubRate result = null;
        try {
            System.out.println("Trying....");
            result = mapper.readValue(jsonString, GithubRate.class);
        } catch (Exception e) {
            System.out.println("t Invalid results");
            System.out.print(e);
            System.out.println("b Invalid results");
        }


        return result;
    }

    public Object parseNestedMap(Map json){
        System.out.println("parsing nested");


        Object result = null;
        try {
            String newjson = new ObjectMapper().writeValueAsString(json);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            System.out.println("parsing json");
            result = mapper.readValue(newjson, Object.class);
        } catch (Exception e) {
            System.out.println("t Invalid results");
            System.out.print(e);
            System.out.println("b Invalid results");
        }

        return result;
    }

    public List<GithubRepo> getGithubREPOS(String repo_url){
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(repo_url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<GithubRepo> result = null;
        try {
            System.out.println("valid string");
            System.out.print(jsonString);
            System.out.println("b valid string");
            result = mapper.readValue(jsonString,  new TypeReference<List<GithubRepo>>(){});
        } catch (Exception e) {
            System.out.println("t Invalid results");
            System.out.print(e);
            System.out.println("b Invalid results");
        }


        return result;
    }

    public List<Map<String,String>> getDescendingSortedandFilteredbyDateRepos(Date date, Integer days, String repo_url){
        List<GithubRepo> repos = getGithubREPOS(repo_url);
        repos.sort((o1, o2) -> o2.getUpdated_at().compareTo(o1.getUpdated_at()));

        List<Map<String,String>> repoarray = new ArrayList();



        General tmethods = new General();

        Long daysms = tmethods.convertDaystoMilliSeconds(days);
        System.out.println(daysms);
        Long time_distance = tmethods.subtractMillisecondsFromDate(date, daysms);

        for (int q=0;q<repos.size();q++){
            /*works - need to optimize, create a general function class*/

            Long repodate = tmethods.getDateAsMilliseconds(repos.get(q).getUpdated_at());

            if(tmethods.dateIsGreater(repodate,time_distance)) {
                System.out.print("SHOULD DISPLAY " + repos.get(q).getUpdated_at() + "\n");
                Map<String,String> repomap = new HashMap<String, String>();
                repomap.put("html_url",repos.get(q).getHtml_url());
                repomap.put("ssh_url",repos.get(q).getSsh_url());
                repomap.put("name",repos.get(q).getName());
                repomap.put("updated_at",repos.get(q).getUpdated_at());
                System.out.println(repomap.toString());
                repoarray.add(repomap);
            }else{
                System.out.print("NO DISPLAY " + repos.get(q).getUpdated_at() + "\n");
            }

        }
        return repoarray;
    }

    public List<Array> getAnonymousJSONArray(String url){
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Array> result = null;
        try {
            result = mapper.readValue(jsonString, new TypeReference<List<Array>>(){});
        } catch (Exception e) {
            System.out.println("t Invalid results");
            System.out.print(e);
            System.out.println("b Invalid results");
        }


        return result;
    }

}
