
package net.jacobpeterson.domain.alpaca.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/orders/">https://docs.alpaca.markets/api-documentation/api-v2/orders/</a>
 * <p>
 * 
 * 
 */
public class CancelledOrder implements Serializable
{

    /**
     * Order ID
     * <p>
     * 
     * 
     */
    @SerializedName("id")
    @Expose
    private String id;
    /**
     * The HTTP status code of the cancel status
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private Integer status;
    /**
     * The cancelled order
     * <p>
     * 
     * 
     */
    @SerializedName("body")
    @Expose
    private Order body;
    private final static long serialVersionUID = 404941935861831989L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CancelledOrder() {
    }

    /**
     * 
     * @param id
     * @param body
     * @param status
     */
    public CancelledOrder(String id, Integer status, Order body) {
        super();
        this.id = id;
        this.status = status;
        this.body = body;
    }

    /**
     * Order ID
     * <p>
     * 
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * Order ID
     * <p>
     * 
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The HTTP status code of the cancel status
     * <p>
     * 
     * 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * The HTTP status code of the cancel status
     * <p>
     * 
     * 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * The cancelled order
     * <p>
     * 
     * 
     */
    public Order getBody() {
        return body;
    }

    /**
     * The cancelled order
     * <p>
     * 
     * 
     */
    public void setBody(Order body) {
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CancelledOrder.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("body");
        sb.append('=');
        sb.append(((this.body == null)?"<null>":this.body));
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
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.body == null)? 0 :this.body.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CancelledOrder) == false) {
            return false;
        }
        CancelledOrder rhs = ((CancelledOrder) other);
        return ((((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id)))&&((this.body == rhs.body)||((this.body!= null)&&this.body.equals(rhs.body))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
