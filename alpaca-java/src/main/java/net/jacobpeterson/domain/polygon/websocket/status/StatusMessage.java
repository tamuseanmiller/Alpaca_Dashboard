
package net.jacobpeterson.domain.polygon.websocket.status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.polygon.websocket.PolygonStreamMessage;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/sockets">https://polygon.io/sockets</a>
 * <p>
 * 
 * 
 */
public class StatusMessage
    extends PolygonStreamMessage
    implements Serializable
{

    /**
     * The status
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private String status;
    /**
     * The message
     * <p>
     * 
     * 
     */
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 306388276741363328L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StatusMessage() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public StatusMessage(String status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    /**
     * The status
     * <p>
     * 
     * 
     */
    public String getStatus() {
        return status;
    }

    /**
     * The status
     * <p>
     * 
     * 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * The message
     * <p>
     * 
     * 
     */
    public String getMessage() {
        return message;
    }

    /**
     * The message
     * <p>
     * 
     * 
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StatusMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
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
        result = ((result* 31)+((this.message == null)? 0 :this.message.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StatusMessage) == false) {
            return false;
        }
        StatusMessage rhs = ((StatusMessage) other);
        return ((super.equals(rhs)&&((this.message == rhs.message)||((this.message!= null)&&this.message.equals(rhs.message))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
