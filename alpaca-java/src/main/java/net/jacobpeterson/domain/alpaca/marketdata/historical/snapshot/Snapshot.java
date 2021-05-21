
package net.jacobpeterson.domain.alpaca.marketdata.historical.snapshot;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.alpaca.marketdata.historical.quote.Quote;
import net.jacobpeterson.domain.alpaca.marketdata.historical.trade.Trade;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/historical/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/historical/</a>
 * <p>
 * 
 * 
 */
public class Snapshot implements Serializable
{

    /**
     * Latest trade.
     * <p>
     * 
     * 
     */
    @SerializedName("latestTrade")
    @Expose
    private Trade latestTrade;
    /**
     * Latest quote.
     * <p>
     * 
     * 
     */
    @SerializedName("latestQuote")
    @Expose
    private Quote latestQuote;
    /**
     * Minute bar.
     * <p>
     * 
     * 
     */
    @SerializedName("minuteBar")
    @Expose
    private net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar minuteBar;
    /**
     * Daily bar.
     * <p>
     * 
     * 
     */
    @SerializedName("dailyBar")
    @Expose
    private net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar dailyBar;
    /**
     * Previous daily close bar.
     * <p>
     * 
     * 
     */
    @SerializedName("prevDailyBar")
    @Expose
    private net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar prevDailyBar;
    private final static long serialVersionUID = -8976735933725070789L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Snapshot() {
    }

    /**
     * 
     * @param latestTrade
     * @param dailyBar
     * @param latestQuote
     * @param prevDailyBar
     * @param minuteBar
     */
    public Snapshot(Trade latestTrade, Quote latestQuote, net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar minuteBar, net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar dailyBar, net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar prevDailyBar) {
        super();
        this.latestTrade = latestTrade;
        this.latestQuote = latestQuote;
        this.minuteBar = minuteBar;
        this.dailyBar = dailyBar;
        this.prevDailyBar = prevDailyBar;
    }

    /**
     * Latest trade.
     * <p>
     * 
     * 
     */
    public Trade getLatestTrade() {
        return latestTrade;
    }

    /**
     * Latest trade.
     * <p>
     * 
     * 
     */
    public void setLatestTrade(Trade latestTrade) {
        this.latestTrade = latestTrade;
    }

    /**
     * Latest quote.
     * <p>
     * 
     * 
     */
    public Quote getLatestQuote() {
        return latestQuote;
    }

    /**
     * Latest quote.
     * <p>
     * 
     * 
     */
    public void setLatestQuote(Quote latestQuote) {
        this.latestQuote = latestQuote;
    }

    /**
     * Minute bar.
     * <p>
     * 
     * 
     */
    public net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar getMinuteBar() {
        return minuteBar;
    }

    /**
     * Minute bar.
     * <p>
     * 
     * 
     */
    public void setMinuteBar(net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar minuteBar) {
        this.minuteBar = minuteBar;
    }

    /**
     * Daily bar.
     * <p>
     * 
     * 
     */
    public net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar getDailyBar() {
        return dailyBar;
    }

    /**
     * Daily bar.
     * <p>
     * 
     * 
     */
    public void setDailyBar(net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar dailyBar) {
        this.dailyBar = dailyBar;
    }

    /**
     * Previous daily close bar.
     * <p>
     * 
     * 
     */
    public net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar getPrevDailyBar() {
        return prevDailyBar;
    }

    /**
     * Previous daily close bar.
     * <p>
     * 
     * 
     */
    public void setPrevDailyBar(net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar prevDailyBar) {
        this.prevDailyBar = prevDailyBar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Snapshot.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("latestTrade");
        sb.append('=');
        sb.append(((this.latestTrade == null)?"<null>":this.latestTrade));
        sb.append(',');
        sb.append("latestQuote");
        sb.append('=');
        sb.append(((this.latestQuote == null)?"<null>":this.latestQuote));
        sb.append(',');
        sb.append("minuteBar");
        sb.append('=');
        sb.append(((this.minuteBar == null)?"<null>":this.minuteBar));
        sb.append(',');
        sb.append("dailyBar");
        sb.append('=');
        sb.append(((this.dailyBar == null)?"<null>":this.dailyBar));
        sb.append(',');
        sb.append("prevDailyBar");
        sb.append('=');
        sb.append(((this.prevDailyBar == null)?"<null>":this.prevDailyBar));
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
        result = ((result* 31)+((this.latestTrade == null)? 0 :this.latestTrade.hashCode()));
        result = ((result* 31)+((this.dailyBar == null)? 0 :this.dailyBar.hashCode()));
        result = ((result* 31)+((this.prevDailyBar == null)? 0 :this.prevDailyBar.hashCode()));
        result = ((result* 31)+((this.minuteBar == null)? 0 :this.minuteBar.hashCode()));
        result = ((result* 31)+((this.latestQuote == null)? 0 :this.latestQuote.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Snapshot) == false) {
            return false;
        }
        Snapshot rhs = ((Snapshot) other);
        return ((((((this.latestTrade == rhs.latestTrade)||((this.latestTrade!= null)&&this.latestTrade.equals(rhs.latestTrade)))&&((this.dailyBar == rhs.dailyBar)||((this.dailyBar!= null)&&this.dailyBar.equals(rhs.dailyBar))))&&((this.prevDailyBar == rhs.prevDailyBar)||((this.prevDailyBar!= null)&&this.prevDailyBar.equals(rhs.prevDailyBar))))&&((this.minuteBar == rhs.minuteBar)||((this.minuteBar!= null)&&this.minuteBar.equals(rhs.minuteBar))))&&((this.latestQuote == rhs.latestQuote)||((this.latestQuote!= null)&&this.latestQuote.equals(rhs.latestQuote))));
    }

}
