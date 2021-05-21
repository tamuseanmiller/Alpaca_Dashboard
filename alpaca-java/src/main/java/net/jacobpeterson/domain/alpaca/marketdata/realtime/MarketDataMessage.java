
package net.jacobpeterson.domain.alpaca.marketdata.realtime;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.abstracts.websocket.message.StreamMessage;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/real-time/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/real-time/</a>
 * <p>
 * 
 * 
 */
public class MarketDataMessage implements Serializable, StreamMessage
{

    /**
     * The message type.
     * <p>
     * 
     * Corresponds to the "T" property.
     * 
     */
    @SerializedName("T")
    @Expose
    private String messageType;
    private final static long serialVersionUID = 8669363319550995858L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MarketDataMessage() {
    }

    /**
     * 
     * @param messageType
     */
    public MarketDataMessage(String messageType) {
        super();
        this.messageType = messageType;
    }

    /**
     * The message type.
     * <p>
     * 
     * Corresponds to the "T" property.
     * 
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * The message type.
     * <p>
     * 
     * Corresponds to the "T" property.
     * 
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MarketDataMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("messageType");
        sb.append('=');
        sb.append(((this.messageType == null)?"<null>":this.messageType));
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
        result = ((result* 31)+((this.messageType == null)? 0 :this.messageType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MarketDataMessage) == false) {
            return false;
        }
        MarketDataMessage rhs = ((MarketDataMessage) other);
        return ((this.messageType == rhs.messageType)||((this.messageType!= null)&&this.messageType.equals(rhs.messageType)));
    }

}
