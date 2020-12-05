
package net.jacobpeterson.domain.alpaca.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/calendar/">https://docs.alpaca.markets/api-documentation/api-v2/calendar/</a>
 * <p>
 * 
 * 
 */
public class Calendar implements Serializable
{

    /**
     * Date string in “%Y-%m-%d” format
     * <p>
     * 
     * 
     */
    @SerializedName("date")
    @Expose
    private String date;
    /**
     * The time the market opens at on this date in “%H:%M” format
     * <p>
     * 
     * 
     */
    @SerializedName("open")
    @Expose
    private String open;
    /**
     * The time the market closes at on this date in “%H:%M” format
     * <p>
     * 
     * 
     */
    @SerializedName("close")
    @Expose
    private String close;
    private final static long serialVersionUID = 1567060638793356498L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Calendar() {
    }

    /**
     * 
     * @param date
     * @param close
     * @param open
     */
    public Calendar(String date, String open, String close) {
        super();
        this.date = date;
        this.open = open;
        this.close = close;
    }

    /**
     * Date string in “%Y-%m-%d” format
     * <p>
     * 
     * 
     */
    public String getDate() {
        return date;
    }

    /**
     * Date string in “%Y-%m-%d” format
     * <p>
     * 
     * 
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * The time the market opens at on this date in “%H:%M” format
     * <p>
     * 
     * 
     */
    public String getOpen() {
        return open;
    }

    /**
     * The time the market opens at on this date in “%H:%M” format
     * <p>
     * 
     * 
     */
    public void setOpen(String open) {
        this.open = open;
    }

    /**
     * The time the market closes at on this date in “%H:%M” format
     * <p>
     * 
     * 
     */
    public String getClose() {
        return close;
    }

    /**
     * The time the market closes at on this date in “%H:%M” format
     * <p>
     * 
     * 
     */
    public void setClose(String close) {
        this.close = close;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Calendar.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("date");
        sb.append('=');
        sb.append(((this.date == null)?"<null>":this.date));
        sb.append(',');
        sb.append("open");
        sb.append('=');
        sb.append(((this.open == null)?"<null>":this.open));
        sb.append(',');
        sb.append("close");
        sb.append('=');
        sb.append(((this.close == null)?"<null>":this.close));
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
        result = ((result* 31)+((this.date == null)? 0 :this.date.hashCode()));
        result = ((result* 31)+((this.close == null)? 0 :this.close.hashCode()));
        result = ((result* 31)+((this.open == null)? 0 :this.open.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Calendar) == false) {
            return false;
        }
        Calendar rhs = ((Calendar) other);
        return ((((this.date == rhs.date)||((this.date!= null)&&this.date.equals(rhs.date)))&&((this.close == rhs.close)||((this.close!= null)&&this.close.equals(rhs.close))))&&((this.open == rhs.open)||((this.open!= null)&&this.open.equals(rhs.open))));
    }

}
