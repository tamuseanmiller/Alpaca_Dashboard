
package net.jacobpeterson.domain.polygon.websocket.trade;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.polygon.websocket.PolygonStreamMessage;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/sockets">https://polygon.io/sockets</a>
 * <p>
 * 
 * 
 */
public class TradeMessage
    extends PolygonStreamMessage
    implements Serializable
{

    /**
     * Exchange ID
     * <p>
     * 
     * 
     */
    @SerializedName("x")
    @Expose
    private String x;
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
     * Tape ( 1=A 2=B 3=C)
     * <p>
     * 
     * 
     */
    @SerializedName("z")
    @Expose
    private Integer z;
    /**
     * Price
     * <p>
     * 
     * 
     */
    @SerializedName("p")
    @Expose
    private Double p;
    /**
     * Trade Size
     * <p>
     * 
     * 
     */
    @SerializedName("s")
    @Expose
    private Integer s;
    /**
     * Trade Conditions
     * <p>
     * 
     * 
     */
    @SerializedName("c")
    @Expose
    private ArrayList<Integer> c;
    /**
     * Trade Timestamp ( Unix MS )
     * <p>
     * 
     * 
     */
    @SerializedName("t")
    @Expose
    private Long t;
    private final static long serialVersionUID = 3585927506836498425L;

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
    public TradeMessage(String x, String i, Integer z, Double p, Integer s, ArrayList<Integer> c, Long t) {
        super();
        this.x = x;
        this.i = i;
        this.z = z;
        this.p = p;
        this.s = s;
        this.c = c;
        this.t = t;
    }

    /**
     * Exchange ID
     * <p>
     * 
     * 
     */
    public String getX() {
        return x;
    }

    /**
     * Exchange ID
     * <p>
     * 
     * 
     */
    public void setX(String x) {
        this.x = x;
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
     * Tape ( 1=A 2=B 3=C)
     * <p>
     * 
     * 
     */
    public Integer getZ() {
        return z;
    }

    /**
     * Tape ( 1=A 2=B 3=C)
     * <p>
     * 
     * 
     */
    public void setZ(Integer z) {
        this.z = z;
    }

    /**
     * Price
     * <p>
     * 
     * 
     */
    public Double getP() {
        return p;
    }

    /**
     * Price
     * <p>
     * 
     * 
     */
    public void setP(Double p) {
        this.p = p;
    }

    /**
     * Trade Size
     * <p>
     * 
     * 
     */
    public Integer getS() {
        return s;
    }

    /**
     * Trade Size
     * <p>
     * 
     * 
     */
    public void setS(Integer s) {
        this.s = s;
    }

    /**
     * Trade Conditions
     * <p>
     * 
     * 
     */
    public ArrayList<Integer> getC() {
        return c;
    }

    /**
     * Trade Conditions
     * <p>
     * 
     * 
     */
    public void setC(ArrayList<Integer> c) {
        this.c = c;
    }

    /**
     * Trade Timestamp ( Unix MS )
     * <p>
     * 
     * 
     */
    public Long getT() {
        return t;
    }

    /**
     * Trade Timestamp ( Unix MS )
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
        sb.append("x");
        sb.append('=');
        sb.append(((this.x == null)?"<null>":this.x));
        sb.append(',');
        sb.append("i");
        sb.append('=');
        sb.append(((this.i == null)?"<null>":this.i));
        sb.append(',');
        sb.append("z");
        sb.append('=');
        sb.append(((this.z == null)?"<null>":this.z));
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
