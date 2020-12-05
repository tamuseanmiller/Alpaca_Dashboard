
package net.jacobpeterson.domain.alpaca.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.jacobpeterson.alpaca.enums.AccountStatus;

import java.io.Serializable;
import java.time.ZonedDateTime;


/**
 * See <a href="https://docs.alpaca.markets/api-documentation/api-v2/account/">https://docs.alpaca.markets/api-documentation/api-v2/account/</a>
 * <p>
 * 
 * 
 */
public class Account implements Serializable
{

    /**
     * Account ID.
     * <p>
     * 
     * 
     */
    @SerializedName("id")
    @Expose
    private String id;
    /**
     * Account number.
     * <p>
     * 
     * 
     */
    @SerializedName("account_number")
    @Expose
    private String accountNumber;
    /**
     * See Account Status
     * <p>
     * 
     * 
     */
    @SerializedName("status")
    @Expose
    private AccountStatus status;
    /**
     * “USD”
     * <p>
     * 
     * 
     */
    @SerializedName("currency")
    @Expose
    private String currency;
    /**
     * Cash balance
     * <p>
     * 
     * 
     */
    @SerializedName("cash")
    @Expose
    private String cash;
    /**
     * Total value of cash + holding positions (This field is deprecated. It is equivalent to the equity field.)
     * <p>
     * 
     * 
     */
    @SerializedName("portfolio_value")
    @Expose
    private String portfolioValue;
    /**
     * Whether or not the account has been flagged as a pattern day trader
     * <p>
     * 
     * 
     */
    @SerializedName("pattern_day_trader")
    @Expose
    private Boolean patternDayTrader;
    /**
     * User setting. If true, the account is not allowed to place orders.
     * <p>
     * 
     * 
     */
    @SerializedName("trade_suspended_by_user")
    @Expose
    private Boolean tradeSuspendedByUser;
    /**
     * If true, the account is not allowed to place orders.
     * <p>
     * 
     * 
     */
    @SerializedName("trading_blocked")
    @Expose
    private Boolean tradingBlocked;
    /**
     * If true, the account is not allowed to request money transfers.
     * <p>
     * 
     * 
     */
    @SerializedName("transfers_blocked")
    @Expose
    private Boolean transfersBlocked;
    /**
     * If true, the account activity by user is prohibited.
     * <p>
     * 
     * 
     */
    @SerializedName("account_blocked")
    @Expose
    private Boolean accountBlocked;
    /**
     * Timestamp this account was created at
     * <p>
     * 
     * 
     */
    @SerializedName("created_at")
    @Expose
    private ZonedDateTime createdAt;
    /**
     * Flag to denote whether or not the account is permitted to short
     * <p>
     * 
     * 
     */
    @SerializedName("shorting_enabled")
    @Expose
    private Boolean shortingEnabled;
    /**
     * Real-time MtM value of all long positions held in the account
     * <p>
     * 
     * 
     */
    @SerializedName("long_market_value")
    @Expose
    private String longMarketValue;
    /**
     * Real-time MtM value of all short positions held in the account
     * <p>
     * 
     * 
     */
    @SerializedName("short_market_value")
    @Expose
    private String shortMarketValue;
    /**
     * Cash + long_market_value + short_market_value
     * <p>
     * 
     * 
     */
    @SerializedName("equity")
    @Expose
    private String equity;
    /**
     * Equity as of previous trading day at 16:00:00 ET
     * <p>
     * 
     * 
     */
    @SerializedName("last_equity")
    @Expose
    private String lastEquity;
    /**
     * Buying power multiplier that represents account margin classification; valid values 1 (standard limited margin account with 1x buying power), 2 (reg T margin account with 2x intraday and overnight buying power; this is the default for all non-PDT accounts with $2,000 or more equity), 4 (PDT account with 4x intraday buying power and 2x reg T overnight buying power)
     * <p>
     * 
     * 
     */
    @SerializedName("multiplier")
    @Expose
    private String multiplier;
    /**
     * Current available $ buying power; If multiplier = 4, this is your daytrade buying power which is calculated as (last_equity - (last) maintenance_margin) * 4; If multiplier = 2,  buying_power = max(equity – initial_margin,0) * 2; If multiplier = 1, buying_power = cash
     * <p>
     * 
     * 
     */
    @SerializedName("buying_power")
    @Expose
    private String buyingPower;
    /**
     * Reg T initial margin requirement (continuously updated value)
     * <p>
     * 
     * 
     */
    @SerializedName("initial_margin")
    @Expose
    private String initialMargin;
    /**
     * Maintenance margin requirement (continuously updated value)
     * <p>
     * 
     * 
     */
    @SerializedName("maintenance_margin")
    @Expose
    private String maintenanceMargin;
    /**
     * Value of special memorandum account (will be used at a later date to provide additional buying_power)
     * <p>
     * 
     * 
     */
    @SerializedName("sma")
    @Expose
    private String sma;
    /**
     * The current number of daytrades that have been made in the last 5 trading days (inclusive of today)
     * <p>
     * 
     * 
     */
    @SerializedName("daytrade_count")
    @Expose
    private Integer daytradeCount;
    /**
     * Your maintenance margin requirement on the previous trading day
     * <p>
     * 
     * 
     */
    @SerializedName("last_maintenance_margin")
    @Expose
    private String lastMaintenanceMargin;
    /**
     * Your buying power for day trades (continuously updated value)
     * <p>
     * 
     * 
     */
    @SerializedName("daytrading_buying_power")
    @Expose
    private String daytradingBuyingPower;
    /**
     * Your buying power under Regulation T (your excess equity - equity minus margin value - times your margin multiplier)
     * <p>
     * 
     * 
     */
    @SerializedName("regt_buying_power")
    @Expose
    private String regtBuyingPower;
    private final static long serialVersionUID = -4431080381636354885L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Account() {
    }

