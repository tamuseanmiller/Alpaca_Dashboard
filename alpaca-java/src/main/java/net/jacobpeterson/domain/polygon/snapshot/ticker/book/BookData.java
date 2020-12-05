
package net.jacobpeterson.domain.polygon.snapshot.ticker.book;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="#">(documentation not yet public)</a>
 * <p>
 * 
 * 
 */
public class BookData implements Serializable
{

    /**
     * The ticker
     * <p>
     * 
     * 
     */
    @SerializedName("ticker")
    @Expose
    private String ticker;
    /**
     * The bids on the book
     * <p>
     * 
     * 
     */
    @SerializedName("bids")
    @Expose
    private ArrayList<BookOrder> bids;
    /**
     * The asks on the book
     * <p>
     * 
     * 
     */
    @SerializedName("asks")
    @Expose
    private ArrayList<BookOrder> asks;
    /**
     * The volume of bids on the book
     * <p>
     * 
     * 
     */
    @SerializedName("bidCount")
    @Expose
    private Integer bidCount;
    /**
     * The volume of asks on the book
     * <p>
     * 
     * 
     */
    @SerializedName("askCount")
    @Expose
    private Integer askCount;
    /**
     * The spread
     * <p>
     * 
     * 
     */
    @SerializedName("spread")
    @Expose
    private Double spread;
    /**
     * Updated timestamp
     * <p>
     * 
     * 
     */
    @SerializedName("updated")
    @Expose
    private Long updated;
    private final static long serialVersionUID = -3237017608544229773L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BookData() {
    }

    /**
     * 
     * @param ticker
     * @param asks
     * @param bids
     * @param bidCount
     * @param updated
     * @param askCount
     * @param spread
     */
    public BookData(String ticker, ArrayList<BookOrder> bids, ArrayList<BookOrder> asks, Integer bidCount, Integer askCount, Double spread, Long updated) {
        super();
        this.ticker = ticker;
        this.bids = bids;
        this.asks = asks;
        this.bidCount = bidCount;
        this.askCount = askCount;
        this.spread = spread;
        this.updated = updated;
    }

    /**
     * The ticker
     * <p>
     * 
     * 
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * The ticker
     * <p>
     * 
     * 
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * The bids on the book
     * <p>
     * 
     * 
     */
    public ArrayList<BookOrder> getBids() {
        return bids;
    }

    /**
     * The bids on the book
     * <p>
     * 
     * 
     */
    public void setBids(ArrayList<BookOrder> bids) {
        this.bids = bids;
    }

    /**
     * The asks on the book
     * <p>
     * 
     * 
     */
    public ArrayList<BookOrder> getAsks() {
        return asks;
    }

    /**
     * The asks on the book
     * <p>
     * 
     * 
     */
    public void setAsks(ArrayList<BookOrder> asks) {
        this.asks = asks;
    }

    /**
     * The volume of bids on the book
     * <p>
     * 
     * 
     */
    public Integer getBidCount() {
        return bidCount;
    }

    /**
     * The volume of bids on the book
     * <p>
     * 
     * 
     */
    public void setBidCount(Integer bidCount) {
        this.bidCount = bidCount;
    }

    /**
     * The volume of asks on the book
     * <p>
     * 
     * 
     */
    public Integer getAskCount() {
        return askCount;
    }

    /**
     * The volume of asks on the book
     * <p>
     * 
     * 
     */
    public void setAskCount(Integer askCount) {
        this.askCount = askCount;
    }

    /**
     * The spread
     * <p>
     * 
     * 
     */
    public Double getSpread() {
        return spread;
    }

    /**
     * The spread
     * <p>
     * 
     * 
     */
    public void setSpread(Double spread) {
        this.spread = spread;
    }

    /**
     * Updated timestamp
     * <p>
     * 
     * 
     */
    public Long getUpdated() {
        return updated;
    }

    /**
     * Updated timestamp
     * <p>
     * 
     * 
     */
    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BookData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ticker");
        sb.append('=');
        sb.append(((this.ticker == null)?"<null>":this.ticker));
        sb.append(',');
        sb.append("bids");
        sb.append('=');
        sb.append(((this.bids == null)?"<null>":this.bids));
        sb.append(',');
        sb.append("asks");
        sb.append('=');
        sb.append(((this.asks == null)?"<null>":this.asks));
        sb.append(',');
        sb.append("bidCount");
        sb.append('=');
        sb.append(((this.bidCount == null)?"<null>":this.bidCount));
        sb.append(',');
        sb.append("askCount");
        sb.append('=');
        sb.append(((this.askCount == null)?"<null>":this.askCount));
        sb.append(',');
        sb.append("spread");
        sb.append('=');
        sb.append(((this.spread == null)?"<null>":this.spread));
        sb.append(',');
        sb.append("updated");
        sb.append('=');
        sb.append(((this.updated == null)?"<null>":this.updated));
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
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+((this.asks == null)? 0 :this.asks.hashCode()));
        result = ((result* 31)+((this.bids == null)? 0 :this.bids.hashCode()));
        result = ((result* 31)+((this.bidCount == null)? 0 :this.bidCount.hashCode()));
        result = ((result* 31)+((this.updated == null)? 0 :this.updated.hashCode()));
        result = ((result* 31)+((this.askCount == null)? 0 :this.askCount.hashCode()));
        result = ((result* 31)+((this.spread == null)? 0 :this.spread.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BookData) == false) {
            return false;
        }
        BookData rhs = ((BookData) other);
        return ((((((((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker)))&&((this.asks == rhs.asks)||((this.asks!= null)&&this.asks.equals(rhs.asks))))&&((this.bids == rhs.bids)||((this.bids!= null)&&this.bids.equals(rhs.bids))))&&((this.bidCount == rhs.bidCount)||((this.bidCount!= null)&&this.bidCount.equals(rhs.bidCount))))&&((this.updated == rhs.updated)||((this.updated!= null)&&this.updated.equals(rhs.updated))))&&((this.askCount == rhs.askCount)||((this.askCount!= null)&&this.askCount.equals(rhs.askCount))))&&((this.spread == rhs.spread)||((this.spread!= null)&&this.spread.equals(rhs.spread))));
    }

}
