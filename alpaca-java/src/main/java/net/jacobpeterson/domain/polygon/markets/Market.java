
package net.jacobpeterson.domain.polygon.markets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_markets">https://polygon.io/docs/#!/Reference/get_v2_reference_markets</a>
 * <p>
 * 
 * 
 */
public class Market implements Serializable
{

    /**
     * Market
     * <p>
     * 
     * 
     */
    @SerializedName("market")
    @Expose
    private String market;
    /**
     * Description
     * <p>
     * 
     * 
     */
    @SerializedName("desc")
    @Expose
    private String desc;
    private final static long serialVersionUID = -5437099791763567538L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Market() {
    }

    /**
     * 
     * @param market
     * @param desc
     */
    public Market(String market, String desc) {
        super();
        this.market = market;
        this.desc = desc;
    }

    /**
     * Market
     * <p>
     * 
     * 
     */
    public String getMarket() {
        return market;
    }

    /**
     * Market
     * <p>
     * 
     * 
     */
    public void setMarket(String market) {
        this.market = market;
    }

    /**
     * Description
     * <p>
     * 
     * 
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Description
     * <p>
     * 
     * 
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Market.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("market");
        sb.append('=');
        sb.append(((this.market == null)?"<null>":this.market));
        sb.append(',');
        sb.append("desc");
        sb.append('=');
        sb.append(((this.desc == null)?"<null>":this.desc));
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
        result = ((result* 31)+((this.market == null)? 0 :this.market.hashCode()));
        result = ((result* 31)+((this.desc == null)? 0 :this.desc.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Market) == false) {
            return false;
        }
        Market rhs = ((Market) other);
        return (((this.market == rhs.market)||((this.market!= null)&&this.market.equals(rhs.market)))&&((this.desc == rhs.desc)||((this.desc!= null)&&this.desc.equals(rhs.desc))));
    }

}
