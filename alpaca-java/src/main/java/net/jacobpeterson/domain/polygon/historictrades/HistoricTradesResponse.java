
package net.jacobpeterson.domain.polygon.historictrades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v2_ticks_stocks_trades_ticker_date">https://polygon.io/docs/#!/Stocks--Equities/get_v2_ticks_stocks_trades_ticker_date</a>
 * <p>
 * 
 * 
 */
public class HistoricTradesResponse implements Serializable
{

    /**
     * Total number of results in this response
     * <p>
     * 
     * 
     */
    @SerializedName("results_count")
    @Expose
    private Integer resultsCount;
    /**
     * Milliseconds of latency for the query results from DB
     * <p>
     * 
     * 
     */
    @SerializedName("db_latency")
    @Expose
    private Integer dbLatency;
    /**
     * If this query was executed successfully
     * <p>
     * 
     * 
     */
    @SerializedName("success")
    @Expose
    private Boolean success;
    /**
     * Ticker symbol that was evaluated from the request
     * <p>
     * 
     * 
     */
    @SerializedName("ticker")
    @Expose
    private String ticker;
    /**
     * results
     * <p>
     * 
     * 
     */
    @SerializedName("results")
    @Expose
    private ArrayList<HistoricTrade> results;
    private final static long serialVersionUID = -5879065640790671272L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HistoricTradesResponse() {
    }

    /**
     * 
     * @param dbLatency
     * @param ticker
     * @param success
     * @param resultsCount
     * @param results
     */
    public HistoricTradesResponse(Integer resultsCount, Integer dbLatency, Boolean success, String ticker, ArrayList<HistoricTrade> results) {
        super();
        this.resultsCount = resultsCount;
        this.dbLatency = dbLatency;
        this.success = success;
        this.ticker = ticker;
        this.results = results;
    }

    /**
     * Total number of results in this response
     * <p>
     * 
     * 
     */
    public Integer getResultsCount() {
        return resultsCount;
    }

    /**
     * Total number of results in this response
     * <p>
     * 
     * 
     */
    public void setResultsCount(Integer resultsCount) {
        this.resultsCount = resultsCount;
    }

    /**
     * Milliseconds of latency for the query results from DB
     * <p>
     * 
     * 
     */
    public Integer getDbLatency() {
        return dbLatency;
    }

    /**
     * Milliseconds of latency for the query results from DB
     * <p>
     * 
     * 
     */
    public void setDbLatency(Integer dbLatency) {
        this.dbLatency = dbLatency;
    }

    /**
     * If this query was executed successfully
     * <p>
     * 
     * 
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * If this query was executed successfully
     * <p>
     * 
     * 
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * Ticker symbol that was evaluated from the request
     * <p>
     * 
     * 
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Ticker symbol that was evaluated from the request
     * <p>
     * 
     * 
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * results
     * <p>
     * 
     * 
     */
    public ArrayList<HistoricTrade> getResults() {
        return results;
    }

    /**
     * results
     * <p>
     * 
     * 
     */
    public void setResults(ArrayList<HistoricTrade> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HistoricTradesResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("resultsCount");
        sb.append('=');
        sb.append(((this.resultsCount == null)?"<null>":this.resultsCount));
        sb.append(',');
        sb.append("dbLatency");
        sb.append('=');
        sb.append(((this.dbLatency == null)?"<null>":this.dbLatency));
        sb.append(',');
        sb.append("success");
        sb.append('=');
        sb.append(((this.success == null)?"<null>":this.success));
        sb.append(',');
        sb.append("ticker");
        sb.append('=');
        sb.append(((this.ticker == null)?"<null>":this.ticker));
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
        result = ((result* 31)+((this.dbLatency == null)? 0 :this.dbLatency.hashCode()));
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+((this.results == null)? 0 :this.results.hashCode()));
        result = ((result* 31)+((this.success == null)? 0 :this.success.hashCode()));
        result = ((result* 31)+((this.resultsCount == null)? 0 :this.resultsCount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HistoricTradesResponse) == false) {
            return false;
        }
        HistoricTradesResponse rhs = ((HistoricTradesResponse) other);
        return ((((((this.dbLatency == rhs.dbLatency)||((this.dbLatency!= null)&&this.dbLatency.equals(rhs.dbLatency)))&&((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker))))&&((this.results == rhs.results)||((this.results!= null)&&this.results.equals(rhs.results))))&&((this.success == rhs.success)||((this.success!= null)&&this.success.equals(rhs.success))))&&((this.resultsCount == rhs.resultsCount)||((this.resultsCount!= null)&&this.resultsCount.equals(rhs.resultsCount))));
    }

}
