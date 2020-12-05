
package net.jacobpeterson.domain.polygon.marketholidays;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.ZonedDateTime;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v1_marketstatus_upcoming">https://polygon.io/docs/#!/Reference/get_v1_marketstatus_upcoming</a>
 * <p>
 * 
 * 
 */
public class MarketHoliday implements Serializable
{

    /**
     * Which market this record is for. Can be NYSE, NASDAQ or OTC
     * <p>
     * 
     * 
     */
    @SerializedName("exchange")
    @Expose
    private String exchange;
    /**
     * Human readable description of the holiday
     * <p>
     * 
     * 
     */
    @SerializedName("name")
    @Expose
    private String name;
    /**
     * Status of the market on this holiday. Can be closed, early-close, late-close, early-open or late-open
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private String status;
    /**
     * Date of this holiday
     * <p>
     * 
     * 
     */
    @SerializedName("date")
    @Expose
    private ZonedDateTime date;
    /**
     * Market open time on this holiday ( if it's not closed )
     * <p>
     * 
     * 
     */
    @SerializedName("open")
    @Expose
    private ZonedDateTime open;
    /**
     * Market close time on this holiday ( if it's not closed )
     * <p>
     * 
     * 
     */
    @SerializedName("close")
    @Expose
    private ZonedDateTime close;
    private final static long serialVersionUID = -125258829452157962L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MarketHoliday() {
    }

    /**
     * 
     * @param date
     * @param name
     * @param exchange
     * @param close
     * @param open
     * @param status
     */
    public MarketHoliday(String exchange, String name, String status, ZonedDateTime date, ZonedDateTime open, ZonedDateTime close) {
        super();
        this.exchange = exchange;
        this.name = name;
        this.status = status;
        this.date = date;
        this.open = open;
        this.close = close;
    }

    /**
     * Which market this record is for. Can be NYSE, NASDAQ or OTC
     * <p>
     * 
     * 
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * Which market this record is for. Can be NYSE, NASDAQ or OTC
     * <p>
     * 
     * 
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    /**
     * Human readable description of the holiday
     * <p>
     * 
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * Human readable description of the holiday
     * <p>
     * 
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Status of the market on this holiday. Can be closed, early-close, late-close, early-open or late-open
     * <p>
     * 
     * 
     */
    public String getStatus() {
        return status;
    }

    /**
     * Status of the market on this holiday. Can be closed, early-close, late-close, early-open or late-open
     * <p>
     * 
     * 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Date of this holiday
     * <p>
     * 
     * 
     */
    public ZonedDateTime getDate() {
        return date;
    }

    /**
     * Date of this holiday
     * <p>
     * 
     * 
     */
    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    /**
     * Market open time on this holiday ( if it's not closed )
     * <p>
     * 
     * 
     */
    public ZonedDateTime getOpen() {
        return open;
    }

    /**
     * Market open time on this holiday ( if it's not closed )
     * <p>
     * 
     * 
     */
    public void setOpen(ZonedDateTime open) {
        this.open = open;
    }

    /**
     * Market close time on this holiday ( if it's not closed )
     * <p>
     * 
     * 
     */
    public ZonedDateTime getClose() {
        return close;
    }

    /**
     * Market close time on this holiday ( if it's not closed )
     * <p>
     * 
     * 
     */
    public void setClose(ZonedDateTime close) {
        this.close = close;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MarketHoliday.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("exchange");
        sb.append('=');
        sb.append(((this.exchange == null)?"<null>":this.exchange));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
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
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.exchange == null)? 0 :this.exchange.hashCode()));
        result = ((result* 31)+((this.close == null)? 0 :this.close.hashCode()));
        result = ((result* 31)+((this.open == null)? 0 :this.open.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MarketHoliday) == false) {
            return false;
        }
        MarketHoliday rhs = ((MarketHoliday) other);
        return (((((((this.date == rhs.date)||((this.date!= null)&&this.date.equals(rhs.date)))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.exchange == rhs.exchange)||((this.exchange!= null)&&this.exchange.equals(rhs.exchange))))&&((this.close == rhs.close)||((this.close!= null)&&this.close.equals(rhs.close))))&&((this.open == rhs.open)||((this.open!= null)&&this.open.equals(rhs.open))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
