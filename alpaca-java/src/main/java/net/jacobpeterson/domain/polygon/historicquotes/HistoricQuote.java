
package net.jacobpeterson.domain.polygon.historicquotes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v2_ticks_stocks_nbbo_ticker_date">https://polygon.io/docs/#!/Stocks--Equities/get_v2_ticks_stocks_nbbo_ticker_date</a>
 * <p>
 * 
 * 
 */
public class HistoricQuote implements Serializable
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
     * Conditions
     * <p>
     * 
     * 
     */
    @SerializedName("c")
    @Expose
    private ArrayList<Integer> c;
    /**
     * Indicators
     * <p>
     * 
     * 
     */
    @SerializedName("i")
    @Expose
    private ArrayList<Integer> i;
    /**
     * BID Price
     * <p>
     * 

     * Corresponds to the "p" property.
     * 
     */
    @SerializedName("p")
    @Expose
    private Double bp;
    /**
     * BID Exchange ID
     * <p>
     * 

     * Corresponds to the "x" property.
     * 
     */
    @SerializedName("x")
    @Expose
    private Integer bx;
    /**
     * BID Size ( In round lots )
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    @SerializedName("s")
    @Expose
    private Integer bs;
    /**
     * ASK Price
     * <p>
     * 

     * Corresponds to the "P" property.
     * 
     */
    @SerializedName("P")
    @Expose
    private Double ap;
    /**
     * ASK Exchange ID
     * <p>
     * 

     * Corresponds to the "X" property.
     * 
     */
    @SerializedName("X")
    @Expose
    private Integer ax;
    /**
     * ASK Size ( In round lots )
     * <p>
     * 

     * Corresponds to the "S" property.
     * 
     */
    @SerializedName("S")
    @Expose
    private Integer as;
    /**
     * Tape where trade occured. ( 1,2 = CTA, 3 = UTP )
     * <p>
     * 
     * 
     */
    @SerializedName("z")
    @Expose
    private Integer z;
    private final static long serialVersionUID = -2732573883393501115L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HistoricQuote() {
    }

    /**
     * 
     * @param ticker
     * @param c
     * @param f
     * @param i
     * @param bp
     * @param ap
     * @param q
     * @param bs
     * @param as
     * @param t
     * @param bx
     * @param ax
     * @param y
     * @param z
     */
    public HistoricQuote(String ticker, Long t, Long y, Long f, Integer q, ArrayList<Integer> c, ArrayList<Integer> i, Double bp, Integer bx, Integer bs, Double ap, Integer ax, Integer as, Integer z) {
        super();
        this.ticker = ticker;
        this.t = t;
        this.y = y;
        this.f = f;
        this.q = q;
        this.c = c;
        this.i = i;
        this.bp = bp;
        this.bx = bx;
        this.bs = bs;
        this.ap = ap;
        this.ax = ax;
        this.as = as;
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
     * Indicators
     * <p>
     * 
     * 
     */
    public ArrayList<Integer> getI() {
        return i;
    }

    /**
     * Indicators
     * <p>
     * 
     * 
     */
    public void setI(ArrayList<Integer> i) {
        this.i = i;
    }

    /**
     * BID Price
     * <p>
     * 

     * Corresponds to the "p" property.
     * 
     */
    public Double getBp() {
        return bp;
    }

    /**
     * BID Price
     * <p>
     * 

     * Corresponds to the "p" property.
     * 
     */
    public void setBp(Double bp) {
        this.bp = bp;
    }

    /**
     * BID Exchange ID
     * <p>
     * 

     * Corresponds to the "x" property.
     * 
     */
    public Integer getBx() {
        return bx;
    }

    /**
     * BID Exchange ID
     * <p>
     * 

     * Corresponds to the "x" property.
     * 
     */
    public void setBx(Integer bx) {
        this.bx = bx;
    }

    /**
     * BID Size ( In round lots )
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    public Integer getBs() {
        return bs;
    }

    /**
     * BID Size ( In round lots )
     * <p>
     * 

     * Corresponds to the "s" property.
     * 
     */
    public void setBs(Integer bs) {
        this.bs = bs;
    }

    /**
     * ASK Price
     * <p>
     * 

     * Corresponds to the "P" property.
     * 
     */
    public Double getAp() {
        return ap;
    }

    /**
     * ASK Price
     * <p>
     * 

     * Corresponds to the "P" property.
     * 
     */
    public void setAp(Double ap) {
        this.ap = ap;
    }

    /**
     * ASK Exchange ID
     * <p>
     * 

     * Corresponds to the "X" property.
     * 
     */
    public Integer getAx() {
        return ax;
    }

    /**
     * ASK Exchange ID
     * <p>
     * 

     * Corresponds to the "X" property.
     * 
     */
    public void setAx(Integer ax) {
        this.ax = ax;
    }

    /**
     * ASK Size ( In round lots )
     * <p>
     * 

     * Corresponds to the "S" property.
     * 
     */
    public Integer getAs() {
        return as;
    }

    /**
     * ASK Size ( In round lots )
     * <p>
     * 

     * Corresponds to the "S" property.
     * 
     */
    public void setAs(Integer as) {
        this.as = as;
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
        sb.append(HistoricQuote.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("c");
        sb.append('=');
        sb.append(((this.c == null)?"<null>":this.c));
        sb.append(',');
        sb.append("i");
        sb.append('=');
        sb.append(((this.i == null)?"<null>":this.i));
        sb.append(',');
        sb.append("bp");
        sb.append('=');
        sb.append(((this.bp == null)?"<null>":this.bp));
        sb.append(',');
        sb.append("bx");
        sb.append('=');
        sb.append(((this.bx == null)?"<null>":this.bx));
        sb.append(',');
        sb.append("bs");
        sb.append('=');
        sb.append(((this.bs == null)?"<null>":this.bs));
        sb.append(',');
        sb.append("ap");
        sb.append('=');
        sb.append(((this.ap == null)?"<null>":this.ap));
        sb.append(',');
        sb.append("ax");
        sb.append('=');
        sb.append(((this.ax == null)?"<null>":this.ax));
        sb.append(',');
        sb.append("as");
        sb.append('=');
        sb.append(((this.as == null)?"<null>":this.as));
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
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+((this.c == null)? 0 :this.c.hashCode()));
        result = ((result* 31)+((this.f == null)? 0 :this.f.hashCode()));
        result = ((result* 31)+((this.i == null)? 0 :this.i.hashCode()));
        result = ((result* 31)+((this.bp == null)? 0 :this.bp.hashCode()));
        result = ((result* 31)+((this.ap == null)? 0 :this.ap.hashCode()));
        result = ((result* 31)+((this.q == null)? 0 :this.q.hashCode()));
        result = ((result* 31)+((this.bs == null)? 0 :this.bs.hashCode()));
        result = ((result* 31)+((this.as == null)? 0 :this.as.hashCode()));
        result = ((result* 31)+((this.t == null)? 0 :this.t.hashCode()));
        result = ((result* 31)+((this.bx == null)? 0 :this.bx.hashCode()));
        result = ((result* 31)+((this.ax == null)? 0 :this.ax.hashCode()));
        result = ((result* 31)+((this.y == null)? 0 :this.y.hashCode()));
        result = ((result* 31)+((this.z == null)? 0 :this.z.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HistoricQuote) == false) {
            return false;
        }
        HistoricQuote rhs = ((HistoricQuote) other);
        return (((((((((((((((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker)))&&((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c))))&&((this.f == rhs.f)||((this.f!= null)&&this.f.equals(rhs.f))))&&((this.i == rhs.i)||((this.i!= null)&&this.i.equals(rhs.i))))&&((this.bp == rhs.bp)||((this.bp!= null)&&this.bp.equals(rhs.bp))))&&((this.ap == rhs.ap)||((this.ap!= null)&&this.ap.equals(rhs.ap))))&&((this.q == rhs.q)||((this.q!= null)&&this.q.equals(rhs.q))))&&((this.bs == rhs.bs)||((this.bs!= null)&&this.bs.equals(rhs.bs))))&&((this.as == rhs.as)||((this.as!= null)&&this.as.equals(rhs.as))))&&((this.t == rhs.t)||((this.t!= null)&&this.t.equals(rhs.t))))&&((this.bx == rhs.bx)||((this.bx!= null)&&this.bx.equals(rhs.bx))))&&((this.ax == rhs.ax)||((this.ax!= null)&&this.ax.equals(rhs.ax))))&&((this.y == rhs.y)||((this.y!= null)&&this.y.equals(rhs.y))))&&((this.z == rhs.z)||((this.z!= null)&&this.z.equals(rhs.z))));
    }

}