    /**
     * 
     * @param tradingBlocked
     * @param sma
     * @param tradeSuspendedByUser
     * @param portfolioValue
     * @param equity
     * @param initialMargin
     * @param createdAt
     * @param buyingPower
     * @param maintenanceMargin
     * @param currency
     * @param id
     * @param cash
     * @param lastMaintenanceMargin
     * @param daytradingBuyingPower
     * @param longMarketValue
     * @param shortingEnabled
     * @param multiplier
     * @param lastEquity
     * @param shortMarketValue
     * @param accountNumber
     * @param patternDayTrader
     * @param daytradeCount
     * @param transfersBlocked
     * @param regtBuyingPower
     * @param accountBlocked
     * @param status
     */
    public Account(String id, String accountNumber, AccountStatus status, String currency, String cash, String portfolioValue, Boolean patternDayTrader, Boolean tradeSuspendedByUser, Boolean tradingBlocked, Boolean transfersBlocked, Boolean accountBlocked, ZonedDateTime createdAt, Boolean shortingEnabled, String longMarketValue, String shortMarketValue, String equity, String lastEquity, String multiplier, String buyingPower, String initialMargin, String maintenanceMargin, String sma, Integer daytradeCount, String lastMaintenanceMargin, String daytradingBuyingPower, String regtBuyingPower) {
        super();
        this.id = id;
        this.accountNumber = accountNumber;
        this.status = status;
        this.currency = currency;
        this.cash = cash;
        this.portfolioValue = portfolioValue;
        this.patternDayTrader = patternDayTrader;
        this.tradeSuspendedByUser = tradeSuspendedByUser;
        this.tradingBlocked = tradingBlocked;
        this.transfersBlocked = transfersBlocked;
        this.accountBlocked = accountBlocked;
        this.createdAt = createdAt;
        this.shortingEnabled = shortingEnabled;
        this.longMarketValue = longMarketValue;
        this.shortMarketValue = shortMarketValue;
        this.equity = equity;
        this.lastEquity = lastEquity;
        this.multiplier = multiplier;
        this.buyingPower = buyingPower;
        this.initialMargin = initialMargin;
        this.maintenanceMargin = maintenanceMargin;
        this.sma = sma;
        this.daytradeCount = daytradeCount;
        this.lastMaintenanceMargin = lastMaintenanceMargin;
        this.daytradingBuyingPower = daytradingBuyingPower;
        this.regtBuyingPower = regtBuyingPower;
    }

