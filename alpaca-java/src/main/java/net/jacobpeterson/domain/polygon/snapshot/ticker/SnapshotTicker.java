
package net.jacobpeterson.domain.polygon.snapshot.ticker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v2_snapshot_locale_us_markets_stocks_tickers">https://polygon.io/docs/#!/Stocks--Equities/get_v2_snapshot_locale_us_markets_stocks_tickers</a>
 * <p>
 * 
 * 
 */
public class SnapshotTicker implements Serializable
{

    /**
     * Ticker of the object
     * <p>
     * 
     * 
     */
    @SerializedName("ticker")
    @Expose
    private String ticker;
    /**
     * day
     * <p>
     * 
     * 
     */
    @SerializedName("day")
    @Expose
    private TickerCandle day;
    /**
     * lastTrade
     * <p>
     *
     *
     */
    @SerializedName("lastTrade")
    @Expose
    private LastTrade lastTrade;
    /**
     * lastQuote
     * <p>
     *
     *
     */
    @SerializedName("lastQuote")
    @Expose
    private LastQuote lastQuote;
    /**
     * min
     * <p>
     *
     *
     */
    @SerializedName("min")
    @Expose
    private TickerCandle min;
    /**
     * prevDay
     * <p>
     *
     *
     */
    @SerializedName("prevDay")
    @Expose
    private TickerCandle prevDay;
    /**
     * Value of the change from previous day
     * <p>
     *
     *
     */
    @SerializedName("todaysChange")
    @Expose
    private Double todaysChange;
    /**
     * Percentage change since previous day
     * <p>
     *
     *
     */
    @SerializedName("todaysChangePerc")
    @Expose
    private Double todaysChangePerc;
    /**
     * Last Updated timestamp
     * <p>
     *
     *
     */
    @SerializedName("updated")
    @Expose
    private Long updated;
    private final static long serialVersionUID = -6240113060421673114L;

    /**
     * No args constructor for use in serialization
     *
     */
    public SnapshotTicker() {
    }

    /**
     *
     * @param prevDay
     * @param ticker
     * @param min
     * @param lastTrade
     * @param todaysChangePerc
     * @param day
     * @param lastQuote
     * @param updated
     * @param todaysChange
     */
    public SnapshotTicker(String ticker, TickerCandle day, LastTrade lastTrade, LastQuote lastQuote, TickerCandle min, TickerCandle prevDay, Double todaysChange, Double todaysChangePerc, Long updated) {
        super();
        this.ticker = ticker;
        this.day = day;
        this.lastTrade = lastTrade;
        this.lastQuote = lastQuote;
        this.min = min;
        this.prevDay = prevDay;
        this.todaysChange = todaysChange;
        this.todaysChangePerc = todaysChangePerc;
        this.updated = updated;
    }

    /**
     * Ticker of the object
     * <p>
     *
     *
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Ticker of the object
     * <p>
     *
     *
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * day
     * <p>
     *
     *
     */
    public TickerCandle getDay() {
        return day;
    }

    /**
     * day
     * <p>
     *
     *
     */
    public void setDay(TickerCandle day) {
        this.day = day;
    }

    /**
     * lastTrade
     * <p>
     *
     *
     */
    public LastTrade getLastTrade() {
        return lastTrade;
    }

    /**
     * lastTrade
     * <p>
     *
     *
     */
    public void setLastTrade(LastTrade lastTrade) {
        this.lastTrade = lastTrade;
    }

    /**
     * lastQuote
     * <p>
     *
     *
     */
    public LastQuote getLastQuote() {
        return lastQuote;
    }

    /**
     * lastQuote
     * <p>
     *
     *
     */
    public void setLastQuote(LastQuote lastQuote) {
        this.lastQuote = lastQuote;
    }

    /**
     * min
     * <p>
     *
     *
     */
    public TickerCandle getMin() {
        return min;
    }

    /**
     * min
     * <p>
     *
     *
     */
    public void setMin(TickerCandle min) {
        this.min = min;
    }

    /**
     * prevDay
     * <p>
     *
     *
     */
    public TickerCandle getPrevDay() {
        return prevDay;
    }

    /**
     * prevDay
     * <p>
     *
     *
     */
    public void setPrevDay(TickerCandle prevDay) {
        this.prevDay = prevDay;
    }

