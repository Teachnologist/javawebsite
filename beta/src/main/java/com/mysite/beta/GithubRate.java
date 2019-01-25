package com.mysite.beta;

import java.util.Map;

public class GithubRate {
    private Object resources;
    private Map rate;

    protected void setResources(Object resources) {
        this.resources = resources;
    }

    protected void setRate(Map rate) {
        this.rate = rate;
    }

    public Map getRate() {
        return rate;
    }

    public Object getResources() {
        return resources;
    }
}
