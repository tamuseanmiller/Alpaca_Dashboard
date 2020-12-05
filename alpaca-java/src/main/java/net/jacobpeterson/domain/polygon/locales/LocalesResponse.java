
package net.jacobpeterson.domain.polygon.locales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_locales">https://polygon.io/docs/#!/Reference/get_v2_reference_locales</a>
 * <p>
 * 
 * 
 */
public class LocalesResponse implements Serializable
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
    private ArrayList<Locale> results;
    private final static long serialVersionUID = 5825465459857059975L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LocalesResponse() {
    }

    /**
     * 
     * @param results
     * @param status
     */
    public LocalesResponse(String status, ArrayList<Locale> results) {
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
    public ArrayList<Locale> getResults() {
        return results;
    }

    /**
     * Results
     * <p>
     * 
     * 
     */
    public void setResults(ArrayList<Locale> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LocalesResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof LocalesResponse) == false) {
            return false;
        }
        LocalesResponse rhs = ((LocalesResponse) other);
        return (((this.results == rhs.results)||((this.results!= null)&&this.results.equals(rhs.results)))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