    /**
     * Value of the change from previous day
     * <p>
     * 
     * 
     */
    public Double getTodaysChange() {
        return todaysChange;
    }

    /**
     * Value of the change from previous day
     * <p>
     * 
     * 
     */
    public void setTodaysChange(Double todaysChange) {
        this.todaysChange = todaysChange;
    }

    /**
     * Percentage change since previous day
     * <p>
     * 
     * 
     */
    public Double getTodaysChangePerc() {
        return todaysChangePerc;
    }

    /**
     * Percentage change since previous day
     * <p>
     * 
     * 
     */
    public void setTodaysChangePerc(Double todaysChangePerc) {
        this.todaysChangePerc = todaysChangePerc;
    }

    /**
     * Last Updated timestamp
     * <p>
     * 
     * 
     */
    public Long getUpdated() {
        return updated;
    }

    /**
     * Last Updated timestamp
     * <p>
     * 
     * 
     */
    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SnapshotTicker.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ticker");
        sb.append('=');
        sb.append(((this.ticker == null)?"<null>":this.ticker));
        sb.append(',');
        sb.append("day");
        sb.append('=');
        sb.append(((this.day == null)?"<null>":this.day));
        sb.append(',');
        sb.append("lastTrade");
        sb.append('=');
        sb.append(((this.lastTrade == null)?"<null>":this.lastTrade));
        sb.append(',');
        sb.append("lastQuote");
        sb.append('=');
        sb.append(((this.lastQuote == null)?"<null>":this.lastQuote));
        sb.append(',');
        sb.append("min");
        sb.append('=');
        sb.append(((this.min == null)?"<null>":this.min));
        sb.append(',');
        sb.append("prevDay");
        sb.append('=');
        sb.append(((this.prevDay == null)?"<null>":this.prevDay));
        sb.append(',');
        sb.append("todaysChange");
        sb.append('=');
        sb.append(((this.todaysChange == null)?"<null>":this.todaysChange));
        sb.append(',');
        sb.append("todaysChangePerc");
        sb.append('=');
        sb.append(((this.todaysChangePerc == null)?"<null>":this.todaysChangePerc));
        sb.append(',');
        sb.append("updated");
        sb.append('=');
        sb.append(((this.updated == null)?"<null>":this.updated));
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
        result = ((result* 31)+((this.prevDay == null)? 0 :this.prevDay.hashCode()));
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+((this.min == null)? 0 :this.min.hashCode()));
        result = ((result* 31)+((this.lastTrade == null)? 0 :this.lastTrade.hashCode()));
        result = ((result* 31)+((this.todaysChangePerc == null)? 0 :this.todaysChangePerc.hashCode()));
        result = ((result* 31)+((this.day == null)? 0 :this.day.hashCode()));
        result = ((result* 31)+((this.lastQuote == null)? 0 :this.lastQuote.hashCode()));
        result = ((result* 31)+((this.updated == null)? 0 :this.updated.hashCode()));
        result = ((result* 31)+((this.todaysChange == null)? 0 :this.todaysChange.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SnapshotTicker) == false) {
            return false;
        }
        SnapshotTicker rhs = ((SnapshotTicker) other);
        return ((((((((((this.prevDay == rhs.prevDay)||((this.prevDay!= null)&&this.prevDay.equals(rhs.prevDay)))&&((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker))))&&((this.min == rhs.min)||((this.min!= null)&&this.min.equals(rhs.min))))&&((this.lastTrade == rhs.lastTrade)||((this.lastTrade!= null)&&this.lastTrade.equals(rhs.lastTrade))))&&((this.todaysChangePerc == rhs.todaysChangePerc)||((this.todaysChangePerc!= null)&&this.todaysChangePerc.equals(rhs.todaysChangePerc))))&&((this.day == rhs.day)||((this.day!= null)&&this.day.equals(rhs.day))))&&((this.lastQuote == rhs.lastQuote)||((this.lastQuote!= null)&&this.lastQuote.equals(rhs.lastQuote))))&&((this.updated == rhs.updated)||((this.updated!= null)&&this.updated.equals(rhs.updated))))&&((this.todaysChange == rhs.todaysChange)||((this.todaysChange!= null)&&this.todaysChange.equals(rhs.todaysChange))));
    }

}
