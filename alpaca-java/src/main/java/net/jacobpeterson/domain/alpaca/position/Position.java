
package net.jacobpeterson.domain.alpaca.position;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/positions/">https://alpaca.markets/docs/api-documentation/api-v2/positions/</a>
 * <p>
 * 
 * 
 */
public class Position implements Serializable
{

    /**
     * Asset ID
     * <p>
     * 
     * 
     */
    @SerializedName("asset_id")
    @Expose
    private String assetId;
    /**
     * Symbol name of the asset
     * <p>
     * 
     * 
     */
    @SerializedName("symbol")
    @Expose
    private String symbol;
    /**
     * Exchange name of the asset
     * <p>
     * 
     * 
     */
    @SerializedName("exchange")
    @Expose
    private String exchange;
    /**
     * Asset class name
     * <p>
     * 
     * 
     */
    @SerializedName("asset_class")
    @Expose
    private String assetClass;
    /**
     * Average entry price of the position
     * <p>
     * 
     * 
     */
    @SerializedName("avg_entry_price")
    @Expose
    private String avgEntryPrice;
    /**
     * The number of shares
     * <p>
     * 
     * 
     */
    @SerializedName("qty")
    @Expose
    private String qty;
    /**
     * “long”
     * <p>
     * 
     * 
     */
    @SerializedName("side")
    @Expose
    private String side;
    /**
     * Total dollar amount of the position
     * <p>
     * 
     * 
     */
    @SerializedName("market_value")
    @Expose
    private String marketValue;
    /**
     * Total cost basis in dollar
     * <p>
     * 
     * 
     */
    @SerializedName("cost_basis")
    @Expose
    private String costBasis;
    /**
     * Unrealized profit/loss in dollars
     * <p>
     * 
     * 
     */
    @SerializedName("unrealized_pl")
    @Expose
    private String unrealizedPl;
    /**
     * Unrealized profit/loss percent (by a factor of 1)
     * <p>
     * 
     * 
     */
    @SerializedName("unrealized_plpc")
    @Expose
    private String unrealizedPlpc;
    /**
     * Unrealized profit/loss in dollars for the day
     * <p>
     * 
     * 
     */
    @SerializedName("unrealized_intraday_pl")
    @Expose
    private String unrealizedIntradayPl;
    /**
     * Unrealized profit/loss percent (by a factor of 1)
     * <p>
     * 
     * 
     */
    @SerializedName("unrealized_intraday_plpc")
    @Expose
    private String unrealizedIntradayPlpc;
    /**
     * Current asset price per share
     * <p>
     * 
     * 
     */
    @SerializedName("current_price")
    @Expose
    private String currentPrice;
    /**
     * Last day’s asset price per share based on the closing value of the last trading day
     * <p>
     * 
     * 
     */
    @SerializedName("lastday_price")
    @Expose
    private String lastdayPrice;
    /**
     * Percent change from last day price (by a factor of 1)
     * <p>
     * 
     * 
     */
    @SerializedName("change_today")
    @Expose
    private String changeToday;
    private final static long serialVersionUID = 7655138772981216086L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Position() {
    }

    /**
     * 
     * @param symbol
     * @param lastdayPrice
     * @param side
     * @param marketValue
     * @param currentPrice
     * @param assetClass
     * @param avgEntryPrice
     * @param unrealizedIntradayPl
     * @param unrealizedPlpc
     * @param unrealizedPl
     * @param changeToday
     * @param assetId
     * @param qty
     * @param costBasis
     * @param exchange
     * @param unrealizedIntradayPlpc
     */
    public Position(String assetId, String symbol, String exchange, String assetClass, String avgEntryPrice, String qty, String side, String marketValue, String costBasis, String unrealizedPl, String unrealizedPlpc, String unrealizedIntradayPl, String unrealizedIntradayPlpc, String currentPrice, String lastdayPrice, String changeToday) {
        super();
        this.assetId = assetId;
        this.symbol = symbol;
        this.exchange = exchange;
        this.assetClass = assetClass;
        this.avgEntryPrice = avgEntryPrice;
        this.qty = qty;
        this.side = side;
        this.marketValue = marketValue;
        this.costBasis = costBasis;
        this.unrealizedPl = unrealizedPl;
        this.unrealizedPlpc = unrealizedPlpc;
        this.unrealizedIntradayPl = unrealizedIntradayPl;
        this.unrealizedIntradayPlpc = unrealizedIntradayPlpc;
        this.currentPrice = currentPrice;
        this.lastdayPrice = lastdayPrice;
        this.changeToday = changeToday;
    }

