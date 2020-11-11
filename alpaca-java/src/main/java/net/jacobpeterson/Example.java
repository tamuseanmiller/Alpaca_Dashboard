package net.jacobpeterson;

import net.jacobpeterson.abstracts.websocket.exception.WebsocketException;
import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.BarsTimeFrame;
import net.jacobpeterson.alpaca.enums.OrderSide;
import net.jacobpeterson.alpaca.enums.OrderTimeInForce;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.alpaca.websocket.broker.listener.AlpacaStreamListenerAdapter;
import net.jacobpeterson.alpaca.websocket.broker.message.AlpacaStreamMessageType;
import net.jacobpeterson.alpaca.websocket.marketdata.listener.MarketDataStreamListenerAdapter;
import net.jacobpeterson.alpaca.websocket.marketdata.message.MarketDataStreamMessageType;
import net.jacobpeterson.domain.alpaca.account.Account;
import net.jacobpeterson.domain.alpaca.marketdata.Bar;
import net.jacobpeterson.domain.alpaca.marketdata.streaming.MarketDataStreamMessage;
import net.jacobpeterson.domain.alpaca.marketdata.streaming.aggregate.AggregateMinuteMessage;
import net.jacobpeterson.domain.alpaca.marketdata.streaming.quote.QuoteMessage;
import net.jacobpeterson.domain.alpaca.marketdata.streaming.trade.TradeMessage;
import net.jacobpeterson.domain.alpaca.order.Order;
import net.jacobpeterson.domain.alpaca.streaming.AlpacaStreamMessage;
import net.jacobpeterson.domain.alpaca.streaming.account.AccountUpdateMessage;
import net.jacobpeterson.domain.alpaca.streaming.trade.TradeUpdateMessage;
import net.jacobpeterson.domain.alpaca.watchlist.Watchlist;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Example {

    public static void main(String args[]) {
        // This logs into Alpaca using the alpaca.properties file on the classpath.
        AlpacaAPI alpacaAPI = new AlpacaAPI("e42b7e0f-930e-405b-a060-e7869e061fe1");

// Register explicitly for ACCOUNT_UPDATES and ORDER_UPDATES Messages via stream listener
        try {
            alpacaAPI.addAlpacaStreamListener(new AlpacaStreamListenerAdapter(
                    AlpacaStreamMessageType.ACCOUNT_UPDATES,
                    AlpacaStreamMessageType.TRADE_UPDATES) {
                @Override
                public void onStreamUpdate(AlpacaStreamMessageType streamMessageType,
                        AlpacaStreamMessage streamMessage) {
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
            });
        } catch (WebsocketException e) {
            e.printStackTrace();
        }

// Add an Alpaca Market data stream listener to listen to "T.AAPL", "Q.AAPL", and "AM.AAPL" messages
        try {
            alpacaAPI.addMarketDataStreamListener(
                    new MarketDataStreamListenerAdapter("AAPL", MarketDataStreamMessageType.values()) {
                        @Override
                        public void onStreamUpdate(MarketDataStreamMessageType streamMessageType,
                                MarketDataStreamMessage streamMessage) {
                            switch (streamMessageType) {
                                case QUOTES:
                                    QuoteMessage quoteMessage = (QuoteMessage) streamMessage;
                                    System.out.println("\nQuote Update: \n\t" +
                                            quoteMessage.toString().replace(",", ",\n\t"));
                                    break;
                                case TRADES:
                                    TradeMessage tradeMessage = (TradeMessage) streamMessage;
                                    System.out.println("\nTrade Update: \n\t" +
                                            tradeMessage.toString().replace(",", ",\n\t"));
                                    break;
                                case AGGREGATE_MINUTE:
                                    AggregateMinuteMessage aggregateMinuteMessage =
                                            (AggregateMinuteMessage) streamMessage;
                                    System.out.println("\nAggregate Minute Update: \n\t" +
                                            aggregateMinuteMessage.toString().replace(",", ",\n\t"));
                                    break;
                            }
                        }
                    });
        } catch (WebsocketException e) {
            e.printStackTrace();
        }

// Get Account Information
        try {
            Account alpacaAccount = alpacaAPI.getAccount();

            System.out.println("\n\nAccount Information:");
            System.out.println("\t" + alpacaAccount.toString().replace(",", ",\n\t"));
        } catch (AlpacaAPIRequestException e) {
            e.printStackTrace();
        }

// Create watchlist
        try {
            Watchlist dayTradeWatchlist = alpacaAPI.createWatchlist("Day Trade", "AAPL");

            System.out.println("\n\nDay Trade Watchlist:");
            System.out.println("\t" + dayTradeWatchlist.toString().replace(",", ",\n\t"));
        } catch (AlpacaAPIRequestException e) {
            e.printStackTrace();
        }

// Get bars
        try {
            ZonedDateTime start = ZonedDateTime.of(2019, 11, 18, 0, 0, 0, 0, ZoneId.of("America/New_York"));
            ZonedDateTime end = ZonedDateTime.of(2019, 11, 22, 23, 59, 0, 0, ZoneId.of("America/New_York"));

            Map<String, ArrayList<Bar>> bars = alpacaAPI.getBars(BarsTimeFrame.ONE_DAY, "AAPL", null, start, end,
                    null, null);

            System.out.println("\n\nBars response:");
            for (Bar bar : bars.get("AAPL")) {
                System.out.println("\t==========");
                System.out.println("\tUnix Time " + ZonedDateTime.ofInstant(Instant.ofEpochSecond(bar.getT()),
                        ZoneOffset.UTC));
                System.out.println("\tOpen: $" + bar.getO());
                System.out.println("\tHigh: $" + bar.getH());
                System.out.println("\tLow: $" + bar.getL());
                System.out.println("\tClose: $" + bar.getC());
                System.out.println("\tVolume: " + bar.getV());
            }
        } catch (AlpacaAPIRequestException e) {
            e.printStackTrace();
        }
    }
}
