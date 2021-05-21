package net.jacobpeterson.alpaca.enums;

import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.abstracts.enums.APIName;
import net.jacobpeterson.util.gson.GsonUtil;

public enum PortfolioTimeFrame implements APIName {

    /** One min portfolio time frame. */
    @SerializedName("1Min")
    ONE_MIN,

    /** Five minute portfolio time frame. */
    @SerializedName("5Min")
    FIVE_MINUTE,

    /** Fifteen minute portfolio time frame. */
    @SerializedName("15Min")
    FIFTEEN_MINUTE,

    /** One hour portfolio time frame. */
    @SerializedName("1H")
    ONE_HOUR,

    /** One day portfolio time frame. */
    @SerializedName("1D")
    ONE_DAY;

    @Override
    public String getAPIName() {
        return GsonUtil.GSON.toJsonTree(this).getAsString();
    }
}
