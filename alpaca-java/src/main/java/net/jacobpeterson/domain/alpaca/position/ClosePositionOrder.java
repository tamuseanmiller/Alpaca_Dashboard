
package net.jacobpeterson.domain.alpaca.position;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.alpaca.order.Order;

import java.io.Serializable;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/positions/">https://alpaca.markets/docs/api-documentation/api-v2/positions/</a>
 * <p>
 * 
 * 
 */
public class ClosePositionOrder implements Serializable
{

    /**
     * The symbol
     * <p>
     * 
     * 
     */
    @SerializedName("symbol")
    @Expose
    private String symbol;
    /**
     * The HTTP status code of the position-closing order status
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private Integer status;
    /**
     * The position-closing order
     * <p>
     * 
     * 
     */
    @SerializedName("body")
    @Expose
    private Order body;
    private final static long serialVersionUID = 4736134522503634360L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ClosePositionOrder() {
    }

    /**
     * 
     * @param symbol
     * @param body
     * @param status
     */
    public ClosePositionOrder(String symbol, Integer status, Order body) {
        super();
        this.symbol = symbol;
        this.status = status;
        this.body = body;
    }

    /**
     * The symbol
     * <p>
     * 
     * 
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * The symbol
     * <p>
     * 
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * The HTTP status code of the position-closing order status
     * <p>
     * 
     * 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * The HTTP status code of the position-closing order status
     * <p>
     * 
     * 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * The position-closing order
     * <p>
     * 
     * 
     */
    public Order getBody() {
        return body;
    }

    /**
     * The position-closing order
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
        sb.append(ClosePositionOrder.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
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
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.body == null)? 0 :this.body.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ClosePositionOrder) == false) {
            return false;
        }
        ClosePositionOrder rhs = ((ClosePositionOrder) other);
        return ((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.body == rhs.body)||((this.body!= null)&&this.body.equals(rhs.body))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
