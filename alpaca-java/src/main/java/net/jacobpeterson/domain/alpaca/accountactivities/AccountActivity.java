
package net.jacobpeterson.domain.alpaca.accountactivities;

import java.io.Serializable;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/account-activities/">https://docs.alpaca.markets/api-documentation/api-v2/account-activities/</a>
 * <p>
 * 
 * 
 */
public class AccountActivity implements Serializable
{

    private final static long serialVersionUID = -487375705429599447L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AccountActivity.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AccountActivity) == false) {
            return false;
        }
        AccountActivity rhs = ((AccountActivity) other);
        return true;
    }

}
