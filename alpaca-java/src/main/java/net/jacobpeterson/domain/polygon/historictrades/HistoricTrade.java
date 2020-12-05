
package net.jacobpeterson.domain.polygon.historictrades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v2_ticks_stocks_trades_ticker_date">https://polygon.io/docs/#!/Stocks--Equities/get_v2_ticks_stocks_trades_ticker_date</a>
 * <p>
 * 
 * 
 */
public class HistoricTrade implements Serializable
{

    /**
     * Ticker of the object
     * <p>
     * 

     * Corresponds to the "T" property.
     * 
     */
    @SerializedName("T")
    @Expose
    private String ticker;
    /**
     * Nanosecond accuracy SIP Unix Timestamp
     * <p>
     * 
     * 
     */
    @SerializedName("t")
    @Expose
    private Long t;
    /**
     * Nanosecond accuracy Participant/Exchange Unix Timestamp
     * <p>
     * 
     * 
     */
    @SerializedName("y")
    @Expose
    private Long y;
    /**
     * Nanosecond accuracy TRF(Trade Reporting Facility) Unix Timestamp
     * <p>
     * 
     * 
     */
    @SerializedName("f")
    @Expose
    private Long f;
    /**
     * Sequence Number
     * <p>
     * 
     * 
     */
    @SerializedName("q")
    @Expose
    private Integer q;
    /**
     * Trade ID
     * <p>
     * 
     * 
     */
    @SerializedName("i")
    @Expose
    private String i;
    /**
     * Exchange ID
     * <p>
     * 
     * 
     */
    @SerializedName("x")
    @Expose
    private Integer x;
    /**
     * Size/Volume of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("s")
    @Expose
    private Integer s;
    /**
     * Conditions
     * <p>
     * 
     * 
     */
    @SerializedName("c")
    @Expose
    private ArrayList<Integer> c;
    /**
     * Price of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("p")
    @Expose
    private Double p;
    /**
     * Tape where trade occured. ( 1,2 = CTA, 3 = UTP )
     * <p>
     * 
     * 
     */
    @SerializedName("z")
    @Expose
    private Integer z;
    private final static long serialVersionUID = 8146370321458518239L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HistoricTrade() {
    }

    /**
     * 
     * @param p
     * @param q
     * @param ticker
     * @param s
     * @param c
     * @param t
     * @param f
     * @param x
     * @param y
     * @param i
     * @param z
     */
    public HistoricTrade(String ticker, Long t, Long y, Long f, Integer q, String i, Integer x, Integer s, ArrayList<Integer> c, Double p, Integer z) {
        super();
        this.ticker = ticker;
        this.t = t;
        this.y = y;
        this.f = f;
        this.q = q;
        this.i = i;
        this.x = x;
        this.s = s;
        this.c = c;
        this.p = p;
        this.z = z;
    }

    /**
     * Ticker of the object
     * <p>
     * 

     * Corresponds to the "T" property.
     * 
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Ticker of the object
     * <p>
     * 

     * Corresponds to the "T" property.
     * 
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * Nanosecond accuracy SIP Unix Timestamp
     * <p>
     * 
     * 
     */
    public Long getT() {
        return t;
    }

    /**
     * Nanosecond accuracy SIP Unix Timestamp
     * <p>
     * 
     * 
     */
    public void setT(Long t) {
        this.t = t;
    }

    /**
     * Nanosecond accuracy Participant/Exchange Unix Timestamp
     * <p>
     * 
     * 
     */
    public Long getY() {
        return y;
    }

    /**
     * Nanosecond accuracy Participant/Exchange Unix Timestamp
     * <p>
     * 
     * 
     */
    public void setY(Long y) {
        this.y = y;
    }

    /**
     * Nanosecond accuracy TRF(Trade Reporting Facility) Unix Timestamp
     * <p>
     * 
     * 
     */
    public Long getF() {
        return f;
    }

    /**
     * Nanosecond accuracy TRF(Trade Reporting Facility) Unix Timestamp
     * <p>
     * 
     * 
     */
    public void setF(Long f) {
        this.f = f;
    }

    /**
     * Sequence Number
     * <p>
     * 
     * 
     */
    public Integer getQ() {
        return q;
    }

    /**
     * Sequence Number
     * <p>
     * 
     * 
     */
    public void setQ(Integer q) {
        this.q = q;
    }

    /**
     * Trade ID
     * <p>
     * 
     * 
     */
    public String getI() {
        return i;
    }

    /**
     * Trade ID
     * <p>
     * 
     * 
     */
    public void setI(String i) {
        this.i = i;
    }