    /**
     * Account ID.
     * <p>
     * 
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * Account ID.
     * <p>
     * 
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Account number.
     * <p>
     * 
     * 
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Account number.
     * <p>
     * 
     * 
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * See Account Status
     * <p>
     * 
     * 
     */
    public AccountStatus getStatus() {
        return status;
    }

    /**
     * See Account Status
     * <p>
     * 
     * 
     */
    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    /**
     * “USD”
     * <p>
     * 
     * 
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * “USD”
     * <p>
     * 
     * 
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Cash balance
     * <p>
     * 
     * 
     */
    public String getCash() {
        return cash;
    }

    /**
     * Cash balance
     * <p>
     * 
     * 
     */
    public void setCash(String cash) {
        this.cash = cash;
    }

    /**
     * Total value of cash + holding positions (This field is deprecated. It is equivalent to the equity field.)
     * <p>
     * 
     * 
     */
    public String getPortfolioValue() {
        return portfolioValue;
    }

    /**
     * Total value of cash + holding positions (This field is deprecated. It is equivalent to the equity field.)
     * <p>
     * 
     * 
     */
    public void setPortfolioValue(String portfolioValue) {
        this.portfolioValue = portfolioValue;
    }

    /**
     * Whether or not the account has been flagged as a pattern day trader
     * <p>
     * 
     * 
     */
    public Boolean getPatternDayTrader() {
        return patternDayTrader;
    }

    /**
     * Whether or not the account has been flagged as a pattern day trader
     * <p>
     * 
     * 
     */
    public void setPatternDayTrader(Boolean patternDayTrader) {
        this.patternDayTrader = patternDayTrader;
    }

    /**
     * User setting. If true, the account is not allowed to place orders.
     * <p>
     * 
     * 
     */
    public Boolean getTradeSuspendedByUser() {
        return tradeSuspendedByUser;
    }

    /**
     * User setting. If true, the account is not allowed to place orders.
     * <p>
     * 
     * 
     */
    public void setTradeSuspendedByUser(Boolean tradeSuspendedByUser) {
        this.tradeSuspendedByUser = tradeSuspendedByUser;
    }

    /**
     * If true, the account is not allowed to place orders.
     * <p>
     * 
     * 
     */
    public Boolean getTradingBlocked() {
        return tradingBlocked;
    }

    /**
     * If true, the account is not allowed to place orders.
     * <p>
     * 
     * 
     */
    public void setTradingBlocked(Boolean tradingBlocked) {
        this.tradingBlocked = tradingBlocked;
    }

    /**
     * If true, the account is not allowed to request money transfers.
     * <p>
     * 
     * 
     */
    public Boolean getTransfersBlocked() {
        return transfersBlocked;
    }

    /**
     * If true, the account is not allowed to request money transfers.
     * <p>
     * 
     * 
     */
    public void setTransfersBlocked(Boolean transfersBlocked) {
        this.transfersBlocked = transfersBlocked;
    }

    /**
     * If true, the account activity by user is prohibited.
     * <p>
     * 
     * 
     */
    public Boolean getAccountBlocked() {
        return accountBlocked;
    }

    /**
     * If true, the account activity by user is prohibited.
     * <p>
     * 
     * 
     */
    public void setAccountBlocked(Boolean accountBlocked) {
        this.accountBlocked = accountBlocked;
    }

    /**
     * Timestamp this account was created at
     * <p>
     * 
     * 
     */
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Timestamp this account was created at
     * <p>
     * 
     * 
     */
    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Flag to denote whether or not the account is permitted to short
     * <p>
     * 
     * 
     */
    public Boolean getShortingEnabled() {
        return shortingEnabled;
    }

