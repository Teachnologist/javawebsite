package com.mysite.beta;

public class GithubRepo {

    private String name;
    private String updated_at;
    private String languages_url;
    private String repo_url;
    private String git_url;

    protected void setName(String name) {
        this.name = name;
    }

    protected void setLanguages_url(String languages_url) {
        this.languages_url = languages_url;
    }

    protected void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
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
}
