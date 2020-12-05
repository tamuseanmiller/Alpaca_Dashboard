
package net.jacobpeterson.domain.polygon.stockdividends;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.ZonedDateTime;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_dividends_symbol">https://polygon.io/docs/#!/Reference/get_v2_reference_dividends_symbol</a>
 * <p>
 * 
 * 
 */
public class StockDividend implements Serializable
{

    /**
     * symbol
     * <p>
     * 
     * 
     */
    @SerializedName("symbol")
    @Expose
    private String symbol;
    /**
     * Refers to the dividend payment type<br>- Dividend income<br>- Interest income<br>- Stock dividend<br>- Short term capital gain<br>- Medium term capital gain<br>- Long term capital gain<br>- Unspecified term capital gain<br>
     * <p>
     * 
     * 
     */
    @SerializedName("type")
    @Expose
    private String type;
    /**
     * Execution date of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("exDate")
    @Expose
    private String exDate;
    /**
     * Payment date of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("paymentDate")
    @Expose
    private ZonedDateTime paymentDate;
    /**
     * Record date of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("recordDate")
    @Expose
    private ZonedDateTime recordDate;
    /**
     * Declared date of the trade
     * <p>
     * 
     * 
     */
    @SerializedName("declaredDate")
    @Expose
    private ZonedDateTime declaredDate;
    /**
     * Amount of the dividend
     * <p>
     * 
     * 
     */
    @SerializedName("amount")
    @Expose
    private Double amount;
    /**
     * Refers to the dividend income type<br>- P = Partially qualified income<br>- Q = Qualified income<br>- N = Unqualified income<br>- null = N/A or unknown<br>Can be P, Q, N or null
     * <p>
     * 
     * 
     */
    @SerializedName("qualified")
    @Expose
    private String qualified;
    /**
     * Refers to the dividend flag, if set<br>FI = Final dividend, div ends or instrument ends<br>LI = Liquidation, instrument liquidates<br>PR = Proceeds of a sale of rights or shares<br>RE = Redemption of rights<br>AC = Accrued dividend<br>AR = Payment in arrears<br>AD = Additional payment<br>EX = Extra payment<br>SP = Special dividend<br>YE = Year end<br>UR = Unknown rate<br>SU = Regular dividend is suspended
     * <p>
     * 
     * 
     */
    @SerializedName("flag")
    @Expose
    private String flag;
    private final static long serialVersionUID = 6121928854167194532L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StockDividend() {
    }

    /**
     * 
     * @param declaredDate
     * @param symbol
     * @param qualified
     * @param amount
     * @param flag
     * @param exDate
     * @param recordDate
     * @param type
     * @param paymentDate
     */
    public StockDividend(String symbol, String type, String exDate, ZonedDateTime paymentDate, ZonedDateTime recordDate, ZonedDateTime declaredDate, Double amount, String qualified, String flag) {
        super();
        this.symbol = symbol;
        this.type = type;
        this.exDate = exDate;
        this.paymentDate = paymentDate;
        this.recordDate = recordDate;
        this.declaredDate = declaredDate;
        this.amount = amount;
        this.qualified = qualified;
        this.flag = flag;
    }

    /**
     * symbol
     * <p>
     * 
     * 
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * symbol
     * <p>
     * 
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Refers to the dividend payment type<br>- Dividend income<br>- Interest income<br>- Stock dividend<br>- Short term capital gain<br>- Medium term capital gain<br>- Long term capital gain<br>- Unspecified term capital gain<br>
     * <p>
     * 
     * 
     */
    public String getType() {
        return type;
    }

    /**
     * Refers to the dividend payment type<br>- Dividend income<br>- Interest income<br>- Stock dividend<br>- Short term capital gain<br>- Medium term capital gain<br>- Long term capital gain<br>- Unspecified term capital gain<br>
     * <p>
     * 
     * 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Execution date of the trade
     * <p>
     * 
     * 
     */
    public String getExDate() {
        return exDate;
    }

    /**
     * Execution date of the trade
     * <p>
     * 
     * 
     */
    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    /**
     * Payment date of the trade
     * <p>
     * 
     * 
     */
    public ZonedDateTime getPaymentDate() {
        return paymentDate;
    }

