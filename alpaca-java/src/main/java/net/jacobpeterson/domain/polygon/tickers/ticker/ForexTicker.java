
package net.jacobpeterson.domain.polygon.tickers.ticker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_tickers">https://polygon.io/docs/#!/Reference/get_v2_reference_tickers</a>
 * <p>
 * 
 * 
 */
public class ForexTicker
    extends Ticker
    implements Serializable
{

    /**
     * Attributes
     * <p>
     * 
     * 
     */
    @SerializedName("attrs")
    @Expose
    private HashMap<String, String> attrs;
    private final static long serialVersionUID = 8005421705763580395L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ForexTicker() {
    }

    /**
     * 
     * @param attrs
     */
    public ForexTicker(HashMap<String, String> attrs) {
        super();
        this.attrs = attrs;
    }

    /**
     * Attributes
     * <p>
     * 
     * 
     */
    public HashMap<String, String> getAttrs() {
        return attrs;
    }

    /**
     * Attributes
     * <p>
     * 
     * 
     */
    public void setAttrs(HashMap<String, String> attrs) {
        this.attrs = attrs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ForexTicker.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("attrs");
        sb.append('=');
        sb.append(((this.attrs == null)?"<null>":this.attrs));
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
        result = ((result* 31)+((this.attrs == null)? 0 :this.attrs.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ForexTicker) == false) {
            return false;
        }
        ForexTicker rhs = ((ForexTicker) other);
        return (super.equals(rhs)&&((this.attrs == rhs.attrs)||((this.attrs!= null)&&this.attrs.equals(rhs.attrs))));
    }

}
