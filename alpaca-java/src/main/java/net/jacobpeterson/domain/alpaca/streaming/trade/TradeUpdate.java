
package net.jacobpeterson.domain.alpaca.streaming.trade;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.alpaca.order.Order;

import java.io.Serializable;
import java.time.ZonedDateTime;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/streaming/">https://docs.alpaca.markets/api-documentation/api-v2/streaming/</a>
 * <p>
 * 
 * 
 */
public class TradeUpdate implements Serializable
{

    /**
     * The event type
     * <p>
     * 
     * 
     */
    @SerializedName("event")
    @Expose
    private String event;
    /**
     * The Price
     * <p>
     * 
     * 
     */
    @SerializedName("price")
    @Expose
    private String price;
    /**
     * The timestamp
     * <p>
     * 
     * 
     */
    @SerializedName("timestamp")
    @Expose
    private ZonedDateTime timestamp;
    /**
     * The position quantity
     * <p>
     * 
     * 
     */
    @SerializedName("position_qty")
    @Expose
    private String positionQty;
    /**
     * The Order
     * <p>
     * 
     * 
     */
    @SerializedName("order")
    @Expose
    private Order order;
    private final static long serialVersionUID = -8868604670177248356L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TradeUpdate() {
    }

    /**
     * 
     * @param positionQty
     * @param price
     * @param event
     * @param timestamp
     * @param order
     */
    public TradeUpdate(String event, String price, ZonedDateTime timestamp, String positionQty, Order order) {
        super();
        this.event = event;
        this.price = price;
        this.timestamp = timestamp;
        this.positionQty = positionQty;
        this.order = order;
    }

    /**
     * The event type
     * <p>
     * 
     * 
     */
    public String getEvent() {
        return event;
    }

    /**
     * The event type
     * <p>
     * 
     * 
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * The Price
     * <p>
     * 
     * 
     */
    public String getPrice() {
        return price;
    }

    /**
     * The Price
     * <p>
     * 
     * 
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * The timestamp
     * <p>
     * 
     * 
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * The timestamp
     * <p>
     * 
     * 
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * The position quantity
     * <p>
     * 
     * 
     */
    public String getPositionQty() {
        return positionQty;
    }

    /**
     * The position quantity
     * <p>
     * 
     * 
     */
    public void setPositionQty(String positionQty) {
        this.positionQty = positionQty;
    }

    /**
     * The Order
     * <p>
     * 
     * 
     */
    public Order getOrder() {
        return order;
    }

    /**
     * The Order
     * <p>
     * 
     * 
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TradeUpdate.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("event");
        sb.append('=');
        sb.append(((this.event == null)?"<null>":this.event));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
        sb.append(',');
        sb.append("positionQty");
        sb.append('=');
        sb.append(((this.positionQty == null)?"<null>":this.positionQty));
        sb.append(',');
        sb.append("order");
        sb.append('=');
        sb.append(((this.order == null)?"<null>":this.order));
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
        result = ((result* 31)+((this.positionQty == null)? 0 :this.positionQty.hashCode()));
        result = ((result* 31)+((this.event == null)? 0 :this.event.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        result = ((result* 31)+((this.order == null)? 0 :this.order.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TradeUpdate) == false) {
            return false;
        }
        TradeUpdate rhs = ((TradeUpdate) other);
        return ((((((this.positionQty == rhs.positionQty)||((this.positionQty!= null)&&this.positionQty.equals(rhs.positionQty)))&&((this.event == rhs.event)||((this.event!= null)&&this.event.equals(rhs.event))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))))&&((this.order == rhs.order)||((this.order!= null)&&this.order.equals(rhs.order))));
    }

}
