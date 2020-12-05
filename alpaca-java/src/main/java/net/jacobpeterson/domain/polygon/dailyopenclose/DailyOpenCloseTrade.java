
package net.jacobpeterson.domain.polygon.dailyopenclose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.ZonedDateTime;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v1_open_close_symbol_date">https://polygon.io/docs/#!/Stocks--Equities/get_v1_open_close_symbol_date</a>
 * <p>
 * 
 * 
 */
public class DailyOpenCloseTrade implements Serializable
{

    /**
     * Condition 1 of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("condition1")
    @Expose
    private Integer condition1;
    /**
     * Condition 2 of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("condition2")
    @Expose
    private Integer condition2;
    /**
     * Condition 3 of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("condition3")
    @Expose
    private Integer condition3;
    /**
     * Condition 4 of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("condition4")
    @Expose
    private Integer condition4;
    /**
     * The exchange this trade happened on
     * <p>
     * 
     * 
     */
    @SerializedName("exchange")
    @Expose
    private Integer exchange;
    /**
     * Price of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("price")
    @Expose
    private Double price;
    /**
     * Size of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("size")
    @Expose
    private Integer size;
    /**
     * Timestamp of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("timestamp")
    @Expose
    private ZonedDateTime timestamp;
    private final static long serialVersionUID = -6548628548406611148L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DailyOpenCloseTrade() {
    }

    /**
     * 
     * @param condition1
     * @param condition2
     * @param size
     * @param price
     * @param exchange
     * @param condition3
     * @param condition4
     * @param timestamp
     */
    public DailyOpenCloseTrade(Integer condition1, Integer condition2, Integer condition3, Integer condition4, Integer exchange, Double price, Integer size, ZonedDateTime timestamp) {
        super();
        this.condition1 = condition1;
        this.condition2 = condition2;
        this.condition3 = condition3;
        this.condition4 = condition4;
        this.exchange = exchange;
        this.price = price;
        this.size = size;
        this.timestamp = timestamp;
    }

    /**
     * Condition 1 of this trade
     * <p>
     * 
     * 
     */
    public Integer getCondition1() {
        return condition1;
    }

    /**
     * Condition 1 of this trade
     * <p>
     * 
     * 
     */
    public void setCondition1(Integer condition1) {
        this.condition1 = condition1;
    }

    /**
     * Condition 2 of this trade
     * <p>
     * 
     * 
     */
    public Integer getCondition2() {
        return condition2;
    }

    /**
     * Condition 2 of this trade
     * <p>
     * 
     * 
     */
    public void setCondition2(Integer condition2) {
        this.condition2 = condition2;
    }

    /**
     * Condition 3 of this trade
     * <p>
     * 
     * 
     */
    public Integer getCondition3() {
        return condition3;
    }

    /**
     * Condition 3 of this trade
     * <p>
     * 
     * 
     */
    public void setCondition3(Integer condition3) {
        this.condition3 = condition3;
    }

    /**
     * Condition 4 of this trade
     * <p>
     * 
     * 
     */
    public Integer getCondition4() {
        return condition4;
    }

    /**
     * Condition 4 of this trade
     * <p>
     * 
     * 
     */
    public void setCondition4(Integer condition4) {
        this.condition4 = condition4;
    }

    /**
     * The exchange this trade happened on
     * <p>
     * 
     * 
     */
    public Integer getExchange() {
        return exchange;
    }

    /**
     * The exchange this trade happened on
     * <p>
     * 
     * 
     */
    public void setExchange(Integer exchange) {
        this.exchange = exchange;
    }

    /**
     * Price of the trade
     * <p>
     * 
     * 
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Price of the trade
     * <p>
     * 
     * 
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Size of the trade
     * <p>
     * 
     * 
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Size of the trade
     * <p>
     * 
     * 
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Timestamp of this trade
     * <p>
     * 
     * 
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp of this trade
     * <p>
     * 
     * 
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DailyOpenCloseTrade.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("condition1");
        sb.append('=');
        sb.append(((this.condition1 == null)?"<null>":this.condition1));
        sb.append(',');
        sb.append("condition2");
        sb.append('=');
        sb.append(((this.condition2 == null)?"<null>":this.condition2));
        sb.append(',');
        sb.append("condition3");
        sb.append('=');
        sb.append(((this.condition3 == null)?"<null>":this.condition3));
        sb.append(',');
        sb.append("condition4");
        sb.append('=');
        sb.append(((this.condition4 == null)?"<null>":this.condition4));
        sb.append(',');
        sb.append("exchange");
        sb.append('=');
        sb.append(((this.exchange == null)?"<null>":this.exchange));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("size");
        sb.append('=');
        sb.append(((this.size == null)?"<null>":this.size));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
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
        result = ((result* 31)+((this.condition1 == null)? 0 :this.condition1 .hashCode()));
        result = ((result* 31)+((this.condition2 == null)? 0 :this.condition2 .hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.exchange == null)? 0 :this.exchange.hashCode()));
        result = ((result* 31)+((this.condition3 == null)? 0 :this.condition3 .hashCode()));
        result = ((result* 31)+((this.condition4 == null)? 0 :this.condition4 .hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DailyOpenCloseTrade) == false) {
            return false;
        }
        DailyOpenCloseTrade rhs = ((DailyOpenCloseTrade) other);
        return (((((((((this.condition1 == rhs.condition1)||((this.condition1 != null)&&this.condition1 .equals(rhs.condition1)))&&((this.condition2 == rhs.condition2)||((this.condition2 != null)&&this.condition2 .equals(rhs.condition2))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.exchange == rhs.exchange)||((this.exchange!= null)&&this.exchange.equals(rhs.exchange))))&&((this.condition3 == rhs.condition3)||((this.condition3 != null)&&this.condition3 .equals(rhs.condition3))))&&((this.condition4 == rhs.condition4)||((this.condition4 != null)&&this.condition4 .equals(rhs.condition4))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
    }

}
