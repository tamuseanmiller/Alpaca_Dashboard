
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
public class TickerCandle implements Serializable
{

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
     * Open price
     * <p>
     * 
     * 
     */
    @SerializedName("o")
    @Expose
    private Double o;
    /**
     * Volume
     * <p>
     * 
     * 
     */
    @SerializedName("v")
    @Expose
    private Double v;
    private final static long serialVersionUID = 8540430718766722397L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TickerCandle() {
    }

    /**
     * 
     * @param c
     * @param v
     * @param h
     * @param l
     * @param o
     */
    public TickerCandle(Double c, Double h, Double l, Double o, Double v) {
        super();
        this.c = c;
        this.h = h;
        this.l = l;
        this.o = o;
        this.v = v;
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
        sb.append(TickerCandle.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("c");
        sb.append('=');
        sb.append(((this.c == null)?"<null>":this.c));
        sb.append(',');
        sb.append("h");
        sb.append('=');
        sb.append(((this.h == null)?"<null>":this.h));
        sb.append(',');
        sb.append("l");
        sb.append('=');
        sb.append(((this.l == null)?"<null>":this.l));
        sb.append(',');
        sb.append("o");
        sb.append('=');
        sb.append(((this.o == null)?"<null>":this.o));
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
        result = ((result* 31)+((this.h == null)? 0 :this.h.hashCode()));
        result = ((result* 31)+((this.c == null)? 0 :this.c.hashCode()));
        result = ((result* 31)+((this.l == null)? 0 :this.l.hashCode()));
        result = ((result* 31)+((this.v == null)? 0 :this.v.hashCode()));
        result = ((result* 31)+((this.o == null)? 0 :this.o.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TickerCandle) == false) {
            return false;
        }
        TickerCandle rhs = ((TickerCandle) other);
        return ((((((this.h == rhs.h)||((this.h!= null)&&this.h.equals(rhs.h)))&&((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c))))&&((this.l == rhs.l)||((this.l!= null)&&this.l.equals(rhs.l))))&&((this.v == rhs.v)||((this.v!= null)&&this.v.equals(rhs.v))))&&((this.o == rhs.o)||((this.o!= null)&&this.o.equals(rhs.o))));
    }

}
