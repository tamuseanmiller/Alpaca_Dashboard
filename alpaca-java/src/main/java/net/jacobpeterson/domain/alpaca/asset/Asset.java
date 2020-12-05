
package net.jacobpeterson.domain.alpaca.asset;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/assets/">https://docs.alpaca.markets/api-documentation/api-v2/assets/</a>
 * <p>
 * 
 * 
 */
public class Asset implements Serializable
{

    /**
     * Asset ID.
     * <p>
     * 
     * 
     */
    @SerializedName("id")
    @Expose
    private String id;
    /**
     * “us_equity”
     * <p>
     * 
     * 
     */
    @SerializedName("class")
    @Expose
    private String _class;
    /**
     * AMEX, ARCA, BATS, NYSE, NASDAQ or NYSEARCA
     * <p>
     * 
     * 
     */
    @SerializedName("exchange")
    @Expose
    private String exchange;
    /**
     * symbol
     * <p>
     * 
     * 
     */
    @SerializedName("symbol")
    @Expose
    private String symbol;
    /**
     * active or inactive
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private String status;
    /**
     * Asset is tradable on Alpaca or not.
     * <p>
     * 
     * 
     */
    @SerializedName("tradable")
    @Expose
    private Boolean tradable;
    /**
     * Asset is marginable or not.
     * <p>
     * 
     * 
     */
    @SerializedName("marginable")
    @Expose
    private Boolean marginable;
    /**
     * Asset is shortable or not.
     * <p>
     * 
     * 
     */
    @SerializedName("shortable")
    @Expose
    private Boolean shortable;
    /**
     * Asset is easy-to-borrow or not (filtering for easy_to_borrow = True is the best way to check whether the name is currently available to short at Alpaca).
     * <p>
     * 
     * 
     */
    @SerializedName("easy_to_borrow")
    @Expose
    private Boolean easyToBorrow;
    private final static long serialVersionUID = 4204689426794653377L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Asset() {
    }

    /**
     * 
     * @param symbol
     * @param shortable
     * @param tradable
     * @param exchange
     * @param id
     * @param _class
     * @param marginable
     * @param easyToBorrow
     * @param status
     */
    public Asset(String id, String _class, String exchange, String symbol, String status, Boolean tradable, Boolean marginable, Boolean shortable, Boolean easyToBorrow) {
        super();
        this.id = id;
        this._class = _class;
        this.exchange = exchange;
        this.symbol = symbol;
        this.status = status;
        this.tradable = tradable;
        this.marginable = marginable;
        this.shortable = shortable;
        this.easyToBorrow = easyToBorrow;
    }

    /**
     * Asset ID.
     * <p>
     * 
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * Asset ID.
     * <p>
     * 
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * “us_equity”
     * <p>
     * 
     * 
     */
    public String getClass_() {
        return _class;
    }

    /**
     * “us_equity”
     * <p>
     * 
     * 
     */
    public void setClass_(String _class) {
        this._class = _class;
    }

    /**
     * AMEX, ARCA, BATS, NYSE, NASDAQ or NYSEARCA
     * <p>
     * 
     * 
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * AMEX, ARCA, BATS, NYSE, NASDAQ or NYSEARCA
     * <p>
     * 
     * 
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    /**
     * symbol
     * <p>
     * 
     * 
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * symbol
     * <p>
     * 
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * active or inactive
     * <p>
     * 
     * 
     */
    public String getStatus() {
        return status;
    }

    /**
     * active or inactive
     * <p>
     * 
     * 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Asset is tradable on Alpaca or not.
     * <p>
     * 
     * 
     */
    public Boolean getTradable() {
        return tradable;
    }

    /**
     * Asset is tradable on Alpaca or not.
     * <p>
     * 
     * 
     */
    public void setTradable(Boolean tradable) {
        this.tradable = tradable;
    }

    /**
     * Asset is marginable or not.
     * <p>
     * 
     * 
     */
    public Boolean getMarginable() {
        return marginable;
    }

    /**
     * Asset is marginable or not.
     * <p>
     * 
     * 
     */
    public void setMarginable(Boolean marginable) {
        this.marginable = marginable;
    }

    /**
     * Asset is shortable or not.
     * <p>
     * 
     * 
     */
    public Boolean getShortable() {
        return shortable;
    }

    /**
     * Asset is shortable or not.
     * <p>
     * 
     * 
     */
    public void setShortable(Boolean shortable) {
        this.shortable = shortable;
    }

    /**
     * Asset is easy-to-borrow or not (filtering for easy_to_borrow = True is the best way to check whether the name is currently available to short at Alpaca).
     * <p>
     * 
     * 
     */
    public Boolean getEasyToBorrow() {
        return easyToBorrow;
    }

    /**
     * Asset is easy-to-borrow or not (filtering for easy_to_borrow = True is the best way to check whether the name is currently available to short at Alpaca).
     * <p>
     * 
     * 
     */
    public void setEasyToBorrow(Boolean easyToBorrow) {
        this.easyToBorrow = easyToBorrow;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Asset.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("_class");
        sb.append('=');
        sb.append(((this._class == null)?"<null>":this._class));
        sb.append(',');
        sb.append("exchange");
        sb.append('=');
        sb.append(((this.exchange == null)?"<null>":this.exchange));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("tradable");
        sb.append('=');
        sb.append(((this.tradable == null)?"<null>":this.tradable));
        sb.append(',');
        sb.append("marginable");
        sb.append('=');
        sb.append(((this.marginable == null)?"<null>":this.marginable));
        sb.append(',');
        sb.append("shortable");
        sb.append('=');
        sb.append(((this.shortable == null)?"<null>":this.shortable));
        sb.append(',');
        sb.append("easyToBorrow");
        sb.append('=');
        sb.append(((this.easyToBorrow == null)?"<null>":this.easyToBorrow));
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
        result = ((result* 31)+((this.shortable == null)? 0 :this.shortable.hashCode()));
        result = ((result* 31)+((this.tradable == null)? 0 :this.tradable.hashCode()));
        result = ((result* 31)+((this.exchange == null)? 0 :this.exchange.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this._class == null)? 0 :this._class.hashCode()));
        result = ((result* 31)+((this.marginable == null)? 0 :this.marginable.hashCode()));
        result = ((result* 31)+((this.easyToBorrow == null)? 0 :this.easyToBorrow.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Asset) == false) {
            return false;
        }
        Asset rhs = ((Asset) other);
        return ((((((((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.shortable == rhs.shortable)||((this.shortable!= null)&&this.shortable.equals(rhs.shortable))))&&((this.tradable == rhs.tradable)||((this.tradable!= null)&&this.tradable.equals(rhs.tradable))))&&((this.exchange == rhs.exchange)||((this.exchange!= null)&&this.exchange.equals(rhs.exchange))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this._class == rhs._class)||((this._class!= null)&&this._class.equals(rhs._class))))&&((this.marginable == rhs.marginable)||((this.marginable!= null)&&this.marginable.equals(rhs.marginable))))&&((this.easyToBorrow == rhs.easyToBorrow)||((this.easyToBorrow!= null)&&this.easyToBorrow.equals(rhs.easyToBorrow))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
