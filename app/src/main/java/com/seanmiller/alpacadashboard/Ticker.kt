package net.jacobpeterson.domain.polygon.tickers.ticker

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.ZonedDateTime

/**
 * See [https://polygon.io/docs/#!/Reference/get_v2_reference_tickers](https://polygon.io/docs/#!/Reference/get_v2_reference_tickers)
 *
 *
 *
 *
 */
class Ticker : Serializable {
    /**
     * Ticker
     *
     *
     *
     *
     */
    /**
     * Ticker
     *
     *
     *
     *
     */
    /**
     * Ticker
     *
     *
     *
     *
     */
    @SerializedName("ticker")
    @Expose
    var ticker: String? = null
    /**
     * Name of the item.
     *
     *
     *
     *
     */
    /**
     * Name of the item.
     *
     *
     *
     *
     */
    /**
     * Name of the item.
     *
     *
     *
     *
     */
    @SerializedName("name")
    @Expose
    var name: String? = null
    /**
     * Market
     *
     *
     *
     *
     */
    /**
     * Market
     *
     *
     *
     *
     */
    /**
     * Market
     *
     *
     *
     *
     */
    @SerializedName("market")
    @Expose
    var market: String? = null
    /**
     * Locale
     *
     *
     *
     *
     */
    /**
     * Locale
     *
     *
     *
     *
     */
    /**
     * Locale
     *
     *
     *
     *
     */
    @SerializedName("locale")
    @Expose
    var locale: String? = null
    /**
     * Currency
     *
     *
     *
     *
     */
    /**
     * Currency
     *
     *
     *
     *
     */
    /**
     * Currency
     *
     *
     *
     *
     */
    @SerializedName("currency")
    @Expose
    var currency: String? = null
    /**
     * Active
     *
     *
     *
     *
     */
    /**
     * Active
     *
     *
     *
     *
     */
    /**
     * Active
     *
     *
     *
     *
     */
    @SerializedName("active")
    @Expose
    var active: Boolean? = null
    /**
     * Primary Exchange
     *
     *
     *
     *
     */
    /**
     * Primary Exchange
     *
     *
     *
     *
     */
    /**
     * Primary Exchange
     *
     *
     *
     *
     */
    @SerializedName("primaryExch")
    @Expose
    var primaryExch: String? = null
    /**
     * Last time this company record was updated.
     *
     *
     *
     *
     */
    /**
     * Last time this company record was updated.
     *
     *
     *
     *
     */
    /**
     * Last time this company record was updated.
     *
     *
     *
     *
     */
    @SerializedName("updated")
    @Expose
    var updated: ZonedDateTime? = null
    /**
     * URL of this symbol. Use this to get this symbols endpoints.
     *
     *
     *
     *
     */
    /**
     * URL of this symbol. Use this to get this symbols endpoints.
     *
     *
     *
     *
     */
    /**
     * URL of this symbol. Use this to get this symbols endpoints.
     *
     *
     *
     *
     */
    @SerializedName("url")
    @Expose
    var url: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param market
     * @param ticker
     * @param name
     * @param active
     * @param primaryExch
     * @param currency
     * @param locale
     * @param updated
     * @param url
     */
    constructor(ticker: String?, name: String?, market: String?, locale: String?, currency: String?, active: Boolean?, primaryExch: String?, updated: ZonedDateTime?, url: String?) : super() {
        this.ticker = ticker
        this.name = name
        this.market = market
        this.locale = locale
        this.currency = currency
        this.active = active
        this.primaryExch = primaryExch
        this.updated = updated
        this.url = url
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Ticker::class.java.name).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("ticker")
        sb.append('=')
        sb.append(if (ticker == null) "<null>" else ticker)
        sb.append(',')
        sb.append("name")
        sb.append('=')
        sb.append(if (name == null) "<null>" else name)
        sb.append(',')
        sb.append("market")
        sb.append('=')
        sb.append(if (market == null) "<null>" else market)
        sb.append(',')
        sb.append("locale")
        sb.append('=')
        sb.append(if (locale == null) "<null>" else locale)
        sb.append(',')
        sb.append("currency")
        sb.append('=')
        sb.append(if (currency == null) "<null>" else currency)
        sb.append(',')
        sb.append("active")
        sb.append('=')
        sb.append(if (active == null) "<null>" else active)
        sb.append(',')
        sb.append("primaryExch")
        sb.append('=')
        sb.append(if (primaryExch == null) "<null>" else primaryExch)
        sb.append(',')
        sb.append("updated")
        sb.append('=')
        sb.append(if (updated == null) "<null>" else updated)
        sb.append(',')
        sb.append("url")
        sb.append('=')
        sb.append(if (url == null) "<null>" else url)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }

    override fun hashCode(): Int {
        var result = 1
        result = result * 31 + if (market == null) 0 else market.hashCode()
        result = result * 31 + if (ticker == null) 0 else ticker.hashCode()
        result = result * 31 + if (name == null) 0 else name.hashCode()
        result = result * 31 + if (active == null) 0 else active.hashCode()
        result = result * 31 + if (primaryExch == null) 0 else primaryExch.hashCode()
        result = result * 31 + if (currency == null) 0 else currency.hashCode()
        result = result * 31 + if (locale == null) 0 else locale.hashCode()
        result = result * 31 + if (updated == null) 0 else updated.hashCode()
        result = result * 31 + if (url == null) 0 else url.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other is Ticker == false) {
            return false
        }
        val rhs = other
        return (market === rhs.market || market != null && market == rhs.market) && (ticker === rhs.ticker || ticker != null && ticker == rhs.ticker) && (name === rhs.name || name != null && name == rhs.name) && (active === rhs.active || active != null && active == rhs.active) && (primaryExch === rhs.primaryExch || primaryExch != null && primaryExch == rhs.primaryExch) && (currency === rhs.currency || currency != null && currency == rhs.currency) && (locale === rhs.locale || locale != null && locale == rhs.locale) && (updated === rhs.updated || updated != null && updated == rhs.updated) && (url === rhs.url || url != null && url == rhs.url)
    }

    companion object {
        private const val serialVersionUID = 4061577169099397337L
    }
}