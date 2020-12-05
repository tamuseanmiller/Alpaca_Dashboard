
package net.jacobpeterson.domain.polygon.websocket.aggregate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.polygon.websocket.PolygonStreamMessage;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/sockets">https://polygon.io/sockets</a>
 * <p>
 * 
 * 
 */
public class AggregateMessage
    extends PolygonStreamMessage
    implements Serializable
{

    /**
     * Tick Volume
     * <p>
     * 
     * 
     */
    @SerializedName("v")
    @Expose
    private Long v;
    /**
     * Accumulated Volume ( Today )
     * <p>
     * 
     * 
     */
    @SerializedName("av")
    @Expose
    private Long av;
    /**
     * Today's official opening price
     * <p>
     * 
     * 
     */
    @SerializedName("op")
    @Expose
    private Double op;
    /**
     * VWAP (Volume Weighted Average Price)
     * <p>
     * 
     * 
     */
    @SerializedName("vw")
    @Expose
    private Double vw;
    /**
     * Tick Open Price
     * <p>
     * 
     * 
     */
    @SerializedName("o")
    @Expose
    private Double o;
    /**
     * Tick Close Price
     * <p>
     * 
     * 
     */
    @SerializedName("c")
    @Expose
    private Double c;
    /**
     * Tick High Price
     * <p>
     * 
     * 
     */
    @SerializedName("h")
    @Expose
    private Double h;
    /**
     * Tick Low Price
     * <p>
     * 
     * 
     */
    @SerializedName("l")
    @Expose
    private Double l;
    /**
     * Tick Average / VWAP Price
     * <p>
     * 
     * 
     */
    @SerializedName("a")
    @Expose
    private Double a;
    /**
     * Tick Start Timestamp ( Unix MS )
     * <p>
     * 
     * 
     */
    @SerializedName("s")
    @Expose
    private Long s;
    /**
     * Tick End Timestamp ( Unix MS )
     * <p>
     * 
     * 
     */
    @SerializedName("e")
    @Expose
    private Long e;
    private final static long serialVersionUID = -3258877642291833777L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AggregateMessage() {
    }

    /**
     * 
     * @param op
     * @param vw
     * @param a
     * @param c
     * @param s
     * @param av
     * @param e
     * @param v
     * @param h
     * @param l
     * @param o
     */
    public AggregateMessage(Long v, Long av, Double op, Double vw, Double o, Double c, Double h, Double l, Double a, Long s, Long e) {
        super();
        this.v = v;
        this.av = av;
        this.op = op;
        this.vw = vw;
        this.o = o;
        this.c = c;
        this.h = h;
        this.l = l;
        this.a = a;
        this.s = s;
        this.e = e;
    }

    /**
     * Tick Volume
     * <p>
     * 
     * 
     */
    public Long getV() {
        return v;
    }

    /**
     * Tick Volume
     * <p>
     * 
     * 
     */
    public void setV(Long v) {
        this.v = v;
    }

    /**
     * Accumulated Volume ( Today )
     * <p>
     * 
     * 
     */
    public Long getAv() {
        return av;
    }

    /**
     * Accumulated Volume ( Today )
     * <p>
     * 
     * 
     */
    public void setAv(Long av) {
        this.av = av;
    }

    /**
     * Today's official opening price
     * <p>
     * 
     * 
     */
    public Double getOp() {
        return op;
    }

    /**
     * Today's official opening price
     * <p>
     * 
     * 
     */
    public void setOp(Double op) {
        this.op = op;
    }

    /**
     * VWAP (Volume Weighted Average Price)
     * <p>
     * 
     * 
     */
    public Double getVw() {
        return vw;
    }

    /**
     * VWAP (Volume Weighted Average Price)
     * <p>
     * 
     * 
     */
    public void setVw(Double vw) {
        this.vw = vw;
    }

    /**
     * Tick Open Price
     * <p>
     * 
     * 
     */
    public Double getO() {
        return o;
    }

    /**
     * Tick Open Price
     * <p>
     * 
     * 
     */
    public void setO(Double o) {
        this.o = o;
    }

    /**
     * Tick Close Price
     * <p>
     * 
     * 
     */
    public Double getC() {
        return c;
    }

    /**
     * Tick Close Price
     * <p>
     * 
     * 
     */
    public void setC(Double c) {
        this.c = c;
    }

    /**
     * Tick High Price
     * <p>
     * 
     * 
     */
    public Double getH() {
        return h;
    }

    /**
     * Tick High Price
     * <p>
     * 
     * 
     */
    public void setH(Double h) {
        this.h = h;
    }

    /**
     * Tick Low Price
     * <p>
     * 
     * 
     */
    public Double getL() {
        return l;
    }

    /**
     * Tick Low Price
     * <p>
     * 
     * 
     */
    public void setL(Double l) {
        this.l = l;
    }

    /**
     * Tick Average / VWAP Price
     * <p>
     * 
     * 
     */
    public Double getA() {
        return a;
    }

    /**
     * Tick Average / VWAP Price
     * <p>
     * 
     * 
     */
    public void setA(Double a) {
        this.a = a;
    }

    /**
     * Tick Start Timestamp ( Unix MS )
     * <p>
     * 
     * 
     */
    public Long getS() {
        return s;
    }

    /**
     * Tick Start Timestamp ( Unix MS )
     * <p>
     * 
     * 
     */
    public void setS(Long s) {
        this.s = s;
    }

    /**
     * Tick End Timestamp ( Unix MS )
     * <p>
     * 
     * 
     */
    public Long getE() {
        return e;
    }

    /**
     * Tick End Timestamp ( Unix MS )
     * <p>
     * 
     * 
     */
    public void setE(Long e) {
        this.e = e;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AggregateMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("v");
        sb.append('=');
        sb.append(((this.v == null)?"<null>":this.v));
        sb.append(',');
        sb.append("av");
        sb.append('=');
        sb.append(((this.av == null)?"<null>":this.av));
        sb.append(',');
        sb.append("op");
        sb.append('=');
        sb.append(((this.op == null)?"<null>":this.op));
        sb.append(',');
        sb.append("vw");
        sb.append('=');
        sb.append(((this.vw == null)?"<null>":this.vw));
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
        sb.append("a");
        sb.append('=');
        sb.append(((this.a == null)?"<null>":this.a));
        sb.append(',');
        sb.append("s");
        sb.append('=');
        sb.append(((this.s == null)?"<null>":this.s));
        sb.append(',');
        sb.append("e");
        sb.append('=');
        sb.append(((this.e == null)?"<null>":this.e));
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
        result = ((result* 31)+((this.op == null)? 0 :this.op.hashCode()));
        result = ((result* 31)+((this.vw == null)? 0 :this.vw.hashCode()));
        result = ((result* 31)+((this.a == null)? 0 :this.a.hashCode()));
        result = ((result* 31)+((this.c == null)? 0 :this.c.hashCode()));
        result = ((result* 31)+((this.s == null)? 0 :this.s.hashCode()));
        result = ((result* 31)+((this.av == null)? 0 :this.av.hashCode()));
        result = ((result* 31)+((this.e == null)? 0 :this.e.hashCode()));
        result = ((result* 31)+((this.v == null)? 0 :this.v.hashCode()));
        result = ((result* 31)+((this.h == null)? 0 :this.h.hashCode()));
        result = ((result* 31)+((this.l == null)? 0 :this.l.hashCode()));
        result = ((result* 31)+((this.o == null)? 0 :this.o.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AggregateMessage) == false) {
            return false;
        }
        AggregateMessage rhs = ((AggregateMessage) other);
        return (((((((((((super.equals(rhs)&&((this.op == rhs.op)||((this.op!= null)&&this.op.equals(rhs.op))))&&((this.vw == rhs.vw)||((this.vw!= null)&&this.vw.equals(rhs.vw))))&&((this.a == rhs.a)||((this.a!= null)&&this.a.equals(rhs.a))))&&((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c))))&&((this.s == rhs.s)||((this.s!= null)&&this.s.equals(rhs.s))))&&((this.av == rhs.av)||((this.av!= null)&&this.av.equals(rhs.av))))&&((this.e == rhs.e)||((this.e!= null)&&this.e.equals(rhs.e))))&&((this.v == rhs.v)||((this.v!= null)&&this.v.equals(rhs.v))))&&((this.h == rhs.h)||((this.h!= null)&&this.h.equals(rhs.h))))&&((this.l == rhs.l)||((this.l!= null)&&this.l.equals(rhs.l))))&&((this.o == rhs.o)||((this.o!= null)&&this.o.equals(rhs.o))));
    }

}