    /**
     * Payment date of the trade
     * <p>
     * 
     * 
     */
    public void setPaymentDate(ZonedDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Record date of the trade
     * <p>
     * 
     * 
     */
    public ZonedDateTime getRecordDate() {
        return recordDate;
    }

    /**
     * Record date of the trade
     * <p>
     * 
     * 
     */
    public void setRecordDate(ZonedDateTime recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * Declared date of the trade
     * <p>
     * 
     * 
     */
    public ZonedDateTime getDeclaredDate() {
        return declaredDate;
    }

    /**
     * Declared date of the trade
     * <p>
     * 
     * 
     */
    public void setDeclaredDate(ZonedDateTime declaredDate) {
        this.declaredDate = declaredDate;
    }

    /**
     * Amount of the dividend
     * <p>
     * 
     * 
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Amount of the dividend
     * <p>
     * 
     * 
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Refers to the dividend income type<br>- P = Partially qualified income<br>- Q = Qualified income<br>- N = Unqualified income<br>- null = N/A or unknown<br>Can be P, Q, N or null
     * <p>
     * 
     * 
     */
    public String getQualified() {
        return qualified;
    }

    /**
     * Refers to the dividend income type<br>- P = Partially qualified income<br>- Q = Qualified income<br>- N = Unqualified income<br>- null = N/A or unknown<br>Can be P, Q, N or null
     * <p>
     * 
     * 
     */
    public void setQualified(String qualified) {
        this.qualified = qualified;
    }

    /**
     * Refers to the dividend flag, if set<br>FI = Final dividend, div ends or instrument ends<br>LI = Liquidation, instrument liquidates<br>PR = Proceeds of a sale of rights or shares<br>RE = Redemption of rights<br>AC = Accrued dividend<br>AR = Payment in arrears<br>AD = Additional payment<br>EX = Extra payment<br>SP = Special dividend<br>YE = Year end<br>UR = Unknown rate<br>SU = Regular dividend is suspended
     * <p>
     * 
     * 
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Refers to the dividend flag, if set<br>FI = Final dividend, div ends or instrument ends<br>LI = Liquidation, instrument liquidates<br>PR = Proceeds of a sale of rights or shares<br>RE = Redemption of rights<br>AC = Accrued dividend<br>AR = Payment in arrears<br>AD = Additional payment<br>EX = Extra payment<br>SP = Special dividend<br>YE = Year end<br>UR = Unknown rate<br>SU = Regular dividend is suspended
     * <p>
     * 
     * 
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StockDividend.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("exDate");
        sb.append('=');
        sb.append(((this.exDate == null)?"<null>":this.exDate));
        sb.append(',');
        sb.append("paymentDate");
        sb.append('=');
        sb.append(((this.paymentDate == null)?"<null>":this.paymentDate));
        sb.append(',');
        sb.append("recordDate");
        sb.append('=');
        sb.append(((this.recordDate == null)?"<null>":this.recordDate));
        sb.append(',');
        sb.append("declaredDate");
        sb.append('=');
        sb.append(((this.declaredDate == null)?"<null>":this.declaredDate));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        sb.append("qualified");
        sb.append('=');
        sb.append(((this.qualified == null)?"<null>":this.qualified));
        sb.append(',');
        sb.append("flag");
        sb.append('=');
        sb.append(((this.flag == null)?"<null>":this.flag));
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
        result = ((result* 31)+((this.declaredDate == null)? 0 :this.declaredDate.hashCode()));
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.qualified == null)? 0 :this.qualified.hashCode()));
        result = ((result* 31)+((this.amount == null)? 0 :this.amount.hashCode()));
        result = ((result* 31)+((this.flag == null)? 0 :this.flag.hashCode()));
        result = ((result* 31)+((this.exDate == null)? 0 :this.exDate.hashCode()));
        result = ((result* 31)+((this.recordDate == null)? 0 :this.recordDate.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.paymentDate == null)? 0 :this.paymentDate.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StockDividend) == false) {
            return false;
        }
        StockDividend rhs = ((StockDividend) other);
        return ((((((((((this.declaredDate == rhs.declaredDate)||((this.declaredDate!= null)&&this.declaredDate.equals(rhs.declaredDate)))&&((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol))))&&((this.qualified == rhs.qualified)||((this.qualified!= null)&&this.qualified.equals(rhs.qualified))))&&((this.amount == rhs.amount)||((this.amount!= null)&&this.amount.equals(rhs.amount))))&&((this.flag == rhs.flag)||((this.flag!= null)&&this.flag.equals(rhs.flag))))&&((this.exDate == rhs.exDate)||((this.exDate!= null)&&this.exDate.equals(rhs.exDate))))&&((this.recordDate == rhs.recordDate)||((this.recordDate!= null)&&this.recordDate.equals(rhs.recordDate))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.paymentDate == rhs.paymentDate)||((this.paymentDate!= null)&&this.paymentDate.equals(rhs.paymentDate))));
    }

}
