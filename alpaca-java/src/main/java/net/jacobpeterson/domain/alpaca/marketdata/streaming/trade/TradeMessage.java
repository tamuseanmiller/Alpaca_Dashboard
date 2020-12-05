
package net.jacobpeterson.domain.alpaca.marketdata.streaming.trade;

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
public class TradeMessage
    extends MarketDataStreamDataMessage
    implements Serializable
{

    /**
     * Trade ID
     * <p>
     * 
     * 
     */
    @SerializedName("i")
    @Expose
    private Integer i;
    /**
     * Exchange code where the trade occurred
     * <p>
     * 
     * 
     */
    @SerializedName("x")
    @Expose
    private String x;
    /**
     * Trade price
     * <p>
     * 
     * 
     */
    @SerializedName("p")
    @Expose
    private Double p;
    /**
     * Trade size (shares)
     * <p>
     * 
     * 
     */
    @SerializedName("s")
    @Expose
    private Integer s;
    /**
     * Epoch timestamp in milliseconds
     * <p>
     * 
     * 
     */
    @SerializedName("t")
    @Expose
    private Long t;
    /**
     * Trade condition flags
     * <p>
     * 
     * 
     */
    @SerializedName("c")
    @Expose
    private ArrayList<Integer> c;
    /**
     * Tape ID ( 1=A 2=B 3=C)
     * <p>
     * 
     * 
     */
    @SerializedName("z")
    @Expose
    private Integer z;
    private final static long serialVersionUID = -4983676458934415483L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TradeMessage() {
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
    public TradeMessage(Integer i, String x, Double p, Integer s, Long t, ArrayList<Integer> c, Integer z) {
        super();
        this.i = i;
        this.x = x;
        this.p = p;
        this.s = s;
        this.t = t;
        this.c = c;
        this.z = z;
    }

    /**
     * Trade ID
     * <p>
     * 
     * 
     */
    public Integer getI() {
        return i;
    }

    /**
     * Trade ID
     * <p>
     * 
     * 
     */
    public void setI(Integer i) {
        this.i = i;
    }

    /**
     * Exchange code where the trade occurred
     * <p>
     * 
     * 
     */
    public String getX() {
        return x;
    }

    /**
     * Exchange code where the trade occurred
     * <p>
     * 
     * 
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * Trade price
     * <p>
     * 
     * 
     */
    public Double getP() {
        return p;
    }

    /**
     * Trade price
     * <p>
     * 
     * 
     */
    public void setP(Double p) {
        this.p = p;
    }

    /**
     * Trade size (shares)
     * <p>
     * 
     * 
     */
    public Integer getS() {
        return s;
    }

    /**
     * Trade size (shares)
     * <p>
     * 
     * 
     */
    public void setS(Integer s) {
        this.s = s;
    }

    /**
     * Epoch timestamp in milliseconds
     * <p>
     * 
     * 
     */
    public Long getT() {
        return t;
    }

    /**
     * Epoch timestamp in milliseconds
     * <p>
     * 
     * 
     */
    public void setT(Long t) {
        this.t = t;
    }

    /**
     * Trade condition flags
     * <p>
     * 
     * 
     */
    public ArrayList<Integer> getC() {
        return c;
    }

    /**
     * Trade condition flags
     * <p>
     * 
     * 
     */
    public void setC(ArrayList<Integer> c) {
        this.c = c;
    }

    /**
     * Tape ID ( 1=A 2=B 3=C)
     * <p>
     * 
     * 
     */
    public Integer getZ() {
        return z;
    }

    /**
     * Tape ID ( 1=A 2=B 3=C)
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
        sb.append(TradeMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("i");
        sb.append('=');
        sb.append(((this.i == null)?"<null>":this.i));
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
        sb.append("t");
        sb.append('=');
        sb.append(((this.t == null)?"<null>":this.t));
        sb.append(',');
        sb.append("c");
        sb.append('=');
        sb.append(((this.c == null)?"<null>":this.c));
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
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TradeMessage) == false) {
            return false;
        }
        TradeMessage rhs = ((TradeMessage) other);
        return (((((((super.equals(rhs)&&((this.p == rhs.p)||((this.p!= null)&&this.p.equals(rhs.p))))&&((this.s == rhs.s)||((this.s!= null)&&this.s.equals(rhs.s))))&&((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c))))&&((this.t == rhs.t)||((this.t!= null)&&this.t.equals(rhs.t))))&&((this.x == rhs.x)||((this.x!= null)&&this.x.equals(rhs.x))))&&((this.i == rhs.i)||((this.i!= null)&&this.i.equals(rhs.i))))&&((this.z == rhs.z)||((this.z!= null)&&this.z.equals(rhs.z))));
    }

}
