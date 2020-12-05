
package net.jacobpeterson.domain.alpaca.accountactivities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/account-activities/">https://docs.alpaca.markets/api-documentation/api-v2/account-activities/</a>
 * <p>
 * 
 * 
 */
public class NonTradeActivity
    extends AccountActivity
    implements Serializable
{

    /**
     * See https://docs.alpaca.markets/api-documentation/api-v2/account-activities/#activity-types for a list of possible values.
     * <p>
     * 
     * 
     */
    @SerializedName("activity_type")
    @Expose
    private String activityType;
    /**
     * An ID for the activity, always in “::” format. Can be sent as page_token in requests to facilitate the paging of results.
     * <p>
     * 
     * 
     */
    @SerializedName("id")
    @Expose
    private String id;
    /**
     * The date on which the activity occurred or on which the transaction associated with the activity settled.
     * <p>
     * 
     * 
     */
    @SerializedName("date")
    @Expose
    private String date;
    /**
     * The net amount of money (positive or negative) associated with the activity.
     * <p>
     * 
     * 
     */
    @SerializedName("net_amount")
    @Expose
    private String netAmount;
    /**
     * The symbol of the security involved with the activity. Not present for all activity types.
     * <p>
     * 
     * 
     */
    @SerializedName("symbol")
    @Expose
    private String symbol;
    /**
     * For dividend activities, the number of shares that contributed to the payment. Not present for other activity types.
     * <p>
     * 
     * 
     */
    @SerializedName("qty")
    @Expose
    private String qty;
    /**
     * For dividend activities, the average amount paid per share. Not present for other activity types.
     * <p>
     * 
     * 
     */
    @SerializedName("per_share_amount")
    @Expose
    private String perShareAmount;
    /**
     * For Transaction records (TRANS,CSR,CSD) a description field is present (even if the docs don't mention it!
     * <p>
     * 
     * 
     */
    @SerializedName("description")
    @Expose
    private String description;
    private final static long serialVersionUID = -1726885694639005669L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NonTradeActivity() {
    }

    /**
     * 
     * @param date
     * @param symbol
     * @param perShareAmount
     * @param netAmount
     * @param qty
     * @param description
     * @param id
     * @param activityType
     */
    public NonTradeActivity(String activityType, String id, String date, String netAmount, String symbol, String qty, String perShareAmount, String description) {
        super();
        this.activityType = activityType;
        this.id = id;
        this.date = date;
        this.netAmount = netAmount;
        this.symbol = symbol;
        this.qty = qty;
        this.perShareAmount = perShareAmount;
        this.description = description;
    }

    /**
     * See https://docs.alpaca.markets/api-documentation/api-v2/account-activities/#activity-types for a list of possible values.
     * <p>
     * 
     * 
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * See https://docs.alpaca.markets/api-documentation/api-v2/account-activities/#activity-types for a list of possible values.
     * <p>
     * 
     * 
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    /**
     * An ID for the activity, always in “::” format. Can be sent as page_token in requests to facilitate the paging of results.
     * <p>
     * 
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * An ID for the activity, always in “::” format. Can be sent as page_token in requests to facilitate the paging of results.
     * <p>
     * 
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The date on which the activity occurred or on which the transaction associated with the activity settled.
     * <p>
     * 
     * 
     */
    public String getDate() {
        return date;
    }

    /**
     * The date on which the activity occurred or on which the transaction associated with the activity settled.
     * <p>
     * 
     * 
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * The net amount of money (positive or negative) associated with the activity.
     * <p>
     * 
     * 
     */
    public String getNetAmount() {
        return netAmount;
    }

    /**
     * The net amount of money (positive or negative) associated with the activity.
     * <p>
     * 
     * 
     */
    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    /**
     * The symbol of the security involved with the activity. Not present for all activity types.
     * <p>
     * 
     * 
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * The symbol of the security involved with the activity. Not present for all activity types.
     * <p>
     * 
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * For dividend activities, the number of shares that contributed to the payment. Not present for other activity types.
     * <p>
     * 
     * 
     */
    public String getQty() {
        return qty;
    }

    /**
     * For dividend activities, the number of shares that contributed to the payment. Not present for other activity types.
     * <p>
     * 
     * 
     */
    public void setQty(String qty) {
        this.qty = qty;
    }

    /**
     * For dividend activities, the average amount paid per share. Not present for other activity types.
     * <p>
     * 
     * 
     */
    public String getPerShareAmount() {
        return perShareAmount;
    }

    /**
     * For dividend activities, the average amount paid per share. Not present for other activity types.
     * <p>
     * 
     * 
     */
    public void setPerShareAmount(String perShareAmount) {
        this.perShareAmount = perShareAmount;
    }

    /**
     * For Transaction records (TRANS,CSR,CSD) a description field is present (even if the docs don't mention it!
     * <p>
     * 
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * For Transaction records (TRANS,CSR,CSD) a description field is present (even if the docs don't mention it!
     * <p>
     * 
     * 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(NonTradeActivity.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("activityType");
        sb.append('=');
        sb.append(((this.activityType == null)?"<null>":this.activityType));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("date");
        sb.append('=');
        sb.append(((this.date == null)?"<null>":this.date));
        sb.append(',');
        sb.append("netAmount");
        sb.append('=');
        sb.append(((this.netAmount == null)?"<null>":this.netAmount));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("qty");
        sb.append('=');
        sb.append(((this.qty == null)?"<null>":this.qty));
        sb.append(',');
        sb.append("perShareAmount");
        sb.append('=');
        sb.append(((this.perShareAmount == null)?"<null>":this.perShareAmount));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
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
        result = ((result* 31)+((this.date == null)? 0 :this.date.hashCode()));
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.perShareAmount == null)? 0 :this.perShareAmount.hashCode()));
        result = ((result* 31)+((this.netAmount == null)? 0 :this.netAmount.hashCode()));
        result = ((result* 31)+((this.qty == null)? 0 :this.qty.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.activityType == null)? 0 :this.activityType.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NonTradeActivity) == false) {
            return false;
        }
        NonTradeActivity rhs = ((NonTradeActivity) other);
        return ((((((((super.equals(rhs)&&((this.date == rhs.date)||((this.date!= null)&&this.date.equals(rhs.date))))&&((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol))))&&((this.perShareAmount == rhs.perShareAmount)||((this.perShareAmount!= null)&&this.perShareAmount.equals(rhs.perShareAmount))))&&((this.netAmount == rhs.netAmount)||((this.netAmount!= null)&&this.netAmount.equals(rhs.netAmount))))&&((this.qty == rhs.qty)||((this.qty!= null)&&this.qty.equals(rhs.qty))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.activityType == rhs.activityType)||((this.activityType!= null)&&this.activityType.equals(rhs.activityType))));
    }

}