    /**
     * Flag to denote whether or not the account is permitted to short
     * <p>
     * 
     * 
     */
    public void setShortingEnabled(Boolean shortingEnabled) {
        this.shortingEnabled = shortingEnabled;
    }

    /**
     * Real-time MtM value of all long positions held in the account
     * <p>
     * 
     * 
     */
    public String getLongMarketValue() {
        return longMarketValue;
    }

    /**
     * Real-time MtM value of all long positions held in the account
     * <p>
     * 
     * 
     */
    public void setLongMarketValue(String longMarketValue) {
        this.longMarketValue = longMarketValue;
    }

    /**
     * Real-time MtM value of all short positions held in the account
     * <p>
     * 
     * 
     */
    public String getShortMarketValue() {
        return shortMarketValue;
    }

    /**
     * Real-time MtM value of all short positions held in the account
     * <p>
     * 
     * 
     */
    public void setShortMarketValue(String shortMarketValue) {
        this.shortMarketValue = shortMarketValue;
    }

    /**
     * Cash + long_market_value + short_market_value
     * <p>
     * 
     * 
     */
    public String getEquity() {
        return equity;
    }

    /**
     * Cash + long_market_value + short_market_value
     * <p>
     * 
     * 
     */
    public void setEquity(String equity) {
        this.equity = equity;
    }

    /**
     * Equity as of previous trading day at 16:00:00 ET
     * <p>
     * 
     * 
     */
    public String getLastEquity() {
        return lastEquity;
    }

    /**
     * Equity as of previous trading day at 16:00:00 ET
     * <p>
     * 
     * 
     */
    public void setLastEquity(String lastEquity) {
        this.lastEquity = lastEquity;
    }

    /**
     * Buying power multiplier that represents account margin classification; valid values 1 (standard limited margin account with 1x buying power), 2 (reg T margin account with 2x intraday and overnight buying power; this is the default for all non-PDT accounts with $2,000 or more equity), 4 (PDT account with 4x intraday buying power and 2x reg T overnight buying power)
     * <p>
     * 
     * 
     */
    public String getMultiplier() {
        return multiplier;
    }

    /**
     * Buying power multiplier that represents account margin classification; valid values 1 (standard limited margin account with 1x buying power), 2 (reg T margin account with 2x intraday and overnight buying power; this is the default for all non-PDT accounts with $2,000 or more equity), 4 (PDT account with 4x intraday buying power and 2x reg T overnight buying power)
     * <p>
     * 
     * 
     */
    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Current available $ buying power; If multiplier = 4, this is your daytrade buying power which is calculated as (last_equity - (last) maintenance_margin) * 4; If multiplier = 2,  buying_power = max(equity – initial_margin,0) * 2; If multiplier = 1, buying_power = cash
     * <p>
     * 
     * 
     */
    public String getBuyingPower() {
        return buyingPower;
    }

    /**
     * Current available $ buying power; If multiplier = 4, this is your daytrade buying power which is calculated as (last_equity - (last) maintenance_margin) * 4; If multiplier = 2,  buying_power = max(equity – initial_margin,0) * 2; If multiplier = 1, buying_power = cash
     * <p>
     * 
     * 
     */
    public void setBuyingPower(String buyingPower) {
        this.buyingPower = buyingPower;
    }

    /**
     * Reg T initial margin requirement (continuously updated value)
     * <p>
     * 
     * 
     */
    public String getInitialMargin() {
        return initialMargin;
    }

    /**
     * Reg T initial margin requirement (continuously updated value)
     * <p>
     * 
     * 
     */
    public void setInitialMargin(String initialMargin) {
        this.initialMargin = initialMargin;
    }

    /**
     * Maintenance margin requirement (continuously updated value)
     * <p>
     * 
     * 
     */
    public String getMaintenanceMargin() {
        return maintenanceMargin;
    }

    /**
     * Maintenance margin requirement (continuously updated value)
     * <p>
     * 
     * 
     */
    public void setMaintenanceMargin(String maintenanceMargin) {
        this.maintenanceMargin = maintenanceMargin;
    }

