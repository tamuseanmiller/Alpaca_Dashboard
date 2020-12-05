
package net.jacobpeterson.domain.alpaca.accountactivities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.ZonedDateTime;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/account-activities/">https://docs.alpaca.markets/api-documentation/api-v2/account-activities/</a>
 * <p>
 * 
 * 
 */
public class TradeActivity
    extends AccountActivity
    implements Serializable
{

    /**
     * FILL
     * <p>
     * 
     * 
     */
    @SerializedName("activity_type")
    @Expose
    private String activityType;
    /**
     * An id for the activity. Always in “::” format. Can be sent as page_token in requests to facilitate the paging of results.
     * <p>
     * 
     * 
     */
    @SerializedName("id")
    @Expose
    private String id;
    /**
     * The cumulative quantity of shares involved in the execution.
     * <p>
     * 
     * 
     */
    @SerializedName("cum_qty")
    @Expose
    private String cumQty;
    /**
     * For partially_filled orders, the quantity of shares that are left to be filled.
     * <p>
     * 
     * 
     */
    @SerializedName("leaves_qty")
    @Expose
    private String leavesQty;
    /**
     * The per-share price that the trade was executed at.
     * <p>
     * 
     * 
     */
    @SerializedName("price")
    @Expose
    private String price;
    /**
     * The number of shares involved in the trade execution.
     * <p>
     * 
     * 
     */
    @SerializedName("qty")
    @Expose
    private String qty;
    /**
     * Buy or sell
     * <p>
     * 
     * 
     */
    @SerializedName("side")
    @Expose
    private String side;
    /**
     * The symbol of the security being traded.
     * <p>
     * 
     * 
     */
    @SerializedName("symbol")
    @Expose
    private String symbol;
    /**
     * The time at which the execution occurred.
     * <p>
     * 
     * 
     */
    @SerializedName("transaction_time")
    @Expose
    private ZonedDateTime transactionTime;
    /**
     * The id for the order that filled. Always in GUID format.
     * <p>
     * 
     * 
     */
    @SerializedName("order_id")
    @Expose
    private String orderId;
    /**
     * fill or partial_fill
     * <p>
     * 
     * 
     */
    @SerializedName("type")
    @Expose
    private String type;
    private final static long serialVersionUID = 2483663234947769388L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TradeActivity() {
    }

    /**
     * 
     * @param symbol
     * @param side
     * @param orderId
     * @param price
     * @param qty
     * @param cumQty
     * @param leavesQty
     * @param id
     * @param activityType
     * @param transactionTime
     * @param type
     */
    public TradeActivity(String activityType, String id, String cumQty, String leavesQty, String price, String qty, String side, String symbol, ZonedDateTime transactionTime, String orderId, String type) {
        super();
        this.activityType = activityType;
        this.id = id;
        this.cumQty = cumQty;
        this.leavesQty = leavesQty;
        this.price = price;
        this.qty = qty;
        this.side = side;
        this.symbol = symbol;
        this.transactionTime = transactionTime;
        this.orderId = orderId;
        this.type = type;
    }

    /**
     * FILL
     * <p>
     * 
     * 
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * FILL
     * <p>
     * 
     * 
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    /**
     * An id for the activity. Always in “::” format. Can be sent as page_token in requests to facilitate the paging of results.
     * <p>
     * 
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * An id for the activity. Always in “::” format. Can be sent as page_token in requests to facilitate the paging of results.
     * <p>
     * 
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The cumulative quantity of shares involved in the execution.
     * <p>
     * 
     * 
     */
    public String getCumQty() {
        return cumQty;
    }

    /**
     * The cumulative quantity of shares involved in the execution.
     * <p>
     * 
     * 
     */
    public void setCumQty(String cumQty) {
        this.cumQty = cumQty;
    }

    /**
     * For partially_filled orders, the quantity of shares that are left to be filled.
     * <p>
     * 
     * 
     */
    public String getLeavesQty() {
        return leavesQty;
    }

    /**
     * For partially_filled orders, the quantity of shares that are left to be filled.
     * <p>
     * 
     * 
     */
    public void setLeavesQty(String leavesQty) {
        this.leavesQty = leavesQty;
    }

    /**
     * The per-share price that the trade was executed at.
     * <p>
     * 
     * 
     */
    public String getPrice() {
        return price;
    }

    /**
     * The per-share price that the trade was executed at.
     * <p>
     * 
     * 
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * The number of shares involved in the trade execution.
     * <p>
     * 
     * 
     */
    public String getQty() {
        return qty;
    }

    /**
     * The number of shares involved in the trade execution.
     * <p>
     * 
     * 
     */
    public void setQty(String qty) {
        this.qty = qty;
    }

    /**
     * Buy or sell
     * <p>
     * 
     * 
     */
    public String getSide() {
        return side;
    }

    /**
     * Buy or sell
     * <p>
     * 
     * 
     */
    public void setSide(String side) {
        this.side = side;
    }

    /**
     * The symbol of the security being traded.
     * <p>
     * 
     * 
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * The symbol of the security being traded.
     * <p>
     * 
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * The time at which the execution occurred.
     * <p>
     * 
     * 
     */
    public ZonedDateTime getTransactionTime() {
        return transactionTime;
    }

    /**
     * The time at which the execution occurred.
     * <p>
     * 
     * 
     */
    public void setTransactionTime(ZonedDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    /**
     * The id for the order that filled. Always in GUID format.
     * <p>
     * 
     * 
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * The id for the order that filled. Always in GUID format.
     * <p>
     * 
     * 
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * fill or partial_fill
     * <p>
     * 
     * 
     */
    public String getType() {
        return type;
    }

    /**
     * fill or partial_fill
     * <p>
     * 
     * 
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TradeActivity.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        int baseLength = sb.length();
        String superString = super.toString();
        if (superString!= null) {
            int contentStart = superString.indexOf('[');
            int contentEnd = superString.lastIndexOf(']');
            if ((contentStart >= 0)&&(contentEnd >contentStart)) {
                sb.append(superString, (contentStart + 1), contentEnd);
            } else {
                sb.append(superString);
            }
        }
        if (sb.length()>baseLength) {
            sb.append(',');
        }
        sb.append("activityType");
        sb.append('=');
        sb.append(((this.activityType == null)?"<null>":this.activityType));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("cumQty");
        sb.append('=');
        sb.append(((this.cumQty == null)?"<null>":this.cumQty));
        sb.append(',');
        sb.append("leavesQty");
        sb.append('=');
        sb.append(((this.leavesQty == null)?"<null>":this.leavesQty));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("qty");
        sb.append('=');
        sb.append(((this.qty == null)?"<null>":this.qty));
        sb.append(',');
        sb.append("side");
        sb.append('=');
        sb.append(((this.side == null)?"<null>":this.side));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("transactionTime");
        sb.append('=');
        sb.append(((this.transactionTime == null)?"<null>":this.transactionTime));
        sb.append(',');
        sb.append("orderId");
        sb.append('=');
        sb.append(((this.orderId == null)?"<null>":this.orderId));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
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
        result = ((result* 31)+((this.side == null)? 0 :this.side.hashCode()));
        result = ((result* 31)+((this.orderId == null)? 0 :this.orderId.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.qty == null)? 0 :this.qty.hashCode()));
        result = ((result* 31)+((this.cumQty == null)? 0 :this.cumQty.hashCode()));
        result = ((result* 31)+((this.leavesQty == null)? 0 :this.leavesQty.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.activityType == null)? 0 :this.activityType.hashCode()));
        result = ((result* 31)+((this.transactionTime == null)? 0 :this.transactionTime.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TradeActivity) == false) {
            return false;
        }
        TradeActivity rhs = ((TradeActivity) other);
        return (((((((((((super.equals(rhs)&&((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol))))&&((this.side == rhs.side)||((this.side!= null)&&this.side.equals(rhs.side))))&&((this.orderId == rhs.orderId)||((this.orderId!= null)&&this.orderId.equals(rhs.orderId))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.qty == rhs.qty)||((this.qty!= null)&&this.qty.equals(rhs.qty))))&&((this.cumQty == rhs.cumQty)||((this.cumQty!= null)&&this.cumQty.equals(rhs.cumQty))))&&((this.leavesQty == rhs.leavesQty)||((this.leavesQty!= null)&&this.leavesQty.equals(rhs.leavesQty))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.activityType == rhs.activityType)||((this.activityType!= null)&&this.activityType.equals(rhs.activityType))))&&((this.transactionTime == rhs.transactionTime)||((this.transactionTime!= null)&&this.transactionTime.equals(rhs.transactionTime))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))));
    }

}
