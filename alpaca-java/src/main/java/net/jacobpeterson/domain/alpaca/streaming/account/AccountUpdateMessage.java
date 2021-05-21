
package net.jacobpeterson.domain.alpaca.streaming.account;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.domain.alpaca.streaming.AlpacaStreamMessage;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/streaming/">https://docs.alpaca.markets/api-documentation/api-v2/streaming/</a>
 * <p>
 * 
 * 
 */
public class AccountUpdateMessage
    extends AlpacaStreamMessage
    implements Serializable
{

    @SerializedName("data")
    @Expose
    private AccountUpdate data;
    private final static long serialVersionUID = 4515257110948672534L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AccountUpdateMessage() {
    }

    /**
     * 
     * @param data
     */
    public AccountUpdateMessage(AccountUpdate data) {
        super();
        this.data = data;
    }

    public AccountUpdate getData() {
        return data;
    }

    public void setData(AccountUpdate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AccountUpdateMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof AccountUpdateMessage) == false) {
            return false;
        }
        AccountUpdateMessage rhs = ((AccountUpdateMessage) other);
        return (super.equals(rhs)&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))));
    }

}
