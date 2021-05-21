
package net.jacobpeterson.domain.alpaca.marketdata.historical.quote;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/historical/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/alpaca-data-api-v2/historical/</a>
 * <p>
 * 
 * 
 */
public class Quote implements Serializable
{

    /**
     * Timestamp with nanosecond precision.
     * <p>
     * 
     * 
     */
    @SerializedName("t")
    @Expose
    private ZonedDateTime t;
    /**
     * Ask exchange.
     * <p>
     * 
     * 
     */
    @SerializedName("ax")
    @Expose
    private String ax;
    /**
     * Ask price.
     * <p>
     * 
     * 
     */
    @SerializedName("ap")
    @Expose
    private Double ap;
    /**
     * Ask size.
     * <p>
     * 
     * 
     */
    @SerializedName("as")
    @Expose
    private Integer as;
    /**
     * Bid exchange.
     * <p>
     * 
     * 
     */
    @SerializedName("bx")
    @Expose
    private String bx;
    /**
     * Bid price.
     * <p>
     * 
     * 
     */
    @SerializedName("bp")
    @Expose
    private Double bp;
    /**
     * Bid size.
     * <p>
     * 
     * 
     */
    @SerializedName("bs")
    @Expose
    private Integer bs;
    /**
     * Quote conditions.
     * <p>
     * 
     * 
     */
    @SerializedName("c")
    @Expose
    private ArrayList<String> c;
    private final static long serialVersionUID = 3051258995795403096L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Quote() {
    }

    /**
     * 
     * @param bs
     * @param as
     * @param c
     * @param t
     * @param bx
     * @param ax
     * @param bp
     * @param ap
     */
    public Quote(ZonedDateTime t, String ax, Double ap, Integer as, String bx, Double bp, Integer bs, ArrayList<String> c) {
        super();
        this.t = t;
        this.ax = ax;
        this.ap = ap;
        this.as = as;
        this.bx = bx;
        this.bp = bp;
        this.bs = bs;
        this.c = c;
    }

    /**
     * Timestamp with nanosecond precision.
     * <p>
     * 
     * 
     */
    public ZonedDateTime getT() {
        return t;
    }

    /**
     * Timestamp with nanosecond precision.
     * <p>
     * 
     * 
     */
    public void setT(ZonedDateTime t) {
        this.t = t;
    }

    /**
     * Ask exchange.
     * <p>
     * 
     * 
     */
    public String getAx() {
        return ax;
    }

    /**
     * Ask exchange.
     * <p>
     * 
     * 
     */
    public void setAx(String ax) {
        this.ax = ax;
    }

    /**
     * Ask price.
     * <p>
     * 
     * 
     */
    public Double getAp() {
        return ap;
    }

    /**
     * Ask price.
     * <p>
     * 
     * 
     */
    public void setAp(Double ap) {
        this.ap = ap;
    }

    /**
     * Ask size.
     * <p>
     * 
     * 
     */
    public Integer getAs() {
        return as;
    }

    /**
     * Ask size.
     * <p>
     * 
     * 
     */
    public void setAs(Integer as) {
        this.as = as;
    }

    /**
     * Bid exchange.
     * <p>
     * 
     * 
     */
    public String getBx() {
        return bx;
    }

    /**
     * Bid exchange.
     * <p>
     * 
     * 
     */
    public void setBx(String bx) {
        this.bx = bx;
    }

    /**
     * Bid price.
     * <p>
     * 
     * 
     */
    public Double getBp() {
        return bp;
    }

    /**
     * Bid price.
     * <p>
     * 
     * 
     */
    public void setBp(Double bp) {
        this.bp = bp;
    }

    /**
     * Bid size.
     * <p>
     * 
     * 
     */
    public Integer getBs() {
        return bs;
    }

    /**
     * Bid size.
     * <p>
     * 
     * 
     */
    public void setBs(Integer bs) {
        this.bs = bs;
    }

    /**
     * Quote conditions.
     * <p>
     * 
     * 
     */
    public ArrayList<String> getC() {
        return c;
    }

    /**
     * Quote conditions.
     * <p>
     * 
     * 
     */
    public void setC(ArrayList<String> c) {
        this.c = c;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Quote.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("t");
        sb.append('=');
        sb.append(((this.t == null)?"<null>":this.t));
        sb.append(',');
        sb.append("ax");
        sb.append('=');
        sb.append(((this.ax == null)?"<null>":this.ax));
        sb.append(',');
        sb.append("ap");
        sb.append('=');
        sb.append(((this.ap == null)?"<null>":this.ap));
        sb.append(',');
        sb.append("as");
        sb.append('=');
        sb.append(((this.as == null)?"<null>":this.as));
        sb.append(',');
        sb.append("bx");
        sb.append('=');
        sb.append(((this.bx == null)?"<null>":this.bx));
        sb.append(',');
        sb.append("bp");
        sb.append('=');
        sb.append(((this.bp == null)?"<null>":this.bp));
        sb.append(',');
        sb.append("bs");
        sb.append('=');
        sb.append(((this.bs == null)?"<null>":this.bs));
        sb.append(',');
        sb.append("c");
        sb.append('=');
        sb.append(((this.c == null)?"<null>":this.c));
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
        result = ((result* 31)+((this.c == null)? 0 :this.c.hashCode()));
        result = ((result* 31)+((this.t == null)? 0 :this.t.hashCode()));
        result = ((result* 31)+((this.bx == null)? 0 :this.bx.hashCode()));
        result = ((result* 31)+((this.ax == null)? 0 :this.ax.hashCode()));
        result = ((result* 31)+((this.bp == null)? 0 :this.bp.hashCode()));
        result = ((result* 31)+((this.ap == null)? 0 :this.ap.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Quote) == false) {
            return false;
        }
        Quote rhs = ((Quote) other);
        return (((((((((this.bs == rhs.bs)||((this.bs!= null)&&this.bs.equals(rhs.bs)))&&((this.as == rhs.as)||((this.as!= null)&&this.as.equals(rhs.as))))&&((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c))))&&((this.t == rhs.t)||((this.t!= null)&&this.t.equals(rhs.t))))&&((this.bx == rhs.bx)||((this.bx!= null)&&this.bx.equals(rhs.bx))))&&((this.ax == rhs.ax)||((this.ax!= null)&&this.ax.equals(rhs.ax))))&&((this.bp == rhs.bp)||((this.bp!= null)&&this.bp.equals(rhs.bp))))&&((this.ap == rhs.ap)||((this.ap!= null)&&this.ap.equals(rhs.ap))));
    }

}
