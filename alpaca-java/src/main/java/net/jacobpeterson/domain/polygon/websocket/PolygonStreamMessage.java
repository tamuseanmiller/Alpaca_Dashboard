
package net.jacobpeterson.domain.polygon.websocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.abstracts.websocket.message.StreamMessage;
import net.jacobpeterson.polygon.websocket.message.PolygonStreamMessageType;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/sockets">https://polygon.io/sockets</a>
 * <p>
 * 
 * 
 */
public class PolygonStreamMessage implements Serializable, StreamMessage
{

    /**
     * The event type
     * <p>
     * 
     * 
     */
    @SerializedName("ev")
    @Expose
    private PolygonStreamMessageType ev;
    /**
     * Symbol Ticker
     * <p>
     * 
     * 
     */
    @SerializedName("sym")
    @Expose
    private String sym;
    private final static long serialVersionUID = -2189708355355512909L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PolygonStreamMessage() {
    }

    /**
     * 
     * @param ev
     * @param sym
     */
    public PolygonStreamMessage(PolygonStreamMessageType ev, String sym) {
        super();
        this.ev = ev;
        this.sym = sym;
    }

    /**
     * The event type
     * <p>
     * 
     * 
     */
    public PolygonStreamMessageType getEv() {
        return ev;
    }

    /**
     * The event type
     * <p>
     * 
     * 
     */
    public void setEv(PolygonStreamMessageType ev) {
        this.ev = ev;
    }

    /**
     * Symbol Ticker
     * <p>
     * 
     * 
     */
    public String getSym() {
        return sym;
    }

    /**
     * Symbol Ticker
     * <p>
     * 
     * 
     */
    public void setSym(String sym) {
        this.sym = sym;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PolygonStreamMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ev");
        sb.append('=');
        sb.append(((this.ev == null)?"<null>":this.ev));
        sb.append(',');
        sb.append("sym");
        sb.append('=');
        sb.append(((this.sym == null)?"<null>":this.sym));
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
        result = ((result* 31)+((this.ev == null)? 0 :this.ev.hashCode()));
        result = ((result* 31)+((this.sym == null)? 0 :this.sym.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PolygonStreamMessage) == false) {
            return false;
        }
        PolygonStreamMessage rhs = ((PolygonStreamMessage) other);
        return (((this.ev == rhs.ev)||((this.ev!= null)&&this.ev.equals(rhs.ev)))&&((this.sym == rhs.sym)||((this.sym!= null)&&this.sym.equals(rhs.sym))));
    }

}
