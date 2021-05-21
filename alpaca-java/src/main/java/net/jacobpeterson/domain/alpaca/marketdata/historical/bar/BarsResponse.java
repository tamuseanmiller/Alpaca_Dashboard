
package net.jacobpeterson.domain.alpaca.marketdata.historical.bar;

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
public class BarsResponse implements Serializable
{

    /**
     * Array of bars.
     * <p>
     * 
     * 
     */
    @SerializedName("bars")
    @Expose
    private ArrayList<Bar> bars;
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
    private final static long serialVersionUID = 3601630741228620445L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BarsResponse() {
    }

    /**
     * 
     * @param symbol
     * @param nextPageToken
     * @param bars
     */
    public BarsResponse(ArrayList<Bar> bars, String symbol, String nextPageToken) {
        super();
        this.bars = bars;
        this.symbol = symbol;
        this.nextPageToken = nextPageToken;
    }

    /**
     * Array of bars.
     * <p>
     * 
     * 
     */
    public ArrayList<Bar> getBars() {
        return bars;
    }

    /**
     * Array of bars.
     * <p>
     * 
     * 
     */
    public void setBars(ArrayList<Bar> bars) {
        this.bars = bars;
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
        sb.append(BarsResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bars");
        sb.append('=');
        sb.append(((this.bars == null)?"<null>":this.bars));
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
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.bars == null)? 0 :this.bars.hashCode()));
        result = ((result* 31)+((this.nextPageToken == null)? 0 :this.nextPageToken.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BarsResponse) == false) {
            return false;
        }
        BarsResponse rhs = ((BarsResponse) other);
        return ((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.bars == rhs.bars)||((this.bars!= null)&&this.bars.equals(rhs.bars))))&&((this.nextPageToken == rhs.nextPageToken)||((this.nextPageToken!= null)&&this.nextPageToken.equals(rhs.nextPageToken))));
    }

}
