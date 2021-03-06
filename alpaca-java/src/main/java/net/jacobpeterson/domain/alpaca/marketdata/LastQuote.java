
package net.jacobpeterson.domain.alpaca.marketdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/last-quote/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/last-quote/</a>
 * <p>
 * 
 * 
 */
public class LastQuote implements Serializable
{

    /**
     * The current ask price
     * <p>
     * 
     * 
     */
    @SerializedName("askprice")
    @Expose
    private Double askprice;
    /**
     * The current ask size
     * <p>
     * 
     * 
     */
    @SerializedName("asksize")
    @Expose
    private Long asksize;
    /**
     * The exchange code of the ask quote
     * <p>
     * 
     * 
     */
    @SerializedName("askexchange")
    @Expose
    private Integer askexchange;
    /**
     * The current bid price
     * <p>
     * 
     * 
     */
    @SerializedName("bidprice")
    @Expose
    private Double bidprice;
    /**
     * The current bid size
     * <p>
     * 
     * 
     */
    @SerializedName("bidsize")
    @Expose
    private Long bidsize;
    /**
     * The exchange code of the bid quote
     * <p>
     * 
     * 
     */
    @SerializedName("bidexchange")
    @Expose
    private Integer bidexchange;
    /**
     * Epoch timestamp in nanoseconds
     * <p>
     * 
     * 
     */
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;
    private final static long serialVersionUID = 1618907571615940831L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LastQuote() {
    }

    /**
     * 
     * @param bidexchange
     * @param askprice
     * @param askexchange
     * @param bidsize
     * @param asksize
     * @param bidprice
     * @param timestamp
     */
    public LastQuote(Double askprice, Long asksize, Integer askexchange, Double bidprice, Long bidsize, Integer bidexchange, Long timestamp) {
        super();
        this.askprice = askprice;
        this.asksize = asksize;
        this.askexchange = askexchange;
        this.bidprice = bidprice;
        this.bidsize = bidsize;
        this.bidexchange = bidexchange;
        this.timestamp = timestamp;
    }

    /**
     * The current ask price
     * <p>
     * 
     * 
     */
    public Double getAskprice() {
        return askprice;
    }

    /**
     * The current ask price
     * <p>
     * 
     * 
     */
    public void setAskprice(Double askprice) {
        this.askprice = askprice;
    }

    /**
     * The current ask size
     * <p>
     * 
     * 
     */
    public Long getAsksize() {
        return asksize;
    }

    /**
     * The current ask size
     * <p>
     * 
     * 
     */
    public void setAsksize(Long asksize) {
        this.asksize = asksize;
    }

    /**
     * The exchange code of the ask quote
     * <p>
     * 
     * 
     */
    public Integer getAskexchange() {
        return askexchange;
    }

    /**
     * The exchange code of the ask quote
     * <p>
     * 
     * 
     */
    public void setAskexchange(Integer askexchange) {
        this.askexchange = askexchange;
    }

    /**
     * The current bid price
     * <p>
     * 
     * 
     */
    public Double getBidprice() {
        return bidprice;
    }

    /**
     * The current bid price
     * <p>
     * 
     * 
     */
    public void setBidprice(Double bidprice) {
        this.bidprice = bidprice;
    }

    /**
     * The current bid size
     * <p>
     * 
     * 
     */
    public Long getBidsize() {
        return bidsize;
    }

    /**
     * The current bid size
     * <p>
     * 
     * 
     */
    public void setBidsize(Long bidsize) {
        this.bidsize = bidsize;
    }

    /**
     * The exchange code of the bid quote
     * <p>
     * 
     * 
     */
    public Integer getBidexchange() {
        return bidexchange;
    }

    /**
     * The exchange code of the bid quote
     * <p>
     * 
     * 
     */
    public void setBidexchange(Integer bidexchange) {
        this.bidexchange = bidexchange;
    }

    /**
     * Epoch timestamp in nanoseconds
     * <p>
     * 
     * 
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Epoch timestamp in nanoseconds
     * <p>
     * 
     * 
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LastQuote.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("askprice");
        sb.append('=');
        sb.append(((this.askprice == null)?"<null>":this.askprice));
        sb.append(',');
        sb.append("asksize");
        sb.append('=');
        sb.append(((this.asksize == null)?"<null>":this.asksize));
        sb.append(',');
        sb.append("askexchange");
        sb.append('=');
        sb.append(((this.askexchange == null)?"<null>":this.askexchange));
        sb.append(',');
        sb.append("bidprice");
        sb.append('=');
        sb.append(((this.bidprice == null)?"<null>":this.bidprice));
        sb.append(',');
        sb.append("bidsize");
        sb.append('=');
        sb.append(((this.bidsize == null)?"<null>":this.bidsize));
        sb.append(',');
        sb.append("bidexchange");
        sb.append('=');
        sb.append(((this.bidexchange == null)?"<null>":this.bidexchange));
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
        result = ((result* 31)+((this.bidexchange == null)? 0 :this.bidexchange.hashCode()));
        result = ((result* 31)+((this.askprice == null)? 0 :this.askprice.hashCode()));
        result = ((result* 31)+((this.askexchange == null)? 0 :this.askexchange.hashCode()));
        result = ((result* 31)+((this.bidsize == null)? 0 :this.bidsize.hashCode()));
        result = ((result* 31)+((this.asksize == null)? 0 :this.asksize.hashCode()));
        result = ((result* 31)+((this.bidprice == null)? 0 :this.bidprice.hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LastQuote) == false) {
            return false;
        }
        LastQuote rhs = ((LastQuote) other);
        return ((((((((this.bidexchange == rhs.bidexchange)||((this.bidexchange!= null)&&this.bidexchange.equals(rhs.bidexchange)))&&((this.askprice == rhs.askprice)||((this.askprice!= null)&&this.askprice.equals(rhs.askprice))))&&((this.askexchange == rhs.askexchange)||((this.askexchange!= null)&&this.askexchange.equals(rhs.askexchange))))&&((this.bidsize == rhs.bidsize)||((this.bidsize!= null)&&this.bidsize.equals(rhs.bidsize))))&&((this.asksize == rhs.asksize)||((this.asksize!= null)&&this.asksize.equals(rhs.asksize))))&&((this.bidprice == rhs.bidprice)||((this.bidprice!= null)&&this.bidprice.equals(rhs.bidprice))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
    }

}
