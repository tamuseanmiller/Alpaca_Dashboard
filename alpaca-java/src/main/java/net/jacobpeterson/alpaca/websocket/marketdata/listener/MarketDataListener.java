package net.jacobpeterson.alpaca.websocket.marketdata.listener;

import net.jacobpeterson.abstracts.websocket.listener.StreamListener;
import net.jacobpeterson.alpaca.websocket.marketdata.message.MarketDataMessageType;
import net.jacobpeterson.domain.alpaca.marketdata.realtime.MarketDataMessage;

import java.util.Map;
import java.util.Set;

/**
 * An asynchronous update interface for receiving websocket messages from
 * {@link net.jacobpeterson.alpaca.websocket.marketdata.client.MarketDataWebsocketClient}
 */
public interface MarketDataListener extends StreamListener<MarketDataMessageType, MarketDataMessage> {

    /**
     * Gets the {@link MarketDataMessageType} of tickers {@link Map} for this {@link MarketDataListener}. Null or empty
     * to listen to all stream messages.
     *
     * @return the {@link MarketDataMessageType} of tickers {@link Map}
     */
    Map<String, Set<MarketDataMessageType>> getDataStreams();
}
