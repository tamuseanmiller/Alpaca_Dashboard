
package net.jacobpeterson.domain.alpaca.accountconfiguration;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/account-configuration/">https://docs.alpaca.markets/api-documentation/api-v2/account-configuration/</a>
 * <p>
 * 
 * 
 */
public class AccountConfiguration implements Serializable
{

    /**
     * Both, entry, or exit. Controls Day Trading Margin Call (DTMC) checks.
     * <p>
     * 
     * 
     */
    @SerializedName("dtbp_check")
    @Expose
    private String dtbpCheck;
    /**
     * all or none. If none, emails for order fills are not sent.
     * <p>
     * 
     * 
     */
    @SerializedName("trade_confirm_email")
    @Expose
    private String tradeConfirmEmail;
    /**
     * If true, new orders are blocked.
     * <p>
     * 
     * 
     */
    @SerializedName("suspend_trade")
    @Expose
    private Boolean suspendTrade;
    /**
     * If true, account becomes long-only mode.
     * <p>
     * 
     * 
     */
    @SerializedName("no_shorting")
    @Expose
    private Boolean noShorting;
    private final static long serialVersionUID = 6204450088610577704L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AccountConfiguration() {
    }

    /**
     * 
     * @param tradeConfirmEmail
     * @param suspendTrade
     * @param dtbpCheck
     * @param noShorting
     */
    public AccountConfiguration(String dtbpCheck, String tradeConfirmEmail, Boolean suspendTrade, Boolean noShorting) {
        super();
        this.dtbpCheck = dtbpCheck;
        this.tradeConfirmEmail = tradeConfirmEmail;
        this.suspendTrade = suspendTrade;
        this.noShorting = noShorting;
    }

    /**
     * Both, entry, or exit. Controls Day Trading Margin Call (DTMC) checks.
     * <p>
     * 
     * 
     */
    public String getDtbpCheck() {
        return dtbpCheck;
    }

    /**
     * Both, entry, or exit. Controls Day Trading Margin Call (DTMC) checks.
     * <p>
     * 
     * 
     */
    public void setDtbpCheck(String dtbpCheck) {
        this.dtbpCheck = dtbpCheck;
    }

    /**
     * all or none. If none, emails for order fills are not sent.
     * <p>
     * 
     * 
     */
    public String getTradeConfirmEmail() {
        return tradeConfirmEmail;
    }

    /**
     * all or none. If none, emails for order fills are not sent.
     * <p>
     * 
     * 
     */
    public void setTradeConfirmEmail(String tradeConfirmEmail) {
        this.tradeConfirmEmail = tradeConfirmEmail;
    }

    /**
     * If true, new orders are blocked.
     * <p>
     * 
     * 
     */
    public Boolean getSuspendTrade() {
        return suspendTrade;
    }

    /**
     * If true, new orders are blocked.
     * <p>
     * 
     * 
     */
    public void setSuspendTrade(Boolean suspendTrade) {
        this.suspendTrade = suspendTrade;
    }

    /**
     * If true, account becomes long-only mode.
     * <p>
     * 
     * 
     */
    public Boolean getNoShorting() {
        return noShorting;
    }

    /**
     * If true, account becomes long-only mode.
     * <p>
     * 
     * 
     */
    public void setNoShorting(Boolean noShorting) {
        this.noShorting = noShorting;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AccountConfiguration.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("dtbpCheck");
        sb.append('=');
        sb.append(((this.dtbpCheck == null)?"<null>":this.dtbpCheck));
        sb.append(',');
        sb.append("tradeConfirmEmail");
        sb.append('=');
        sb.append(((this.tradeConfirmEmail == null)?"<null>":this.tradeConfirmEmail));
        sb.append(',');
        sb.append("suspendTrade");
        sb.append('=');
        sb.append(((this.suspendTrade == null)?"<null>":this.suspendTrade));
        sb.append(',');
        sb.append("noShorting");
        sb.append('=');
        sb.append(((this.noShorting == null)?"<null>":this.noShorting));
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
        result = ((result* 31)+((this.tradeConfirmEmail == null)? 0 :this.tradeConfirmEmail.hashCode()));
        result = ((result* 31)+((this.dtbpCheck == null)? 0 :this.dtbpCheck.hashCode()));
        result = ((result* 31)+((this.noShorting == null)? 0 :this.noShorting.hashCode()));
        result = ((result* 31)+((this.suspendTrade == null)? 0 :this.suspendTrade.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AccountConfiguration) == false) {
            return false;
        }
        AccountConfiguration rhs = ((AccountConfiguration) other);
        return (((((this.tradeConfirmEmail == rhs.tradeConfirmEmail)||((this.tradeConfirmEmail!= null)&&this.tradeConfirmEmail.equals(rhs.tradeConfirmEmail)))&&((this.dtbpCheck == rhs.dtbpCheck)||((this.dtbpCheck!= null)&&this.dtbpCheck.equals(rhs.dtbpCheck))))&&((this.noShorting == rhs.noShorting)||((this.noShorting!= null)&&this.noShorting.equals(rhs.noShorting))))&&((this.suspendTrade == rhs.suspendTrade)||((this.suspendTrade!= null)&&this.suspendTrade.equals(rhs.suspendTrade))));
    }

}
