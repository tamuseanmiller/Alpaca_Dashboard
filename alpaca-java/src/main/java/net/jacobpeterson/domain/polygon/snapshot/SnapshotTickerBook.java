
package net.jacobpeterson.domain.polygon.snapshot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.polygon.snapshot.ticker.book.BookData;

import java.io.Serializable;


/**
 * See <a href="#">(documentation not yet public)</a>
 * <p>
 * 
 * 
 */
public class SnapshotTickerBook implements Serializable
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
     * The book data
     * <p>
     * 
     * 
     */
    @SerializedName("data")
    @Expose
    private BookData data;
    private final static long serialVersionUID = 444999797319112006L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SnapshotTickerBook() {
    }

    /**
     * 
     * @param data
     * @param status
     */
    public SnapshotTickerBook(String status, BookData data) {
        super();
        this.status = status;
        this.data = data;
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
     * The book data
     * <p>
     * 
     * 
     */
    public BookData getData() {
        return data;
    }

    /**
     * The book data
     * <p>
     * 
     * 
     */
    public void setData(BookData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SnapshotTickerBook.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
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
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SnapshotTickerBook) == false) {
            return false;
        }
        SnapshotTickerBook rhs = ((SnapshotTickerBook) other);
        return (((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
