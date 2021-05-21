
package net.jacobpeterson.domain.alpaca.marketdata.historical.quote;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/historical/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/historical/</a>
 * <p>
 * 
 * 
 */
public class LatestQuoteResponse implements Serializable
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
     * The latest quote.
     * <p>
     * 
     * 
     */
    @SerializedName("quote")
    @Expose
    private Quote quote;
    private final static long serialVersionUID = 4859688259481297140L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LatestQuoteResponse() {
    }

    /**
     * 
     * @param symbol
     * @param quote
     */
    public LatestQuoteResponse(String symbol, Quote quote) {
        super();
        this.symbol = symbol;
        this.quote = quote;
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
     * The latest quote.
     * <p>
     * 
     * 
     */
    public Quote getQuote() {
        return quote;
    }

    /**
     * The latest quote.
     * <p>
     * 
     * 
     */
    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LatestQuoteResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("quote");
        sb.append('=');
        sb.append(((this.quote == null)?"<null>":this.quote));
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
        result = ((result* 31)+((this.quote == null)? 0 :this.quote.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LatestQuoteResponse) == false) {
            return false;
        }
        LatestQuoteResponse rhs = ((LatestQuoteResponse) other);
        return (((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.quote == rhs.quote)||((this.quote!= null)&&this.quote.equals(rhs.quote))));
    }

}
