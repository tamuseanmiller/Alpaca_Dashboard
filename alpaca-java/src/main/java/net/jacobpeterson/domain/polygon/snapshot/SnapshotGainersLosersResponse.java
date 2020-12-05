
package net.jacobpeterson.domain.polygon.snapshot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.polygon.snapshot.ticker.SnapshotTicker;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v2_snapshot_locale_us_markets_stocks_direction">https://polygon.io/docs/#!/Stocks--Equities/get_v2_snapshot_locale_us_markets_stocks_direction</a>
 * <p>
 * 
 * 
 */
public class SnapshotGainersLosersResponse implements Serializable
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
    @SerializedName("tickers")
    @Expose
    private ArrayList<SnapshotTicker> tickers;
    private final static long serialVersionUID = 5917914235804342126L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SnapshotGainersLosersResponse() {
    }

    /**
     * 
     * @param tickers
     * @param status
     */
    public SnapshotGainersLosersResponse(String status, ArrayList<SnapshotTicker> tickers) {
        super();
        this.status = status;
        this.tickers = tickers;
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
    public ArrayList<SnapshotTicker> getTickers() {
        return tickers;
    }

    /**
     * tickers
     * <p>
     * 
     * 
     */
    public void setTickers(ArrayList<SnapshotTicker> tickers) {
        this.tickers = tickers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SnapshotGainersLosersResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("tickers");
        sb.append('=');
        sb.append(((this.tickers == null)?"<null>":this.tickers));
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
        result = ((result* 31)+((this.tickers == null)? 0 :this.tickers.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SnapshotGainersLosersResponse) == false) {
            return false;
        }
        SnapshotGainersLosersResponse rhs = ((SnapshotGainersLosersResponse) other);
        return (((this.tickers == rhs.tickers)||((this.tickers!= null)&&this.tickers.equals(rhs.tickers)))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
