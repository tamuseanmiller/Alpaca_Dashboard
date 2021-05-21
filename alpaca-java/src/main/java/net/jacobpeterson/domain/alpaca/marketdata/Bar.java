
package net.jacobpeterson.domain.alpaca.marketdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/bars/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/bars/</a>
 * <p>
 * 
 * 
 */
public class Bar implements Serializable
{

    /**
     * The beginning time of this bar as a Unix epoch in seconds
     * <p>
     * 
     * 
     */
    @SerializedName("t")
    @Expose
    private Long t;
    /**
     * Open price
     * <p>
     * 
     * 
     */
    @SerializedName("o")
    @Expose
    private Double o;
    /**
     * High price
     * <p>
     * 
     * 
     */
    @SerializedName("h")
    @Expose
    private Double h;
    /**
     * Low price
     * <p>
     * 
     * 
     */
    @SerializedName("l")
    @Expose
    private Double l;
    /**
     * Close price
     * <p>
     * 
     * 
     */
    @SerializedName("c")
    @Expose
    private Double c;
    /**
     * Volume
     * <p>
     * 
     * 
     */
    @SerializedName("v")
    @Expose
    private Double v;
    private final static long serialVersionUID = -4189687874251747182L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Bar() {
    }

    /**
     * 
     * @param c
     * @param t
     * @param v
     * @param h
     * @param l
     * @param o
     */
    public Bar(Long t, Double o, Double h, Double l, Double c, Double v) {
        super();
        this.t = t;
        this.o = o;
        this.h = h;
        this.l = l;
        this.c = c;
        this.v = v;
    }

    /**
     * The beginning time of this bar as a Unix epoch in seconds
     * <p>
     * 
     * 
     */
    public Long getT() {
        return t;
    }

    /**
     * The beginning time of this bar as a Unix epoch in seconds
     * <p>
     * 
     * 
     */
    public void setT(Long t) {
        this.t = t;
    }

    /**
     * Open price
     * <p>
     * 
     * 
     */
    public Double getO() {
        return o;
    }

    /**
     * Open price
     * <p>
     * 
     * 
     */
    public void setO(Double o) {
        this.o = o;
    }

    /**
     * High price
     * <p>
     * 
     * 
     */
    public Double getH() {
        return h;
    }

    /**
     * High price
     * <p>
     * 
     * 
     */
    public void setH(Double h) {
        this.h = h;
    }

    /**
     * Low price
     * <p>
     * 
     * 
     */
    public Double getL() {
        return l;
    }

    /**
     * Low price
     * <p>
     * 
     * 
     */
    public void setL(Double l) {
        this.l = l;
    }

    /**
     * Close price
     * <p>
     * 
     * 
     */
    public Double getC() {
        return c;
    }

    /**
     * Close price
     * <p>
     * 
     * 
     */
    public void setC(Double c) {
        this.c = c;
    }

    /**
     * Volume
     * <p>
     * 
     * 
     */
    public Double getV() {
        return v;
    }

    /**
     * Volume
     * <p>
     * 
     * 
     */
    public void setV(Double v) {
        this.v = v;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Bar.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("t");
        sb.append('=');
        sb.append(((this.t == null)?"<null>":this.t));
        sb.append(',');
        sb.append("o");
        sb.append('=');
        sb.append(((this.o == null)?"<null>":this.o));
        sb.append(',');
        sb.append("h");
        sb.append('=');
        sb.append(((this.h == null)?"<null>":this.h));
        sb.append(',');
        sb.append("l");
        sb.append('=');
        sb.append(((this.l == null)?"<null>":this.l));
        sb.append(',');
        sb.append("c");
        sb.append('=');
        sb.append(((this.c == null)?"<null>":this.c));
        sb.append(',');
        sb.append("v");
        sb.append('=');
        sb.append(((this.v == null)?"<null>":this.v));
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
        result = ((result* 31)+((this.c == null)? 0 :this.c.hashCode()));
        result = ((result* 31)+((this.t == null)? 0 :this.t.hashCode()));
        result = ((result* 31)+((this.v == null)? 0 :this.v.hashCode()));
        result = ((result* 31)+((this.h == null)? 0 :this.h.hashCode()));
        result = ((result* 31)+((this.l == null)? 0 :this.l.hashCode()));
        result = ((result* 31)+((this.o == null)? 0 :this.o.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Bar) == false) {
            return false;
        }
        Bar rhs = ((Bar) other);
        return (((((((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c)))&&((this.t == rhs.t)||((this.t!= null)&&this.t.equals(rhs.t))))&&((this.v == rhs.v)||((this.v!= null)&&this.v.equals(rhs.v))))&&((this.h == rhs.h)||((this.h!= null)&&this.h.equals(rhs.h))))&&((this.l == rhs.l)||((this.l!= null)&&this.l.equals(rhs.l))))&&((this.o == rhs.o)||((this.o!= null)&&this.o.equals(rhs.o))));
    }

}
