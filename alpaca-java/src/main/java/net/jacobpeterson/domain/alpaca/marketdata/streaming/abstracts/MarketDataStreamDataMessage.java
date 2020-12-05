
package net.jacobpeterson.domain.alpaca.marketdata.streaming.abstracts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.alpaca.websocket.marketdata.message.MarketDataStreamMessageType;
import net.jacobpeterson.domain.alpaca.marketdata.streaming.MarketDataStreamMessage;

import java.io.Serializable;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/</a>
 * <p>
 * 
 * 
 */
public class MarketDataStreamDataMessage
    extends MarketDataStreamMessage
    implements Serializable
{

    /**
     * The event type
     * <p>
     * 
     * 
     */
    @SerializedName("ev")
    @Expose
    private MarketDataStreamMessageType ev;
    /**
     * The ticker
     * <p>
     * 

     * Corresponds to the "T" property.
     * 
     */
    @SerializedName("T")
    @Expose
    private String ticker;
    private final static long serialVersionUID = 3813626603377022706L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MarketDataStreamDataMessage() {
    }

    /**
     * 
     * @param ev
     * @param ticker
     */
    public MarketDataStreamDataMessage(MarketDataStreamMessageType ev, String ticker) {
        super();
        this.ev = ev;
        this.ticker = ticker;
    }

    /**
     * The event type
     * <p>
     * 
     * 
     */
    public MarketDataStreamMessageType getEv() {
        return ev;
    }

    /**
     * The event type
     * <p>
     * 
     * 
     */
    public void setEv(MarketDataStreamMessageType ev) {
        this.ev = ev;
    }

    /**
     * The ticker
     * <p>
     * 

     * Corresponds to the "T" property.
     * 
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * The ticker
     * <p>
     * 

     * Corresponds to the "T" property.
     * 
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MarketDataStreamDataMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("ev");
        sb.append('=');
        sb.append(((this.ev == null)?"<null>":this.ev));
        sb.append(',');
        sb.append("ticker");
        sb.append('=');
        sb.append(((this.ticker == null)?"<null>":this.ticker));
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
        result = ((result* 31)+((this.ev == null)? 0 :this.ev.hashCode()));
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MarketDataStreamDataMessage) == false) {
            return false;
        }
        MarketDataStreamDataMessage rhs = ((MarketDataStreamDataMessage) other);
        return ((super.equals(rhs)&&((this.ev == rhs.ev)||((this.ev!= null)&&this.ev.equals(rhs.ev))))&&((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker))));
    }

}
