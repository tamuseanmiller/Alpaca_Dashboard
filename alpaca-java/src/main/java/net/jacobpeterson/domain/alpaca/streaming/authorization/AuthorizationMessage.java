
package net.jacobpeterson.domain.alpaca.streaming.authorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.alpaca.streaming.AlpacaStreamMessage;

import java.io.Serializable;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/streaming/">https://docs.alpaca.markets/api-documentation/api-v2/streaming/</a>
 * <p>
 * 
 * 
 */
public class AuthorizationMessage
    extends AlpacaStreamMessage
    implements Serializable
{

    /**
     * The data
     * <p>
     * 
     * 
     */
    @SerializedName("data")
    @Expose
    private AuthorizationData data;
    private final static long serialVersionUID = 7336205265736560360L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AuthorizationMessage() {
    }

    /**
     * 
     * @param data
     */
    public AuthorizationMessage(AuthorizationData data) {
        super();
        this.data = data;
    }

    /**
     * The data
     * <p>
     * 
     * 
     */
    public AuthorizationData getData() {
        return data;
    }

    /**
     * The data
     * <p>
     * 
     * 
     */
    public void setData(AuthorizationData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AuthorizationMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AuthorizationMessage) == false) {
            return false;
        }
        AuthorizationMessage rhs = ((AuthorizationMessage) other);
        return (super.equals(rhs)&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))));
    }

}
