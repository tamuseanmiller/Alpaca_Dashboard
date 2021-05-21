
package net.jacobpeterson.domain.alpaca.marketdata.streaming.quote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.alpaca.marketdata.streaming.abstracts.MarketDataStreamDataMessage;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/</a>
 * <p>
 * 
 * 
 */
public class QuoteMessage
    extends MarketDataStreamDataMessage
    implements Serializable
{

    /**
     * Bix Exchange ID
     * <p>
     * 

     * Corresponds to the "x" property.
     * 
     */
    @SerializedName("x")
    @Expose
    private String bx;
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
     * Bid Size
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    @SerializedName("s")
    @Expose
    private Integer bs;
    /**
     * Ask Exchange ID
     * <p>
     * 

     * Corresponds to the "X" property.
     * 
     */
    @SerializedName("X")
    @Expose
    private String ax;
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
     * Ask Size
     * <p>
     * 

     * Corresponds to the "S" property.
     * 
     */
    @SerializedName("S")
    @Expose
    private Integer as;
    /**
     * Quote condition flags
     * <p>
     * 
     * 
     */
    @SerializedName("c")
    @Expose
    private ArrayList<Integer> c;
    /**
     * Quote Timestamp ( Unix MS )
     * <p>
     * 
     * 
     */
    @SerializedName("t")
    @Expose
    private Long t;
    private final static long serialVersionUID = -2308687439196058407L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public QuoteMessage() {
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
    public QuoteMessage(String bx, Double bp, Integer bs, String ax, Double ap, Integer as, ArrayList<Integer> c, Long t) {
        super();
        this.bx = bx;
        this.bp = bp;
        this.bs = bs;
        this.ax = ax;
        this.ap = ap;
        this.as = as;
        this.c = c;
        this.t = t;
    }

    /**
     * Bix Exchange ID
     * <p>
     * 

     * Corresponds to the "x" property.
     * 
     */
    public String getBx() {
        return bx;
    }

    /**
     * Bix Exchange ID
     * <p>
     * 

     * Corresponds to the "x" property.
     * 
     */
    public void setBx(String bx) {
        this.bx = bx;
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
     * Bid Size
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    public Integer getBs() {
        return bs;
    }

    /**
     * Bid Size
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    public void setBs(Integer bs) {
        this.bs = bs;
    }

    /**
     * Ask Exchange ID
     * <p>
     * 

     * Corresponds to the "X" property.
     * 
     */
    public String getAx() {
        return ax;
    }

    /**
     * Ask Exchange ID
     * <p>
     * 

     * Corresponds to the "X" property.
     * 
     */
    public void setAx(String ax) {
        this.ax = ax;
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
     * Ask Size
     * <p>
     * 

     * Corresponds to the "S" property.
     * 
     */
    public Integer getAs() {
        return as;
    }

    /**
     * Ask Size
     * <p>
     * 

     * Corresponds to the "S" property.
     * 
     */
    public void setAs(Integer as) {
        this.as = as;
    }

    /**
     * Quote condition flags
     * <p>
     * 
     * 
     */
    public ArrayList<Integer> getC() {
        return c;
    }

    /**
     * Quote condition flags
     * <p>
     * 
     * 
     */
    public void setC(ArrayList<Integer> c) {
        this.c = c;
    }

    /**
     * Quote Timestamp ( Unix MS )
     * <p>
     * 
     * 
     */
    public Long getT() {
        return t;
    }

    /**
     * Quote Timestamp ( Unix MS )
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
        sb.append(QuoteMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("c");
        sb.append('=');
        sb.append(((this.c == null)?"<null>":this.c));
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
        result = ((result* 31)+((this.c == null)? 0 :this.c.hashCode()));
        result = ((result* 31)+((this.t == null)? 0 :this.t.hashCode()));
        result = ((result* 31)+((this.bx == null)? 0 :this.bx.hashCode()));
        result = ((result* 31)+((this.ax == null)? 0 :this.ax.hashCode()));
        result = ((result* 31)+((this.bp == null)? 0 :this.bp.hashCode()));
        result = ((result* 31)+((this.ap == null)? 0 :this.ap.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QuoteMessage) == false) {
            return false;
        }
        QuoteMessage rhs = ((QuoteMessage) other);
        return ((((((((super.equals(rhs)&&((this.bs == rhs.bs)||((this.bs!= null)&&this.bs.equals(rhs.bs))))&&((this.as == rhs.as)||((this.as!= null)&&this.as.equals(rhs.as))))&&((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c))))&&((this.t == rhs.t)||((this.t!= null)&&this.t.equals(rhs.t))))&&((this.bx == rhs.bx)||((this.bx!= null)&&this.bx.equals(rhs.bx))))&&((this.ax == rhs.ax)||((this.ax!= null)&&this.ax.equals(rhs.ax))))&&((this.bp == rhs.bp)||((this.bp!= null)&&this.bp.equals(rhs.bp))))&&((this.ap == rhs.ap)||((this.ap!= null)&&this.ap.equals(rhs.ap))));
    }

}
