
package net.jacobpeterson.domain.alpaca.marketdata.realtime.trade;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.alpaca.marketdata.realtime.SymbolMessage;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/</a>
 * <p>
 * 
 * 
 */
public class TradeMessage
    extends SymbolMessage
    implements Serializable
{

    /**
     * Trade ID
     * <p>
     * 

     * Corresponds to the "i" property.
     * 
     */
    @SerializedName("i")
    @Expose
    private Integer tradeID;
    /**
     * Exchange code where the trade occurred
     * <p>
     * 

     * Corresponds to the "x" property.
     * 
     */
    @SerializedName("x")
    @Expose
    private String exchange;
    /**
     * Trade price
     * <p>
     * 

     * Corresponds to the "p" property.
     * 
     */
    @SerializedName("p")
    @Expose
    private Double price;
    /**
     * Trade size
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    @SerializedName("s")
    @Expose
    private Integer size;
    /**
     * Timestamp with nanosecond precision
     * <p>
     * 

     * Corresponds to the "t" property.
     * 
     */
    @SerializedName("t")
    @Expose
    private ZonedDateTime timestamp;
    /**
     * Trade conditions
     * <p>
     * 

     * Corresponds to the "c" property.
     * 
     */
    @SerializedName("c")
    @Expose
    private ArrayList<String> conditions;
    /**
     * Tape
     * <p>
     * 

     * Corresponds to the "z" property.
     * 
     */
    @SerializedName("z")
    @Expose
    private String tape;
    private final static long serialVersionUID = 5251255196067955080L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TradeMessage() {
    }

    /**
     * 
     * @param size
     * @param tape
     * @param price
     * @param exchange
     * @param conditions
     * @param tradeID
     * @param timestamp
     */
    public TradeMessage(Integer tradeID, String exchange, Double price, Integer size, ZonedDateTime timestamp, ArrayList<String> conditions, String tape) {
        super();
        this.tradeID = tradeID;
        this.exchange = exchange;
        this.price = price;
        this.size = size;
        this.timestamp = timestamp;
        this.conditions = conditions;
        this.tape = tape;
    }

    /**
     * Trade ID
     * <p>
     * 

     * Corresponds to the "i" property.
     * 
     */
    public Integer getTradeID() {
        return tradeID;
    }

    /**
     * Trade ID
     * <p>
     * 

     * Corresponds to the "i" property.
     * 
     */
    public void setTradeID(Integer tradeID) {
        this.tradeID = tradeID;
    }

    /**
     * Exchange code where the trade occurred
     * <p>
     * 

     * Corresponds to the "x" property.
     * 
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * Exchange code where the trade occurred
     * <p>
     * 

     * Corresponds to the "x" property.
     * 
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    /**
     * Trade price
     * <p>
     * 

     * Corresponds to the "p" property.
     * 
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Trade price
     * <p>
     * 

     * Corresponds to the "p" property.
     * 
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Trade size
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Trade size
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Timestamp with nanosecond precision
     * <p>
     * 

     * Corresponds to the "t" property.
     * 
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp with nanosecond precision
     * <p>
     * 

     * Corresponds to the "t" property.
     * 
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Trade conditions
     * <p>
     * 

     * Corresponds to the "c" property.
     * 
     */
    public ArrayList<String> getConditions() {
        return conditions;
    }

    /**
     * Trade conditions
     * <p>
     * 

     * Corresponds to the "c" property.
     * 
     */
    public void setConditions(ArrayList<String> conditions) {
        this.conditions = conditions;
    }

    /**
     * Tape
     * <p>
     * 

     * Corresponds to the "z" property.
     * 
     */
    public String getTape() {
        return tape;
    }

    /**
     * Tape
     * <p>
     * 

     * Corresponds to the "z" property.
     * 
     */
    public void setTape(String tape) {
        this.tape = tape;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TradeMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("tradeID");
        sb.append('=');
        sb.append(((this.tradeID == null)?"<null>":this.tradeID));
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
        sb.append("conditions");
        sb.append('=');
        sb.append(((this.conditions == null)?"<null>":this.conditions));
        sb.append(',');
        sb.append("tape");
        sb.append('=');
        sb.append(((this.tape == null)?"<null>":this.tape));
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
        result = ((result* 31)+((this.tape == null)? 0 :this.tape.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.exchange == null)? 0 :this.exchange.hashCode()));
        result = ((result* 31)+((this.conditions == null)? 0 :this.conditions.hashCode()));
        result = ((result* 31)+((this.tradeID == null)? 0 :this.tradeID.hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TradeMessage) == false) {
            return false;
        }
        TradeMessage rhs = ((TradeMessage) other);
        return (((((((super.equals(rhs)&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.tape == rhs.tape)||((this.tape!= null)&&this.tape.equals(rhs.tape))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.exchange == rhs.exchange)||((this.exchange!= null)&&this.exchange.equals(rhs.exchange))))&&((this.conditions == rhs.conditions)||((this.conditions!= null)&&this.conditions.equals(rhs.conditions))))&&((this.tradeID == rhs.tradeID)||((this.tradeID!= null)&&this.tradeID.equals(rhs.tradeID))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
    }

}
