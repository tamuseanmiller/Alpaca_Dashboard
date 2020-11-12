package net.jacobpeterson.alpaca.websocket.listener;

import net.jacobpeterson.alpaca.websocket.message.AlpacaStreamMessageType;
import net.jacobpeterson.domain.alpaca.websocket.AlpacaStreamMessage;
import net.jacobpeterson.domain.alpaca.websocket.account.AccountUpdateMessage;
import net.jacobpeterson.domain.alpaca.websocket.trade.TradeUpdateMessage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class AlpacaStreamListenerAdapter.
 */
public class AlpacaStreamListenerAdapter implements AlpacaStreamListener {

    /** The message types. */
    private final HashSet<AlpacaStreamMessageType> streamUpdateTypes = new HashSet<>();

    /**
     * Instantiates a new Alpaca stream listener adapter.
     *
     * @param streamUpdateTypes the stream update types
     */
    public AlpacaStreamListenerAdapter(AlpacaStreamMessageType... streamUpdateTypes) {
        if (streamUpdateTypes != null && streamUpdateTypes.length > 0) {
            this.streamUpdateTypes.addAll(Arrays.asList(streamUpdateTypes));
        }
    }

    @Override
    public Set<AlpacaStreamMessageType> getStreamMessageTypes() {
        return streamUpdateTypes;
    }

    // @Override
    // public void onStreamUpdate(AlpacaStreamMessageType streamMessageType, AlpacaStreamMessage streamMessage) {
    // }

    public void onStreamUpdate(AlpacaStreamMessageType streamMessageType, AlpacaStreamMessage streamMessage) {
        switch (streamMessageType) {
            case ACCOUNT_UPDATES:
                AccountUpdateMessage accountUpdateMessage = (AccountUpdateMessage) streamMessage;
                System.out.println("\nReceived Account Update: \n\t" +
                        accountUpdateMessage.toString().replace(",", ",\n\t"));
                break;
            case TRADE_UPDATES:
                TradeUpdateMessage tradeUpdateMessage = (TradeUpdateMessage) streamMessage;
                System.out.println("\nReceived Order Update: \n\t" +
                        tradeUpdateMessage.toString().replace(",", ",\n\t"));
                break;
        }
    }
}