    /**
     * Exchange ID
     * <p>
     * 
     * 
     */
    public Integer getX() {
        return x;
    }

    /**
     * Exchange ID
     * <p>
     * 
     * 
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Size/Volume of the trade
     * <p>
     * 
     * 
     */
    public Integer getS() {
        return s;
    }

    /**
     * Size/Volume of the trade
     * <p>
     * 
     * 
     */
    public void setS(Integer s) {
        this.s = s;
    }

    /**
     * Conditions
     * <p>
     * 
     * 
     */
    public ArrayList<Integer> getC() {
        return c;
    }

    /**
     * Conditions
     * <p>
     * 
     * 
     */
    public void setC(ArrayList<Integer> c) {
        this.c = c;
    }

    /**
     * Price of the trade
     * <p>
     * 
     * 
     */
    public Double getP() {
        return p;
    }

    /**
     * Price of the trade
     * <p>
     * 
     * 
     */
    public void setP(Double p) {
        this.p = p;
    }

    /**
     * Tape where trade occured. ( 1,2 = CTA, 3 = UTP )
     * <p>
     * 
     * 
     */
    public Integer getZ() {
        return z;
    }

    /**
     * Tape where trade occured. ( 1,2 = CTA, 3 = UTP )
     * <p>
     * 
     * 
     */
    public void setZ(Integer z) {
        this.z = z;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HistoricTrade.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ticker");
        sb.append('=');
        sb.append(((this.ticker == null)?"<null>":this.ticker));
        sb.append(',');
        sb.append("t");
        sb.append('=');
        sb.append(((this.t == null)?"<null>":this.t));
        sb.append(',');
        sb.append("y");
        sb.append('=');
        sb.append(((this.y == null)?"<null>":this.y));
        sb.append(',');
        sb.append("f");
        sb.append('=');
        sb.append(((this.f == null)?"<null>":this.f));
        sb.append(',');
        sb.append("q");
        sb.append('=');
        sb.append(((this.q == null)?"<null>":this.q));
        sb.append(',');
        sb.append("i");
        sb.append('=');
        sb.append(((this.i == null)?"<null>":this.i));
        sb.append(',');
        sb.append("x");
        sb.append('=');
        sb.append(((this.x == null)?"<null>":this.x));
        sb.append(',');
        sb.append("s");
        sb.append('=');
        sb.append(((this.s == null)?"<null>":this.s));
        sb.append(',');
        sb.append("c");
        sb.append('=');
        sb.append(((this.c == null)?"<null>":this.c));
        sb.append(',');
        sb.append("p");
        sb.append('=');
        sb.append(((this.p == null)?"<null>":this.p));
        sb.append(',');
        sb.append("z");
        sb.append('=');
        sb.append(((this.z == null)?"<null>":this.z));
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
        result = ((result* 31)+((this.p == null)? 0 :this.p.hashCode()));
        result = ((result* 31)+((this.q == null)? 0 :this.q.hashCode()));
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+((this.s == null)? 0 :this.s.hashCode()));
        result = ((result* 31)+((this.c == null)? 0 :this.c.hashCode()));
        result = ((result* 31)+((this.t == null)? 0 :this.t.hashCode()));
        result = ((result* 31)+((this.f == null)? 0 :this.f.hashCode()));
        result = ((result* 31)+((this.x == null)? 0 :this.x.hashCode()));
        result = ((result* 31)+((this.y == null)? 0 :this.y.hashCode()));
        result = ((result* 31)+((this.i == null)? 0 :this.i.hashCode()));
        result = ((result* 31)+((this.z == null)? 0 :this.z.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HistoricTrade) == false) {
            return false;
        }
        HistoricTrade rhs = ((HistoricTrade) other);
        return ((((((((((((this.p == rhs.p)||((this.p!= null)&&this.p.equals(rhs.p)))&&((this.q == rhs.q)||((this.q!= null)&&this.q.equals(rhs.q))))&&((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker))))&&((this.s == rhs.s)||((this.s!= null)&&this.s.equals(rhs.s))))&&((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c))))&&((this.t == rhs.t)||((this.t!= null)&&this.t.equals(rhs.t))))&&((this.f == rhs.f)||((this.f!= null)&&this.f.equals(rhs.f))))&&((this.x == rhs.x)||((this.x!= null)&&this.x.equals(rhs.x))))&&((this.y == rhs.y)||((this.y!= null)&&this.y.equals(rhs.y))))&&((this.i == rhs.i)||((this.i!= null)&&this.i.equals(rhs.i))))&&((this.z == rhs.z)||((this.z!= null)&&this.z.equals(rhs.z))));
    }

}
