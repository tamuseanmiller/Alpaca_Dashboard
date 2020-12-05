
package net.jacobpeterson.domain.polygon.stocksplits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_splits_symbol">https://polygon.io/docs/#!/Reference/get_v2_reference_splits_symbol</a>
 * <p>
 * 
 * 
 */
public class StockSplit implements Serializable
{

    /**
     * ticker
     * <p>
     * 
     * 
     */
    @SerializedName("ticker")
    @Expose
    private String ticker;
    /**
     * Execution date of the split
     * <p>
     * 
     * 
     */
    @SerializedName("exDate")
    @Expose
    private String exDate;
    /**
     * Payment date of the split
     * <p>
     * 
     * 
     */
    @SerializedName("paymentDate")
    @Expose
    private String paymentDate;
    /**
     * Payment date of the split
     * <p>
     * 
     * 
     */
    @SerializedName("recordDate")
    @Expose
    private String recordDate;
    /**
     * Payment date of the split
     * <p>
     * 
     * 
     */
    @SerializedName("declaredDate")
    @Expose
    private String declaredDate;
    /**
     * refers to the split ratio. The split ratio is an inverse of the number of shares that a holder of the stock would have after the split divided by the number of shares that the holder had before. For example: Split ratio of .5 = 2 for 1 split.
     * <p>
     * 
     * 
     */
    @SerializedName("ratio")
    @Expose
    private Double ratio;
    /**
     * To factor of the split. Used to calculate the split ratio forfactor/tofactor = ratio (eg ½ = 0.5)
     * <p>
     * 
     * 
     */
    @SerializedName("tofactor")
    @Expose
    private Double tofactor;
    /**
     * For factor of the split. Used to calculate the split ratio forfactor/tofactor = ratio (eg ½ = 0.5)
     * <p>
     * 
     * 
     */
    @SerializedName("forfactor")
    @Expose
    private Double forfactor;
    private final static long serialVersionUID = -2618862773458885169L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StockSplit() {
    }

    /**
     * 
     * @param declaredDate
     * @param ticker
     * @param exDate
     * @param tofactor
     * @param recordDate
     * @param forfactor
     * @param paymentDate
     * @param ratio
     */
    public StockSplit(String ticker, String exDate, String paymentDate, String recordDate, String declaredDate, Double ratio, Double tofactor, Double forfactor) {
        super();
        this.ticker = ticker;
        this.exDate = exDate;
        this.paymentDate = paymentDate;
        this.recordDate = recordDate;
        this.declaredDate = declaredDate;
        this.ratio = ratio;
        this.tofactor = tofactor;
        this.forfactor = forfactor;
    }

    /**
     * ticker
     * <p>
     * 
     * 
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * ticker
     * <p>
     * 
     * 
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * Execution date of the split
     * <p>
     * 
     * 
     */
    public String getExDate() {
        return exDate;
    }

    /**
     * Execution date of the split
     * <p>
     * 
     * 
     */
    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    /**
     * Payment date of the split
     * <p>
     * 
     * 
     */
    public String getPaymentDate() {
        return paymentDate;
    }

    /**
     * Payment date of the split
     * <p>
     * 
     * 
     */
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Payment date of the split
     * <p>
     * 
     * 
     */
    public String getRecordDate() {
        return recordDate;
    }

    /**
     * Payment date of the split
     * <p>
     * 
     * 
     */
    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * Payment date of the split
     * <p>
     * 
     * 
     */
    public String getDeclaredDate() {
        return declaredDate;
    }

    /**
     * Payment date of the split
     * <p>
     * 
     * 
     */
    public void setDeclaredDate(String declaredDate) {
        this.declaredDate = declaredDate;
    }

    /**
     * refers to the split ratio. The split ratio is an inverse of the number of shares that a holder of the stock would have after the split divided by the number of shares that the holder had before. For example: Split ratio of .5 = 2 for 1 split.
     * <p>
     * 
     * 
     */
    public Double getRatio() {
        return ratio;
    }

    /**
     * refers to the split ratio. The split ratio is an inverse of the number of shares that a holder of the stock would have after the split divided by the number of shares that the holder had before. For example: Split ratio of .5 = 2 for 1 split.
     * <p>
     * 
     * 
     */
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    /**
     * To factor of the split. Used to calculate the split ratio forfactor/tofactor = ratio (eg ½ = 0.5)
     * <p>
     * 
     * 
     */
    public Double getTofactor() {
        return tofactor;
    }

    /**
     * To factor of the split. Used to calculate the split ratio forfactor/tofactor = ratio (eg ½ = 0.5)
     * <p>
     * 
     * 
     */
    public void setTofactor(Double tofactor) {
        this.tofactor = tofactor;
    }

    /**
     * For factor of the split. Used to calculate the split ratio forfactor/tofactor = ratio (eg ½ = 0.5)
     * <p>
     * 
     * 
     */
    public Double getForfactor() {
        return forfactor;
    }

    /**
     * For factor of the split. Used to calculate the split ratio forfactor/tofactor = ratio (eg ½ = 0.5)
     * <p>
     * 
     * 
     */
    public void setForfactor(Double forfactor) {
        this.forfactor = forfactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StockSplit.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ticker");
        sb.append('=');
        sb.append(((this.ticker == null)?"<null>":this.ticker));
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
        sb.append("ratio");
        sb.append('=');
        sb.append(((this.ratio == null)?"<null>":this.ratio));
        sb.append(',');
        sb.append("tofactor");
        sb.append('=');
        sb.append(((this.tofactor == null)?"<null>":this.tofactor));
        sb.append(',');
        sb.append("forfactor");
        sb.append('=');
        sb.append(((this.forfactor == null)?"<null>":this.forfactor));
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
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+((this.exDate == null)? 0 :this.exDate.hashCode()));
        result = ((result* 31)+((this.tofactor == null)? 0 :this.tofactor.hashCode()));
        result = ((result* 31)+((this.recordDate == null)? 0 :this.recordDate.hashCode()));
        result = ((result* 31)+((this.forfactor == null)? 0 :this.forfactor.hashCode()));
        result = ((result* 31)+((this.paymentDate == null)? 0 :this.paymentDate.hashCode()));
        result = ((result* 31)+((this.ratio == null)? 0 :this.ratio.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StockSplit) == false) {
            return false;
        }
        StockSplit rhs = ((StockSplit) other);
        return (((((((((this.declaredDate == rhs.declaredDate)||((this.declaredDate!= null)&&this.declaredDate.equals(rhs.declaredDate)))&&((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker))))&&((this.exDate == rhs.exDate)||((this.exDate!= null)&&this.exDate.equals(rhs.exDate))))&&((this.tofactor == rhs.tofactor)||((this.tofactor!= null)&&this.tofactor.equals(rhs.tofactor))))&&((this.recordDate == rhs.recordDate)||((this.recordDate!= null)&&this.recordDate.equals(rhs.recordDate))))&&((this.forfactor == rhs.forfactor)||((this.forfactor!= null)&&this.forfactor.equals(rhs.forfactor))))&&((this.paymentDate == rhs.paymentDate)||((this.paymentDate!= null)&&this.paymentDate.equals(rhs.paymentDate))))&&((this.ratio == rhs.ratio)||((this.ratio!= null)&&this.ratio.equals(rhs.ratio))));
    }

}
