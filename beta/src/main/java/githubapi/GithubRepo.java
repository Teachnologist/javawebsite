package githubapi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GithubRepo {

    private String name;
    private String updated_at;
    private String languages_url;
    private String repo_url;
    private String git_url;
    private String html_url;
    private String ssh_url;
    private Long date_epoch;

    protected void setName(String name) {
        this.name = name;
    }

    protected void setLanguages_url(String languages_url) {
        this.languages_url = languages_url;
    }

    protected void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    protected void setHtml_url(String html_url) { this.html_url = html_url; }

    protected void setSsh_url(String ssh_url) {
        this.ssh_url = ssh_url;
    }

    protected void setDate_epoch(String updated_at) {
        String DateStr=updated_at;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d'T'HH:mm:ss'Z'");
        try {
            Date d = sdf.parse(DateStr);
            this.date_epoch = date_epoch;
        }catch(Exception e){
            this.date_epoch = null;
        }
    }

    public Long getDate_epoch(String updated_at) {

        String DateStr=updated_at;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d'T'HH:mm:ss'Z'");
        try {
            Date d = sdf.parse(DateStr);
            Long i = (d.getTime()/1000);
            this.date_epoch = i;
        }catch(Exception e){
            this.date_epoch = null;
        }
        return this.date_epoch;
    }



    public String getName() {
        return name;
    }

    public String getUpdated_at() {
        return updated_at;
    }


    public String getLanguages_url() {
        return languages_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getSsh_url() {
        return ssh_url;
    }
}