    /**
     * Asset ID
     * <p>
     * 
     * 
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * Asset ID
     * <p>
     * 
     * 
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    /**
     * Symbol name of the asset
     * <p>
     * 
     * 
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Symbol name of the asset
     * <p>
     * 
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Exchange name of the asset
     * <p>
     * 
     * 
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * Exchange name of the asset
     * <p>
     * 
     * 
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    /**
     * Asset class name
     * <p>
     * 
     * 
     */
    public String getAssetClass() {
        return assetClass;
    }

    /**
     * Asset class name
     * <p>
     * 
     * 
     */
    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    /**
     * Average entry price of the position
     * <p>
     * 
     * 
     */
    public String getAvgEntryPrice() {
        return avgEntryPrice;
    }

    /**
     * Average entry price of the position
     * <p>
     * 
     * 
     */
    public void setAvgEntryPrice(String avgEntryPrice) {
        this.avgEntryPrice = avgEntryPrice;
    }

    /**
     * The number of shares
     * <p>
     * 
     * 
     */
    public String getQty() {
        return qty;
    }

    /**
     * The number of shares
     * <p>
     * 
     * 
     */
    public void setQty(String qty) {
        this.qty = qty;
    }

    /**
     * “long”
     * <p>
     * 
     * 
     */
    public String getSide() {
        return side;
    }

    /**
     * “long”
     * <p>
     * 
     * 
     */
    public void setSide(String side) {
        this.side = side;
    }

    /**
     * Total dollar amount of the position
     * <p>
     * 
     * 
     */
    public String getMarketValue() {
        return marketValue;
    }

    /**
     * Total dollar amount of the position
     * <p>
     * 
     * 
     */
    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * Total cost basis in dollar
     * <p>
     * 
     * 
     */
    public String getCostBasis() {
        return costBasis;
    }

    /**
     * Total cost basis in dollar
     * <p>
     * 
     * 
     */
    public void setCostBasis(String costBasis) {
        this.costBasis = costBasis;
    }

    /**
     * Unrealized profit/loss in dollars
     * <p>
     * 
     * 
     */
    public String getUnrealizedPl() {
        return unrealizedPl;
    }

    /**
     * Unrealized profit/loss in dollars
     * <p>
     * 
     * 
     */
    public void setUnrealizedPl(String unrealizedPl) {
        this.unrealizedPl = unrealizedPl;
    }

    /**
     * Unrealized profit/loss percent (by a factor of 1)
     * <p>
     * 
     * 
     */
    public String getUnrealizedPlpc() {
        return unrealizedPlpc;
    }

    /**
     * Unrealized profit/loss percent (by a factor of 1)
     * <p>
     * 
     * 
     */
    public void setUnrealizedPlpc(String unrealizedPlpc) {
        this.unrealizedPlpc = unrealizedPlpc;
    }

    /**
     * Unrealized profit/loss in dollars for the day
     * <p>
     * 
     * 
     */
    public String getUnrealizedIntradayPl() {
        return unrealizedIntradayPl;
    }

    /**
     * Unrealized profit/loss in dollars for the day
     * <p>
     * 
     * 
     */
    public void setUnrealizedIntradayPl(String unrealizedIntradayPl) {
        this.unrealizedIntradayPl = unrealizedIntradayPl;
    }

