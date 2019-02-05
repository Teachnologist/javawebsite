package githubapi;

public class GithubRateo {

    private Double limit;

    private Double remaining;

    private Integer reset;

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }

    public void setReset(Integer reset) {
        this.reset = reset;
    }

    public Integer getReset() {
        return reset;
    }

    public Double getLimit() {
        return limit;
    }

    public Double getRemaining() {
        return remaining;
    }
}
