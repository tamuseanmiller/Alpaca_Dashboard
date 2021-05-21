
package net.jacobpeterson.domain.alpaca.marketdata.realtime.quote;

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
public class QuoteMessage
    extends SymbolMessage
    implements Serializable
{

    /**
     * Ask exchange code
     * <p>
     * 

     * Corresponds to the "ax" property.
     * 
     */
    @SerializedName("ax")
    @Expose
    private String askExchangeCode;
    /**
     * Ask price
     * <p>
     * 

     * Corresponds to the "ap" property.
     * 
     */
    @SerializedName("ap")
    @Expose
    private Double askPrice;
    /**
     * Ask size
     * <p>
     * 

     * Corresponds to the "as" property.
     * 
     */
    @SerializedName("as")
    @Expose
    private Integer askSize;
    /**
     * Bid exchange code
     * <p>
     * 

     * Corresponds to the "bx" property.
     * 
     */
    @SerializedName("bx")
    @Expose
    private String bidExchangeCode;
    /**
     * Bid price
     * <p>
     * 

     * Corresponds to the "bp" property.
     * 
     */
    @SerializedName("bp")
    @Expose
    private Double bidPrice;
    /**
     * Bid size
     * <p>
     * 

     * Corresponds to the "bs" property.
     * 
     */
    @SerializedName("bs")
    @Expose
    private Integer bidSize;
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
     * Quote conditions
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
    private final static long serialVersionUID = -4305853875180890768L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public QuoteMessage() {
    }

    /**
     * 
     * @param askPrice
     * @param tape
     * @param bidSize
     * @param askExchangeCode
     * @param conditions
     * @param bidExchangeCode
     * @param askSize
     * @param bidPrice
     * @param timestamp
     */
    public QuoteMessage(String askExchangeCode, Double askPrice, Integer askSize, String bidExchangeCode, Double bidPrice, Integer bidSize, ZonedDateTime timestamp, ArrayList<String> conditions, String tape) {
        super();
        this.askExchangeCode = askExchangeCode;
        this.askPrice = askPrice;
        this.askSize = askSize;
        this.bidExchangeCode = bidExchangeCode;
        this.bidPrice = bidPrice;
        this.bidSize = bidSize;
        this.timestamp = timestamp;
        this.conditions = conditions;
        this.tape = tape;
    }

    /**
     * Ask exchange code
     * <p>
     * 

     * Corresponds to the "ax" property.
     * 
     */
    public String getAskExchangeCode() {
        return askExchangeCode;
    }

    /**
     * Ask exchange code
     * <p>
     * 

     * Corresponds to the "ax" property.
     * 
     */
    public void setAskExchangeCode(String askExchangeCode) {
        this.askExchangeCode = askExchangeCode;
    }

    /**
     * Ask price
     * <p>
     * 

     * Corresponds to the "ap" property.
     * 
     */
    public Double getAskPrice() {
        return askPrice;
    }

    /**
     * Ask price
     * <p>
     * 

     * Corresponds to the "ap" property.
     * 
     */
    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    /**
     * Ask size
     * <p>
     * 

     * Corresponds to the "as" property.
     * 
     */
    public Integer getAskSize() {
        return askSize;
    }

    /**
     * Ask size
     * <p>
     * 

     * Corresponds to the "as" property.
     * 
     */
    public void setAskSize(Integer askSize) {
        this.askSize = askSize;
    }

    /**
     * Bid exchange code
     * <p>
     * 

     * Corresponds to the "bx" property.
     * 
     */
    public String getBidExchangeCode() {
        return bidExchangeCode;
    }

    /**
     * Bid exchange code
     * <p>
     * 

     * Corresponds to the "bx" property.
     * 
     */
    public void setBidExchangeCode(String bidExchangeCode) {
        this.bidExchangeCode = bidExchangeCode;
    }

    /**
     * Bid price
     * <p>
     * 

     * Corresponds to the "bp" property.
     * 
     */
    public Double getBidPrice() {
        return bidPrice;
    }

    /**
     * Bid price
     * <p>
     * 

     * Corresponds to the "bp" property.
     * 
     */
    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    /**
     * Bid size
     * <p>
     * 

     * Corresponds to the "bs" property.
     * 
     */
    public Integer getBidSize() {
        return bidSize;
    }

    /**
     * Bid size
     * <p>
     * 

     * Corresponds to the "bs" property.
     * 
     */
    public void setBidSize(Integer bidSize) {
        this.bidSize = bidSize;
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
     * Quote conditions
     * <p>
     * 

     * Corresponds to the "c" property.
     * 
     */
    public ArrayList<String> getConditions() {
        return conditions;
    }

    /**
     * Quote conditions
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
        sb.append(QuoteMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("askExchangeCode");
        sb.append('=');
        sb.append(((this.askExchangeCode == null)?"<null>":this.askExchangeCode));
        sb.append(',');
        sb.append("askPrice");
        sb.append('=');
        sb.append(((this.askPrice == null)?"<null>":this.askPrice));
        sb.append(',');
        sb.append("askSize");
        sb.append('=');
        sb.append(((this.askSize == null)?"<null>":this.askSize));
        sb.append(',');
        sb.append("bidExchangeCode");
        sb.append('=');
        sb.append(((this.bidExchangeCode == null)?"<null>":this.bidExchangeCode));
        sb.append(',');
        sb.append("bidPrice");
        sb.append('=');
        sb.append(((this.bidPrice == null)?"<null>":this.bidPrice));
        sb.append(',');
        sb.append("bidSize");
        sb.append('=');
        sb.append(((this.bidSize == null)?"<null>":this.bidSize));
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
        result = ((result* 31)+((this.askPrice == null)? 0 :this.askPrice.hashCode()));
        result = ((result* 31)+((this.tape == null)? 0 :this.tape.hashCode()));
        result = ((result* 31)+((this.bidSize == null)? 0 :this.bidSize.hashCode()));
        result = ((result* 31)+((this.askExchangeCode == null)? 0 :this.askExchangeCode.hashCode()));
        result = ((result* 31)+((this.conditions == null)? 0 :this.conditions.hashCode()));
        result = ((result* 31)+((this.bidExchangeCode == null)? 0 :this.bidExchangeCode.hashCode()));
        result = ((result* 31)+((this.askSize == null)? 0 :this.askSize.hashCode()));
        result = ((result* 31)+((this.bidPrice == null)? 0 :this.bidPrice.hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QuoteMessage) == false) {
            return false;
        }
        QuoteMessage rhs = ((QuoteMessage) other);
        return (((((((((super.equals(rhs)&&((this.askPrice == rhs.askPrice)||((this.askPrice!= null)&&this.askPrice.equals(rhs.askPrice))))&&((this.tape == rhs.tape)||((this.tape!= null)&&this.tape.equals(rhs.tape))))&&((this.bidSize == rhs.bidSize)||((this.bidSize!= null)&&this.bidSize.equals(rhs.bidSize))))&&((this.askExchangeCode == rhs.askExchangeCode)||((this.askExchangeCode!= null)&&this.askExchangeCode.equals(rhs.askExchangeCode))))&&((this.conditions == rhs.conditions)||((this.conditions!= null)&&this.conditions.equals(rhs.conditions))))&&((this.bidExchangeCode == rhs.bidExchangeCode)||((this.bidExchangeCode!= null)&&this.bidExchangeCode.equals(rhs.bidExchangeCode))))&&((this.askSize == rhs.askSize)||((this.askSize!= null)&&this.askSize.equals(rhs.askSize))))&&((this.bidPrice == rhs.bidPrice)||((this.bidPrice!= null)&&this.bidPrice.equals(rhs.bidPrice))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
    }

}
