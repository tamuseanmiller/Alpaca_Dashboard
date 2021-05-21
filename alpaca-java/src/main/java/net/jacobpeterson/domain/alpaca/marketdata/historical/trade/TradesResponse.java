
package net.jacobpeterson.domain.alpaca.marketdata.historical.trade;

import java.io.Serializable;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/historical/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/historical/</a>
 * <p>
 * 
 * 
 */
public class TradesResponse implements Serializable
{

    /**
     * Array of trades.
     * <p>
     * 
     * 
     */
    @SerializedName("trades")
    @Expose
    private ArrayList<Trade> trades;
    /**
     * Symbol that was queried.
     * <p>
     * 
     * 
     */
    @SerializedName("symbol")
    @Expose
    private String symbol;
    /**
     * Token that can be used to query the next page.
     * <p>
     * 
     * 
     */
    @SerializedName("next_page_token")
    @Expose
    private String nextPageToken;
    private final static long serialVersionUID = -122444118148669657L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TradesResponse() {
    }

    /**
     * 
     * @param symbol
     * @param nextPageToken
     * @param trades
     */
    public TradesResponse(ArrayList<Trade> trades, String symbol, String nextPageToken) {
        super();
        this.trades = trades;
        this.symbol = symbol;
        this.nextPageToken = nextPageToken;
    }

    /**
     * Array of trades.
     * <p>
     * 
     * 
     */
    public ArrayList<Trade> getTrades() {
        return trades;
    }

    /**
     * Array of trades.
     * <p>
     * 
     * 
     */
    public void setTrades(ArrayList<Trade> trades) {
        this.trades = trades;
    }

    /**
     * Symbol that was queried.
     * <p>
     * 
     * 
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Symbol that was queried.
     * <p>
     * 
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Token that can be used to query the next page.
     * <p>
     * 
     * 
     */
    public String getNextPageToken() {
        return nextPageToken;
    }

    /**
     * Token that can be used to query the next page.
     * <p>
     * 
     * 
     */
    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TradesResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("trades");
        sb.append('=');
        sb.append(((this.trades == null)?"<null>":this.trades));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("nextPageToken");
        sb.append('=');
        sb.append(((this.nextPageToken == null)?"<null>":this.nextPageToken));
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
        result = ((result* 31)+((this.trades == null)? 0 :this.trades.hashCode()));
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.nextPageToken == null)? 0 :this.nextPageToken.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TradesResponse) == false) {
            return false;
        }
        TradesResponse rhs = ((TradesResponse) other);
        return ((((this.trades == rhs.trades)||((this.trades!= null)&&this.trades.equals(rhs.trades)))&&((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol))))&&((this.nextPageToken == rhs.nextPageToken)||((this.nextPageToken!= null)&&this.nextPageToken.equals(rhs.nextPageToken))));
    }

}
