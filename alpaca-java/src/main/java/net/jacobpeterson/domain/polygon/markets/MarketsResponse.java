
package net.jacobpeterson.domain.polygon.markets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_markets">https://polygon.io/docs/#!/Reference/get_v2_reference_markets</a>
 * <p>
 * 
 * 
 */
public class MarketsResponse implements Serializable
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
     * Results
     * <p>
     * 
     * 
     */
    @SerializedName("results")
    @Expose
    private ArrayList<Market> results;
    private final static long serialVersionUID = 4510973950775512117L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MarketsResponse() {
    }

    /**
     * 
     * @param results
     * @param status
     */
    public MarketsResponse(String status, ArrayList<Market> results) {
        super();
        this.status = status;
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
     * Results
     * <p>
     * 
     * 
     */
    public ArrayList<Market> getResults() {
        return results;
    }

    /**
     * Results
     * <p>
     * 
     * 
     */
    public void setResults(ArrayList<Market> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MarketsResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
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
        result = ((result* 31)+((this.results == null)? 0 :this.results.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MarketsResponse) == false) {
            return false;
        }
        MarketsResponse rhs = ((MarketsResponse) other);
        return (((this.results == rhs.results)||((this.results!= null)&&this.results.equals(rhs.results)))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
