
package net.jacobpeterson.domain.polygon.aggregates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v2_aggs_ticker_ticker_range_multiplier_timespan_from_to">https://polygon.io/docs/#!/Stocks--Equities/get_v2_aggs_ticker_ticker_range_multiplier_timespan_from_to</a>
 * <p>
 * 
 * 
 */
public class Aggregate implements Serializable
{

    /**
     * Ticker symbol
     * <p>
     * 

     * Corresponds to the "T" property.
     * 
     */
    @SerializedName("T")
    @Expose
    private String ticker;
    /**
     * Volume
     * <p>
     * 
     * 
     */
    @SerializedName("v")
    @Expose
    private Long v;
    /**
     * Open
     * <p>
     * 
     * 
     */
    @SerializedName("o")
    @Expose
    private Double o;
    /**
     * Close
     * <p>
     * 
     * 
     */
    @SerializedName("c")
    @Expose
    private Double c;
    /**
     * High
     * <p>
     * 
     * 
     */
    @SerializedName("h")
    @Expose
    private Double h;
    /**
     * Low
     * <p>
     * 
     * 
     */
    @SerializedName("l")
    @Expose
    private Double l;
    /**
     * Unix Msec Timestamp ( Start of Aggregate window )
     * <p>
     * 
     * 
     */
    @SerializedName("t")
    @Expose
    private Long t;
    /**
     * Number of items in aggregate window
     * <p>
     * 
     * 
     */
    @SerializedName("n")
    @Expose
    private Integer n;
    private final static long serialVersionUID = 1564316639887899176L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Aggregate() {
    }

    /**
     * 
     * @param ticker
     * @param c
     * @param t
     * @param v
     * @param h
     * @param l
     * @param n
     * @param o
     */
    public Aggregate(String ticker, Long v, Double o, Double c, Double h, Double l, Long t, Integer n) {
        super();
        this.ticker = ticker;
        this.v = v;
        this.o = o;
        this.c = c;
        this.h = h;
        this.l = l;
        this.t = t;
        this.n = n;
    }

    /**
     * Ticker symbol
     * <p>
     * 

     * Corresponds to the "T" property.
     * 
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Ticker symbol
     * <p>
     * 

     * Corresponds to the "T" property.
     * 
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * Volume
     * <p>
     * 
     * 
     */
    public Long getV() {
        return v;
    }

    /**
     * Volume
     * <p>
     * 
     * 
     */
    public void setV(Long v) {
        this.v = v;
    }

    /**
     * Open
     * <p>
     * 
     * 
     */
    public Double getO() {
        return o;
    }

    /**
     * Open
     * <p>
     * 
     * 
     */
    public void setO(Double o) {
        this.o = o;
    }

    /**
     * Close
     * <p>
     * 
     * 
     */
    public Double getC() {
        return c;
    }

    /**
     * Close
     * <p>
     * 
     * 
     */
    public void setC(Double c) {
        this.c = c;
    }

    /**
     * High
     * <p>
     * 
     * 
     */
    public Double getH() {
        return h;
    }

    /**
     * High
     * <p>
     * 
     * 
     */
    public void setH(Double h) {
        this.h = h;
    }

    /**
     * Low
     * <p>
     * 
     * 
     */
    public Double getL() {
        return l;
    }

    /**
     * Low
     * <p>
     * 
     * 
     */
    public void setL(Double l) {
        this.l = l;
    }

    /**
     * Unix Msec Timestamp ( Start of Aggregate window )
     * <p>
     * 
     * 
     */
    public Long getT() {
        return t;
    }

    /**
     * Unix Msec Timestamp ( Start of Aggregate window )
     * <p>
     * 
     * 
     */
    public void setT(Long t) {
        this.t = t;
    }

    /**
     * Number of items in aggregate window
     * <p>
     * 
     * 
     */
    public Integer getN() {
        return n;
    }

    /**
     * Number of items in aggregate window
     * <p>
     * 
     * 
     */
    public void setN(Integer n) {
        this.n = n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Aggregate.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ticker");
        sb.append('=');
        sb.append(((this.ticker == null)?"<null>":this.ticker));
        sb.append(',');
        sb.append("v");
        sb.append('=');
        sb.append(((this.v == null)?"<null>":this.v));
        sb.append(',');
        sb.append("o");
        sb.append('=');
        sb.append(((this.o == null)?"<null>":this.o));
        sb.append(',');
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
        sb.append("t");
        sb.append('=');
        sb.append(((this.t == null)?"<null>":this.t));
        sb.append(',');
        sb.append("n");
        sb.append('=');
        sb.append(((this.n == null)?"<null>":this.n));
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
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+((this.c == null)? 0 :this.c.hashCode()));
        result = ((result* 31)+((this.t == null)? 0 :this.t.hashCode()));
        result = ((result* 31)+((this.v == null)? 0 :this.v.hashCode()));
        result = ((result* 31)+((this.h == null)? 0 :this.h.hashCode()));
        result = ((result* 31)+((this.l == null)? 0 :this.l.hashCode()));
        result = ((result* 31)+((this.n == null)? 0 :this.n.hashCode()));
        result = ((result* 31)+((this.o == null)? 0 :this.o.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Aggregate) == false) {
            return false;
        }
        Aggregate rhs = ((Aggregate) other);
        return (((((((((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker)))&&((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c))))&&((this.t == rhs.t)||((this.t!= null)&&this.t.equals(rhs.t))))&&((this.v == rhs.v)||((this.v!= null)&&this.v.equals(rhs.v))))&&((this.h == rhs.h)||((this.h!= null)&&this.h.equals(rhs.h))))&&((this.l == rhs.l)||((this.l!= null)&&this.l.equals(rhs.l))))&&((this.n == rhs.n)||((this.n!= null)&&this.n.equals(rhs.n))))&&((this.o == rhs.o)||((this.o!= null)&&this.o.equals(rhs.o))));
    }

}
