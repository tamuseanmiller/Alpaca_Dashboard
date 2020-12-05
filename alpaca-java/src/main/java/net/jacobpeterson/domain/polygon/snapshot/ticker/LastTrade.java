
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
public class LastTrade implements Serializable
{

    /**
     * Condition 1 of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("c1")
    @Expose
    private Integer c1;
    /**
     * Condition 2 of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("c2")
    @Expose
    private Integer c2;
    /**
     * Condition 3 of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("c3")
    @Expose
    private Integer c3;
    /**
     * Condition 4 of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("c4")
    @Expose
    private Integer c4;
    /**
     * The exchange this trade happened on
     * <p>
     * 
     * 
     */
    @SerializedName("e")
    @Expose
    private Integer e;
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
     * Size of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("s")
    @Expose
    private Integer s;
    /**
     * Timestamp of this trade
     * <p>
     * 
     * 
     */
    @SerializedName("t")
    @Expose
    private Long t;
    private final static long serialVersionUID = 640204233315572284L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LastTrade() {
    }

    /**
     * 
     * @param c3
     * @param p
     * @param c4
     * @param s
     * @param t
     * @param e
     * @param c1
     * @param c2
     */
    public LastTrade(Integer c1, Integer c2, Integer c3, Integer c4, Integer e, Double p, Integer s, Long t) {
        super();
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.e = e;
        this.p = p;
        this.s = s;
        this.t = t;
    }

    /**
     * Condition 1 of this trade
     * <p>
     * 
     * 
     */
    public Integer getC1() {
        return c1;
    }

    /**
     * Condition 1 of this trade
     * <p>
     * 
     * 
     */
    public void setC1(Integer c1) {
        this.c1 = c1;
    }

    /**
     * Condition 2 of this trade
     * <p>
     * 
     * 
     */
    public Integer getC2() {
        return c2;
    }

    /**
     * Condition 2 of this trade
     * <p>
     * 
     * 
     */
    public void setC2(Integer c2) {
        this.c2 = c2;
    }

    /**
     * Condition 3 of this trade
     * <p>
     * 
     * 
     */
    public Integer getC3() {
        return c3;
    }

    /**
     * Condition 3 of this trade
     * <p>
     * 
     * 
     */
    public void setC3(Integer c3) {
        this.c3 = c3;
    }

    /**
     * Condition 4 of this trade
     * <p>
     * 
     * 
     */
    public Integer getC4() {
        return c4;
    }

    /**
     * Condition 4 of this trade
     * <p>
     * 
     * 
     */
    public void setC4(Integer c4) {
        this.c4 = c4;
    }

    /**
     * The exchange this trade happened on
     * <p>
     * 
     * 
     */
    public Integer getE() {
        return e;
    }

    /**
     * The exchange this trade happened on
     * <p>
     * 
     * 
     */
    public void setE(Integer e) {
        this.e = e;
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
     * Size of the trade
     * <p>
     * 
     * 
     */
    public Integer getS() {
        return s;
    }

    /**
     * Size of the trade
     * <p>
     * 
     * 
     */
    public void setS(Integer s) {
        this.s = s;
    }

    /**
     * Timestamp of this trade
     * <p>
     * 
     * 
     */
    public Long getT() {
        return t;
    }

    /**
     * Timestamp of this trade
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
        sb.append(LastTrade.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("c1");
        sb.append('=');
        sb.append(((this.c1 == null)?"<null>":this.c1));
        sb.append(',');
        sb.append("c2");
        sb.append('=');
        sb.append(((this.c2 == null)?"<null>":this.c2));
        sb.append(',');
        sb.append("c3");
        sb.append('=');
        sb.append(((this.c3 == null)?"<null>":this.c3));
        sb.append(',');
        sb.append("c4");
        sb.append('=');
        sb.append(((this.c4 == null)?"<null>":this.c4));
        sb.append(',');
        sb.append("e");
        sb.append('=');
        sb.append(((this.e == null)?"<null>":this.e));
        sb.append(',');
        sb.append("p");
        sb.append('=');
        sb.append(((this.p == null)?"<null>":this.p));
        sb.append(',');
        sb.append("s");
        sb.append('=');
        sb.append(((this.s == null)?"<null>":this.s));
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
        result = ((result* 31)+((this.c3 == null)? 0 :this.c3 .hashCode()));
        result = ((result* 31)+((this.p == null)? 0 :this.p.hashCode()));
        result = ((result* 31)+((this.c4 == null)? 0 :this.c4 .hashCode()));
        result = ((result* 31)+((this.s == null)? 0 :this.s.hashCode()));
        result = ((result* 31)+((this.t == null)? 0 :this.t.hashCode()));
        result = ((result* 31)+((this.e == null)? 0 :this.e.hashCode()));
        result = ((result* 31)+((this.c1 == null)? 0 :this.c1 .hashCode()));
        result = ((result* 31)+((this.c2 == null)? 0 :this.c2 .hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LastTrade) == false) {
            return false;
        }
        LastTrade rhs = ((LastTrade) other);
        return (((((((((this.c3 == rhs.c3)||((this.c3 != null)&&this.c3 .equals(rhs.c3)))&&((this.p == rhs.p)||((this.p!= null)&&this.p.equals(rhs.p))))&&((this.c4 == rhs.c4)||((this.c4 != null)&&this.c4 .equals(rhs.c4))))&&((this.s == rhs.s)||((this.s!= null)&&this.s.equals(rhs.s))))&&((this.t == rhs.t)||((this.t!= null)&&this.t.equals(rhs.t))))&&((this.e == rhs.e)||((this.e!= null)&&this.e.equals(rhs.e))))&&((this.c1 == rhs.c1)||((this.c1 != null)&&this.c1 .equals(rhs.c1))))&&((this.c2 == rhs.c2)||((this.c2 != null)&&this.c2 .equals(rhs.c2))));
    }

}