    /**
     * Unrealized profit/loss percent (by a factor of 1)
     * <p>
     * 
     * 
     */
    public String getUnrealizedIntradayPlpc() {
        return unrealizedIntradayPlpc;
    }

    /**
     * Unrealized profit/loss percent (by a factor of 1)
     * <p>
     * 
     * 
     */
    public void setUnrealizedIntradayPlpc(String unrealizedIntradayPlpc) {
        this.unrealizedIntradayPlpc = unrealizedIntradayPlpc;
    }

    /**
     * Current asset price per share
     * <p>
     * 
     * 
     */
    public String getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Current asset price per share
     * <p>
     * 
     * 
     */
    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * Last day’s asset price per share based on the closing value of the last trading day
     * <p>
     * 
     * 
     */
    public String getLastdayPrice() {
        return lastdayPrice;
    }

    /**
     * Last day’s asset price per share based on the closing value of the last trading day
     * <p>
     * 
     * 
     */
    public void setLastdayPrice(String lastdayPrice) {
        this.lastdayPrice = lastdayPrice;
    }

    /**
     * Percent change from last day price (by a factor of 1)
     * <p>
     * 
     * 
     */
    public String getChangeToday() {
        return changeToday;
    }

    /**
     * Percent change from last day price (by a factor of 1)
     * <p>
     * 
     * 
     */
    public void setChangeToday(String changeToday) {
        this.changeToday = changeToday;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Position.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("assetId");
        sb.append('=');
        sb.append(((this.assetId == null)?"<null>":this.assetId));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("exchange");
        sb.append('=');
        sb.append(((this.exchange == null)?"<null>":this.exchange));
        sb.append(',');
        sb.append("assetClass");
        sb.append('=');
        sb.append(((this.assetClass == null)?"<null>":this.assetClass));
        sb.append(',');
        sb.append("avgEntryPrice");
        sb.append('=');
        sb.append(((this.avgEntryPrice == null)?"<null>":this.avgEntryPrice));
        sb.append(',');
        sb.append("qty");
        sb.append('=');
        sb.append(((this.qty == null)?"<null>":this.qty));
        sb.append(',');
        sb.append("side");
        sb.append('=');
        sb.append(((this.side == null)?"<null>":this.side));
        sb.append(',');
        sb.append("marketValue");
        sb.append('=');
        sb.append(((this.marketValue == null)?"<null>":this.marketValue));
        sb.append(',');
        sb.append("costBasis");
        sb.append('=');
        sb.append(((this.costBasis == null)?"<null>":this.costBasis));
        sb.append(',');
        sb.append("unrealizedPl");
        sb.append('=');
        sb.append(((this.unrealizedPl == null)?"<null>":this.unrealizedPl));
        sb.append(',');
        sb.append("unrealizedPlpc");
        sb.append('=');
        sb.append(((this.unrealizedPlpc == null)?"<null>":this.unrealizedPlpc));
        sb.append(',');
        sb.append("unrealizedIntradayPl");
        sb.append('=');
        sb.append(((this.unrealizedIntradayPl == null)?"<null>":this.unrealizedIntradayPl));
        sb.append(',');
        sb.append("unrealizedIntradayPlpc");
        sb.append('=');
        sb.append(((this.unrealizedIntradayPlpc == null)?"<null>":this.unrealizedIntradayPlpc));
        sb.append(',');
        sb.append("currentPrice");
        sb.append('=');
        sb.append(((this.currentPrice == null)?"<null>":this.currentPrice));
        sb.append(',');
        sb.append("lastdayPrice");
        sb.append('=');
        sb.append(((this.lastdayPrice == null)?"<null>":this.lastdayPrice));
        sb.append(',');
        sb.append("changeToday");
        sb.append('=');
        sb.append(((this.changeToday == null)?"<null>":this.changeToday));
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
        result = ((result* 31)+((this.lastdayPrice == null)? 0 :this.lastdayPrice.hashCode()));
        result = ((result* 31)+((this.side == null)? 0 :this.side.hashCode()));
        result = ((result* 31)+((this.marketValue == null)? 0 :this.marketValue.hashCode()));
        result = ((result* 31)+((this.currentPrice == null)? 0 :this.currentPrice.hashCode()));
        result = ((result* 31)+((this.assetClass == null)? 0 :this.assetClass.hashCode()));
        result = ((result* 31)+((this.avgEntryPrice == null)? 0 :this.avgEntryPrice.hashCode()));
        result = ((result* 31)+((this.unrealizedIntradayPl == null)? 0 :this.unrealizedIntradayPl.hashCode()));
        result = ((result* 31)+((this.unrealizedPlpc == null)? 0 :this.unrealizedPlpc.hashCode()));
        result = ((result* 31)+((this.unrealizedPl == null)? 0 :this.unrealizedPl.hashCode()));
        result = ((result* 31)+((this.changeToday == null)? 0 :this.changeToday.hashCode()));
        result = ((result* 31)+((this.assetId == null)? 0 :this.assetId.hashCode()));
        result = ((result* 31)+((this.qty == null)? 0 :this.qty.hashCode()));
        result = ((result* 31)+((this.costBasis == null)? 0 :this.costBasis.hashCode()));
        result = ((result* 31)+((this.exchange == null)? 0 :this.exchange.hashCode()));
        result = ((result* 31)+((this.unrealizedIntradayPlpc == null)? 0 :this.unrealizedIntradayPlpc.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Position) == false) {
            return false;
        }
        Position rhs = ((Position) other);
        return (((((((((((((((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.lastdayPrice == rhs.lastdayPrice)||((this.lastdayPrice!= null)&&this.lastdayPrice.equals(rhs.lastdayPrice))))&&((this.side == rhs.side)||((this.side!= null)&&this.side.equals(rhs.side))))&&((this.marketValue == rhs.marketValue)||((this.marketValue!= null)&&this.marketValue.equals(rhs.marketValue))))&&((this.currentPrice == rhs.currentPrice)||((this.currentPrice!= null)&&this.currentPrice.equals(rhs.currentPrice))))&&((this.assetClass == rhs.assetClass)||((this.assetClass!= null)&&this.assetClass.equals(rhs.assetClass))))&&((this.avgEntryPrice == rhs.avgEntryPrice)||((this.avgEntryPrice!= null)&&this.avgEntryPrice.equals(rhs.avgEntryPrice))))&&((this.unrealizedIntradayPl == rhs.unrealizedIntradayPl)||((this.unrealizedIntradayPl!= null)&&this.unrealizedIntradayPl.equals(rhs.unrealizedIntradayPl))))&&((this.unrealizedPlpc == rhs.unrealizedPlpc)||((this.unrealizedPlpc!= null)&&this.unrealizedPlpc.equals(rhs.unrealizedPlpc))))&&((this.unrealizedPl == rhs.unrealizedPl)||((this.unrealizedPl!= null)&&this.unrealizedPl.equals(rhs.unrealizedPl))))&&((this.changeToday == rhs.changeToday)||((this.changeToday!= null)&&this.changeToday.equals(rhs.changeToday))))&&((this.assetId == rhs.assetId)||((this.assetId!= null)&&this.assetId.equals(rhs.assetId))))&&((this.qty == rhs.qty)||((this.qty!= null)&&this.qty.equals(rhs.qty))))&&((this.costBasis == rhs.costBasis)||((this.costBasis!= null)&&this.costBasis.equals(rhs.costBasis))))&&((this.exchange == rhs.exchange)||((this.exchange!= null)&&this.exchange.equals(rhs.exchange))))&&((this.unrealizedIntradayPlpc == rhs.unrealizedIntradayPlpc)||((this.unrealizedIntradayPlpc!= null)&&this.unrealizedIntradayPlpc.equals(rhs.unrealizedIntradayPlpc))));
    }

}
