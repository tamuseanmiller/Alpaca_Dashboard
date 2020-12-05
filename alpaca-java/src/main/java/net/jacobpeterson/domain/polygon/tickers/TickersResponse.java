
package net.jacobpeterson.domain.polygon.tickers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.polygon.tickers.ticker.Ticker;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_tickers">https://polygon.io/docs/#!/Reference/get_v2_reference_tickers</a>
 * <p>
 * 
 * 
 */
public class TickersResponse implements Serializable
{

    /**
     * Status
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private String status;
    /**
     * Page
     * <p>
     * 
     * 
     */
    @SerializedName("page")
    @Expose
    private Integer page;
    /**
     * Per Page
     * <p>
     * 
     * 
     */
    @SerializedName("perPage")
    @Expose
    private Integer perPage;
    /**
     * Count
     * <p>
     * 
     * 
     */
    @SerializedName("count")
    @Expose
    private Integer count;
    /**
     * Results
     * <p>
     * 
     * 
     */
    @SerializedName("results")
    @Expose
    private ArrayList<Ticker> results;
    private final static long serialVersionUID = 7720158471610718268L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TickersResponse() {
    }

    /**
     * 
     * @param perPage
     * @param count
     * @param page
     * @param results
     * @param status
     */
    public TickersResponse(String status, Integer page, Integer perPage, Integer count, ArrayList<Ticker> results) {
        super();
        this.status = status;
        this.page = page;
        this.perPage = perPage;
        this.count = count;
        this.results = results;
    }

    /**
     * Status
     * <p>
     * 
     * 
     */
    public String getStatus() {
        return status;
    }

    /**
     * Status
     * <p>
     * 
     * 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Page
     * <p>
     * 
     * 
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Page
     * <p>
     * 
     * 
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * Per Page
     * <p>
     * 
     * 
     */
    public Integer getPerPage() {
        return perPage;
    }

    /**
     * Per Page
     * <p>
     * 
     * 
     */
    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    /**
     * Count
     * <p>
     * 
     * 
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Count
     * <p>
     * 
     * 
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Results
     * <p>
     * 
     * 
     */
    public ArrayList<Ticker> getResults() {
        return results;
    }

    /**
     * Results
     * <p>
     * 
     * 
     */
    public void setResults(ArrayList<Ticker> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TickersResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("page");
        sb.append('=');
        sb.append(((this.page == null)?"<null>":this.page));
        sb.append(',');
        sb.append("perPage");
        sb.append('=');
        sb.append(((this.perPage == null)?"<null>":this.perPage));
        sb.append(',');
        sb.append("count");
        sb.append('=');
        sb.append(((this.count == null)?"<null>":this.count));
        sb.append(',');
        sb.append("results");
        sb.append('=');
        sb.append(((this.results == null)?"<null>":this.results));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.count == null)? 0 :this.count.hashCode()));
        result = ((result* 31)+((this.page == null)? 0 :this.page.hashCode()));
        result = ((result* 31)+((this.perPage == null)? 0 :this.perPage.hashCode()));
        result = ((result* 31)+((this.results == null)? 0 :this.results.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TickersResponse) == false) {
            return false;
        }
        TickersResponse rhs = ((TickersResponse) other);
        return ((((((this.count == rhs.count)||((this.count!= null)&&this.count.equals(rhs.count)))&&((this.page == rhs.page)||((this.page!= null)&&this.page.equals(rhs.page))))&&((this.perPage == rhs.perPage)||((this.perPage!= null)&&this.perPage.equals(rhs.perPage))))&&((this.results == rhs.results)||((this.results!= null)&&this.results.equals(rhs.results))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