    /**
     * Value of special memorandum account (will be used at a later date to provide additional buying_power)
     * <p>
     * 
     * 
     */
    public String getSma() {
        return sma;
    }

    /**
     * Value of special memorandum account (will be used at a later date to provide additional buying_power)
     * <p>
     * 
     * 
     */
    public void setSma(String sma) {
        this.sma = sma;
    }

    /**
     * The current number of daytrades that have been made in the last 5 trading days (inclusive of today)
     * <p>
     * 
     * 
     */
    public Integer getDaytradeCount() {
        return daytradeCount;
    }

    /**
     * The current number of daytrades that have been made in the last 5 trading days (inclusive of today)
     * <p>
     * 
     * 
     */
    public void setDaytradeCount(Integer daytradeCount) {
        this.daytradeCount = daytradeCount;
    }

    /**
     * Your maintenance margin requirement on the previous trading day
     * <p>
     * 
     * 
     */
    public String getLastMaintenanceMargin() {
        return lastMaintenanceMargin;
    }

    /**
     * Your maintenance margin requirement on the previous trading day
     * <p>
     * 
     * 
     */
    public void setLastMaintenanceMargin(String lastMaintenanceMargin) {
        this.lastMaintenanceMargin = lastMaintenanceMargin;
    }

    /**
     * Your buying power for day trades (continuously updated value)
     * <p>
     * 
     * 
     */
    public String getDaytradingBuyingPower() {
        return daytradingBuyingPower;
    }

    /**
     * Your buying power for day trades (continuously updated value)
     * <p>
     * 
     * 
     */
    public void setDaytradingBuyingPower(String daytradingBuyingPower) {
        this.daytradingBuyingPower = daytradingBuyingPower;
    }

    /**
     * Your buying power under Regulation T (your excess equity - equity minus margin value - times your margin multiplier)
     * <p>
     * 
     * 
     */
    public String getRegtBuyingPower() {
        return regtBuyingPower;
    }

