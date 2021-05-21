
package net.jacobpeterson.domain.alpaca.order;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/orders/">https://docs.alpaca.markets/api-documentation/api-v2/orders/</a>
 * <p>
 * 
 * 
 */
public class Order implements Serializable
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
     * Client unique order ID
     * <p>
     * 
     * 
     */
    @SerializedName("client_order_id")
    @Expose
    private String clientOrderId;
    /**
     * Created at
     * <p>
     * 
     * 
     */
    @SerializedName("created_at")
    @Expose
    private ZonedDateTime createdAt;
    /**
     * Updated at
     * <p>
     * 
     * 
     */
    @SerializedName("updated_at")
    @Expose
    private ZonedDateTime updatedAt;
    /**
     * Submitted at
     * <p>
     * 
     * 
     */
    @SerializedName("submitted_at")
    @Expose
    private ZonedDateTime submittedAt;
    /**
     * Filled at
     * <p>
     * 
     * 
     */
    @SerializedName("filled_at")
    @Expose
    private ZonedDateTime filledAt;
    /**
     * Expired at
     * <p>
     * 
     * 
     */
    @SerializedName("expired_at")
    @Expose
    private ZonedDateTime expiredAt;
    /**
     * Canceled at
     * <p>
     * 
     * 
     */
    @SerializedName("canceled_at")
    @Expose
    private ZonedDateTime canceledAt;
    /**
     * Failed at
     * <p>
     * 
     * 
     */
    @SerializedName("failed_at")
    @Expose
    private ZonedDateTime failedAt;
    /**
     * Replaced at
     * <p>
     * 
     * 
     */
    @SerializedName("replaced_at")
    @Expose
    private ZonedDateTime replacedAt;
    /**
     * The order ID that this order was replaced by
     * <p>
     * 
     * 
     */
    @SerializedName("replaced_by")
    @Expose
    private String replacedBy;
    /**
     * The order ID that this order replaces
     * <p>
     * 
     * 
     */
    @SerializedName("replaces")
    @Expose
    private String replaces;
    /**
     * Asset ID
     * <p>
     * 
     * 
     */
    @SerializedName("asset_id")
    @Expose
    private String assetId;
    /**
     * Asset symbol
     * <p>
     * 
     * 
     */
    @SerializedName("symbol")
    @Expose
    private String symbol;
    /**
     * Asset class
     * <p>
     * 
     * 
     */
    @SerializedName("asset_class")
    @Expose
    private String assetClass;
    /**
     * Ordered notional amount. If entered, qty will be null. Can take up to 9 decimal points.
     * <p>
     * 
     * 
     */
    @SerializedName("notional")
    @Expose
    private String notional;
    /**
     * Ordered quantity. If entered, notional will be null. Can take up to 9 decimal points.
     * <p>
     * 
     * 
     */
    @SerializedName("qty")
    @Expose
    private String qty;
    /**
     * Filled quantity
     * <p>
     * 
     * 
     */
    @SerializedName("filled_qty")
    @Expose
    private String filledQty;
    /**
     * Filled average price
     * <p>
     * 
     * 
     */
    @SerializedName("filled_avg_price")
    @Expose
    private String filledAvgPrice;
    /**
     * simple, bracket, oco or oto. For details of non-simple order classes, please see Bracket Order Overview
     * <p>
     * 
     * 
     */
    @SerializedName("order_class")
    @Expose
    private String orderClass;
    /**
     * (Deprecated with just type field below.)
     * <p>
     * 
     * 
     */
    @SerializedName("order_type")
    @Expose
    private String orderType;
    /**
     * Valid values: market, limit, stop, stop_limit, trailing_stop
     * <p>
     * 
     * 
     */
    @SerializedName("type")
    @Expose
    private String type;
    /**
     * Valid values: buy, sell
     * <p>
     * 
     * 
     */
    @SerializedName("side")
    @Expose
    private String side;
    /**
     * See Orders page
     * <p>
     * 
     * 
     */
    @SerializedName("time_in_force")
    @Expose
    private String timeInForce;
    /**
     * Limit price
     * <p>
     * 
     * 
     */
    @SerializedName("limit_price")
    @Expose
    private String limitPrice;
    /**
     * Stop price
     * <p>
     * 
     * 
     */
    @SerializedName("stop_price")
    @Expose
    private String stopPrice;
    /**
     * See Orders page
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private String status;
    /**
     * If true, eligible for execution outside regular trading hours.
     * <p>
     * 
     * 
     */
    @SerializedName("extended_hours")
    @Expose
    private Boolean extendedHours;
    /**
     * When querying non-simple order_class orders in a nested style, an array of Order entities associated with this order. Otherwise, null.
     * <p>
     * 
     * 
     */
    @SerializedName("legs")
    @Expose
    private ArrayList<Order> legs;
    /**
     * The percent value away from the high water mark for trailing stop orders.
     * <p>
     * 
     * 
     */
    @SerializedName("trail_percent")
    @Expose
    private String trailPercent;
    /**
     * The dollar value away from the high water mark for trailing stop orders.
     * <p>
     * 
     * 
     */
    @SerializedName("trail_price")
    @Expose
    private String trailPrice;
    /**
     * High Water Mark - The highest (lowest) market price seen since the trailing stop order was submitted.
     * <p>
     * 
     * 
     */
    @SerializedName("hwm")
    @Expose
    private String hwm;
    private final static long serialVersionUID = 1108722180936587891L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Order() {
    }

    /**
     * 
     * @param symbol
     * @param orderType
     * @param replacedAt
     * @param notional
     * @param extendedHours
     * @param trailPrice
     * @param assetClass
     * @param type
     * @param orderClass
     * @param createdAt
     * @param expiredAt
     * @param failedAt
     * @param assetId
     * @param legs
     * @param id
     * @param submittedAt
     * @param timeInForce
     * @param updatedAt
     * @param side
     * @param limitPrice
     * @param trailPercent
     * @param replacedBy
     * @param replaces
     * @param clientOrderId
     * @param filledAt
     * @param filledAvgPrice
     * @param stopPrice
     * @param canceledAt
     * @param qty
     * @param filledQty
     * @param status
     * @param hwm
     */
    public Order(String id, String clientOrderId, ZonedDateTime createdAt, ZonedDateTime updatedAt, ZonedDateTime submittedAt, ZonedDateTime filledAt, ZonedDateTime expiredAt, ZonedDateTime canceledAt, ZonedDateTime failedAt, ZonedDateTime replacedAt, String replacedBy, String replaces, String assetId, String symbol, String assetClass, String notional, String qty, String filledQty, String filledAvgPrice, String orderClass, String orderType, String type, String side, String timeInForce, String limitPrice, String stopPrice, String status, Boolean extendedHours, ArrayList<Order> legs, String trailPercent, String trailPrice, String hwm) {
        super();
        this.id = id;
        this.clientOrderId = clientOrderId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.submittedAt = submittedAt;
        this.filledAt = filledAt;
        this.expiredAt = expiredAt;
        this.canceledAt = canceledAt;
        this.failedAt = failedAt;
        this.replacedAt = replacedAt;
        this.replacedBy = replacedBy;
        this.replaces = replaces;
        this.assetId = assetId;
        this.symbol = symbol;
        this.assetClass = assetClass;
        this.notional = notional;
        this.qty = qty;
        this.filledQty = filledQty;
        this.filledAvgPrice = filledAvgPrice;
        this.orderClass = orderClass;
        this.orderType = orderType;
        this.type = type;
        this.side = side;
        this.timeInForce = timeInForce;
        this.limitPrice = limitPrice;
        this.stopPrice = stopPrice;
        this.status = status;
        this.extendedHours = extendedHours;
        this.legs = legs;
        this.trailPercent = trailPercent;
        this.trailPrice = trailPrice;
        this.hwm = hwm;
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
     * Client unique order ID
     * <p>
     * 
     * 
     */
    public String getClientOrderId() {
        return clientOrderId;
    }

    /**
     * Client unique order ID
     * <p>
     * 
     * 
     */
    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    /**
     * Created at
     * <p>
     * 
     * 
     */
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Created at
     * <p>
     * 
     * 
     */
    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Updated at
     * <p>
     * 
     * 
     */
    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Updated at
     * <p>
     * 
     * 
     */
    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Submitted at
     * <p>
     * 
     * 
     */
    public ZonedDateTime getSubmittedAt() {
        return submittedAt;
    }

    /**
     * Submitted at
     * <p>
     * 
     * 
     */
    public void setSubmittedAt(ZonedDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    /**
     * Filled at
     * <p>
     * 
     * 
     */
    public ZonedDateTime getFilledAt() {
        return filledAt;
    }

    /**
     * Filled at
     * <p>
     * 
     * 
     */
    public void setFilledAt(ZonedDateTime filledAt) {
        this.filledAt = filledAt;
    }

    /**
     * Expired at
     * <p>
     * 
     * 
     */
    public ZonedDateTime getExpiredAt() {
        return expiredAt;
    }

    /**
     * Expired at
     * <p>
     * 
     * 
     */
    public void setExpiredAt(ZonedDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    /**
     * Canceled at
     * <p>
     * 
     * 
     */
    public ZonedDateTime getCanceledAt() {
        return canceledAt;
    }

    /**
     * Canceled at
     * <p>
     * 
     * 
     */
    public void setCanceledAt(ZonedDateTime canceledAt) {
        this.canceledAt = canceledAt;
    }

    /**
     * Failed at
     * <p>
     * 
     * 
     */
    public ZonedDateTime getFailedAt() {
        return failedAt;
    }

    /**
     * Failed at
     * <p>
     * 
     * 
     */
    public void setFailedAt(ZonedDateTime failedAt) {
        this.failedAt = failedAt;
    }

    /**
     * Replaced at
     * <p>
     * 
     * 
     */
    public ZonedDateTime getReplacedAt() {
        return replacedAt;
    }

    /**
     * Replaced at
     * <p>
     * 
     * 
     */
    public void setReplacedAt(ZonedDateTime replacedAt) {
        this.replacedAt = replacedAt;
    }

    /**
     * The order ID that this order was replaced by
     * <p>
     * 
     * 
     */
    public String getReplacedBy() {
        return replacedBy;
    }

    /**
     * The order ID that this order was replaced by
     * <p>
     * 
     * 
     */
    public void setReplacedBy(String replacedBy) {
        this.replacedBy = replacedBy;
    }

    /**
     * The order ID that this order replaces
     * <p>
     * 
     * 
     */
    public String getReplaces() {
        return replaces;
    }

    /**
     * The order ID that this order replaces
     * <p>
     * 
     * 
     */
    public void setReplaces(String replaces) {
        this.replaces = replaces;
    }

    /**
     * Asset ID
     * <p>
     * 
     * 
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * Asset ID
     * <p>
     * 
     * 
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    /**
     * Asset symbol
     * <p>
     * 
     * 
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Asset symbol
     * <p>
     * 
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Asset class
     * <p>
     * 
     * 
     */
    public String getAssetClass() {
        return assetClass;
    }

    /**
     * Asset class
     * <p>
     * 
     * 
     */
    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    /**
     * Ordered notional amount. If entered, qty will be null. Can take up to 9 decimal points.
     * <p>
     * 
     * 
     */
    public String getNotional() {
        return notional;
    }

    /**
     * Ordered notional amount. If entered, qty will be null. Can take up to 9 decimal points.
     * <p>
     * 
     * 
     */
    public void setNotional(String notional) {
        this.notional = notional;
    }

    /**
     * Ordered quantity. If entered, notional will be null. Can take up to 9 decimal points.
     * <p>
     * 
     * 
     */
    public String getQty() {
        return qty;
    }

    /**
     * Ordered quantity. If entered, notional will be null. Can take up to 9 decimal points.
     * <p>
     * 
     * 
     */
    public void setQty(String qty) {
        this.qty = qty;
    }

    /**
     * Filled quantity
     * <p>
     * 
     * 
     */
    public String getFilledQty() {
        return filledQty;
    }

    /**
     * Filled quantity
     * <p>
     * 
     * 
     */
    public void setFilledQty(String filledQty) {
        this.filledQty = filledQty;
    }

    /**
     * Filled average price
     * <p>
     * 
     * 
     */
    public String getFilledAvgPrice() {
        return filledAvgPrice;
    }

    /**
     * Filled average price
     * <p>
     * 
     * 
     */
    public void setFilledAvgPrice(String filledAvgPrice) {
        this.filledAvgPrice = filledAvgPrice;
    }

    /**
     * simple, bracket, oco or oto. For details of non-simple order classes, please see Bracket Order Overview
     * <p>
     * 
     * 
     */
    public String getOrderClass() {
        return orderClass;
    }

    /**
     * simple, bracket, oco or oto. For details of non-simple order classes, please see Bracket Order Overview
     * <p>
     * 
     * 
     */
    public void setOrderClass(String orderClass) {
        this.orderClass = orderClass;
    }

    /**
     * (Deprecated with just type field below.)
     * <p>
     * 
     * 
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * (Deprecated with just type field below.)
     * <p>
     * 
     * 
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * Valid values: market, limit, stop, stop_limit, trailing_stop
     * <p>
     * 
     * 
     */
    public String getType() {
        return type;
    }

    /**
     * Valid values: market, limit, stop, stop_limit, trailing_stop
     * <p>
     * 
     * 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Valid values: buy, sell
     * <p>
     * 
     * 
     */
    public String getSide() {
        return side;
    }

    /**
     * Valid values: buy, sell
     * <p>
     * 
     * 
     */
    public void setSide(String side) {
        this.side = side;
    }

    /**
     * See Orders page
     * <p>
     * 
     * 
     */
    public String getTimeInForce() {
        return timeInForce;
    }

    /**
     * See Orders page
     * <p>
     * 
     * 
     */
    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    /**
     * Limit price
     * <p>
     * 
     * 
     */
    public String getLimitPrice() {
        return limitPrice;
    }

    /**
     * Limit price
     * <p>
     * 
     * 
     */
    public void setLimitPrice(String limitPrice) {
        this.limitPrice = limitPrice;
    }

    /**
     * Stop price
     * <p>
     * 
     * 
     */
    public String getStopPrice() {
        return stopPrice;
    }

    /**
     * Stop price
     * <p>
     * 
     * 
     */
    public void setStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
    }

    /**
     * See Orders page
     * <p>
     * 
     * 
     */
    public String getStatus() {
        return status;
    }

    /**
     * See Orders page
     * <p>
     * 
     * 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * If true, eligible for execution outside regular trading hours.
     * <p>
     * 
     * 
     */
    public Boolean getExtendedHours() {
        return extendedHours;
    }

    /**
     * If true, eligible for execution outside regular trading hours.
     * <p>
     * 
     * 
     */
    public void setExtendedHours(Boolean extendedHours) {
        this.extendedHours = extendedHours;
    }

    /**
     * When querying non-simple order_class orders in a nested style, an array of Order entities associated with this order. Otherwise, null.
     * <p>
     * 
     * 
     */
    public ArrayList<Order> getLegs() {
        return legs;
    }

    /**
     * When querying non-simple order_class orders in a nested style, an array of Order entities associated with this order. Otherwise, null.
     * <p>
     * 
     * 
     */
    public void setLegs(ArrayList<Order> legs) {
        this.legs = legs;
    }

    /**
     * The percent value away from the high water mark for trailing stop orders.
     * <p>
     * 
     * 
     */
    public String getTrailPercent() {
        return trailPercent;
    }

    /**
     * The percent value away from the high water mark for trailing stop orders.
     * <p>
     * 
     * 
     */
    public void setTrailPercent(String trailPercent) {
        this.trailPercent = trailPercent;
    }

    /**
     * The dollar value away from the high water mark for trailing stop orders.
     * <p>
     * 
     * 
     */
    public String getTrailPrice() {
        return trailPrice;
    }

    /**
     * The dollar value away from the high water mark for trailing stop orders.
     * <p>
     * 
     * 
     */
    public void setTrailPrice(String trailPrice) {
        this.trailPrice = trailPrice;
    }

    /**
     * High Water Mark - The highest (lowest) market price seen since the trailing stop order was submitted.
     * <p>
     * 
     * 
     */
    public String getHwm() {
        return hwm;
    }

    /**
     * High Water Mark - The highest (lowest) market price seen since the trailing stop order was submitted.
     * <p>
     * 
     * 
     */
    public void setHwm(String hwm) {
        this.hwm = hwm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Order.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("clientOrderId");
        sb.append('=');
        sb.append(((this.clientOrderId == null)?"<null>":this.clientOrderId));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("submittedAt");
        sb.append('=');
        sb.append(((this.submittedAt == null)?"<null>":this.submittedAt));
        sb.append(',');
        sb.append("filledAt");
        sb.append('=');
        sb.append(((this.filledAt == null)?"<null>":this.filledAt));
        sb.append(',');
        sb.append("expiredAt");
        sb.append('=');
        sb.append(((this.expiredAt == null)?"<null>":this.expiredAt));
        sb.append(',');
        sb.append("canceledAt");
        sb.append('=');
        sb.append(((this.canceledAt == null)?"<null>":this.canceledAt));
        sb.append(',');
        sb.append("failedAt");
        sb.append('=');
        sb.append(((this.failedAt == null)?"<null>":this.failedAt));
        sb.append(',');
        sb.append("replacedAt");
        sb.append('=');
        sb.append(((this.replacedAt == null)?"<null>":this.replacedAt));
        sb.append(',');
        sb.append("replacedBy");
        sb.append('=');
        sb.append(((this.replacedBy == null)?"<null>":this.replacedBy));
        sb.append(',');
        sb.append("replaces");
        sb.append('=');
        sb.append(((this.replaces == null)?"<null>":this.replaces));
        sb.append(',');
        sb.append("assetId");
        sb.append('=');
        sb.append(((this.assetId == null)?"<null>":this.assetId));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("assetClass");
        sb.append('=');
        sb.append(((this.assetClass == null)?"<null>":this.assetClass));
        sb.append(',');
        sb.append("notional");
        sb.append('=');
        sb.append(((this.notional == null)?"<null>":this.notional));
        sb.append(',');
        sb.append("qty");
        sb.append('=');
        sb.append(((this.qty == null)?"<null>":this.qty));
        sb.append(',');
        sb.append("filledQty");
        sb.append('=');
        sb.append(((this.filledQty == null)?"<null>":this.filledQty));
        sb.append(',');
        sb.append("filledAvgPrice");
        sb.append('=');
        sb.append(((this.filledAvgPrice == null)?"<null>":this.filledAvgPrice));
        sb.append(',');
        sb.append("orderClass");
        sb.append('=');
        sb.append(((this.orderClass == null)?"<null>":this.orderClass));
        sb.append(',');
        sb.append("orderType");
        sb.append('=');
        sb.append(((this.orderType == null)?"<null>":this.orderType));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("side");
        sb.append('=');
        sb.append(((this.side == null)?"<null>":this.side));
        sb.append(',');
        sb.append("timeInForce");
        sb.append('=');
        sb.append(((this.timeInForce == null)?"<null>":this.timeInForce));
        sb.append(',');
        sb.append("limitPrice");
        sb.append('=');
        sb.append(((this.limitPrice == null)?"<null>":this.limitPrice));
        sb.append(',');
        sb.append("stopPrice");
        sb.append('=');
        sb.append(((this.stopPrice == null)?"<null>":this.stopPrice));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("extendedHours");
        sb.append('=');
        sb.append(((this.extendedHours == null)?"<null>":this.extendedHours));
        sb.append(',');
        sb.append("legs");
        sb.append('=');
        sb.append(((this.legs == null)?"<null>":this.legs));
        sb.append(',');
        sb.append("trailPercent");
        sb.append('=');
        sb.append(((this.trailPercent == null)?"<null>":this.trailPercent));
        sb.append(',');
        sb.append("trailPrice");
        sb.append('=');
        sb.append(((this.trailPrice == null)?"<null>":this.trailPrice));
        sb.append(',');
        sb.append("hwm");
        sb.append('=');
        sb.append(((this.hwm == null)?"<null>":this.hwm));
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
        result = ((result* 31)+((this.orderType == null)? 0 :this.orderType.hashCode()));
        result = ((result* 31)+((this.replacedAt == null)? 0 :this.replacedAt.hashCode()));
        result = ((result* 31)+((this.notional == null)? 0 :this.notional.hashCode()));
        result = ((result* 31)+((this.extendedHours == null)? 0 :this.extendedHours.hashCode()));
        result = ((result* 31)+((this.trailPrice == null)? 0 :this.trailPrice.hashCode()));
        result = ((result* 31)+((this.assetClass == null)? 0 :this.assetClass.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.orderClass == null)? 0 :this.orderClass.hashCode()));
        result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
        result = ((result* 31)+((this.expiredAt == null)? 0 :this.expiredAt.hashCode()));
        result = ((result* 31)+((this.failedAt == null)? 0 :this.failedAt.hashCode()));
        result = ((result* 31)+((this.assetId == null)? 0 :this.assetId.hashCode()));
        result = ((result* 31)+((this.legs == null)? 0 :this.legs.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.submittedAt == null)? 0 :this.submittedAt.hashCode()));
        result = ((result* 31)+((this.timeInForce == null)? 0 :this.timeInForce.hashCode()));
        result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
        result = ((result* 31)+((this.side == null)? 0 :this.side.hashCode()));
        result = ((result* 31)+((this.limitPrice == null)? 0 :this.limitPrice.hashCode()));
        result = ((result* 31)+((this.trailPercent == null)? 0 :this.trailPercent.hashCode()));
        result = ((result* 31)+((this.replacedBy == null)? 0 :this.replacedBy.hashCode()));
        result = ((result* 31)+((this.replaces == null)? 0 :this.replaces.hashCode()));
        result = ((result* 31)+((this.clientOrderId == null)? 0 :this.clientOrderId.hashCode()));
        result = ((result* 31)+((this.filledAt == null)? 0 :this.filledAt.hashCode()));
        result = ((result* 31)+((this.filledAvgPrice == null)? 0 :this.filledAvgPrice.hashCode()));
        result = ((result* 31)+((this.stopPrice == null)? 0 :this.stopPrice.hashCode()));
        result = ((result* 31)+((this.canceledAt == null)? 0 :this.canceledAt.hashCode()));
        result = ((result* 31)+((this.qty == null)? 0 :this.qty.hashCode()));
        result = ((result* 31)+((this.filledQty == null)? 0 :this.filledQty.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        result = ((result* 31)+((this.hwm == null)? 0 :this.hwm.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Order) == false) {
            return false;
        }
        Order rhs = ((Order) other);
        return (((((((((((((((((((((((((((((((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.orderType == rhs.orderType)||((this.orderType!= null)&&this.orderType.equals(rhs.orderType))))&&((this.replacedAt == rhs.replacedAt)||((this.replacedAt!= null)&&this.replacedAt.equals(rhs.replacedAt))))&&((this.notional == rhs.notional)||((this.notional!= null)&&this.notional.equals(rhs.notional))))&&((this.extendedHours == rhs.extendedHours)||((this.extendedHours!= null)&&this.extendedHours.equals(rhs.extendedHours))))&&((this.trailPrice == rhs.trailPrice)||((this.trailPrice!= null)&&this.trailPrice.equals(rhs.trailPrice))))&&((this.assetClass == rhs.assetClass)||((this.assetClass!= null)&&this.assetClass.equals(rhs.assetClass))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.orderClass == rhs.orderClass)||((this.orderClass!= null)&&this.orderClass.equals(rhs.orderClass))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.expiredAt == rhs.expiredAt)||((this.expiredAt!= null)&&this.expiredAt.equals(rhs.expiredAt))))&&((this.failedAt == rhs.failedAt)||((this.failedAt!= null)&&this.failedAt.equals(rhs.failedAt))))&&((this.assetId == rhs.assetId)||((this.assetId!= null)&&this.assetId.equals(rhs.assetId))))&&((this.legs == rhs.legs)||((this.legs!= null)&&this.legs.equals(rhs.legs))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.submittedAt == rhs.submittedAt)||((this.submittedAt!= null)&&this.submittedAt.equals(rhs.submittedAt))))&&((this.timeInForce == rhs.timeInForce)||((this.timeInForce!= null)&&this.timeInForce.equals(rhs.timeInForce))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))))&&((this.side == rhs.side)||((this.side!= null)&&this.side.equals(rhs.side))))&&((this.limitPrice == rhs.limitPrice)||((this.limitPrice!= null)&&this.limitPrice.equals(rhs.limitPrice))))&&((this.trailPercent == rhs.trailPercent)||((this.trailPercent!= null)&&this.trailPercent.equals(rhs.trailPercent))))&&((this.replacedBy == rhs.replacedBy)||((this.replacedBy!= null)&&this.replacedBy.equals(rhs.replacedBy))))&&((this.replaces == rhs.replaces)||((this.replaces!= null)&&this.replaces.equals(rhs.replaces))))&&((this.clientOrderId == rhs.clientOrderId)||((this.clientOrderId!= null)&&this.clientOrderId.equals(rhs.clientOrderId))))&&((this.filledAt == rhs.filledAt)||((this.filledAt!= null)&&this.filledAt.equals(rhs.filledAt))))&&((this.filledAvgPrice == rhs.filledAvgPrice)||((this.filledAvgPrice!= null)&&this.filledAvgPrice.equals(rhs.filledAvgPrice))))&&((this.stopPrice == rhs.stopPrice)||((this.stopPrice!= null)&&this.stopPrice.equals(rhs.stopPrice))))&&((this.canceledAt == rhs.canceledAt)||((this.canceledAt!= null)&&this.canceledAt.equals(rhs.canceledAt))))&&((this.qty == rhs.qty)||((this.qty!= null)&&this.qty.equals(rhs.qty))))&&((this.filledQty == rhs.filledQty)||((this.filledQty!= null)&&this.filledQty.equals(rhs.filledQty))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))))&&((this.hwm == rhs.hwm)||((this.hwm!= null)&&this.hwm.equals(rhs.hwm))));
    }

}
