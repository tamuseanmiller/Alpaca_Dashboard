
package net.jacobpeterson.domain.polygon.conditionsmapping;

import java.io.Serializable;
import java.util.HashMap;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v1_meta_conditions_ticktype">https://polygon.io/docs/#!/Stocks--Equities/get_v1_meta_conditions_ticktype</a>
 * <p>
 * 
 * 
 */
public class ConditionsMapping
    extends HashMap<String, String>
    implements Serializable
{

    private final static long serialVersionUID = -2246695482757566990L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ConditionsMapping.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof ConditionsMapping) == false) {
            return false;
        }
        ConditionsMapping rhs = ((ConditionsMapping) other);
        return super.equals(rhs);
    }

}
