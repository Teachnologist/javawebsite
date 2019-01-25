package com.mysite.beta;

public class GithubUser {

    private String login;
    private String avatar_url;
    private String url;
    private String repos_url;
    private String followers_url;
    private String html_url;


    protected void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    protected void setLogin(String login) {
        this.login = login;
    }

    protected void setUrl(String url){
        this.avatar_url = avatar_url;
    }

    protected void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    protected void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    protected void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml_url() {
        return html_url;
    }
}
