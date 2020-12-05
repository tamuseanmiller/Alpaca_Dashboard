
package net.jacobpeterson.domain.alpaca.clock;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.ZonedDateTime;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/clock/">https://docs.alpaca.markets/api-documentation/api-v2/clock/</a>
 * <p>
 * 
 * 
 */
public class Clock implements Serializable
{

    /**
     * Current timestamp
     * <p>
     * 
     * 
     */
    @SerializedName("timestamp")
    @Expose
    private ZonedDateTime timestamp;
    /**
     * Whether or not the market is open
     * <p>
     * 
     * 
     */
    @SerializedName("is_open")
    @Expose
    private Boolean isOpen;
    /**
     * Next market open timestamp
     * <p>
     * 
     * 
     */
    @SerializedName("next_open")
    @Expose
    private ZonedDateTime nextOpen;
    /**
     * Next market close timestamp
     * <p>
     * 
     * 
     */
    @SerializedName("next_close")
    @Expose
    private ZonedDateTime nextClose;
    private final static long serialVersionUID = -1350450248688576840L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Clock() {
    }

    /**
     * 
     * @param isOpen
     * @param nextOpen
     * @param nextClose
     * @param timestamp
     */
    public Clock(ZonedDateTime timestamp, Boolean isOpen, ZonedDateTime nextOpen, ZonedDateTime nextClose) {
        super();
        this.timestamp = timestamp;
        this.isOpen = isOpen;
        this.nextOpen = nextOpen;
        this.nextClose = nextClose;
    }

    /**
     * Current timestamp
     * <p>
     * 
     * 
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Current timestamp
     * <p>
     * 
     * 
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Whether or not the market is open
     * <p>
     * 
     * 
     */
    public Boolean getIsOpen() {
        return isOpen;
    }

    /**
     * Whether or not the market is open
     * <p>
     * 
     * 
     */
    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * Next market open timestamp
     * <p>
     * 
     * 
     */
    public ZonedDateTime getNextOpen() {
        return nextOpen;
    }

    /**
     * Next market open timestamp
     * <p>
     * 
     * 
     */
    public void setNextOpen(ZonedDateTime nextOpen) {
        this.nextOpen = nextOpen;
    }

    /**
     * Next market close timestamp
     * <p>
     * 
     * 
     */
    public ZonedDateTime getNextClose() {
        return nextClose;
    }

    /**
     * Next market close timestamp
     * <p>
     * 
     * 
     */
    public void setNextClose(ZonedDateTime nextClose) {
        this.nextClose = nextClose;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Clock.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
        sb.append(',');
        sb.append("isOpen");
        sb.append('=');
        sb.append(((this.isOpen == null)?"<null>":this.isOpen));
        sb.append(',');
        sb.append("nextOpen");
        sb.append('=');
        sb.append(((this.nextOpen == null)?"<null>":this.nextOpen));
        sb.append(',');
        sb.append("nextClose");
        sb.append('=');
        sb.append(((this.nextClose == null)?"<null>":this.nextClose));
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
        result = ((result* 31)+((this.nextOpen == null)? 0 :this.nextOpen.hashCode()));
        result = ((result* 31)+((this.nextClose == null)? 0 :this.nextClose.hashCode()));
        result = ((result* 31)+((this.isOpen == null)? 0 :this.isOpen.hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Clock) == false) {
            return false;
        }
        Clock rhs = ((Clock) other);
        return (((((this.nextOpen == rhs.nextOpen)||((this.nextOpen!= null)&&this.nextOpen.equals(rhs.nextOpen)))&&((this.nextClose == rhs.nextClose)||((this.nextClose!= null)&&this.nextClose.equals(rhs.nextClose))))&&((this.isOpen == rhs.isOpen)||((this.isOpen!= null)&&this.isOpen.equals(rhs.isOpen))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
    }

}
