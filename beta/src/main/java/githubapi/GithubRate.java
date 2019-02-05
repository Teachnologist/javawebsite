package githubapi;

public class GithubRate {
    private Object resources;
    private GithubRateo rate;

    protected void setResources(Object resources) {
        this.resources = resources;
    }

    protected void setRate(GithubRateo rate) {
        this.rate = rate;
    }

    public GithubRateo getRate() {
        return rate;
    }

    public Object getResources() {
        return resources;
    }
}
