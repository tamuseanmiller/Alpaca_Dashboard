
package net.jacobpeterson.domain.alpaca.marketdata.realtime;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/</a>
 * <p>
 * 
 * 
 */
public class SymbolMessage
    extends MarketDataMessage
    implements Serializable
{

    /**
     * Symbol
     * <p>
     * 
     * Corresponds to the "S" property.
     * 
     */
    @SerializedName("S")
    @Expose
    private String symbol;
    private final static long serialVersionUID = -9136008096050140475L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SymbolMessage() {
    }

    /**
     * 
     * @param symbol
     */
    public SymbolMessage(String symbol) {
        super();
        this.symbol = symbol;
    }

    /**
     * Symbol
     * <p>
     * 
     * Corresponds to the "S" property.
     * 
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Symbol
     * <p>
     * 
     * Corresponds to the "S" property.
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SymbolMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
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
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SymbolMessage) == false) {
            return false;
        }
        SymbolMessage rhs = ((SymbolMessage) other);
        return (super.equals(rhs)&&((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol))));
    }

}
