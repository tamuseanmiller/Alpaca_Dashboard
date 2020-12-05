
package net.jacobpeterson.domain.alpaca.portfoliohistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.alpaca.enums.PortfolioTimeFrame;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/portfolio-history/">https://docs.alpaca.markets/api-documentation/api-v2/portfolio-history/</a>
 * <p>
 * 
 * 
 */
public class PortfolioHistory implements Serializable
{

    /**
     * Time of each data element, left-labeled (the beginning of time window)
     * <p>
     * 
     * 
     */
    @SerializedName("timestamp")
    @Expose
    private ArrayList<Long> timestamp;
    /**
     * Equity value of the account in dollar amount as of the end of each time window
     * <p>
     * 
     * 
     */
    @SerializedName("equity")
    @Expose
    private ArrayList<Double> equity;
    /**
     * Profit/loss in dollar from the base value
     * <p>
     * 
     * 
     */
    @SerializedName("profit_loss")
    @Expose
    private ArrayList<Double> profitLoss;
    /**
     * Profit/loss in percentage from the base value
     * <p>
     * 
     * 
     */
    @SerializedName("profit_loss_pct")
    @Expose
    private ArrayList<Double> profitLossPct;
    /**
     * Basis in dollar of the profit loss calculation
     * <p>
     * 
     * 
     */
    @SerializedName("base_value")
    @Expose
    private Double baseValue;
    /**
     * Time window size of each data element
     * <p>
     * 
     * 
     */
    @SerializedName("timeframe")
    @Expose
    private PortfolioTimeFrame timeframe;
    private final static long serialVersionUID = 7754240739390326653L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PortfolioHistory() {
    }

    /**
     * 
     * @param timeframe
     * @param baseValue
     * @param profitLossPct
     * @param profitLoss
     * @param equity
     * @param timestamp
     */
    public PortfolioHistory(ArrayList<Long> timestamp, ArrayList<Double> equity, ArrayList<Double> profitLoss, ArrayList<Double> profitLossPct, Double baseValue, PortfolioTimeFrame timeframe) {
        super();
        this.timestamp = timestamp;
        this.equity = equity;
        this.profitLoss = profitLoss;
        this.profitLossPct = profitLossPct;
        this.baseValue = baseValue;
        this.timeframe = timeframe;
    }

    /**
     * Time of each data element, left-labeled (the beginning of time window)
     * <p>
     * 
     * 
     */
    public ArrayList<Long> getTimestamp() {
        return timestamp;
    }

    /**
     * Time of each data element, left-labeled (the beginning of time window)
     * <p>
     * 
     * 
     */
    public void setTimestamp(ArrayList<Long> timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Equity value of the account in dollar amount as of the end of each time window
     * <p>
     * 
     * 
     */
    public ArrayList<Double> getEquity() {
        return equity;
    }

    /**
     * Equity value of the account in dollar amount as of the end of each time window
     * <p>
     * 
     * 
     */
    public void setEquity(ArrayList<Double> equity) {
        this.equity = equity;
    }

    /**
     * Profit/loss in dollar from the base value
     * <p>
     * 
     * 
     */
    public ArrayList<Double> getProfitLoss() {
        return profitLoss;
    }

    /**
     * Profit/loss in dollar from the base value
     * <p>
     * 
     * 
     */
    public void setProfitLoss(ArrayList<Double> profitLoss) {
        this.profitLoss = profitLoss;
    }

    /**
     * Profit/loss in percentage from the base value
     * <p>
     * 
     * 
     */
    public ArrayList<Double> getProfitLossPct() {
        return profitLossPct;
    }

    /**
     * Profit/loss in percentage from the base value
     * <p>
     * 
     * 
     */
    public void setProfitLossPct(ArrayList<Double> profitLossPct) {
        this.profitLossPct = profitLossPct;
    }

    /**
     * Basis in dollar of the profit loss calculation
     * <p>
     * 
     * 
     */
    public Double getBaseValue() {
        return baseValue;
    }

    /**
     * Basis in dollar of the profit loss calculation
     * <p>
     * 
     * 
     */
    public void setBaseValue(Double baseValue) {
        this.baseValue = baseValue;
    }

    /**
     * Time window size of each data element
     * <p>
     * 
     * 
     */
    public PortfolioTimeFrame getTimeframe() {
        return timeframe;
    }

    /**
     * Time window size of each data element
     * <p>
     * 
     * 
     */
    public void setTimeframe(PortfolioTimeFrame timeframe) {
        this.timeframe = timeframe;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PortfolioHistory.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
        sb.append(',');
        sb.append("equity");
        sb.append('=');
        sb.append(((this.equity == null)?"<null>":this.equity));
        sb.append(',');
        sb.append("profitLoss");
        sb.append('=');
        sb.append(((this.profitLoss == null)?"<null>":this.profitLoss));
        sb.append(',');
        sb.append("profitLossPct");
        sb.append('=');
        sb.append(((this.profitLossPct == null)?"<null>":this.profitLossPct));
        sb.append(',');
        sb.append("baseValue");
        sb.append('=');
        sb.append(((this.baseValue == null)?"<null>":this.baseValue));
        sb.append(',');
        sb.append("timeframe");
        sb.append('=');
        sb.append(((this.timeframe == null)?"<null>":this.timeframe));
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
        result = ((result* 31)+((this.timeframe == null)? 0 :this.timeframe.hashCode()));
        result = ((result* 31)+((this.baseValue == null)? 0 :this.baseValue.hashCode()));
        result = ((result* 31)+((this.profitLossPct == null)? 0 :this.profitLossPct.hashCode()));
        result = ((result* 31)+((this.profitLoss == null)? 0 :this.profitLoss.hashCode()));
        result = ((result* 31)+((this.equity == null)? 0 :this.equity.hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PortfolioHistory) == false) {
            return false;
        }
        PortfolioHistory rhs = ((PortfolioHistory) other);
        return (((((((this.timeframe == rhs.timeframe)||((this.timeframe!= null)&&this.timeframe.equals(rhs.timeframe)))&&((this.baseValue == rhs.baseValue)||((this.baseValue!= null)&&this.baseValue.equals(rhs.baseValue))))&&((this.profitLossPct == rhs.profitLossPct)||((this.profitLossPct!= null)&&this.profitLossPct.equals(rhs.profitLossPct))))&&((this.profitLoss == rhs.profitLoss)||((this.profitLoss!= null)&&this.profitLoss.equals(rhs.profitLoss))))&&((this.equity == rhs.equity)||((this.equity!= null)&&this.equity.equals(rhs.equity))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
    }

}
