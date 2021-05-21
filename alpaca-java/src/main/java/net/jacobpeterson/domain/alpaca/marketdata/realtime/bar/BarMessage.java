
package net.jacobpeterson.domain.alpaca.marketdata.realtime.bar;

import java.io.Serializable;
import java.time.ZonedDateTime;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.alpaca.marketdata.realtime.SymbolMessage;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/</a>
 * <p>
 * 
 * 
 */
public class BarMessage
    extends SymbolMessage
    implements Serializable
{

    /**
     * Open price
     * <p>
     * 
     * Corresponds to the "o" property.
     * 
     */
    @SerializedName("o")
    @Expose
    private Double open;
    /**
     * High price
     * <p>
     * 
     * Corresponds to the "h" property.
     * 
     */
    @SerializedName("h")
    @Expose
    private Double high;
    /**
     * Low price
     * <p>
     * 
     * Corresponds to the "l" property.
     * 
     */
    @SerializedName("l")
    @Expose
    private Double low;
    /**
     * Close price
     * <p>
     * 
     * Corresponds to the "c" property.
     * 
     */
    @SerializedName("c")
    @Expose
    private Double close;
    /**
     * Volume
     * <p>
     * 
     * Corresponds to the "v" property.
     * 
     */
    @SerializedName("v")
    @Expose
    private Integer volume;
    /**
     * Timestamp
     * <p>
     * 
     * Corresponds to the "t" property.
     * 
     */
    @SerializedName("t")
    @Expose
    private ZonedDateTime timestamp;
    private final static long serialVersionUID = -9159237616476829686L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BarMessage() {
    }

    /**
     * 
     * @param volume
     * @param high
     * @param low
     * @param close
     * @param open
     * @param timestamp
     */
    public BarMessage(Double open, Double high, Double low, Double close, Integer volume, ZonedDateTime timestamp) {
        super();
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.timestamp = timestamp;
    }

    /**
     * Open price
     * <p>
     * 
     * Corresponds to the "o" property.
     * 
     */
    public Double getOpen() {
        return open;
    }

    /**
     * Open price
     * <p>
     * 
     * Corresponds to the "o" property.
     * 
     */
    public void setOpen(Double open) {
        this.open = open;
    }

    /**
     * High price
     * <p>
     * 
     * Corresponds to the "h" property.
     * 
     */
    public Double getHigh() {
        return high;
    }

    /**
     * High price
     * <p>
     * 
     * Corresponds to the "h" property.
     * 
     */
    public void setHigh(Double high) {
        this.high = high;
    }

    /**
     * Low price
     * <p>
     * 
     * Corresponds to the "l" property.
     * 
     */
    public Double getLow() {
        return low;
    }

    /**
     * Low price
     * <p>
     * 
     * Corresponds to the "l" property.
     * 
     */
    public void setLow(Double low) {
        this.low = low;
    }

    /**
     * Close price
     * <p>
     * 
     * Corresponds to the "c" property.
     * 
     */
    public Double getClose() {
        return close;
    }

    /**
     * Close price
     * <p>
     * 
     * Corresponds to the "c" property.
     * 
     */
    public void setClose(Double close) {
        this.close = close;
    }

    /**
     * Volume
     * <p>
     * 
     * Corresponds to the "v" property.
     * 
     */
    public Integer getVolume() {
        return volume;
    }

    /**
     * Volume
     * <p>
     * 
     * Corresponds to the "v" property.
     * 
     */
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    /**
     * Timestamp
     * <p>
     * 
     * Corresponds to the "t" property.
     * 
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp
     * <p>
     * 
     * Corresponds to the "t" property.
     * 
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BarMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        int baseLength = sb.length();
        String superString = super.toString();
        if (superString!= null) {
            int contentStart = superString.indexOf('[');
            int contentEnd = superString.lastIndexOf(']');
            if ((contentStart >= 0)&&(contentEnd >contentStart)) {
                sb.append(superString, (contentStart + 1), contentEnd);
            } else {
                sb.append(superString);
            }
        }
        if (sb.length()>baseLength) {
            sb.append(',');
        }
        sb.append("open");
        sb.append('=');
        sb.append(((this.open == null)?"<null>":this.open));
        sb.append(',');
        sb.append("high");
        sb.append('=');
        sb.append(((this.high == null)?"<null>":this.high));
        sb.append(',');
        sb.append("low");
        sb.append('=');
        sb.append(((this.low == null)?"<null>":this.low));
        sb.append(',');
        sb.append("close");
        sb.append('=');
        sb.append(((this.close == null)?"<null>":this.close));
        sb.append(',');
        sb.append("volume");
        sb.append('=');
        sb.append(((this.volume == null)?"<null>":this.volume));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
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
        result = ((result* 31)+((this.volume == null)? 0 :this.volume.hashCode()));
        result = ((result* 31)+((this.high == null)? 0 :this.high.hashCode()));
        result = ((result* 31)+((this.low == null)? 0 :this.low.hashCode()));
        result = ((result* 31)+((this.close == null)? 0 :this.close.hashCode()));
        result = ((result* 31)+((this.open == null)? 0 :this.open.hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BarMessage) == false) {
            return false;
        }
        BarMessage rhs = ((BarMessage) other);
        return ((((((super.equals(rhs)&&((this.volume == rhs.volume)||((this.volume!= null)&&this.volume.equals(rhs.volume))))&&((this.high == rhs.high)||((this.high!= null)&&this.high.equals(rhs.high))))&&((this.low == rhs.low)||((this.low!= null)&&this.low.equals(rhs.low))))&&((this.close == rhs.close)||((this.close!= null)&&this.close.equals(rhs.close))))&&((this.open == rhs.open)||((this.open!= null)&&this.open.equals(rhs.open))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
    }

}
