
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
public class StockTicker
    extends Ticker
    implements Serializable
{

    /**
     * Type of symbol this is. See symbol types.
     * <p>
     * 
     * 
     */
    @SerializedName("type")
    @Expose
    private String type;
    /**
     * Codes
     * <p>
     * 
     * 
     */
    @SerializedName("codes")
    @Expose
    private HashMap<String, String> codes;
    private final static long serialVersionUID = -549438382429791447L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StockTicker() {
    }

    /**
     * 
     * @param codes
     * @param type
     */
    public StockTicker(String type, HashMap<String, String> codes) {
        super();
        this.type = type;
        this.codes = codes;
    }

    /**
     * Type of symbol this is. See symbol types.
     * <p>
     * 
     * 
     */
    public String getType() {
        return type;
    }

    /**
     * Type of symbol this is. See symbol types.
     * <p>
     * 
     * 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Codes
     * <p>
     * 
     * 
     */
    public HashMap<String, String> getCodes() {
        return codes;
    }

    /**
     * Codes
     * <p>
     * 
     * 
     */
    public void setCodes(HashMap<String, String> codes) {
        this.codes = codes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StockTicker.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("codes");
        sb.append('=');
        sb.append(((this.codes == null)?"<null>":this.codes));
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
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.codes == null)? 0 :this.codes.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StockTicker) == false) {
            return false;
        }
        StockTicker rhs = ((StockTicker) other);
        return ((super.equals(rhs)&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.codes == rhs.codes)||((this.codes!= null)&&this.codes.equals(rhs.codes))));
    }

}
