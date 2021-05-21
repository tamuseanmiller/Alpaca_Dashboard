
package net.jacobpeterson.domain.alpaca.marketdata.historical.trade;

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
public class Trade implements Serializable
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
     * Exchange where the trade happened.
     * <p>
     * 
     * 
     */
    @SerializedName("x")
    @Expose
    private String x;
    /**
     * Trade price.
     * <p>
     * 
     * 
     */
    @SerializedName("p")
    @Expose
    private Double p;
    /**
     * Trade size.
     * <p>
     * 
     * 
     */
    @SerializedName("s")
    @Expose
    private Integer s;
    /**
     * Trade conditions.
     * <p>
     * 
     * 
     */
    @SerializedName("c")
    @Expose
    private ArrayList<String> c;
    /**
     * Trade ID.
     * <p>
     * 
     * 
     */
    @SerializedName("i")
    @Expose
    private Long i;
    /**
     * Tape.
     * <p>
     * 
     * 
     */
    @SerializedName("z")
    @Expose
    private String z;
    private final static long serialVersionUID = -8483860569227837949L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Trade() {
    }

    /**
     * 
     * @param p
     * @param s
     * @param c
     * @param t
     * @param x
     * @param i
     * @param z
     */
    public Trade(ZonedDateTime t, String x, Double p, Integer s, ArrayList<String> c, Long i, String z) {
        super();
        this.t = t;
        this.x = x;
        this.p = p;
        this.s = s;
        this.c = c;
        this.i = i;
        this.z = z;
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
     * Exchange where the trade happened.
     * <p>
     * 
     * 
     */
    public String getX() {
        return x;
    }

    /**
     * Exchange where the trade happened.
     * <p>
     * 
     * 
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * Trade price.
     * <p>
     * 
     * 
     */
    public Double getP() {
        return p;
    }

    /**
     * Trade price.
     * <p>
     * 
     * 
     */
    public void setP(Double p) {
        this.p = p;
    }

    /**
     * Trade size.
     * <p>
     * 
     * 
     */
    public Integer getS() {
        return s;
    }

    /**
     * Trade size.
     * <p>
     * 
     * 
     */
    public void setS(Integer s) {
        this.s = s;
    }

    /**
     * Trade conditions.
     * <p>
     * 
     * 
     */
    public ArrayList<String> getC() {
        return c;
    }

    /**
     * Trade conditions.
     * <p>
     * 
     * 
     */
    public void setC(ArrayList<String> c) {
        this.c = c;
    }

    /**
     * Trade ID.
     * <p>
     * 
     * 
     */
    public Long getI() {
        return i;
    }

    /**
     * Trade ID.
     * <p>
     * 
     * 
     */
    public void setI(Long i) {
        this.i = i;
    }

    /**
     * Tape.
     * <p>
     * 
     * 
     */
    public String getZ() {
        return z;
    }

    /**
     * Tape.
     * <p>
     * 
     * 
     */
    public void setZ(String z) {
        this.z = z;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Trade.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("t");
        sb.append('=');
        sb.append(((this.t == null)?"<null>":this.t));
        sb.append(',');
        sb.append("x");
        sb.append('=');
        sb.append(((this.x == null)?"<null>":this.x));
        sb.append(',');
        sb.append("p");
        sb.append('=');
        sb.append(((this.p == null)?"<null>":this.p));
        sb.append(',');
        sb.append("s");
        sb.append('=');
        sb.append(((this.s == null)?"<null>":this.s));
        sb.append(',');
        sb.append("c");
        sb.append('=');
        sb.append(((this.c == null)?"<null>":this.c));
        sb.append(',');
        sb.append("i");
        sb.append('=');
        sb.append(((this.i == null)?"<null>":this.i));
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
        result = ((result* 31)+((this.s == null)? 0 :this.s.hashCode()));
        result = ((result* 31)+((this.c == null)? 0 :this.c.hashCode()));
        result = ((result* 31)+((this.t == null)? 0 :this.t.hashCode()));
        result = ((result* 31)+((this.x == null)? 0 :this.x.hashCode()));
        result = ((result* 31)+((this.i == null)? 0 :this.i.hashCode()));
        result = ((result* 31)+((this.z == null)? 0 :this.z.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Trade) == false) {
            return false;
        }
        Trade rhs = ((Trade) other);
        return ((((((((this.p == rhs.p)||((this.p!= null)&&this.p.equals(rhs.p)))&&((this.s == rhs.s)||((this.s!= null)&&this.s.equals(rhs.s))))&&((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c))))&&((this.t == rhs.t)||((this.t!= null)&&this.t.equals(rhs.t))))&&((this.x == rhs.x)||((this.x!= null)&&this.x.equals(rhs.x))))&&((this.i == rhs.i)||((this.i!= null)&&this.i.equals(rhs.i))))&&((this.z == rhs.z)||((this.z!= null)&&this.z.equals(rhs.z))));
    }

}
