
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
public class MarketDataStreamStatusMessage
    extends MarketDataStreamMessage
    implements Serializable
{

    /**
     * The stream type
     * <p>
     * 
     * 
     */
    @SerializedName("stream")
    @Expose
    private MarketDataStreamMessageType stream;
    private final static long serialVersionUID = 5522953617780461458L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MarketDataStreamStatusMessage() {
    }

    /**
     * 
     * @param stream
     */
    public MarketDataStreamStatusMessage(MarketDataStreamMessageType stream) {
        super();
        this.stream = stream;
    }

    /**
     * The stream type
     * <p>
     * 
     * 
     */
    public MarketDataStreamMessageType getStream() {
        return stream;
    }

    /**
     * The stream type
     * <p>
     * 
     * 
     */
    public void setStream(MarketDataStreamMessageType stream) {
        this.stream = stream;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MarketDataStreamStatusMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("stream");
        sb.append('=');
        sb.append(((this.stream == null)?"<null>":this.stream));
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
        result = ((result* 31)+((this.stream == null)? 0 :this.stream.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MarketDataStreamStatusMessage) == false) {
            return false;
        }
        MarketDataStreamStatusMessage rhs = ((MarketDataStreamStatusMessage) other);
        return (super.equals(rhs)&&((this.stream == rhs.stream)||((this.stream!= null)&&this.stream.equals(rhs.stream))));
    }

}