    /**
     * Your buying power under Regulation T (your excess equity - equity minus margin value - times your margin multiplier)
     * <p>
     * 
     * 
     */
    public void setRegtBuyingPower(String regtBuyingPower) {
        this.regtBuyingPower = regtBuyingPower;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Account.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("accountNumber");
        sb.append('=');
        sb.append(((this.accountNumber == null)?"<null>":this.accountNumber));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("cash");
        sb.append('=');
        sb.append(((this.cash == null)?"<null>":this.cash));
        sb.append(',');
        sb.append("portfolioValue");
        sb.append('=');
        sb.append(((this.portfolioValue == null)?"<null>":this.portfolioValue));
        sb.append(',');
        sb.append("patternDayTrader");
        sb.append('=');
        sb.append(((this.patternDayTrader == null)?"<null>":this.patternDayTrader));
        sb.append(',');
        sb.append("tradeSuspendedByUser");
        sb.append('=');
        sb.append(((this.tradeSuspendedByUser == null)?"<null>":this.tradeSuspendedByUser));
        sb.append(',');
        sb.append("tradingBlocked");
        sb.append('=');
        sb.append(((this.tradingBlocked == null)?"<null>":this.tradingBlocked));
        sb.append(',');
        sb.append("transfersBlocked");
        sb.append('=');
        sb.append(((this.transfersBlocked == null)?"<null>":this.transfersBlocked));
        sb.append(',');
        sb.append("accountBlocked");
        sb.append('=');
        sb.append(((this.accountBlocked == null)?"<null>":this.accountBlocked));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("shortingEnabled");
        sb.append('=');
        sb.append(((this.shortingEnabled == null)?"<null>":this.shortingEnabled));
        sb.append(',');
        sb.append("longMarketValue");
        sb.append('=');
        sb.append(((this.longMarketValue == null)?"<null>":this.longMarketValue));
        sb.append(',');
        sb.append("shortMarketValue");
        sb.append('=');
        sb.append(((this.shortMarketValue == null)?"<null>":this.shortMarketValue));
        sb.append(',');
        sb.append("equity");
        sb.append('=');
        sb.append(((this.equity == null)?"<null>":this.equity));
        sb.append(',');
        sb.append("lastEquity");
        sb.append('=');
        sb.append(((this.lastEquity == null)?"<null>":this.lastEquity));
        sb.append(',');
        sb.append("multiplier");
        sb.append('=');
        sb.append(((this.multiplier == null)?"<null>":this.multiplier));
        sb.append(',');
        sb.append("buyingPower");
        sb.append('=');
        sb.append(((this.buyingPower == null)?"<null>":this.buyingPower));
        sb.append(',');
        sb.append("initialMargin");
        sb.append('=');
        sb.append(((this.initialMargin == null)?"<null>":this.initialMargin));
        sb.append(',');
        sb.append("maintenanceMargin");
        sb.append('=');
        sb.append(((this.maintenanceMargin == null)?"<null>":this.maintenanceMargin));
        sb.append(',');
        sb.append("sma");
        sb.append('=');
        sb.append(((this.sma == null)?"<null>":this.sma));
        sb.append(',');
        sb.append("daytradeCount");
        sb.append('=');
        sb.append(((this.daytradeCount == null)?"<null>":this.daytradeCount));
        sb.append(',');
        sb.append("lastMaintenanceMargin");
        sb.append('=');
        sb.append(((this.lastMaintenanceMargin == null)?"<null>":this.lastMaintenanceMargin));
        sb.append(',');
        sb.append("daytradingBuyingPower");
        sb.append('=');
        sb.append(((this.daytradingBuyingPower == null)?"<null>":this.daytradingBuyingPower));
        sb.append(',');
        sb.append("regtBuyingPower");
        sb.append('=');
        sb.append(((this.regtBuyingPower == null)?"<null>":this.regtBuyingPower));
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
        result = ((result* 31)+((this.tradingBlocked == null)? 0 :this.tradingBlocked.hashCode()));
        result = ((result* 31)+((this.sma == null)? 0 :this.sma.hashCode()));
        result = ((result* 31)+((this.tradeSuspendedByUser == null)? 0 :this.tradeSuspendedByUser.hashCode()));
        result = ((result* 31)+((this.portfolioValue == null)? 0 :this.portfolioValue.hashCode()));
        result = ((result* 31)+((this.equity == null)? 0 :this.equity.hashCode()));
        result = ((result* 31)+((this.initialMargin == null)? 0 :this.initialMargin.hashCode()));
        result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
        result = ((result* 31)+((this.buyingPower == null)? 0 :this.buyingPower.hashCode()));
        result = ((result* 31)+((this.maintenanceMargin == null)? 0 :this.maintenanceMargin.hashCode()));
        result = ((result* 31)+((this.currency == null)? 0 :this.currency.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.cash == null)? 0 :this.cash.hashCode()));
        result = ((result* 31)+((this.lastMaintenanceMargin == null)? 0 :this.lastMaintenanceMargin.hashCode()));
        result = ((result* 31)+((this.daytradingBuyingPower == null)? 0 :this.daytradingBuyingPower.hashCode()));
        result = ((result* 31)+((this.longMarketValue == null)? 0 :this.longMarketValue.hashCode()));
        result = ((result* 31)+((this.shortingEnabled == null)? 0 :this.shortingEnabled.hashCode()));
        result = ((result* 31)+((this.multiplier == null)? 0 :this.multiplier.hashCode()));
        result = ((result* 31)+((this.lastEquity == null)? 0 :this.lastEquity.hashCode()));
        result = ((result* 31)+((this.shortMarketValue == null)? 0 :this.shortMarketValue.hashCode()));
        result = ((result* 31)+((this.accountNumber == null)? 0 :this.accountNumber.hashCode()));
        result = ((result* 31)+((this.patternDayTrader == null)? 0 :this.patternDayTrader.hashCode()));
        result = ((result* 31)+((this.daytradeCount == null)? 0 :this.daytradeCount.hashCode()));
        result = ((result* 31)+((this.transfersBlocked == null)? 0 :this.transfersBlocked.hashCode()));
        result = ((result* 31)+((this.regtBuyingPower == null)? 0 :this.regtBuyingPower.hashCode()));
        result = ((result* 31)+((this.accountBlocked == null)? 0 :this.accountBlocked.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Account) == false) {
            return false;
        }
        Account rhs = ((Account) other);
        return (((((((((((((((((((((((((((this.tradingBlocked == rhs.tradingBlocked)||((this.tradingBlocked!= null)&&this.tradingBlocked.equals(rhs.tradingBlocked)))&&((this.sma == rhs.sma)||((this.sma!= null)&&this.sma.equals(rhs.sma))))&&((this.tradeSuspendedByUser == rhs.tradeSuspendedByUser)||((this.tradeSuspendedByUser!= null)&&this.tradeSuspendedByUser.equals(rhs.tradeSuspendedByUser))))&&((this.portfolioValue == rhs.portfolioValue)||((this.portfolioValue!= null)&&this.portfolioValue.equals(rhs.portfolioValue))))&&((this.equity == rhs.equity)||((this.equity!= null)&&this.equity.equals(rhs.equity))))&&((this.initialMargin == rhs.initialMargin)||((this.initialMargin!= null)&&this.initialMargin.equals(rhs.initialMargin))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.buyingPower == rhs.buyingPower)||((this.buyingPower!= null)&&this.buyingPower.equals(rhs.buyingPower))))&&((this.maintenanceMargin == rhs.maintenanceMargin)||((this.maintenanceMargin!= null)&&this.maintenanceMargin.equals(rhs.maintenanceMargin))))&&((this.currency == rhs.currency)||((this.currency!= null)&&this.currency.equals(rhs.currency))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.cash == rhs.cash)||((this.cash!= null)&&this.cash.equals(rhs.cash))))&&((this.lastMaintenanceMargin == rhs.lastMaintenanceMargin)||((this.lastMaintenanceMargin!= null)&&this.lastMaintenanceMargin.equals(rhs.lastMaintenanceMargin))))&&((this.daytradingBuyingPower == rhs.daytradingBuyingPower)||((this.daytradingBuyingPower!= null)&&this.daytradingBuyingPower.equals(rhs.daytradingBuyingPower))))&&((this.longMarketValue == rhs.longMarketValue)||((this.longMarketValue!= null)&&this.longMarketValue.equals(rhs.longMarketValue))))&&((this.shortingEnabled == rhs.shortingEnabled)||((this.shortingEnabled!= null)&&this.shortingEnabled.equals(rhs.shortingEnabled))))&&((this.multiplier == rhs.multiplier)||((this.multiplier!= null)&&this.multiplier.equals(rhs.multiplier))))&&((this.lastEquity == rhs.lastEquity)||((this.lastEquity!= null)&&this.lastEquity.equals(rhs.lastEquity))))&&((this.shortMarketValue == rhs.shortMarketValue)||((this.shortMarketValue!= null)&&this.shortMarketValue.equals(rhs.shortMarketValue))))&&((this.accountNumber == rhs.accountNumber)||((this.accountNumber!= null)&&this.accountNumber.equals(rhs.accountNumber))))&&((this.patternDayTrader == rhs.patternDayTrader)||((this.patternDayTrader!= null)&&this.patternDayTrader.equals(rhs.patternDayTrader))))&&((this.daytradeCount == rhs.daytradeCount)||((this.daytradeCount!= null)&&this.daytradeCount.equals(rhs.daytradeCount))))&&((this.transfersBlocked == rhs.transfersBlocked)||((this.transfersBlocked!= null)&&this.transfersBlocked.equals(rhs.transfersBlocked))))&&((this.regtBuyingPower == rhs.regtBuyingPower)||((this.regtBuyingPower!= null)&&this.regtBuyingPower.equals(rhs.regtBuyingPower))))&&((this.accountBlocked == rhs.accountBlocked)||((this.accountBlocked!= null)&&this.accountBlocked.equals(rhs.accountBlocked))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
