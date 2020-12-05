
package net.jacobpeterson.domain.polygon.marketstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashMap;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v1_marketstatus_now">https://polygon.io/docs/#!/Reference/get_v1_marketstatus_now</a>
 * <p>
 * 
 * 
 */
public class MarketStatus implements Serializable
{

    /**
     * Status of the market as a whole. Can be open, closed or extended-hours
     * <p>
     * 
     * 
     */
    @SerializedName("market")
    @Expose
    private String market;
    /**
     * Current time of the server
     * <p>
     * 
     * 
     */
    @SerializedName("serverTime")
    @Expose
    private ZonedDateTime serverTime;
    /**
     * exchanges
     * <p>
     * 
     * 
     */
    @SerializedName("exchanges")
    @Expose
    private HashMap<String, String> exchanges;
    /**
     * currencies
     * <p>
     * 
     * 
     */
    @SerializedName("currencies")
    @Expose
    private HashMap<String, String> currencies;
    private final static long serialVersionUID = -3080548276146939427L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MarketStatus() {
    }

    /**
     * 
     * @param market
     * @param exchanges
     * @param serverTime
     * @param currencies
     */
    public MarketStatus(String market, ZonedDateTime serverTime, HashMap<String, String> exchanges, HashMap<String, String> currencies) {
        super();
        this.market = market;
        this.serverTime = serverTime;
        this.exchanges = exchanges;
        this.currencies = currencies;
    }

    /**
     * Status of the market as a whole. Can be open, closed or extended-hours
     * <p>
     * 
     * 
     */
    public String getMarket() {
        return market;
    }

    /**
     * Status of the market as a whole. Can be open, closed or extended-hours
     * <p>
     * 
     * 
     */
    public void setMarket(String market) {
        this.market = market;
    }

    /**
     * Current time of the server
     * <p>
     * 
     * 
     */
    public ZonedDateTime getServerTime() {
        return serverTime;
    }

    /**
     * Current time of the server
     * <p>
     * 
     * 
     */
    public void setServerTime(ZonedDateTime serverTime) {
        this.serverTime = serverTime;
    }

    /**
     * exchanges
     * <p>
     * 
     * 
     */
    public HashMap<String, String> getExchanges() {
        return exchanges;
    }

    /**
     * exchanges
     * <p>
     * 
     * 
     */
    public void setExchanges(HashMap<String, String> exchanges) {
        this.exchanges = exchanges;
    }

    /**
     * currencies
     * <p>
     * 
     * 
     */
    public HashMap<String, String> getCurrencies() {
        return currencies;
    }

    /**
     * currencies
     * <p>
     * 
     * 
     */
    public void setCurrencies(HashMap<String, String> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MarketStatus.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("market");
        sb.append('=');
        sb.append(((this.market == null)?"<null>":this.market));
        sb.append(',');
        sb.append("serverTime");
        sb.append('=');
        sb.append(((this.serverTime == null)?"<null>":this.serverTime));
        sb.append(',');
        sb.append("exchanges");
        sb.append('=');
        sb.append(((this.exchanges == null)?"<null>":this.exchanges));
        sb.append(',');
        sb.append("currencies");
        sb.append('=');
        sb.append(((this.currencies == null)?"<null>":this.currencies));
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
        result = ((result* 31)+((this.market == null)? 0 :this.market.hashCode()));
        result = ((result* 31)+((this.exchanges == null)? 0 :this.exchanges.hashCode()));
        result = ((result* 31)+((this.serverTime == null)? 0 :this.serverTime.hashCode()));
        result = ((result* 31)+((this.currencies == null)? 0 :this.currencies.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MarketStatus) == false) {
            return false;
        }
        MarketStatus rhs = ((MarketStatus) other);
        return (((((this.market == rhs.market)||((this.market!= null)&&this.market.equals(rhs.market)))&&((this.exchanges == rhs.exchanges)||((this.exchanges!= null)&&this.exchanges.equals(rhs.exchanges))))&&((this.serverTime == rhs.serverTime)||((this.serverTime!= null)&&this.serverTime.equals(rhs.serverTime))))&&((this.currencies == rhs.currencies)||((this.currencies!= null)&&this.currencies.equals(rhs.currencies))));
    }

}
