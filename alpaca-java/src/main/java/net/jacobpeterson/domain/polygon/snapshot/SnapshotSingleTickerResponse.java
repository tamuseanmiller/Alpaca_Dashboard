
package net.jacobpeterson.domain.polygon.snapshot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.polygon.snapshot.ticker.SnapshotTicker;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v2_snapshot_locale_us_markets_stocks_tickers_ticker">https://polygon.io/docs/#!/Stocks--Equities/get_v2_snapshot_locale_us_markets_stocks_tickers_ticker</a>
 * <p>
 * 
 * 
 */
public class SnapshotSingleTickerResponse implements Serializable
{

    /**
     * Status of this requests response
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private String status;
    /**
     * tickers
     * <p>
     * 
     * 
     */
    @SerializedName("ticker")
    @Expose
    private SnapshotTicker ticker;
    private final static long serialVersionUID = -222009656352100240L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SnapshotSingleTickerResponse() {
    }

    /**
     * 
     * @param ticker
     * @param status
     */
    public SnapshotSingleTickerResponse(String status, SnapshotTicker ticker) {
        super();
        this.status = status;
        this.ticker = ticker;
    }

    /**
     * Status of this requests response
     * <p>
     * 
     * 
     */
    public String getStatus() {
        return status;
    }

    /**
     * Status of this requests response
     * <p>
     * 
     * 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * tickers
     * <p>
     * 
     * 
     */
    public SnapshotTicker getTicker() {
        return ticker;
    }

    /**
     * tickers
     * <p>
     * 
     * 
     */
    public void setTicker(SnapshotTicker ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SnapshotSingleTickerResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("ticker");
        sb.append('=');
        sb.append(((this.ticker == null)?"<null>":this.ticker));
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
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SnapshotSingleTickerResponse) == false) {
            return false;
        }
        SnapshotSingleTickerResponse rhs = ((SnapshotSingleTickerResponse) other);
        return (((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker)))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
