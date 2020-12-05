
package net.jacobpeterson.domain.polygon.websocket.aggregate;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/sockets">https://polygon.io/sockets</a>
 * <p>
 * 
 * 
 */
public class AggregatePerMinuteMessage
    extends AggregateMessage
    implements Serializable
{

    private final static long serialVersionUID = 3487405590399382205L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AggregatePerMinuteMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        result = ((result* 31)+ super.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AggregatePerMinuteMessage) == false) {
            return false;
        }
        AggregatePerMinuteMessage rhs = ((AggregatePerMinuteMessage) other);
        return super.equals(rhs);
    }

}
