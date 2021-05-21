
package net.jacobpeterson.domain.alpaca.marketdata.streaming.listening;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/">https://alpaca.markets/docs/api-documentation/api-v2/market-data/streaming/</a>
 * <p>
 * 
 * 
 */
public class ListeningData implements Serializable
{

    /**
     * The streams listened to
     * <p>
     * 
     * 
     */
    @SerializedName("streams")
    @Expose
    private ArrayList<String> streams;
    /**
     * The error, if one exists
     * <p>
     * 
     * 
     */
    @SerializedName("error")
    @Expose
    private ArrayList<String> error;
    private final static long serialVersionUID = -4338335518370167978L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListeningData() {
    }

    /**
     * 
     * @param streams
     * @param error
     */
    public ListeningData(ArrayList<String> streams, ArrayList<String> error) {
        super();
        this.streams = streams;
        this.error = error;
    }

    /**
     * The streams listened to
     * <p>
     * 
     * 
     */
    public ArrayList<String> getStreams() {
        return streams;
    }

    /**
     * The streams listened to
     * <p>
     * 
     * 
     */
    public void setStreams(ArrayList<String> streams) {
        this.streams = streams;
    }

    /**
     * The error, if one exists
     * <p>
     * 
     * 
     */
    public ArrayList<String> getError() {
        return error;
    }

    /**
     * The error, if one exists
     * <p>
     * 
     * 
     */
    public void setError(ArrayList<String> error) {
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ListeningData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("streams");
        sb.append('=');
        sb.append(((this.streams == null)?"<null>":this.streams));
        sb.append(',');
        sb.append("error");
        sb.append('=');
        sb.append(((this.error == null)?"<null>":this.error));
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
        result = ((result* 31)+((this.error == null)? 0 :this.error.hashCode()));
        result = ((result* 31)+((this.streams == null)? 0 :this.streams.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ListeningData) == false) {
            return false;
        }
        ListeningData rhs = ((ListeningData) other);
        return (((this.error == rhs.error)||((this.error!= null)&&this.error.equals(rhs.error)))&&((this.streams == rhs.streams)||((this.streams!= null)&&this.streams.equals(rhs.streams))));
    }

}
