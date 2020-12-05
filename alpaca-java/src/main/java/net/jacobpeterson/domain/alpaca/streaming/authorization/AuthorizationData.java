
package net.jacobpeterson.domain.alpaca.streaming.authorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/streaming/">https://docs.alpaca.markets/api-documentation/api-v2/streaming/</a>
 * <p>
 * 
 * 
 */
public class AuthorizationData implements Serializable
{

    /**
     * The status.
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private String status;
    /**
     * The desired action.
     * <p>
     * 
     * 
     */
    @SerializedName("action")
    @Expose
    private String action;
    private final static long serialVersionUID = -3648049115239445571L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AuthorizationData() {
    }

    /**
     * 
     * @param action
     * @param status
     */
    public AuthorizationData(String status, String action) {
        super();
        this.status = status;
        this.action = action;
    }

    /**
     * The status.
     * <p>
     * 
     * 
     */
    public String getStatus() {
        return status;
    }

    /**
     * The status.
     * <p>
     * 
     * 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * The desired action.
     * <p>
     * 
     * 
     */
    public String getAction() {
        return action;
    }

    /**
     * The desired action.
     * <p>
     * 
     * 
     */
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AuthorizationData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("action");
        sb.append('=');
        sb.append(((this.action == null)?"<null>":this.action));
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
        result = ((result* 31)+((this.action == null)? 0 :this.action.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AuthorizationData) == false) {
            return false;
        }
        AuthorizationData rhs = ((AuthorizationData) other);
        return (((this.action == rhs.action)||((this.action!= null)&&this.action.equals(rhs.action)))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
