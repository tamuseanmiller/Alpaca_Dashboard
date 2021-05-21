
package net.jacobpeterson.domain.alpaca.streaming;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.abstracts.websocket.message.StreamMessage;
import net.jacobpeterson.alpaca.websocket.broker.message.AlpacaStreamMessageType;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/streaming/">https://docs.alpaca.markets/api-documentation/api-v2/streaming/</a>
 * <p>
 * 
 * 
 */
public class AlpacaStreamMessage implements Serializable, StreamMessage
{

    /**
     * The stream type
     * <p>
     * 
     * 
     */
    @SerializedName("stream")
    @Expose
    private AlpacaStreamMessageType stream;
    private final static long serialVersionUID = -8564719619936132963L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AlpacaStreamMessage() {
    }

    /**
     * 
     * @param stream
     */
    public AlpacaStreamMessage(AlpacaStreamMessageType stream) {
        super();
        this.stream = stream;
    }

    /**
     * The stream type
     * <p>
     * 
     * 
     */
    public AlpacaStreamMessageType getStream() {
        return stream;
    }

    /**
     * The stream type
     * <p>
     * 
     * 
     */
    public void setStream(AlpacaStreamMessageType stream) {
        this.stream = stream;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AlpacaStreamMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AlpacaStreamMessage) == false) {
            return false;
        }
        AlpacaStreamMessage rhs = ((AlpacaStreamMessage) other);
        return ((this.stream == rhs.stream)||((this.stream!= null)&&this.stream.equals(rhs.stream)));
    }

}
