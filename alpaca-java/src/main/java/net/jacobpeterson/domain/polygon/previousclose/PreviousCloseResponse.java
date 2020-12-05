
package net.jacobpeterson.domain.polygon.previousclose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.polygon.aggregates.Aggregate;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v2_aggs_ticker_ticker_prev">https://polygon.io/docs/#!/Stocks--Equities/get_v2_aggs_ticker_ticker_prev</a>
 * <p>
 * 
 * 
 */
public class PreviousCloseResponse implements Serializable
{

    /**
     * Ticker symbol requested
     * <p>
     * 
     * 
     */
    @SerializedName("ticker")
    @Expose
    private String ticker;
    /**
     * Status of the response
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private String status;
    /**
     * If this response was adjusted for splits
     * <p>
     * 
     * 
     */
    @SerializedName("adjusted")
    @Expose
    private Boolean adjusted;
    /**
     * Number of aggregate ( min or day ) used to generate the response
     * <p>
     * 
     * 
     */
    @SerializedName("queryCount")
    @Expose
    private Integer queryCount;
    /**
     * Total number of results generated
     * <p>
     * 
     * 
     */
    @SerializedName("resultsCount")
    @Expose
    private Integer resultsCount;
    /**
     * Results
     * <p>
     * 
     * 
     */
    @SerializedName("results")
    @Expose
    private ArrayList<Aggregate> results;
    private final static long serialVersionUID = 8294697377074537245L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PreviousCloseResponse() {
    }

    /**
     * 
     * @param ticker
     * @param adjusted
     * @param resultsCount
     * @param queryCount
     * @param results
     * @param status
     */
    public PreviousCloseResponse(String ticker, String status, Boolean adjusted, Integer queryCount, Integer resultsCount, ArrayList<Aggregate> results) {
        super();
        this.ticker = ticker;
        this.status = status;
        this.adjusted = adjusted;
        this.queryCount = queryCount;
        this.resultsCount = resultsCount;
        this.results = results;
    }

    /**
     * Ticker symbol requested
     * <p>
     * 
     * 
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Ticker symbol requested
     * <p>
     * 
     * 
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * Status of the response
     * <p>
     * 
     * 
     */
    public String getStatus() {
        return status;
    }

    /**
     * Status of the response
     * <p>
     * 
     * 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * If this response was adjusted for splits
     * <p>
     * 
     * 
     */
    public Boolean getAdjusted() {
        return adjusted;
    }

    /**
     * If this response was adjusted for splits
     * <p>
     * 
     * 
     */
    public void setAdjusted(Boolean adjusted) {
        this.adjusted = adjusted;
    }

    /**
     * Number of aggregate ( min or day ) used to generate the response
     * <p>
     * 
     * 
     */
    public Integer getQueryCount() {
        return queryCount;
    }

    /**
     * Number of aggregate ( min or day ) used to generate the response
     * <p>
     * 
     * 
     */
    public void setQueryCount(Integer queryCount) {
        this.queryCount = queryCount;
    }

    /**
     * Total number of results generated
     * <p>
     * 
     * 
     */
    public Integer getResultsCount() {
        return resultsCount;
    }

    /**
     * Total number of results generated
     * <p>
     * 
     * 
     */
    public void setResultsCount(Integer resultsCount) {
        this.resultsCount = resultsCount;
    }

    /**
     * Results
     * <p>
     * 
     * 
     */
    public ArrayList<Aggregate> getResults() {
        return results;
    }

    /**
     * Results
     * <p>
     * 
     * 
     */
    public void setResults(ArrayList<Aggregate> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PreviousCloseResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ticker");
        sb.append('=');
        sb.append(((this.ticker == null)?"<null>":this.ticker));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("adjusted");
        sb.append('=');
        sb.append(((this.adjusted == null)?"<null>":this.adjusted));
        sb.append(',');
        sb.append("queryCount");
        sb.append('=');
        sb.append(((this.queryCount == null)?"<null>":this.queryCount));
        sb.append(',');
        sb.append("resultsCount");
        sb.append('=');
        sb.append(((this.resultsCount == null)?"<null>":this.resultsCount));
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
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+((this.adjusted == null)? 0 :this.adjusted.hashCode()));
        result = ((result* 31)+((this.resultsCount == null)? 0 :this.resultsCount.hashCode()));
        result = ((result* 31)+((this.queryCount == null)? 0 :this.queryCount.hashCode()));
        result = ((result* 31)+((this.results == null)? 0 :this.results.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PreviousCloseResponse) == false) {
            return false;
        }
        PreviousCloseResponse rhs = ((PreviousCloseResponse) other);
        return (((((((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker)))&&((this.adjusted == rhs.adjusted)||((this.adjusted!= null)&&this.adjusted.equals(rhs.adjusted))))&&((this.resultsCount == rhs.resultsCount)||((this.resultsCount!= null)&&this.resultsCount.equals(rhs.resultsCount))))&&((this.queryCount == rhs.queryCount)||((this.queryCount!= null)&&this.queryCount.equals(rhs.queryCount))))&&((this.results == rhs.results)||((this.results!= null)&&this.results.equals(rhs.results))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
