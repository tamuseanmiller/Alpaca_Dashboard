
package net.jacobpeterson.domain.polygon.lasttrade;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v1_last_stocks_symbol">https://polygon.io/docs/#!/Stocks--Equities/get_v1_last_stocks_symbol</a>
 * <p>
 * 
 * 
 */
public class LastTradeResponse implements Serializable
{

    /**
     * Status of this requests response
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private String status;
    /**
     * Symbol that was evaluated from the request
     * <p>
     * 
     * 
     */
    @SerializedName("symbol")
    @Expose
    private String symbol;
    /**
     * last
     * <p>
     * 
     * 
     */
    @SerializedName("last")
    @Expose
    private LastTrade last;
    private final static long serialVersionUID = -7299894223730743348L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LastTradeResponse() {
    }

    /**
     * 
     * @param symbol
     * @param last
     * @param status
     */
    public LastTradeResponse(String status, String symbol, LastTrade last) {
        super();
        this.status = status;
        this.symbol = symbol;
        this.last = last;
    }

    /**
     * Status of this requests response
     * <p>
     * 
     * 
     */
    public String getStatus() {
        return status;
    }

    /**
     * Status of this requests response
     * <p>
     * 
     * 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Symbol that was evaluated from the request
     * <p>
     * 
     * 
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Symbol that was evaluated from the request
     * <p>
     * 
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * last
     * <p>
     * 
     * 
     */
    public LastTrade getLast() {
        return last;
    }

    /**
     * last
     * <p>
     * 
     * 
     */
    public void setLast(LastTrade last) {
        this.last = last;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LastTradeResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("last");
        sb.append('=');
        sb.append(((this.last == null)?"<null>":this.last));
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
        result = ((result* 31)+((this.last == null)? 0 :this.last.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LastTradeResponse) == false) {
            return false;
        }
        LastTradeResponse rhs = ((LastTradeResponse) other);
        return ((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.last == rhs.last)||((this.last!= null)&&this.last.equals(rhs.last))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
