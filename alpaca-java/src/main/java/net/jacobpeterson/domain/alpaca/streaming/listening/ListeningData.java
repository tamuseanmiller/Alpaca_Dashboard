
package net.jacobpeterson.domain.alpaca.streaming.listening;

import java.io.Serializable;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/streaming/">https://docs.alpaca.markets/api-documentation/api-v2/streaming/</a>
 * <p>
 * 
 * 
 */
public class ListeningData implements Serializable
{

    @SerializedName("streams")
    @Expose
    private ArrayList<String> streams;
    private final static long serialVersionUID = -6251610011376603475L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListeningData() {
    }

    /**
     * 
     * @param streams
     */
    public ListeningData(ArrayList<String> streams) {
        super();
        this.streams = streams;
    }

    public ArrayList<String> getStreams() {
        return streams;
    }

    public void setStreams(ArrayList<String> streams) {
        this.streams = streams;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ListeningData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("streams");
        sb.append('=');
        sb.append(((this.streams == null)?"<null>":this.streams));
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
        return ((this.streams == rhs.streams)||((this.streams!= null)&&this.streams.equals(rhs.streams)));
    }

}
