
package net.jacobpeterson.domain.polygon.stockfinancials;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_financials_symbol">https://polygon.io/docs/#!/Reference/get_v2_reference_financials_symbol</a>
 * <p>
 * 
 * 
 */
public class StockFinancialsResponse implements Serializable
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
    private ArrayList<StockFinancials> results;
    private final static long serialVersionUID = -1006113715634411520L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StockFinancialsResponse() {
    }

    /**
     * 
     * @param count
     * @param results
     * @param status
     */
    public StockFinancialsResponse(String status, Integer count, ArrayList<StockFinancials> results) {
        super();
        this.status = status;
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
    public ArrayList<StockFinancials> getResults() {
        return results;
    }

    /**
     * Results
     * <p>
     * 
     * 
     */
    public void setResults(ArrayList<StockFinancials> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StockFinancialsResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
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
        result = ((result* 31)+((this.results == null)? 0 :this.results.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StockFinancialsResponse) == false) {
            return false;
        }
        StockFinancialsResponse rhs = ((StockFinancialsResponse) other);
        return ((((this.count == rhs.count)||((this.count!= null)&&this.count.equals(rhs.count)))&&((this.results == rhs.results)||((this.results!= null)&&this.results.equals(rhs.results))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
