
package net.jacobpeterson.domain.polygon.tickers.ticker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.ZonedDateTime;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_tickers">https://polygon.io/docs/#!/Reference/get_v2_reference_tickers</a>
 * <p>
 * 
 * 
 */
public class Ticker implements Serializable
{

    /**
     * Ticker
     * <p>
     * 
     * 
     */
    @SerializedName("ticker")
    @Expose
    private String ticker;
    /**
     * Name of the item.
     * <p>
     * 
     * 
     */
    @SerializedName("name")
    @Expose
    private String name;
    /**
     * Market
     * <p>
     * 
     * 
     */
    @SerializedName("market")
    @Expose
    private String market;
    /**
     * Locale
     * <p>
     * 
     * 
     */
    @SerializedName("locale")
    @Expose
    private String locale;
    /**
     * Currency
     * <p>
     * 
     * 
     */
    @SerializedName("currency")
    @Expose
    private String currency;
    /**
     * Active
     * <p>
     * 
     * 
     */
    @SerializedName("active")
    @Expose
    private Boolean active;
    /**
     * Primary Exchange
     * <p>
     * 
     * 
     */
    @SerializedName("primaryExch")
    @Expose
    private String primaryExch;
    /**
     * Last time this company record was updated.
     * <p>
     * 
     * 
     */
    @SerializedName("updated")
    @Expose
    private ZonedDateTime updated;
    /**
     * URL of this symbol. Use this to get this symbols endpoints.
     * <p>
     * 
     * 
     */
    @SerializedName("url")
    @Expose
    private String url;
    private final static long serialVersionUID = 4061577169099397337L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Ticker() {
    }

    /**
     * 
     * @param market
     * @param ticker
     * @param name
     * @param active
     * @param primaryExch
     * @param currency
     * @param locale
     * @param updated
     * @param url
     */
    public Ticker(String ticker, String name, String market, String locale, String currency, Boolean active, String primaryExch, ZonedDateTime updated, String url) {
        super();
        this.ticker = ticker;
        this.name = name;
        this.market = market;
        this.locale = locale;
        this.currency = currency;
        this.active = active;
        this.primaryExch = primaryExch;
        this.updated = updated;
        this.url = url;
    }

    /**
     * Ticker
     * <p>
     * 
     * 
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Ticker
     * <p>
     * 
     * 
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * Name of the item.
     * <p>
     * 
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * Name of the item.
     * <p>
     * 
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Market
     * <p>
     * 
     * 
     */
    public String getMarket() {
        return market;
    }

    /**
     * Market
     * <p>
     * 
     * 
     */
    public void setMarket(String market) {
        this.market = market;
    }

    /**
     * Locale
     * <p>
     * 
     * 
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Locale
     * <p>
     * 
     * 
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Currency
     * <p>
     * 
     * 
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Currency
     * <p>
     * 
     * 
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Active
     * <p>
     * 
     * 
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Active
     * <p>
     * 
     * 
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Primary Exchange
     * <p>
     * 
     * 
     */
    public String getPrimaryExch() {
        return primaryExch;
    }

    /**
     * Primary Exchange
     * <p>
     * 
     * 
     */
    public void setPrimaryExch(String primaryExch) {
        this.primaryExch = primaryExch;
    }

    /**
     * Last time this company record was updated.
     * <p>
     * 
     * 
     */
    public ZonedDateTime getUpdated() {
        return updated;
    }

    /**
     * Last time this company record was updated.
     * <p>
     * 
     * 
     */
    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }

    /**
     * URL of this symbol. Use this to get this symbols endpoints.
     * <p>
     * 
     * 
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL of this symbol. Use this to get this symbols endpoints.
     * <p>
     * 
     * 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Ticker.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ticker");
        sb.append('=');
        sb.append(((this.ticker == null)?"<null>":this.ticker));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("market");
        sb.append('=');
        sb.append(((this.market == null)?"<null>":this.market));
        sb.append(',');
        sb.append("locale");
        sb.append('=');
        sb.append(((this.locale == null)?"<null>":this.locale));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("active");
        sb.append('=');
        sb.append(((this.active == null)?"<null>":this.active));
        sb.append(',');
        sb.append("primaryExch");
        sb.append('=');
        sb.append(((this.primaryExch == null)?"<null>":this.primaryExch));
        sb.append(',');
        sb.append("updated");
        sb.append('=');
        sb.append(((this.updated == null)?"<null>":this.updated));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
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
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.active == null)? 0 :this.active.hashCode()));
        result = ((result* 31)+((this.primaryExch == null)? 0 :this.primaryExch.hashCode()));
        result = ((result* 31)+((this.currency == null)? 0 :this.currency.hashCode()));
        result = ((result* 31)+((this.locale == null)? 0 :this.locale.hashCode()));
        result = ((result* 31)+((this.updated == null)? 0 :this.updated.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Ticker) == false) {
            return false;
        }
        Ticker rhs = ((Ticker) other);
        return ((((((((((this.market == rhs.market)||((this.market!= null)&&this.market.equals(rhs.market)))&&((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.active == rhs.active)||((this.active!= null)&&this.active.equals(rhs.active))))&&((this.primaryExch == rhs.primaryExch)||((this.primaryExch!= null)&&this.primaryExch.equals(rhs.primaryExch))))&&((this.currency == rhs.currency)||((this.currency!= null)&&this.currency.equals(rhs.currency))))&&((this.locale == rhs.locale)||((this.locale!= null)&&this.locale.equals(rhs.locale))))&&((this.updated == rhs.updated)||((this.updated!= null)&&this.updated.equals(rhs.updated))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))));
    }

}
