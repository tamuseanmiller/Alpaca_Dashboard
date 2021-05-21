
package net.jacobpeterson.domain.alpaca.marketdata.streaming;

import net.jacobpeterson.abstracts.websocket.message.StreamMessage;

import java.io.Serializable;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/</a>
 * <p>
 * 
 * 
 */
public class MarketDataStreamMessage implements Serializable, StreamMessage
{

    private final static long serialVersionUID = -9023241388676993260L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MarketDataStreamMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MarketDataStreamMessage) == false) {
            return false;
        }
        MarketDataStreamMessage rhs = ((MarketDataStreamMessage) other);
        return true;
    }

}
