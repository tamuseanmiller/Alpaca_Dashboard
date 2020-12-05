
package net.jacobpeterson.domain.polygon.snapshot.ticker.book;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;


/**
 * See <a href="#">(documentation not yet public)</a>
 * <p>
 * 
 * 
 */
public class BookOrder implements Serializable
{

    /**
     * The price
     * <p>
     * 
     * 
     */
    @SerializedName("p")
    @Expose
    private Double p;
    /**
     * The map of an exchange ID to its order volume
     * <p>
     * 
     * 
     */
    @SerializedName("x")
    @Expose
    private HashMap<Integer, Long> x;
    private final static long serialVersionUID = 4795788330928535192L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BookOrder() {
    }

    /**
     * 
     * @param p
     * @param x
     */
    public BookOrder(Double p, HashMap<Integer, Long> x) {
        super();
        this.p = p;
        this.x = x;
    }

    /**
     * The price
     * <p>
     * 
     * 
     */
    public Double getP() {
        return p;
    }

    /**
     * The price
     * <p>
     * 
     * 
     */
    public void setP(Double p) {
        this.p = p;
    }

    /**
     * The map of an exchange ID to its order volume
     * <p>
     * 
     * 
     */
    public HashMap<Integer, Long> getX() {
        return x;
    }

    /**
     * The map of an exchange ID to its order volume
     * <p>
     * 
     * 
     */
    public void setX(HashMap<Integer, Long> x) {
        this.x = x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BookOrder.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("p");
        sb.append('=');
        sb.append(((this.p == null)?"<null>":this.p));
        sb.append(',');
        sb.append("x");
        sb.append('=');
        sb.append(((this.x == null)?"<null>":this.x));
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
        result = ((result* 31)+((this.x == null)? 0 :this.x.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BookOrder) == false) {
            return false;
        }
        BookOrder rhs = ((BookOrder) other);
        return (((this.p == rhs.p)||((this.p!= null)&&this.p.equals(rhs.p)))&&((this.x == rhs.x)||((this.x!= null)&&this.x.equals(rhs.x))));
    }

}
