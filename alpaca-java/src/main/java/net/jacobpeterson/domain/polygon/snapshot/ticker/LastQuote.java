
package net.jacobpeterson.domain.polygon.snapshot.ticker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v2_snapshot_locale_us_markets_stocks_tickers">https://polygon.io/docs/#!/Stocks--Equities/get_v2_snapshot_locale_us_markets_stocks_tickers</a>
 * <p>
 * 
 * 
 */
public class LastQuote implements Serializable
{

    /**
     * Bid Price
     * <p>
     * 

     * Corresponds to the "p" property.
     * 
     */
    @SerializedName("p")
    @Expose
    private Double bp;
    /**
     * Bid size in lots
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    @SerializedName("s")
    @Expose
    private Integer bs;
    /**
     * Ask Price
     * <p>
     * 

     * Corresponds to the "P" property.
     * 
     */
    @SerializedName("P")
    @Expose
    private Double ap;
    /**
     * Ask size in lots
     * <p>
     * 

     * Corresponds to the "S" property.
     * 
     */
    @SerializedName("S")
    @Expose
    private Integer as;
    /**
     * Last Updated timestamp ( Nanosecond Timestamp )
     * <p>
     * 
     * 
     */
    @SerializedName("t")
    @Expose
    private Long t;
    private final static long serialVersionUID = -2349470291420353269L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LastQuote() {
    }

    /**
     * 
     * @param bs
     * @param as
     * @param t
     * @param bp
     * @param ap
     */
    public LastQuote(Double bp, Integer bs, Double ap, Integer as, Long t) {
        super();
        this.bp = bp;
        this.bs = bs;
        this.ap = ap;
        this.as = as;
        this.t = t;
    }

    /**
     * Bid Price
     * <p>
     * 

     * Corresponds to the "p" property.
     * 
     */
    public Double getBp() {
        return bp;
    }

    /**
     * Bid Price
     * <p>
     * 

     * Corresponds to the "p" property.
     * 
     */
    public void setBp(Double bp) {
        this.bp = bp;
    }

    /**
     * Bid size in lots
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    public Integer getBs() {
        return bs;
    }

    /**
     * Bid size in lots
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    public void setBs(Integer bs) {
        this.bs = bs;
    }

    /**
     * Ask Price
     * <p>
     * 

     * Corresponds to the "P" property.
     * 
     */
    public Double getAp() {
        return ap;
    }

    /**
     * Ask Price
     * <p>
     * 

     * Corresponds to the "P" property.
     * 
     */
    public void setAp(Double ap) {
        this.ap = ap;
    }

    /**
     * Ask size in lots
     * <p>
     * 

     * Corresponds to the "S" property.
     * 
     */
    public Integer getAs() {
        return as;
    }

    /**
     * Ask size in lots
     * <p>
     * 

     * Corresponds to the "S" property.
     * 
     */
    public void setAs(Integer as) {
        this.as = as;
    }

    /**
     * Last Updated timestamp ( Nanosecond Timestamp )
     * <p>
     * 
     * 
     */
    public Long getT() {
        return t;
    }

    /**
     * Last Updated timestamp ( Nanosecond Timestamp )
     * <p>
     * 
     * 
     */
    public void setT(Long t) {
        this.t = t;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LastQuote.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bp");
        sb.append('=');
        sb.append(((this.bp == null)?"<null>":this.bp));
        sb.append(',');
        sb.append("bs");
        sb.append('=');
        sb.append(((this.bs == null)?"<null>":this.bs));
        sb.append(',');
        sb.append("ap");
        sb.append('=');
        sb.append(((this.ap == null)?"<null>":this.ap));
        sb.append(',');
        sb.append("as");
        sb.append('=');
        sb.append(((this.as == null)?"<null>":this.as));
        sb.append(',');
        sb.append("t");
        sb.append('=');
        sb.append(((this.t == null)?"<null>":this.t));
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
        result = ((result* 31)+((this.bs == null)? 0 :this.bs.hashCode()));
        result = ((result* 31)+((this.as == null)? 0 :this.as.hashCode()));
        result = ((result* 31)+((this.t == null)? 0 :this.t.hashCode()));
        result = ((result* 31)+((this.bp == null)? 0 :this.bp.hashCode()));
        result = ((result* 31)+((this.ap == null)? 0 :this.ap.hashCode()));
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
        return ((((((this.bs == rhs.bs)||((this.bs!= null)&&this.bs.equals(rhs.bs)))&&((this.as == rhs.as)||((this.as!= null)&&this.as.equals(rhs.as))))&&((this.t == rhs.t)||((this.t!= null)&&this.t.equals(rhs.t))))&&((this.bp == rhs.bp)||((this.bp!= null)&&this.bp.equals(rhs.bp))))&&((this.ap == rhs.ap)||((this.ap!= null)&&this.ap.equals(rhs.ap))));
    }

}
