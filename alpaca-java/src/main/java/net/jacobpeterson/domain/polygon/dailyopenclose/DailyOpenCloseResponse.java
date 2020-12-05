
package net.jacobpeterson.domain.polygon.dailyopenclose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v1_open_close_symbol_date">https://polygon.io/docs/#!/Stocks--Equities/get_v1_open_close_symbol_date</a>
 * <p>
 * 
 * 
 */
public class DailyOpenCloseResponse implements Serializable
{

    /**
     * Symbol that was evaluated from the request
     * <p>
     * 
     * 
     */
    @SerializedName("symbol")
    @Expose
    private String symbol;
    /**
     * open
     * <p>
     * 
     * 
     */
    @SerializedName("open")
    @Expose
    private DailyOpenCloseTrade open;
    /**
     * close
     * <p>
     *
     *
     */
    @SerializedName("close")
    @Expose
    private DailyOpenCloseTrade close;
    /**
     * afterHours
     * <p>
     *
     *
     */
    @SerializedName("afterHours")
    @Expose
    private DailyOpenCloseTrade afterHours;
    private final static long serialVersionUID = 3245515862243981632L;

    /**
     * No args constructor for use in serialization
     *
     */
    public DailyOpenCloseResponse() {
    }

    /**
     *
     * @param symbol
     * @param afterHours
     * @param close
     * @param open
     */
    public DailyOpenCloseResponse(String symbol, DailyOpenCloseTrade open, DailyOpenCloseTrade close, DailyOpenCloseTrade afterHours) {
        super();
        this.symbol = symbol;
        this.open = open;
        this.close = close;
        this.afterHours = afterHours;
    }

    /**
     * Symbol that was evaluated from the request
     * <p>
     *
     *
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Symbol that was evaluated from the request
     * <p>
     *
     *
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * open
     * <p>
     *
     *
     */
    public DailyOpenCloseTrade getOpen() {
        return open;
    }

    /**
     * open
     * <p>
     *
     *
     */
    public void setOpen(DailyOpenCloseTrade open) {
        this.open = open;
    }

    /**
     * close
     * <p>
     *
     *
     */
    public DailyOpenCloseTrade getClose() {
        return close;
    }

    /**
     * close
     * <p>
     *
     *
     */
    public void setClose(DailyOpenCloseTrade close) {
        this.close = close;
    }

    /**
     * afterHours
     * <p>
     *
     *
     */
    public DailyOpenCloseTrade getAfterHours() {
        return afterHours;
    }

    /**
     * afterHours
     * <p>
     *
     *
     */
    public void setAfterHours(DailyOpenCloseTrade afterHours) {
        this.afterHours = afterHours;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DailyOpenCloseResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("open");
        sb.append('=');
        sb.append(((this.open == null)?"<null>":this.open));
        sb.append(',');
        sb.append("close");
        sb.append('=');
        sb.append(((this.close == null)?"<null>":this.close));
        sb.append(',');
        sb.append("afterHours");
        sb.append('=');
        sb.append(((this.afterHours == null)?"<null>":this.afterHours));
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
        result = ((result* 31)+((this.afterHours == null)? 0 :this.afterHours.hashCode()));
        result = ((result* 31)+((this.close == null)? 0 :this.close.hashCode()));
        result = ((result* 31)+((this.open == null)? 0 :this.open.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DailyOpenCloseResponse) == false) {
            return false;
        }
        DailyOpenCloseResponse rhs = ((DailyOpenCloseResponse) other);
        return (((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.afterHours == rhs.afterHours)||((this.afterHours!= null)&&this.afterHours.equals(rhs.afterHours))))&&((this.close == rhs.close)||((this.close!= null)&&this.close.equals(rhs.close))))&&((this.open == rhs.open)||((this.open!= null)&&this.open.equals(rhs.open))));
    }

}
