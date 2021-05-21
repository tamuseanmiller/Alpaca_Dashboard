
package net.jacobpeterson.domain.alpaca.marketdata.historical.trade;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/historical/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/historical/</a>
 * <p>
 * 
 * 
 */
public class LatestTradeResponse implements Serializable
{

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
     * The latest trade.
     * <p>
     * 
     * 
     */
    @SerializedName("trade")
    @Expose
    private Trade trade;
    private final static long serialVersionUID = 3742217765416054017L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LatestTradeResponse() {
    }

    /**
     * 
     * @param symbol
     * @param trade
     */
    public LatestTradeResponse(String symbol, Trade trade) {
        super();
        this.symbol = symbol;
        this.trade = trade;
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
     * The latest trade.
     * <p>
     * 
     * 
     */
    public Trade getTrade() {
        return trade;
    }

    /**
     * The latest trade.
     * <p>
     * 
     * 
     */
    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LatestTradeResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("trade");
        sb.append('=');
        sb.append(((this.trade == null)?"<null>":this.trade));
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
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.trade == null)? 0 :this.trade.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LatestTradeResponse) == false) {
            return false;
        }
        LatestTradeResponse rhs = ((LatestTradeResponse) other);
        return (((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.trade == rhs.trade)||((this.trade!= null)&&this.trade.equals(rhs.trade))));
    }

}
