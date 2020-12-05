
package net.jacobpeterson.domain.polygon.lasttrade;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v1_last_stocks_symbol">https://polygon.io/docs/#!/Stocks--Equities/get_v1_last_stocks_symbol</a>
 * <p>
 * 
 * 
 */
public class LastTrade implements Serializable
{

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
     * Size of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("size")
    @Expose
    private Long size;
    /**
     * Exchange this trade happened on
     * <p>
     * 
     * 
     */
    @SerializedName("exchange")
    @Expose
    private Integer exchange;
    /**
     * Condition 1 of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("cond1")
    @Expose
    private Integer cond1;
    /**
     * Condition 2 of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("cond2")
    @Expose
    private Integer cond2;
    /**
     * Condition 3 of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("cond3")
    @Expose
    private Integer cond3;
    /**
     * Condition 4 of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("cond4")
    @Expose
    private Integer cond4;
    /**
     * Timestamp of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;
    private final static long serialVersionUID = -4746876930562048991L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LastTrade() {
    }

    /**
     * 
     * @param size
     * @param cond4
     * @param price
     * @param cond3
     * @param cond2
     * @param cond1
     * @param exchange
     * @param timestamp
     */
    public LastTrade(Double price, Long size, Integer exchange, Integer cond1, Integer cond2, Integer cond3, Integer cond4, Long timestamp) {
        super();
        this.price = price;
        this.size = size;
        this.exchange = exchange;
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.cond3 = cond3;
        this.cond4 = cond4;
        this.timestamp = timestamp;
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
     * Size of this trade
     * <p>
     * 
     * 
     */
    public Long getSize() {
        return size;
    }

    /**
     * Size of this trade
     * <p>
     * 
     * 
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * Exchange this trade happened on
     * <p>
     * 
     * 
     */
    public Integer getExchange() {
        return exchange;
    }

    /**
     * Exchange this trade happened on
     * <p>
     * 
     * 
     */
    public void setExchange(Integer exchange) {
        this.exchange = exchange;
    }

    /**
     * Condition 1 of the trade
     * <p>
     * 
     * 
     */
    public Integer getCond1() {
        return cond1;
    }

    /**
     * Condition 1 of the trade
     * <p>
     * 
     * 
     */
    public void setCond1(Integer cond1) {
        this.cond1 = cond1;
    }

    /**
     * Condition 2 of the trade
     * <p>
     * 
     * 
     */
    public Integer getCond2() {
        return cond2;
    }

    /**
     * Condition 2 of the trade
     * <p>
     * 
     * 
     */
    public void setCond2(Integer cond2) {
        this.cond2 = cond2;
    }

    /**
     * Condition 3 of the trade
     * <p>
     * 
     * 
     */
    public Integer getCond3() {
        return cond3;
    }

    /**
     * Condition 3 of the trade
     * <p>
     * 
     * 
     */
    public void setCond3(Integer cond3) {
        this.cond3 = cond3;
    }

    /**
     * Condition 4 of the trade
     * <p>
     * 
     * 
     */
    public Integer getCond4() {
        return cond4;
    }

    /**
     * Condition 4 of the trade
     * <p>
     * 
     * 
     */
    public void setCond4(Integer cond4) {
        this.cond4 = cond4;
    }

    /**
     * Timestamp of this trade
     * <p>
     * 
     * 
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp of this trade
     * <p>
     * 
     * 
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LastTrade.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("size");
        sb.append('=');
        sb.append(((this.size == null)?"<null>":this.size));
        sb.append(',');
        sb.append("exchange");
        sb.append('=');
        sb.append(((this.exchange == null)?"<null>":this.exchange));
        sb.append(',');
        sb.append("cond1");
        sb.append('=');
        sb.append(((this.cond1 == null)?"<null>":this.cond1));
        sb.append(',');
        sb.append("cond2");
        sb.append('=');
        sb.append(((this.cond2 == null)?"<null>":this.cond2));
        sb.append(',');
        sb.append("cond3");
        sb.append('=');
        sb.append(((this.cond3 == null)?"<null>":this.cond3));
        sb.append(',');
        sb.append("cond4");
        sb.append('=');
        sb.append(((this.cond4 == null)?"<null>":this.cond4));
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
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.cond4 == null)? 0 :this.cond4 .hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.cond3 == null)? 0 :this.cond3 .hashCode()));
        result = ((result* 31)+((this.cond2 == null)? 0 :this.cond2 .hashCode()));
        result = ((result* 31)+((this.cond1 == null)? 0 :this.cond1 .hashCode()));
        result = ((result* 31)+((this.exchange == null)? 0 :this.exchange.hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LastTrade) == false) {
            return false;
        }
        LastTrade rhs = ((LastTrade) other);
        return (((((((((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size)))&&((this.cond4 == rhs.cond4)||((this.cond4 != null)&&this.cond4 .equals(rhs.cond4))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.cond3 == rhs.cond3)||((this.cond3 != null)&&this.cond3 .equals(rhs.cond3))))&&((this.cond2 == rhs.cond2)||((this.cond2 != null)&&this.cond2 .equals(rhs.cond2))))&&((this.cond1 == rhs.cond1)||((this.cond1 != null)&&this.cond1 .equals(rhs.cond1))))&&((this.exchange == rhs.exchange)||((this.exchange!= null)&&this.exchange.equals(rhs.exchange))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
    }

}
