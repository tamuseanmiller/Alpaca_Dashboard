
package net.jacobpeterson.domain.polygon.stockfinancials;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_financials_symbol">https://polygon.io/docs/#!/Reference/get_v2_reference_financials_symbol</a>
 * <p>
 * 
 * 
 */
public class StockFinancials implements Serializable
{

    /**
     * ticker
     * <p>
     * 
     * 
     */
    @SerializedName("ticker")
    @Expose
    private String ticker;
    /**
     * Reporting period. Can be Q, T, QA, TA, Y or YA
     * <p>
     * 
     * 
     */
    @SerializedName("period")
    @Expose
    private String period;
    /**
     * calendarDate
     * <p>
     * 
     * 
     */
    @SerializedName("calendarDate")
    @Expose
    private String calendarDate;
    /**
     * reportPeriod
     * <p>
     * 
     * 
     */
    @SerializedName("reportPeriod")
    @Expose
    private String reportPeriod;
    /**
     * updated
     * <p>
     * 
     * 
     */
    @SerializedName("updated")
    @Expose
    private String updated;
    /**
     * accumulatedOtherComprehensiveIncome
     * <p>
     * 
     * 
     */
    @SerializedName("accumulatedOtherComprehensiveIncome")
    @Expose
    private Double accumulatedOtherComprehensiveIncome;
    /**
     * assets
     * <p>
     * 
     * 
     */
    @SerializedName("assets")
    @Expose
    private Double assets;
    /**
     * assetsAverage
     * <p>
     * 
     * 
     */
    @SerializedName("assetsAverage")
    @Expose
    private Double assetsAverage;
    /**
     * assetsCurrent
     * <p>
     * 
     * 
     */
    @SerializedName("assetsCurrent")
    @Expose
    private Double assetsCurrent;
    /**
     * assetTurnover
     * <p>
     * 
     * 
     */
    @SerializedName("assetTurnover")
    @Expose
    private Double assetTurnover;
    /**
     * assetsNonCurrent
     * <p>
     * 
     * 
     */
    @SerializedName("assetsNonCurrent")
    @Expose
    private Double assetsNonCurrent;
    /**
     * bookValuePerShare
     * <p>
     * 
     * 
     */
    @SerializedName("bookValuePerShare")
    @Expose
    private Double bookValuePerShare;
    /**
     * capitalExpenditure
     * <p>
     * 
     * 
     */
    @SerializedName("capitalExpenditure")
    @Expose
    private Double capitalExpenditure;
    /**
     * cashAndEquivalents
     * <p>
     * 
     * 
     */
    @SerializedName("cashAndEquivalents")
    @Expose
    private Double cashAndEquivalents;
    /**
     * cashAndEquivalentsUSD
     * <p>
     * 
     * 
     */
    @SerializedName("cashAndEquivalentsUSD")
    @Expose
    private Double cashAndEquivalentsUSD;
    /**
     * costOfRevenue
     * <p>
     * 
     * 
     */
    @SerializedName("costOfRevenue")
    @Expose
    private Double costOfRevenue;
    /**
     * consolidatedIncome
     * <p>
     * 
     * 
     */
    @SerializedName("consolidatedIncome")
    @Expose
    private Double consolidatedIncome;
    /**
     * currentRatio
     * <p>
     * 
     * 
     */
    @SerializedName("currentRatio")
    @Expose
    private Double currentRatio;
    /**
     * debtToEquityRatio
     * <p>
     * 
     * 
     */
    @SerializedName("debtToEquityRatio")
    @Expose
    private Double debtToEquityRatio;
    /**
     * debt
     * <p>
     * 
     * 
     */
    @SerializedName("debt")
    @Expose
    private Double debt;
    /**
     * debtCurrent
     * <p>
     * 
     * 
     */
    @SerializedName("debtCurrent")
    @Expose
    private Double debtCurrent;
    /**
     * debtNonCurrent
     * <p>
     * 
     * 
     */
    @SerializedName("debtNonCurrent")
    @Expose
    private Double debtNonCurrent;
    /**
     * debtUSD
     * <p>
     * 
     * 
     */
    @SerializedName("debtUSD")
    @Expose
    private Double debtUSD;
    /**
     * deferredRevenue
     * <p>
     * 
     * 
     */
    @SerializedName("deferredRevenue")
    @Expose
    private Double deferredRevenue;
    /**
     * depreciationAmortizationAndAccretion
     * <p>
     * 
     * 
     */
    @SerializedName("depreciationAmortizationAndAccretion")
    @Expose
    private Double depreciationAmortizationAndAccretion;
    /**
     * deposits
     * <p>
     * 
     * 
     */
    @SerializedName("deposits")
    @Expose
    private Double deposits;
    /**
     * dividendYield
     * <p>
     * 
     * 
     */
    @SerializedName("dividendYield")
    @Expose
    private Double dividendYield;
    /**
     * dividendsPerBasicCommonShare
     * <p>
     * 
     * 
     */
    @SerializedName("dividendsPerBasicCommonShare")
    @Expose
    private Double dividendsPerBasicCommonShare;
    /**
     * earningBeforeInterestTaxes
     * <p>
     * 
     * 
     */
    @SerializedName("earningBeforeInterestTaxes")
    @Expose
    private Double earningBeforeInterestTaxes;
    /**
     * earningsBeforeInterestTaxesDepreciationAmortization
     * <p>
     * 
     * 
     */
    @SerializedName("earningsBeforeInterestTaxesDepreciationAmortization")
    @Expose
    private Double earningsBeforeInterestTaxesDepreciationAmortization;
    /**
     * EBITDAMargin
     * <p>
     * 
     * 
     */
    @SerializedName("EBITDAMargin")
    @Expose
    private Double eBITDAMargin;
    /**
     * earningsBeforeInterestTaxesDepreciationAmortizationUSD
     * <p>
     * 
     * 
     */
    @SerializedName("earningsBeforeInterestTaxesDepreciationAmortizationUSD")
    @Expose
    private Double earningsBeforeInterestTaxesDepreciationAmortizationUSD;
    /**
     * earningBeforeInterestTaxesUSD
     * <p>
     * 
     * 
     */
    @SerializedName("earningBeforeInterestTaxesUSD")
    @Expose
    private Double earningBeforeInterestTaxesUSD;
    /**
     * earningsBeforeTax
     * <p>
     * 
     * 
     */
    @SerializedName("earningsBeforeTax")
    @Expose
    private Double earningsBeforeTax;
    /**
     * earningsPerBasicShare
     * <p>
     * 
     * 
     */
    @SerializedName("earningsPerBasicShare")
    @Expose
    private Double earningsPerBasicShare;
    /**
     * earningsPerDilutedShare
     * <p>
     * 
     * 
     */
    @SerializedName("earningsPerDilutedShare")
    @Expose
    private Double earningsPerDilutedShare;
    /**
     * earningsPerBasicShareUSD
     * <p>
     * 
     * 
     */
    @SerializedName("earningsPerBasicShareUSD")
    @Expose
    private Double earningsPerBasicShareUSD;
    /**
     * shareholdersEquity
     * <p>
     * 
     * 
     */
    @SerializedName("shareholdersEquity")
    @Expose
    private Double shareholdersEquity;
    /**
     * averageEquity
     * <p>
     * 
     * 
     */
    @SerializedName("averageEquity")
    @Expose
    private Double averageEquity;
    /**
     * shareholdersEquityUSD
     * <p>
     * 
     * 
     */
    @SerializedName("shareholdersEquityUSD")
    @Expose
    private Double shareholdersEquityUSD;
    /**
     * enterpriseValue
     * <p>
     * 
     * 
     */
    @SerializedName("enterpriseValue")
    @Expose
    private Double enterpriseValue;
    /**
     * enterpriseValueOverEBIT
     * <p>
     * 
     * 
     */
    @SerializedName("enterpriseValueOverEBIT")
    @Expose
    private Double enterpriseValueOverEBIT;
    /**
     * enterpriseValueOverEBITDA
     * <p>
     * 
     * 
     */
    @SerializedName("enterpriseValueOverEBITDA")
    @Expose
    private Double enterpriseValueOverEBITDA;
    /**
     * freeCashFlow
     * <p>
     * 
     * 
     */
    @SerializedName("freeCashFlow")
    @Expose
    private Double freeCashFlow;
    /**
     * freeCashFlowPerShare
     * <p>
     * 
     * 
     */
    @SerializedName("freeCashFlowPerShare")
    @Expose
    private Double freeCashFlowPerShare;
    /**
     * foreignCurrencyUSDExchangeRate
     * <p>
     * 
     * 
     */
    @SerializedName("foreignCurrencyUSDExchangeRate")
    @Expose
    private Double foreignCurrencyUSDExchangeRate;
    /**
     * grossProfit
     * <p>
     * 
     * 
     */
    @SerializedName("grossProfit")
    @Expose
    private Double grossProfit;
    /**
     * grossMargin
     * <p>
     * 
     * 
     */
    @SerializedName("grossMargin")
    @Expose
    private Double grossMargin;
    /**
     * goodwillAndIntangibleAssets
     * <p>
     * 
     * 
     */
    @SerializedName("goodwillAndIntangibleAssets")
    @Expose
    private Double goodwillAndIntangibleAssets;
    /**
     * interestExpense
     * <p>
     * 
     * 
     */
    @SerializedName("interestExpense")
    @Expose
    private Double interestExpense;
    /**
     * investedCapital
     * <p>
     * 
     * 
     */
    @SerializedName("investedCapital")
    @Expose
    private Double investedCapital;
    /**
     * investedCapitalAverage
     * <p>
     * 
     * 
     */
    @SerializedName("investedCapitalAverage")
    @Expose
    private Double investedCapitalAverage;
    /**
     * inventory
     * <p>
     * 
     * 
     */
    @SerializedName("inventory")
    @Expose
    private Double inventory;
    /**
     * investments
     * <p>
     * 
     * 
     */
    @SerializedName("investments")
    @Expose
    private Double investments;
    /**
     * investmentsCurrent
     * <p>
     * 
     * 
     */
    @SerializedName("investmentsCurrent")
    @Expose
    private Double investmentsCurrent;
    /**
     * investmentsNonCurrent
     * <p>
     * 
     * 
     */
    @SerializedName("investmentsNonCurrent")
    @Expose
    private Double investmentsNonCurrent;
    /**
     * totalLiabilities
     * <p>
     * 
     * 
     */
    @SerializedName("totalLiabilities")
    @Expose
    private Double totalLiabilities;
    /**
     * currentLiabilities
     * <p>
     * 
     * 
     */
    @SerializedName("currentLiabilities")
    @Expose
    private Double currentLiabilities;
    /**
     * liabilitiesNonCurrent
     * <p>
     * 
     * 
     */
    @SerializedName("liabilitiesNonCurrent")
    @Expose
    private Double liabilitiesNonCurrent;
    /**
     * marketCapitalization
     * <p>
     * 
     * 
     */
    @SerializedName("marketCapitalization")
    @Expose
    private Double marketCapitalization;
    /**
     * netCashFlow
     * <p>
     * 
     * 
     */
    @SerializedName("netCashFlow")
    @Expose
    private Double netCashFlow;
    /**
     * netCashFlowBusinessAcquisitionsDisposals
     * <p>
     * 
     * 
     */
    @SerializedName("netCashFlowBusinessAcquisitionsDisposals")
    @Expose
    private Double netCashFlowBusinessAcquisitionsDisposals;
    /**
     * issuanceEquityShares
     * <p>
     * 
     * 
     */
    @SerializedName("issuanceEquityShares")
    @Expose
    private Double issuanceEquityShares;
    /**
     * issuanceDebtSecurities
     * <p>
     * 
     * 
     */
    @SerializedName("issuanceDebtSecurities")
    @Expose
    private Double issuanceDebtSecurities;
    /**
     * paymentDividendsOtherCashDistributions
     * <p>
     * 
     * 
     */
    @SerializedName("paymentDividendsOtherCashDistributions")
    @Expose
    private Double paymentDividendsOtherCashDistributions;
    /**
     * netCashFlowFromFinancing
     * <p>
     * 
     * 
     */
    @SerializedName("netCashFlowFromFinancing")
    @Expose
    private Double netCashFlowFromFinancing;
    /**
     * netCashFlowFromInvesting
     * <p>
     * 
     * 
     */
    @SerializedName("netCashFlowFromInvesting")
    @Expose
    private Double netCashFlowFromInvesting;
    /**
     * netCashFlowInvestmentAcquisitionsDisposals
     * <p>
     * 
     * 
     */
    @SerializedName("netCashFlowInvestmentAcquisitionsDisposals")
    @Expose
    private Double netCashFlowInvestmentAcquisitionsDisposals;
    /**
     * netCashFlowFromOperations
     * <p>
     * 
     * 
     */
    @SerializedName("netCashFlowFromOperations")
    @Expose
    private Double netCashFlowFromOperations;
    /**
     * effectOfExchangeRateChangesOnCash
     * <p>
     * 
     * 
     */
    @SerializedName("effectOfExchangeRateChangesOnCash")
    @Expose
    private Double effectOfExchangeRateChangesOnCash;
    /**
     * netIncome
     * <p>
     * 
     * 
     */
    @SerializedName("netIncome")
    @Expose
    private Double netIncome;
    /**
     * netIncomeCommonStock
     * <p>
     * 
     * 
     */
    @SerializedName("netIncomeCommonStock")
    @Expose
    private Double netIncomeCommonStock;
    /**
     * netIncomeCommonStockUSD
     * <p>
     * 
     * 
     */
    @SerializedName("netIncomeCommonStockUSD")
    @Expose
    private Double netIncomeCommonStockUSD;
    /**
     * netLossIncomeFromDiscontinuedOperations
     * <p>
     * 
     * 
     */
    @SerializedName("netLossIncomeFromDiscontinuedOperations")
    @Expose
    private Double netLossIncomeFromDiscontinuedOperations;
    /**
     * netIncomeToNonControllingInterests
     * <p>
     * 
     * 
     */
    @SerializedName("netIncomeToNonControllingInterests")
    @Expose
    private Double netIncomeToNonControllingInterests;
    /**
     * profitMargin
     * <p>
     * 
     * 
     */
    @SerializedName("profitMargin")
    @Expose
    private Double profitMargin;
    /**
     * operatingExpenses
     * <p>
     * 
     * 
     */
    @SerializedName("operatingExpenses")
    @Expose
    private Double operatingExpenses;
    /**
     * operatingIncome
     * <p>
     * 
     * 
     */
    @SerializedName("operatingIncome")
    @Expose
    private Double operatingIncome;
    /**
     * tradeAndNonTradePayables
     * <p>
     * 
     * 
     */
    @SerializedName("tradeAndNonTradePayables")
    @Expose
    private Double tradeAndNonTradePayables;
    /**
     * payoutRatio
     * <p>
     * 
     * 
     */
    @SerializedName("payoutRatio")
    @Expose
    private Double payoutRatio;
    /**
     * priceToBookValue
     * <p>
     * 
     * 
     */
    @SerializedName("priceToBookValue")
    @Expose
    private Double priceToBookValue;
    /**
     * priceEarnings
     * <p>
     * 
     * 
     */
    @SerializedName("priceEarnings")
    @Expose
    private Double priceEarnings;
    /**
     * priceToEarningsRatio
     * <p>
     * 
     * 
     */
    @SerializedName("priceToEarningsRatio")
    @Expose
    private Double priceToEarningsRatio;
    /**
     * propertyPlantEquipmentNet
     * <p>
     * 
     * 
     */
    @SerializedName("propertyPlantEquipmentNet")
    @Expose
    private Double propertyPlantEquipmentNet;
    /**
     * preferredDividendsIncomeStatementImpact
     * <p>
     * 
     * 
     */
    @SerializedName("preferredDividendsIncomeStatementImpact")
    @Expose
    private Double preferredDividendsIncomeStatementImpact;
    /**
     * sharePriceAdjustedClose
     * <p>
     * 
     * 
     */
    @SerializedName("sharePriceAdjustedClose")
    @Expose
    private Double sharePriceAdjustedClose;
    /**
     * priceSales
     * <p>
     * 
     * 
     */
    @SerializedName("priceSales")
    @Expose
    private Double priceSales;
    /**
     * priceToSalesRatio
     * <p>
     * 
     * 
     */
    @SerializedName("priceToSalesRatio")
    @Expose
    private Double priceToSalesRatio;
    /**
     * tradeAndNonTradeReceivables
     * <p>
     * 
     * 
     */
    @SerializedName("tradeAndNonTradeReceivables")
    @Expose
    private Double tradeAndNonTradeReceivables;
    /**
     * accumulatedRetainedEarningsDeficit
     * <p>
     * 
     * 
     */
    @SerializedName("accumulatedRetainedEarningsDeficit")
    @Expose
    private Double accumulatedRetainedEarningsDeficit;
    /**
     * revenues
     * <p>
     * 
     * 
     */
    @SerializedName("revenues")
    @Expose
    private Double revenues;
    /**
     * revenuesUSD
     * <p>
     * 
     * 
     */
    @SerializedName("revenuesUSD")
    @Expose
    private Double revenuesUSD;
    /**
     * researchAndDevelopmentExpense
     * <p>
     * 
     * 
     */
    @SerializedName("researchAndDevelopmentExpense")
    @Expose
    private Double researchAndDevelopmentExpense;
    /**
     * returnOnAverageAssets
     * <p>
     * 
     * 
     */
    @SerializedName("returnOnAverageAssets")
    @Expose
    private Double returnOnAverageAssets;
    /**
     * returnOnAverageEquity
     * <p>
     * 
     * 
     */
    @SerializedName("returnOnAverageEquity")
    @Expose
    private Double returnOnAverageEquity;
    /**
     * returnOnInvestedCapital
     * <p>
     * 
     * 
     */
    @SerializedName("returnOnInvestedCapital")
    @Expose
    private Double returnOnInvestedCapital;
    /**
     * returnOnSales
     * <p>
     * 
     * 
     */
    @SerializedName("returnOnSales")
    @Expose
    private Double returnOnSales;
    /**
     * shareBasedCompensation
     * <p>
     * 
     * 
     */
    @SerializedName("shareBasedCompensation")
    @Expose
    private Double shareBasedCompensation;
    /**
     * sellingGeneralAndAdministrativeExpense
     * <p>
     * 
     * 
     */
    @SerializedName("sellingGeneralAndAdministrativeExpense")
    @Expose
    private Double sellingGeneralAndAdministrativeExpense;
    /**
     * shareFactor
     * <p>
     * 
     * 
     */
    @SerializedName("shareFactor")
    @Expose
    private Double shareFactor;
    /**
     * shares
     * <p>
     * 
     * 
     */
    @SerializedName("shares")
    @Expose
    private Double shares;
    /**
     * weightedAverageShares
     * <p>
     * 
     * 
     */
    @SerializedName("weightedAverageShares")
    @Expose
    private Double weightedAverageShares;
    /**
     * weightedAverageSharesDiluted
     * <p>
     * 
     * 
     */
    @SerializedName("weightedAverageSharesDiluted")
    @Expose
    private Double weightedAverageSharesDiluted;
    /**
     * salesPerShare
     * <p>
     * 
     * 
     */
    @SerializedName("salesPerShare")
    @Expose
    private Double salesPerShare;
    /**
     * tangibleAssetValue
     * <p>
     * 
     * 
     */
    @SerializedName("tangibleAssetValue")
    @Expose
    private Double tangibleAssetValue;
    /**
     * taxAssets
     * <p>
     * 
     * 
     */
    @SerializedName("taxAssets")
    @Expose
    private Double taxAssets;
    /**
     * incomeTaxExpense
     * <p>
     * 
     * 
     */
    @SerializedName("incomeTaxExpense")
    @Expose
    private Double incomeTaxExpense;
    /**
     * taxLiabilities
     * <p>
     * 
     * 
     */
    @SerializedName("taxLiabilities")
    @Expose
    private Double taxLiabilities;
    /**
     * tangibleAssetsBookValuePerShare
     * <p>
     * 
     * 
     */
    @SerializedName("tangibleAssetsBookValuePerShare")
    @Expose
    private Double tangibleAssetsBookValuePerShare;
    /**
     * workingCapital
     * <p>
     * 
     * 
     */
    @SerializedName("workingCapital")
    @Expose
    private Double workingCapital;
    private final static long serialVersionUID = 1417073496666568616L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StockFinancials() {
    }

    /**
     * 
     * @param earningsPerBasicShareUSD
     * @param returnOnAverageAssets
     * @param dividendYield
     * @param reportPeriod
     * @param bookValuePerShare
     * @param debtUSD
     * @param cashAndEquivalentsUSD
     * @param tradeAndNonTradePayables
     * @param earningBeforeInterestTaxesUSD
     * @param netIncomeCommonStockUSD
     * @param accumulatedRetainedEarningsDeficit
     * @param freeCashFlowPerShare
     * @param investedCapitalAverage
     * @param period
     * @param enterpriseValueOverEBIT
     * @param operatingExpenses
     * @param workingCapital
     * @param tradeAndNonTradeReceivables
     * @param salesPerShare
     * @param tangibleAssetsBookValuePerShare
     * @param netCashFlow
     * @param netCashFlowFromFinancing
     * @param shareBasedCompensation
     * @param shareholdersEquityUSD
     * @param shareholdersEquity
     * @param enterpriseValue
     * @param returnOnSales
     * @param averageEquity
     * @param priceEarnings
     * @param interestExpense
     * @param investments
     * @param issuanceDebtSecurities
     * @param tangibleAssetValue
     * @param effectOfExchangeRateChangesOnCash
     * @param shares
     * @param freeCashFlow
     * @param sellingGeneralAndAdministrativeExpense
     * @param assetsNonCurrent
     * @param profitMargin
     * @param depreciationAmortizationAndAccretion
     * @param eBITDAMargin
     * @param priceSales
     * @param earningsBeforeInterestTaxesDepreciationAmortizationUSD
     * @param grossProfit
     * @param netIncomeToNonControllingInterests
     * @param investmentsNonCurrent
     * @param paymentDividendsOtherCashDistributions
     * @param operatingIncome
     * @param preferredDividendsIncomeStatementImpact
     * @param debtToEquityRatio
     * @param issuanceEquityShares
     * @param accumulatedOtherComprehensiveIncome
     * @param earningsPerDilutedShare
     * @param netCashFlowFromOperations
     * @param liabilitiesNonCurrent
     * @param earningsBeforeTax
     * @param priceToBookValue
     * @param returnOnInvestedCapital
     * @param enterpriseValueOverEBITDA
     * @param netIncomeCommonStock
     * @param taxLiabilities
     * @param debtCurrent
     * @param netCashFlowInvestmentAcquisitionsDisposals
     * @param priceToSalesRatio
     * @param propertyPlantEquipmentNet
     * @param assets
     * @param investedCapital
     * @param returnOnAverageEquity
     * @param shareFactor
     * @param revenuesUSD
     * @param earningsPerBasicShare
     * @param marketCapitalization
     * @param foreignCurrencyUSDExchangeRate
     * @param totalLiabilities
     * @param costOfRevenue
     * @param earningsBeforeInterestTaxesDepreciationAmortization
     * @param deposits
     * @param netLossIncomeFromDiscontinuedOperations
     * @param taxAssets
     * @param payoutRatio
     * @param researchAndDevelopmentExpense
     * @param updated
     * @param debt
     * @param assetsCurrent
     * @param earningBeforeInterestTaxes
     * @param investmentsCurrent
     * @param weightedAverageShares
     * @param goodwillAndIntangibleAssets
     * @param inventory
     * @param netCashFlowBusinessAcquisitionsDisposals
     * @param priceToEarningsRatio
     * @param currentLiabilities
     * @param weightedAverageSharesDiluted
     * @param consolidatedIncome
     * @param currentRatio
     * @param capitalExpenditure
     * @param ticker
     * @param dividendsPerBasicCommonShare
     * @param revenues
     * @param cashAndEquivalents
     * @param assetTurnover
     * @param sharePriceAdjustedClose
     * @param deferredRevenue
     * @param debtNonCurrent
     * @param netCashFlowFromInvesting
     * @param netIncome
     * @param incomeTaxExpense
     * @param grossMargin
     * @param assetsAverage
     * @param calendarDate
     */
    public StockFinancials(String ticker, String period, String calendarDate, String reportPeriod, String updated, Double accumulatedOtherComprehensiveIncome, Double assets, Double assetsAverage, Double assetsCurrent, Double assetTurnover, Double assetsNonCurrent, Double bookValuePerShare, Double capitalExpenditure, Double cashAndEquivalents, Double cashAndEquivalentsUSD, Double costOfRevenue, Double consolidatedIncome, Double currentRatio, Double debtToEquityRatio, Double debt, Double debtCurrent, Double debtNonCurrent, Double debtUSD, Double deferredRevenue, Double depreciationAmortizationAndAccretion, Double deposits, Double dividendYield, Double dividendsPerBasicCommonShare, Double earningBeforeInterestTaxes, Double earningsBeforeInterestTaxesDepreciationAmortization, Double eBITDAMargin, Double earningsBeforeInterestTaxesDepreciationAmortizationUSD, Double earningBeforeInterestTaxesUSD, Double earningsBeforeTax, Double earningsPerBasicShare, Double earningsPerDilutedShare, Double earningsPerBasicShareUSD, Double shareholdersEquity, Double averageEquity, Double shareholdersEquityUSD, Double enterpriseValue, Double enterpriseValueOverEBIT, Double enterpriseValueOverEBITDA, Double freeCashFlow, Double freeCashFlowPerShare, Double foreignCurrencyUSDExchangeRate, Double grossProfit, Double grossMargin, Double goodwillAndIntangibleAssets, Double interestExpense, Double investedCapital, Double investedCapitalAverage, Double inventory, Double investments, Double investmentsCurrent, Double investmentsNonCurrent, Double totalLiabilities, Double currentLiabilities, Double liabilitiesNonCurrent, Double marketCapitalization, Double netCashFlow, Double netCashFlowBusinessAcquisitionsDisposals, Double issuanceEquityShares, Double issuanceDebtSecurities, Double paymentDividendsOtherCashDistributions, Double netCashFlowFromFinancing, Double netCashFlowFromInvesting, Double netCashFlowInvestmentAcquisitionsDisposals, Double netCashFlowFromOperations, Double effectOfExchangeRateChangesOnCash, Double netIncome, Double netIncomeCommonStock, Double netIncomeCommonStockUSD, Double netLossIncomeFromDiscontinuedOperations, Double netIncomeToNonControllingInterests, Double profitMargin, Double operatingExpenses, Double operatingIncome, Double tradeAndNonTradePayables, Double payoutRatio, Double priceToBookValue, Double priceEarnings, Double priceToEarningsRatio, Double propertyPlantEquipmentNet, Double preferredDividendsIncomeStatementImpact, Double sharePriceAdjustedClose, Double priceSales, Double priceToSalesRatio, Double tradeAndNonTradeReceivables, Double accumulatedRetainedEarningsDeficit, Double revenues, Double revenuesUSD, Double researchAndDevelopmentExpense, Double returnOnAverageAssets, Double returnOnAverageEquity, Double returnOnInvestedCapital, Double returnOnSales, Double shareBasedCompensation, Double sellingGeneralAndAdministrativeExpense, Double shareFactor, Double shares, Double weightedAverageShares, Double weightedAverageSharesDiluted, Double salesPerShare, Double tangibleAssetValue, Double taxAssets, Double incomeTaxExpense, Double taxLiabilities, Double tangibleAssetsBookValuePerShare, Double workingCapital) {
        super();
        this.ticker = ticker;
        this.period = period;
        this.calendarDate = calendarDate;
        this.reportPeriod = reportPeriod;
        this.updated = updated;
        this.accumulatedOtherComprehensiveIncome = accumulatedOtherComprehensiveIncome;
        this.assets = assets;
        this.assetsAverage = assetsAverage;
        this.assetsCurrent = assetsCurrent;
        this.assetTurnover = assetTurnover;
        this.assetsNonCurrent = assetsNonCurrent;
        this.bookValuePerShare = bookValuePerShare;
        this.capitalExpenditure = capitalExpenditure;
        this.cashAndEquivalents = cashAndEquivalents;
        this.cashAndEquivalentsUSD = cashAndEquivalentsUSD;
        this.costOfRevenue = costOfRevenue;
        this.consolidatedIncome = consolidatedIncome;
        this.currentRatio = currentRatio;
        this.debtToEquityRatio = debtToEquityRatio;
        this.debt = debt;
        this.debtCurrent = debtCurrent;
        this.debtNonCurrent = debtNonCurrent;
        this.debtUSD = debtUSD;
        this.deferredRevenue = deferredRevenue;
        this.depreciationAmortizationAndAccretion = depreciationAmortizationAndAccretion;
        this.deposits = deposits;
        this.dividendYield = dividendYield;
        this.dividendsPerBasicCommonShare = dividendsPerBasicCommonShare;
        this.earningBeforeInterestTaxes = earningBeforeInterestTaxes;
        this.earningsBeforeInterestTaxesDepreciationAmortization = earningsBeforeInterestTaxesDepreciationAmortization;
        this.eBITDAMargin = eBITDAMargin;
        this.earningsBeforeInterestTaxesDepreciationAmortizationUSD = earningsBeforeInterestTaxesDepreciationAmortizationUSD;
        this.earningBeforeInterestTaxesUSD = earningBeforeInterestTaxesUSD;
        this.earningsBeforeTax = earningsBeforeTax;
        this.earningsPerBasicShare = earningsPerBasicShare;
        this.earningsPerDilutedShare = earningsPerDilutedShare;
        this.earningsPerBasicShareUSD = earningsPerBasicShareUSD;
        this.shareholdersEquity = shareholdersEquity;
        this.averageEquity = averageEquity;
        this.shareholdersEquityUSD = shareholdersEquityUSD;
        this.enterpriseValue = enterpriseValue;
        this.enterpriseValueOverEBIT = enterpriseValueOverEBIT;
        this.enterpriseValueOverEBITDA = enterpriseValueOverEBITDA;
        this.freeCashFlow = freeCashFlow;
        this.freeCashFlowPerShare = freeCashFlowPerShare;
        this.foreignCurrencyUSDExchangeRate = foreignCurrencyUSDExchangeRate;
        this.grossProfit = grossProfit;
        this.grossMargin = grossMargin;
        this.goodwillAndIntangibleAssets = goodwillAndIntangibleAssets;
        this.interestExpense = interestExpense;
        this.investedCapital = investedCapital;
        this.investedCapitalAverage = investedCapitalAverage;
        this.inventory = inventory;
        this.investments = investments;
        this.investmentsCurrent = investmentsCurrent;
        this.investmentsNonCurrent = investmentsNonCurrent;
        this.totalLiabilities = totalLiabilities;
        this.currentLiabilities = currentLiabilities;
        this.liabilitiesNonCurrent = liabilitiesNonCurrent;
        this.marketCapitalization = marketCapitalization;
        this.netCashFlow = netCashFlow;
        this.netCashFlowBusinessAcquisitionsDisposals = netCashFlowBusinessAcquisitionsDisposals;
        this.issuanceEquityShares = issuanceEquityShares;
        this.issuanceDebtSecurities = issuanceDebtSecurities;
        this.paymentDividendsOtherCashDistributions = paymentDividendsOtherCashDistributions;
        this.netCashFlowFromFinancing = netCashFlowFromFinancing;
        this.netCashFlowFromInvesting = netCashFlowFromInvesting;
        this.netCashFlowInvestmentAcquisitionsDisposals = netCashFlowInvestmentAcquisitionsDisposals;
        this.netCashFlowFromOperations = netCashFlowFromOperations;
        this.effectOfExchangeRateChangesOnCash = effectOfExchangeRateChangesOnCash;
        this.netIncome = netIncome;
        this.netIncomeCommonStock = netIncomeCommonStock;
        this.netIncomeCommonStockUSD = netIncomeCommonStockUSD;
        this.netLossIncomeFromDiscontinuedOperations = netLossIncomeFromDiscontinuedOperations;
        this.netIncomeToNonControllingInterests = netIncomeToNonControllingInterests;
        this.profitMargin = profitMargin;
        this.operatingExpenses = operatingExpenses;
        this.operatingIncome = operatingIncome;
        this.tradeAndNonTradePayables = tradeAndNonTradePayables;
        this.payoutRatio = payoutRatio;
        this.priceToBookValue = priceToBookValue;
        this.priceEarnings = priceEarnings;
        this.priceToEarningsRatio = priceToEarningsRatio;
        this.propertyPlantEquipmentNet = propertyPlantEquipmentNet;
        this.preferredDividendsIncomeStatementImpact = preferredDividendsIncomeStatementImpact;
        this.sharePriceAdjustedClose = sharePriceAdjustedClose;
        this.priceSales = priceSales;
        this.priceToSalesRatio = priceToSalesRatio;
        this.tradeAndNonTradeReceivables = tradeAndNonTradeReceivables;
        this.accumulatedRetainedEarningsDeficit = accumulatedRetainedEarningsDeficit;
        this.revenues = revenues;
        this.revenuesUSD = revenuesUSD;
        this.researchAndDevelopmentExpense = researchAndDevelopmentExpense;
        this.returnOnAverageAssets = returnOnAverageAssets;
        this.returnOnAverageEquity = returnOnAverageEquity;
        this.returnOnInvestedCapital = returnOnInvestedCapital;
        this.returnOnSales = returnOnSales;
        this.shareBasedCompensation = shareBasedCompensation;
        this.sellingGeneralAndAdministrativeExpense = sellingGeneralAndAdministrativeExpense;
        this.shareFactor = shareFactor;
        this.shares = shares;
        this.weightedAverageShares = weightedAverageShares;
        this.weightedAverageSharesDiluted = weightedAverageSharesDiluted;
        this.salesPerShare = salesPerShare;
        this.tangibleAssetValue = tangibleAssetValue;
        this.taxAssets = taxAssets;
        this.incomeTaxExpense = incomeTaxExpense;
        this.taxLiabilities = taxLiabilities;
        this.tangibleAssetsBookValuePerShare = tangibleAssetsBookValuePerShare;
        this.workingCapital = workingCapital;
    }

    /**
     * ticker
     * <p>
     * 
     * 
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * ticker
     * <p>
     * 
     * 
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * Reporting period. Can be Q, T, QA, TA, Y or YA
     * <p>
     * 
     * 
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Reporting period. Can be Q, T, QA, TA, Y or YA
     * <p>
     * 
     * 
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * calendarDate
     * <p>
     * 
     * 
     */
    public String getCalendarDate() {
        return calendarDate;
    }

    /**
     * calendarDate
     * <p>
     * 
     * 
     */
    public void setCalendarDate(String calendarDate) {
        this.calendarDate = calendarDate;
    }

    /**
     * reportPeriod
     * <p>
     * 
     * 
     */
    public String getReportPeriod() {
        return reportPeriod;
    }

    /**
     * reportPeriod
     * <p>
     * 
     * 
     */
    public void setReportPeriod(String reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    /**
     * updated
     * <p>
     * 
     * 
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * updated
     * <p>
     * 
     * 
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    /**
     * accumulatedOtherComprehensiveIncome
     * <p>
     * 
     * 
     */
    public Double getAccumulatedOtherComprehensiveIncome() {
        return accumulatedOtherComprehensiveIncome;
    }

    /**
     * accumulatedOtherComprehensiveIncome
     * <p>
     * 
     * 
     */
    public void setAccumulatedOtherComprehensiveIncome(Double accumulatedOtherComprehensiveIncome) {
        this.accumulatedOtherComprehensiveIncome = accumulatedOtherComprehensiveIncome;
    }

    /**
     * assets
     * <p>
     * 
     * 
     */
    public Double getAssets() {
        return assets;
    }

    /**
     * assets
     * <p>
     * 
     * 
     */
    public void setAssets(Double assets) {
        this.assets = assets;
    }

    /**
     * assetsAverage
     * <p>
     * 
     * 
     */
    public Double getAssetsAverage() {
        return assetsAverage;
    }

    /**
     * assetsAverage
     * <p>
     * 
     * 
     */
    public void setAssetsAverage(Double assetsAverage) {
        this.assetsAverage = assetsAverage;
    }

    /**
     * assetsCurrent
     * <p>
     * 
     * 
     */
    public Double getAssetsCurrent() {
        return assetsCurrent;
    }

    /**
     * assetsCurrent
     * <p>
     * 
     * 
     */
    public void setAssetsCurrent(Double assetsCurrent) {
        this.assetsCurrent = assetsCurrent;
    }

    /**
     * assetTurnover
     * <p>
     * 
     * 
     */
    public Double getAssetTurnover() {
        return assetTurnover;
    }

    /**
     * assetTurnover
     * <p>
     * 
     * 
     */
    public void setAssetTurnover(Double assetTurnover) {
        this.assetTurnover = assetTurnover;
    }

    /**
     * assetsNonCurrent
     * <p>
     * 
     * 
     */
    public Double getAssetsNonCurrent() {
        return assetsNonCurrent;
    }

    /**
     * assetsNonCurrent
     * <p>
     * 
     * 
     */
    public void setAssetsNonCurrent(Double assetsNonCurrent) {
        this.assetsNonCurrent = assetsNonCurrent;
    }

    /**
     * bookValuePerShare
     * <p>
     * 
     * 
     */
    public Double getBookValuePerShare() {
        return bookValuePerShare;
    }

    /**
     * bookValuePerShare
     * <p>
     * 
     * 
     */
    public void setBookValuePerShare(Double bookValuePerShare) {
        this.bookValuePerShare = bookValuePerShare;
    }

    /**
     * capitalExpenditure
     * <p>
     * 
     * 
     */
    public Double getCapitalExpenditure() {
        return capitalExpenditure;
    }

    /**
     * capitalExpenditure
     * <p>
     * 
     * 
     */
    public void setCapitalExpenditure(Double capitalExpenditure) {
        this.capitalExpenditure = capitalExpenditure;
    }

    /**
     * cashAndEquivalents
     * <p>
     * 
     * 
     */
    public Double getCashAndEquivalents() {
        return cashAndEquivalents;
    }

    /**
     * cashAndEquivalents
     * <p>
     * 
     * 
     */
    public void setCashAndEquivalents(Double cashAndEquivalents) {
        this.cashAndEquivalents = cashAndEquivalents;
    }

    /**
     * cashAndEquivalentsUSD
     * <p>
     * 
     * 
     */
    public Double getCashAndEquivalentsUSD() {
        return cashAndEquivalentsUSD;
    }

    /**
     * cashAndEquivalentsUSD
     * <p>
     * 
     * 
     */
    public void setCashAndEquivalentsUSD(Double cashAndEquivalentsUSD) {
        this.cashAndEquivalentsUSD = cashAndEquivalentsUSD;
    }

    /**
     * costOfRevenue
     * <p>
     * 
     * 
     */
    public Double getCostOfRevenue() {
        return costOfRevenue;
    }

    /**
     * costOfRevenue
     * <p>
     * 
     * 
     */
    public void setCostOfRevenue(Double costOfRevenue) {
        this.costOfRevenue = costOfRevenue;
    }

    /**
     * consolidatedIncome
     * <p>
     * 
     * 
     */
    public Double getConsolidatedIncome() {
        return consolidatedIncome;
    }

    /**
     * consolidatedIncome
     * <p>
     * 
     * 
     */
    public void setConsolidatedIncome(Double consolidatedIncome) {
        this.consolidatedIncome = consolidatedIncome;
    }

    /**
     * currentRatio
     * <p>
     * 
     * 
     */
    public Double getCurrentRatio() {
        return currentRatio;
    }

    /**
     * currentRatio
     * <p>
     * 
     * 
     */
    public void setCurrentRatio(Double currentRatio) {
        this.currentRatio = currentRatio;
    }

    /**
     * debtToEquityRatio
     * <p>
     * 
     * 
     */
    public Double getDebtToEquityRatio() {
        return debtToEquityRatio;
    }

    /**
     * debtToEquityRatio
     * <p>
     * 
     * 
     */
    public void setDebtToEquityRatio(Double debtToEquityRatio) {
        this.debtToEquityRatio = debtToEquityRatio;
    }

    /**
     * debt
     * <p>
     * 
     * 
     */
    public Double getDebt() {
        return debt;
    }

    /**
     * debt
     * <p>
     * 
     * 
     */
    public void setDebt(Double debt) {
        this.debt = debt;
    }

    /**
     * debtCurrent
     * <p>
     * 
     * 
     */
    public Double getDebtCurrent() {
        return debtCurrent;
    }

    /**
     * debtCurrent
     * <p>
     * 
     * 
     */
    public void setDebtCurrent(Double debtCurrent) {
        this.debtCurrent = debtCurrent;
    }

    /**
     * debtNonCurrent
     * <p>
     * 
     * 
     */
    public Double getDebtNonCurrent() {
        return debtNonCurrent;
    }

    /**
     * debtNonCurrent
     * <p>
     * 
     * 
     */
    public void setDebtNonCurrent(Double debtNonCurrent) {
        this.debtNonCurrent = debtNonCurrent;
    }

    /**
     * debtUSD
     * <p>
     * 
     * 
     */
    public Double getDebtUSD() {
        return debtUSD;
    }

    /**
     * debtUSD
     * <p>
     * 
     * 
     */
    public void setDebtUSD(Double debtUSD) {
        this.debtUSD = debtUSD;
    }

    /**
     * deferredRevenue
     * <p>
     * 
     * 
     */
    public Double getDeferredRevenue() {
        return deferredRevenue;
    }

    /**
     * deferredRevenue
     * <p>
     * 
     * 
     */
    public void setDeferredRevenue(Double deferredRevenue) {
        this.deferredRevenue = deferredRevenue;
    }

    /**
     * depreciationAmortizationAndAccretion
     * <p>
     * 
     * 
     */
    public Double getDepreciationAmortizationAndAccretion() {
        return depreciationAmortizationAndAccretion;
    }

    /**
     * depreciationAmortizationAndAccretion
     * <p>
     * 
     * 
     */
    public void setDepreciationAmortizationAndAccretion(Double depreciationAmortizationAndAccretion) {
        this.depreciationAmortizationAndAccretion = depreciationAmortizationAndAccretion;
    }

    /**
     * deposits
     * <p>
     * 
     * 
     */
    public Double getDeposits() {
        return deposits;
    }

    /**
     * deposits
     * <p>
     * 
     * 
     */
    public void setDeposits(Double deposits) {
        this.deposits = deposits;
    }

    /**
     * dividendYield
     * <p>
     * 
     * 
     */
    public Double getDividendYield() {
        return dividendYield;
    }

    /**
     * dividendYield
     * <p>
     * 
     * 
     */
    public void setDividendYield(Double dividendYield) {
        this.dividendYield = dividendYield;
    }

    /**
     * dividendsPerBasicCommonShare
     * <p>
     * 
     * 
     */
    public Double getDividendsPerBasicCommonShare() {
        return dividendsPerBasicCommonShare;
    }

    /**
     * dividendsPerBasicCommonShare
     * <p>
     * 
     * 
     */
    public void setDividendsPerBasicCommonShare(Double dividendsPerBasicCommonShare) {
        this.dividendsPerBasicCommonShare = dividendsPerBasicCommonShare;
    }

    /**
     * earningBeforeInterestTaxes
     * <p>
     * 
     * 
     */
    public Double getEarningBeforeInterestTaxes() {
        return earningBeforeInterestTaxes;
    }

    /**
     * earningBeforeInterestTaxes
     * <p>
     * 
     * 
     */
    public void setEarningBeforeInterestTaxes(Double earningBeforeInterestTaxes) {
        this.earningBeforeInterestTaxes = earningBeforeInterestTaxes;
    }

    /**
     * earningsBeforeInterestTaxesDepreciationAmortization
     * <p>
     * 
     * 
     */
    public Double getEarningsBeforeInterestTaxesDepreciationAmortization() {
        return earningsBeforeInterestTaxesDepreciationAmortization;
    }

    /**
     * earningsBeforeInterestTaxesDepreciationAmortization
     * <p>
     * 
     * 
     */
    public void setEarningsBeforeInterestTaxesDepreciationAmortization(Double earningsBeforeInterestTaxesDepreciationAmortization) {
        this.earningsBeforeInterestTaxesDepreciationAmortization = earningsBeforeInterestTaxesDepreciationAmortization;
    }

    /**
     * EBITDAMargin
     * <p>
     * 
     * 
     */
    public Double getEBITDAMargin() {
        return eBITDAMargin;
    }

    /**
     * EBITDAMargin
     * <p>
     * 
     * 
     */
    public void setEBITDAMargin(Double eBITDAMargin) {
        this.eBITDAMargin = eBITDAMargin;
    }

    /**
     * earningsBeforeInterestTaxesDepreciationAmortizationUSD
     * <p>
     * 
     * 
     */
    public Double getEarningsBeforeInterestTaxesDepreciationAmortizationUSD() {
        return earningsBeforeInterestTaxesDepreciationAmortizationUSD;
    }

    /**
     * earningsBeforeInterestTaxesDepreciationAmortizationUSD
     * <p>
     * 
     * 
     */
    public void setEarningsBeforeInterestTaxesDepreciationAmortizationUSD(Double earningsBeforeInterestTaxesDepreciationAmortizationUSD) {
        this.earningsBeforeInterestTaxesDepreciationAmortizationUSD = earningsBeforeInterestTaxesDepreciationAmortizationUSD;
    }

    /**
     * earningBeforeInterestTaxesUSD
     * <p>
     * 
     * 
     */
    public Double getEarningBeforeInterestTaxesUSD() {
        return earningBeforeInterestTaxesUSD;
    }

    /**
     * earningBeforeInterestTaxesUSD
     * <p>
     * 
     * 
     */
    public void setEarningBeforeInterestTaxesUSD(Double earningBeforeInterestTaxesUSD) {
        this.earningBeforeInterestTaxesUSD = earningBeforeInterestTaxesUSD;
    }

    /**
     * earningsBeforeTax
     * <p>
     * 
     * 
     */
    public Double getEarningsBeforeTax() {
        return earningsBeforeTax;
    }

    /**
     * earningsBeforeTax
     * <p>
     * 
     * 
     */
    public void setEarningsBeforeTax(Double earningsBeforeTax) {
        this.earningsBeforeTax = earningsBeforeTax;
    }

    /**
     * earningsPerBasicShare
     * <p>
     * 
     * 
     */
    public Double getEarningsPerBasicShare() {
        return earningsPerBasicShare;
    }

    /**
     * earningsPerBasicShare
     * <p>
     * 
     * 
     */
    public void setEarningsPerBasicShare(Double earningsPerBasicShare) {
        this.earningsPerBasicShare = earningsPerBasicShare;
    }

    /**
     * earningsPerDilutedShare
     * <p>
     * 
     * 
     */
    public Double getEarningsPerDilutedShare() {
        return earningsPerDilutedShare;
    }

    /**
     * earningsPerDilutedShare
     * <p>
     * 
     * 
     */
    public void setEarningsPerDilutedShare(Double earningsPerDilutedShare) {
        this.earningsPerDilutedShare = earningsPerDilutedShare;
    }

    /**
     * earningsPerBasicShareUSD
     * <p>
     * 
     * 
     */
    public Double getEarningsPerBasicShareUSD() {
        return earningsPerBasicShareUSD;
    }

    /**
     * earningsPerBasicShareUSD
     * <p>
     * 
     * 
     */
    public void setEarningsPerBasicShareUSD(Double earningsPerBasicShareUSD) {
        this.earningsPerBasicShareUSD = earningsPerBasicShareUSD;
    }

    /**
     * shareholdersEquity
     * <p>
     * 
     * 
     */
    public Double getShareholdersEquity() {
        return shareholdersEquity;
    }

    /**
     * shareholdersEquity
     * <p>
     * 
     * 
     */
    public void setShareholdersEquity(Double shareholdersEquity) {
        this.shareholdersEquity = shareholdersEquity;
    }

    /**
     * averageEquity
     * <p>
     * 
     * 
     */
    public Double getAverageEquity() {
        return averageEquity;
    }

    /**
     * averageEquity
     * <p>
     * 
     * 
     */
    public void setAverageEquity(Double averageEquity) {
        this.averageEquity = averageEquity;
    }

    /**
     * shareholdersEquityUSD
     * <p>
     * 
     * 
     */
    public Double getShareholdersEquityUSD() {
        return shareholdersEquityUSD;
    }

    /**
     * shareholdersEquityUSD
     * <p>
     * 
     * 
     */
    public void setShareholdersEquityUSD(Double shareholdersEquityUSD) {
        this.shareholdersEquityUSD = shareholdersEquityUSD;
    }

    /**
     * enterpriseValue
     * <p>
     * 
     * 
     */
    public Double getEnterpriseValue() {
        return enterpriseValue;
    }

    /**
     * enterpriseValue
     * <p>
     * 
     * 
     */
    public void setEnterpriseValue(Double enterpriseValue) {
        this.enterpriseValue = enterpriseValue;
    }

    /**
     * enterpriseValueOverEBIT
     * <p>
     * 
     * 
     */
    public Double getEnterpriseValueOverEBIT() {
        return enterpriseValueOverEBIT;
    }

    /**
     * enterpriseValueOverEBIT
     * <p>
     * 
     * 
     */
    public void setEnterpriseValueOverEBIT(Double enterpriseValueOverEBIT) {
        this.enterpriseValueOverEBIT = enterpriseValueOverEBIT;
    }

    /**
     * enterpriseValueOverEBITDA
     * <p>
     * 
     * 
     */
    public Double getEnterpriseValueOverEBITDA() {
        return enterpriseValueOverEBITDA;
    }

    /**
     * enterpriseValueOverEBITDA
     * <p>
     * 
     * 
     */
    public void setEnterpriseValueOverEBITDA(Double enterpriseValueOverEBITDA) {
        this.enterpriseValueOverEBITDA = enterpriseValueOverEBITDA;
    }

    /**
     * freeCashFlow
     * <p>
     * 
     * 
     */
    public Double getFreeCashFlow() {
        return freeCashFlow;
    }

    /**
     * freeCashFlow
     * <p>
     * 
     * 
     */
    public void setFreeCashFlow(Double freeCashFlow) {
        this.freeCashFlow = freeCashFlow;
    }

    /**
     * freeCashFlowPerShare
     * <p>
     * 
     * 
     */
    public Double getFreeCashFlowPerShare() {
        return freeCashFlowPerShare;
    }

    /**
     * freeCashFlowPerShare
     * <p>
     * 
     * 
     */
    public void setFreeCashFlowPerShare(Double freeCashFlowPerShare) {
        this.freeCashFlowPerShare = freeCashFlowPerShare;
    }

    /**
     * foreignCurrencyUSDExchangeRate
     * <p>
     * 
     * 
     */
    public Double getForeignCurrencyUSDExchangeRate() {
        return foreignCurrencyUSDExchangeRate;
    }

    /**
     * foreignCurrencyUSDExchangeRate
     * <p>
     * 
     * 
     */
    public void setForeignCurrencyUSDExchangeRate(Double foreignCurrencyUSDExchangeRate) {
        this.foreignCurrencyUSDExchangeRate = foreignCurrencyUSDExchangeRate;
    }

    /**
     * grossProfit
     * <p>
     * 
     * 
     */
    public Double getGrossProfit() {
        return grossProfit;
    }

    /**
     * grossProfit
     * <p>
     * 
     * 
     */
    public void setGrossProfit(Double grossProfit) {
        this.grossProfit = grossProfit;
    }

    /**
     * grossMargin
     * <p>
     * 
     * 
     */
    public Double getGrossMargin() {
        return grossMargin;
    }

    /**
     * grossMargin
     * <p>
     * 
     * 
     */
    public void setGrossMargin(Double grossMargin) {
        this.grossMargin = grossMargin;
    }

    /**
     * goodwillAndIntangibleAssets
     * <p>
     * 
     * 
     */
    public Double getGoodwillAndIntangibleAssets() {
        return goodwillAndIntangibleAssets;
    }

    /**
     * goodwillAndIntangibleAssets
     * <p>
     * 
     * 
     */
    public void setGoodwillAndIntangibleAssets(Double goodwillAndIntangibleAssets) {
        this.goodwillAndIntangibleAssets = goodwillAndIntangibleAssets;
    }

    /**
     * interestExpense
     * <p>
     * 
     * 
     */
    public Double getInterestExpense() {
        return interestExpense;
    }

    /**
     * interestExpense
     * <p>
     * 
     * 
     */
    public void setInterestExpense(Double interestExpense) {
        this.interestExpense = interestExpense;
    }

    /**
     * investedCapital
     * <p>
     * 
     * 
     */
    public Double getInvestedCapital() {
        return investedCapital;
    }

    /**
     * investedCapital
     * <p>
     * 
     * 
     */
    public void setInvestedCapital(Double investedCapital) {
        this.investedCapital = investedCapital;
    }

    /**
     * investedCapitalAverage
     * <p>
     * 
     * 
     */
    public Double getInvestedCapitalAverage() {
        return investedCapitalAverage;
    }

    /**
     * investedCapitalAverage
     * <p>
     * 
     * 
     */
    public void setInvestedCapitalAverage(Double investedCapitalAverage) {
        this.investedCapitalAverage = investedCapitalAverage;
    }

    /**
     * inventory
     * <p>
     * 
     * 
     */
    public Double getInventory() {
        return inventory;
    }

    /**
     * inventory
     * <p>
     * 
     * 
     */
    public void setInventory(Double inventory) {
        this.inventory = inventory;
    }

    /**
     * investments
     * <p>
     * 
     * 
     */
    public Double getInvestments() {
        return investments;
    }

    /**
     * investments
     * <p>
     * 
     * 
     */
    public void setInvestments(Double investments) {
        this.investments = investments;
    }

    /**
     * investmentsCurrent
     * <p>
     * 
     * 
     */
    public Double getInvestmentsCurrent() {
        return investmentsCurrent;
    }

    /**
     * investmentsCurrent
     * <p>
     * 
     * 
     */
    public void setInvestmentsCurrent(Double investmentsCurrent) {
        this.investmentsCurrent = investmentsCurrent;
    }

    /**
     * investmentsNonCurrent
     * <p>
     * 
     * 
     */
    public Double getInvestmentsNonCurrent() {
        return investmentsNonCurrent;
    }

    /**
     * investmentsNonCurrent
     * <p>
     * 
     * 
     */
    public void setInvestmentsNonCurrent(Double investmentsNonCurrent) {
        this.investmentsNonCurrent = investmentsNonCurrent;
    }

    /**
     * totalLiabilities
     * <p>
     * 
     * 
     */
    public Double getTotalLiabilities() {
        return totalLiabilities;
    }

    /**
     * totalLiabilities
     * <p>
     * 
     * 
     */
    public void setTotalLiabilities(Double totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    /**
     * currentLiabilities
     * <p>
     * 
     * 
     */
    public Double getCurrentLiabilities() {
        return currentLiabilities;
    }

    /**
     * currentLiabilities
     * <p>
     * 
     * 
     */
    public void setCurrentLiabilities(Double currentLiabilities) {
        this.currentLiabilities = currentLiabilities;
    }

    /**
     * liabilitiesNonCurrent
     * <p>
     * 
     * 
     */
    public Double getLiabilitiesNonCurrent() {
        return liabilitiesNonCurrent;
    }

    /**
     * liabilitiesNonCurrent
     * <p>
     * 
     * 
     */
    public void setLiabilitiesNonCurrent(Double liabilitiesNonCurrent) {
        this.liabilitiesNonCurrent = liabilitiesNonCurrent;
    }

    /**
     * marketCapitalization
     * <p>
     * 
     * 
     */
    public Double getMarketCapitalization() {
        return marketCapitalization;
    }

    /**
     * marketCapitalization
     * <p>
     * 
     * 
     */
    public void setMarketCapitalization(Double marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    /**
     * netCashFlow
     * <p>
     * 
     * 
     */
    public Double getNetCashFlow() {
        return netCashFlow;
    }

    /**
     * netCashFlow
     * <p>
     * 
     * 
     */
    public void setNetCashFlow(Double netCashFlow) {
        this.netCashFlow = netCashFlow;
    }

    /**
     * netCashFlowBusinessAcquisitionsDisposals
     * <p>
     * 
     * 
     */
    public Double getNetCashFlowBusinessAcquisitionsDisposals() {
        return netCashFlowBusinessAcquisitionsDisposals;
    }

    /**
     * netCashFlowBusinessAcquisitionsDisposals
     * <p>
     * 
     * 
     */
    public void setNetCashFlowBusinessAcquisitionsDisposals(Double netCashFlowBusinessAcquisitionsDisposals) {
        this.netCashFlowBusinessAcquisitionsDisposals = netCashFlowBusinessAcquisitionsDisposals;
    }

    /**
     * issuanceEquityShares
     * <p>
     * 
     * 
     */
    public Double getIssuanceEquityShares() {
        return issuanceEquityShares;
    }

    /**
     * issuanceEquityShares
     * <p>
     * 
     * 
     */
    public void setIssuanceEquityShares(Double issuanceEquityShares) {
        this.issuanceEquityShares = issuanceEquityShares;
    }

    /**
     * issuanceDebtSecurities
     * <p>
     * 
     * 
     */
    public Double getIssuanceDebtSecurities() {
        return issuanceDebtSecurities;
    }

    /**
     * issuanceDebtSecurities
     * <p>
     * 
     * 
     */
    public void setIssuanceDebtSecurities(Double issuanceDebtSecurities) {
        this.issuanceDebtSecurities = issuanceDebtSecurities;
    }

    /**
     * paymentDividendsOtherCashDistributions
     * <p>
     * 
     * 
     */
    public Double getPaymentDividendsOtherCashDistributions() {
        return paymentDividendsOtherCashDistributions;
    }

    /**
     * paymentDividendsOtherCashDistributions
     * <p>
     * 
     * 
     */
    public void setPaymentDividendsOtherCashDistributions(Double paymentDividendsOtherCashDistributions) {
        this.paymentDividendsOtherCashDistributions = paymentDividendsOtherCashDistributions;
    }

    /**
     * netCashFlowFromFinancing
     * <p>
     * 
     * 
     */
    public Double getNetCashFlowFromFinancing() {
        return netCashFlowFromFinancing;
    }

    /**
     * netCashFlowFromFinancing
     * <p>
     * 
     * 
     */
    public void setNetCashFlowFromFinancing(Double netCashFlowFromFinancing) {
        this.netCashFlowFromFinancing = netCashFlowFromFinancing;
    }

    /**
     * netCashFlowFromInvesting
     * <p>
     * 
     * 
     */
    public Double getNetCashFlowFromInvesting() {
        return netCashFlowFromInvesting;
    }

    /**
     * netCashFlowFromInvesting
     * <p>
     * 
     * 
     */
    public void setNetCashFlowFromInvesting(Double netCashFlowFromInvesting) {
        this.netCashFlowFromInvesting = netCashFlowFromInvesting;
    }

    /**
     * netCashFlowInvestmentAcquisitionsDisposals
     * <p>
     * 
     * 
     */
    public Double getNetCashFlowInvestmentAcquisitionsDisposals() {
        return netCashFlowInvestmentAcquisitionsDisposals;
    }

    /**
     * netCashFlowInvestmentAcquisitionsDisposals
     * <p>
     * 
     * 
     */
    public void setNetCashFlowInvestmentAcquisitionsDisposals(Double netCashFlowInvestmentAcquisitionsDisposals) {
        this.netCashFlowInvestmentAcquisitionsDisposals = netCashFlowInvestmentAcquisitionsDisposals;
    }

    /**
     * netCashFlowFromOperations
     * <p>
     * 
     * 
     */
    public Double getNetCashFlowFromOperations() {
        return netCashFlowFromOperations;
    }

    /**
     * netCashFlowFromOperations
     * <p>
     * 
     * 
     */
    public void setNetCashFlowFromOperations(Double netCashFlowFromOperations) {
        this.netCashFlowFromOperations = netCashFlowFromOperations;
    }

    /**
     * effectOfExchangeRateChangesOnCash
     * <p>
     * 
     * 
     */
    public Double getEffectOfExchangeRateChangesOnCash() {
        return effectOfExchangeRateChangesOnCash;
    }

    /**
     * effectOfExchangeRateChangesOnCash
     * <p>
     * 
     * 
     */
    public void setEffectOfExchangeRateChangesOnCash(Double effectOfExchangeRateChangesOnCash) {
        this.effectOfExchangeRateChangesOnCash = effectOfExchangeRateChangesOnCash;
    }

    /**
     * netIncome
     * <p>
     * 
     * 
     */
    public Double getNetIncome() {
        return netIncome;
    }

    /**
     * netIncome
     * <p>
     * 
     * 
     */
    public void setNetIncome(Double netIncome) {
        this.netIncome = netIncome;
    }

    /**
     * netIncomeCommonStock
     * <p>
     * 
     * 
     */
    public Double getNetIncomeCommonStock() {
        return netIncomeCommonStock;
    }

    /**
     * netIncomeCommonStock
     * <p>
     * 
     * 
     */
    public void setNetIncomeCommonStock(Double netIncomeCommonStock) {
        this.netIncomeCommonStock = netIncomeCommonStock;
    }

    /**
     * netIncomeCommonStockUSD
     * <p>
     * 
     * 
     */
    public Double getNetIncomeCommonStockUSD() {
        return netIncomeCommonStockUSD;
    }

    /**
     * netIncomeCommonStockUSD
     * <p>
     * 
     * 
     */
    public void setNetIncomeCommonStockUSD(Double netIncomeCommonStockUSD) {
        this.netIncomeCommonStockUSD = netIncomeCommonStockUSD;
    }

    /**
     * netLossIncomeFromDiscontinuedOperations
     * <p>
     * 
     * 
     */
    public Double getNetLossIncomeFromDiscontinuedOperations() {
        return netLossIncomeFromDiscontinuedOperations;
    }

    /**
     * netLossIncomeFromDiscontinuedOperations
     * <p>
     * 
     * 
     */
    public void setNetLossIncomeFromDiscontinuedOperations(Double netLossIncomeFromDiscontinuedOperations) {
        this.netLossIncomeFromDiscontinuedOperations = netLossIncomeFromDiscontinuedOperations;
    }

    /**
     * netIncomeToNonControllingInterests
     * <p>
     * 
     * 
     */
    public Double getNetIncomeToNonControllingInterests() {
        return netIncomeToNonControllingInterests;
    }

    /**
     * netIncomeToNonControllingInterests
     * <p>
     * 
     * 
     */
    public void setNetIncomeToNonControllingInterests(Double netIncomeToNonControllingInterests) {
        this.netIncomeToNonControllingInterests = netIncomeToNonControllingInterests;
    }

    /**
     * profitMargin
     * <p>
     * 
     * 
     */
    public Double getProfitMargin() {
        return profitMargin;
    }

    /**
     * profitMargin
     * <p>
     * 
     * 
     */
    public void setProfitMargin(Double profitMargin) {
        this.profitMargin = profitMargin;
    }

    /**
     * operatingExpenses
     * <p>
     * 
     * 
     */
    public Double getOperatingExpenses() {
        return operatingExpenses;
    }

    /**
     * operatingExpenses
     * <p>
     * 
     * 
     */
    public void setOperatingExpenses(Double operatingExpenses) {
        this.operatingExpenses = operatingExpenses;
    }

    /**
     * operatingIncome
     * <p>
     * 
     * 
     */
    public Double getOperatingIncome() {
        return operatingIncome;
    }

    /**
     * operatingIncome
     * <p>
     * 
     * 
     */
    public void setOperatingIncome(Double operatingIncome) {
        this.operatingIncome = operatingIncome;
    }

    /**
     * tradeAndNonTradePayables
     * <p>
     * 
     * 
     */
    public Double getTradeAndNonTradePayables() {
        return tradeAndNonTradePayables;
    }

    /**
     * tradeAndNonTradePayables
     * <p>
     * 
     * 
     */
    public void setTradeAndNonTradePayables(Double tradeAndNonTradePayables) {
        this.tradeAndNonTradePayables = tradeAndNonTradePayables;
    }

    /**
     * payoutRatio
     * <p>
     * 
     * 
     */
    public Double getPayoutRatio() {
        return payoutRatio;
    }

    /**
     * payoutRatio
     * <p>
     * 
     * 
     */
    public void setPayoutRatio(Double payoutRatio) {
        this.payoutRatio = payoutRatio;
    }

    /**
     * priceToBookValue
     * <p>
     * 
     * 
     */
    public Double getPriceToBookValue() {
        return priceToBookValue;
    }

    /**
     * priceToBookValue
     * <p>
     * 
     * 
     */
    public void setPriceToBookValue(Double priceToBookValue) {
        this.priceToBookValue = priceToBookValue;
    }

    /**
     * priceEarnings
     * <p>
     * 
     * 
     */
    public Double getPriceEarnings() {
        return priceEarnings;
    }

    /**
     * priceEarnings
     * <p>
     * 
     * 
     */
    public void setPriceEarnings(Double priceEarnings) {
        this.priceEarnings = priceEarnings;
    }

    /**
     * priceToEarningsRatio
     * <p>
     * 
     * 
     */
    public Double getPriceToEarningsRatio() {
        return priceToEarningsRatio;
    }

    /**
     * priceToEarningsRatio
     * <p>
     * 
     * 
     */
    public void setPriceToEarningsRatio(Double priceToEarningsRatio) {
        this.priceToEarningsRatio = priceToEarningsRatio;
    }

    /**
     * propertyPlantEquipmentNet
     * <p>
     * 
     * 
     */
    public Double getPropertyPlantEquipmentNet() {
        return propertyPlantEquipmentNet;
    }

    /**
     * propertyPlantEquipmentNet
     * <p>
     * 
     * 
     */
    public void setPropertyPlantEquipmentNet(Double propertyPlantEquipmentNet) {
        this.propertyPlantEquipmentNet = propertyPlantEquipmentNet;
    }

    /**
     * preferredDividendsIncomeStatementImpact
     * <p>
     * 
     * 
     */
    public Double getPreferredDividendsIncomeStatementImpact() {
        return preferredDividendsIncomeStatementImpact;
    }

    /**
     * preferredDividendsIncomeStatementImpact
     * <p>
     * 
     * 
     */
    public void setPreferredDividendsIncomeStatementImpact(Double preferredDividendsIncomeStatementImpact) {
        this.preferredDividendsIncomeStatementImpact = preferredDividendsIncomeStatementImpact;
    }

    /**
     * sharePriceAdjustedClose
     * <p>
     * 
     * 
     */
    public Double getSharePriceAdjustedClose() {
        return sharePriceAdjustedClose;
    }

    /**
     * sharePriceAdjustedClose
     * <p>
     * 
     * 
     */
    public void setSharePriceAdjustedClose(Double sharePriceAdjustedClose) {
        this.sharePriceAdjustedClose = sharePriceAdjustedClose;
    }

    /**
     * priceSales
     * <p>
     * 
     * 
     */
    public Double getPriceSales() {
        return priceSales;
    }

    /**
     * priceSales
     * <p>
     * 
     * 
     */
    public void setPriceSales(Double priceSales) {
        this.priceSales = priceSales;
    }

    /**
     * priceToSalesRatio
     * <p>
     * 
     * 
     */
    public Double getPriceToSalesRatio() {
        return priceToSalesRatio;
    }

    /**
     * priceToSalesRatio
     * <p>
     * 
     * 
     */
    public void setPriceToSalesRatio(Double priceToSalesRatio) {
        this.priceToSalesRatio = priceToSalesRatio;
    }

    /**
     * tradeAndNonTradeReceivables
     * <p>
     * 
     * 
     */
    public Double getTradeAndNonTradeReceivables() {
        return tradeAndNonTradeReceivables;
    }

    /**
     * tradeAndNonTradeReceivables
     * <p>
     * 
     * 
     */
    public void setTradeAndNonTradeReceivables(Double tradeAndNonTradeReceivables) {
        this.tradeAndNonTradeReceivables = tradeAndNonTradeReceivables;
    }

    /**
     * accumulatedRetainedEarningsDeficit
     * <p>
     * 
     * 
     */
    public Double getAccumulatedRetainedEarningsDeficit() {
        return accumulatedRetainedEarningsDeficit;
    }

    /**
     * accumulatedRetainedEarningsDeficit
     * <p>
     * 
     * 
     */
    public void setAccumulatedRetainedEarningsDeficit(Double accumulatedRetainedEarningsDeficit) {
        this.accumulatedRetainedEarningsDeficit = accumulatedRetainedEarningsDeficit;
    }

    /**
     * revenues
     * <p>
     * 
     * 
     */
    public Double getRevenues() {
        return revenues;
    }

    /**
     * revenues
     * <p>
     * 
     * 
     */
    public void setRevenues(Double revenues) {
        this.revenues = revenues;
    }

    /**
     * revenuesUSD
     * <p>
     * 
     * 
     */
    public Double getRevenuesUSD() {
        return revenuesUSD;
    }

    /**
     * revenuesUSD
     * <p>
     * 
     * 
     */
    public void setRevenuesUSD(Double revenuesUSD) {
        this.revenuesUSD = revenuesUSD;
    }

    /**
     * researchAndDevelopmentExpense
     * <p>
     * 
     * 
     */
    public Double getResearchAndDevelopmentExpense() {
        return researchAndDevelopmentExpense;
    }

    /**
     * researchAndDevelopmentExpense
     * <p>
     * 
     * 
     */
    public void setResearchAndDevelopmentExpense(Double researchAndDevelopmentExpense) {
        this.researchAndDevelopmentExpense = researchAndDevelopmentExpense;
    }

    /**
     * returnOnAverageAssets
     * <p>
     * 
     * 
     */
    public Double getReturnOnAverageAssets() {
        return returnOnAverageAssets;
    }

    /**
     * returnOnAverageAssets
     * <p>
     * 
     * 
     */
    public void setReturnOnAverageAssets(Double returnOnAverageAssets) {
        this.returnOnAverageAssets = returnOnAverageAssets;
    }

    /**
     * returnOnAverageEquity
     * <p>
     * 
     * 
     */
    public Double getReturnOnAverageEquity() {
        return returnOnAverageEquity;
    }

    /**
     * returnOnAverageEquity
     * <p>
     * 
     * 
     */
    public void setReturnOnAverageEquity(Double returnOnAverageEquity) {
        this.returnOnAverageEquity = returnOnAverageEquity;
    }

    /**
     * returnOnInvestedCapital
     * <p>
     * 
     * 
     */
    public Double getReturnOnInvestedCapital() {
        return returnOnInvestedCapital;
    }

    /**
     * returnOnInvestedCapital
     * <p>
     * 
     * 
     */
    public void setReturnOnInvestedCapital(Double returnOnInvestedCapital) {
        this.returnOnInvestedCapital = returnOnInvestedCapital;
    }

    /**
     * returnOnSales
     * <p>
     * 
     * 
     */
    public Double getReturnOnSales() {
        return returnOnSales;
    }

    /**
     * returnOnSales
     * <p>
     * 
     * 
     */
    public void setReturnOnSales(Double returnOnSales) {
        this.returnOnSales = returnOnSales;
    }

    /**
     * shareBasedCompensation
     * <p>
     * 
     * 
     */
    public Double getShareBasedCompensation() {
        return shareBasedCompensation;
    }

    /**
     * shareBasedCompensation
     * <p>
     * 
     * 
     */
    public void setShareBasedCompensation(Double shareBasedCompensation) {
        this.shareBasedCompensation = shareBasedCompensation;
    }

    /**
     * sellingGeneralAndAdministrativeExpense
     * <p>
     * 
     * 
     */
    public Double getSellingGeneralAndAdministrativeExpense() {
        return sellingGeneralAndAdministrativeExpense;
    }

    /**
     * sellingGeneralAndAdministrativeExpense
     * <p>
     * 
     * 
     */
    public void setSellingGeneralAndAdministrativeExpense(Double sellingGeneralAndAdministrativeExpense) {
        this.sellingGeneralAndAdministrativeExpense = sellingGeneralAndAdministrativeExpense;
    }

    /**
     * shareFactor
     * <p>
     * 
     * 
     */
    public Double getShareFactor() {
        return shareFactor;
    }

    /**
     * shareFactor
     * <p>
     * 
     * 
     */
    public void setShareFactor(Double shareFactor) {
        this.shareFactor = shareFactor;
    }

    /**
     * shares
     * <p>
     * 
     * 
     */
    public Double getShares() {
        return shares;
    }

    /**
     * shares
     * <p>
     * 
     * 
     */
    public void setShares(Double shares) {
        this.shares = shares;
    }

    /**
     * weightedAverageShares
     * <p>
     * 
     * 
     */
    public Double getWeightedAverageShares() {
        return weightedAverageShares;
    }

    /**
     * weightedAverageShares
     * <p>
     * 
     * 
     */
    public void setWeightedAverageShares(Double weightedAverageShares) {
        this.weightedAverageShares = weightedAverageShares;
    }

    /**
     * weightedAverageSharesDiluted
     * <p>
     * 
     * 
     */
    public Double getWeightedAverageSharesDiluted() {
        return weightedAverageSharesDiluted;
    }

    /**
     * weightedAverageSharesDiluted
     * <p>
     * 
     * 
     */
    public void setWeightedAverageSharesDiluted(Double weightedAverageSharesDiluted) {
        this.weightedAverageSharesDiluted = weightedAverageSharesDiluted;
    }

    /**
     * salesPerShare
     * <p>
     * 
     * 
     */
    public Double getSalesPerShare() {
        return salesPerShare;
    }

    /**
     * salesPerShare
     * <p>
     * 
     * 
     */
    public void setSalesPerShare(Double salesPerShare) {
        this.salesPerShare = salesPerShare;
    }

    /**
     * tangibleAssetValue
     * <p>
     * 
     * 
     */
    public Double getTangibleAssetValue() {
        return tangibleAssetValue;
    }

    /**
     * tangibleAssetValue
     * <p>
     * 
     * 
     */
    public void setTangibleAssetValue(Double tangibleAssetValue) {
        this.tangibleAssetValue = tangibleAssetValue;
    }

    /**
     * taxAssets
     * <p>
     * 
     * 
     */
    public Double getTaxAssets() {
        return taxAssets;
    }

    /**
     * taxAssets
     * <p>
     * 
     * 
     */
    public void setTaxAssets(Double taxAssets) {
        this.taxAssets = taxAssets;
    }

    /**
     * incomeTaxExpense
     * <p>
     * 
     * 
     */
    public Double getIncomeTaxExpense() {
        return incomeTaxExpense;
    }

    /**
     * incomeTaxExpense
     * <p>
     * 
     * 
     */
    public void setIncomeTaxExpense(Double incomeTaxExpense) {
        this.incomeTaxExpense = incomeTaxExpense;
    }

    /**
     * taxLiabilities
     * <p>
     * 
     * 
     */
    public Double getTaxLiabilities() {
        return taxLiabilities;
    }

    /**
     * taxLiabilities
     * <p>
     * 
     * 
     */
    public void setTaxLiabilities(Double taxLiabilities) {
        this.taxLiabilities = taxLiabilities;
    }

    /**
     * tangibleAssetsBookValuePerShare
     * <p>
     * 
     * 
     */
    public Double getTangibleAssetsBookValuePerShare() {
        return tangibleAssetsBookValuePerShare;
    }

    /**
     * tangibleAssetsBookValuePerShare
     * <p>
     * 
     * 
     */
    public void setTangibleAssetsBookValuePerShare(Double tangibleAssetsBookValuePerShare) {
        this.tangibleAssetsBookValuePerShare = tangibleAssetsBookValuePerShare;
    }

    /**
     * workingCapital
     * <p>
     * 
     * 
     */
    public Double getWorkingCapital() {
        return workingCapital;
    }

    /**
     * workingCapital
     * <p>
     * 
     * 
     */
    public void setWorkingCapital(Double workingCapital) {
        this.workingCapital = workingCapital;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StockFinancials.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ticker");
        sb.append('=');
        sb.append(((this.ticker == null)?"<null>":this.ticker));
        sb.append(',');
        sb.append("period");
        sb.append('=');
        sb.append(((this.period == null)?"<null>":this.period));
        sb.append(',');
        sb.append("calendarDate");
        sb.append('=');
        sb.append(((this.calendarDate == null)?"<null>":this.calendarDate));
        sb.append(',');
        sb.append("reportPeriod");
        sb.append('=');
        sb.append(((this.reportPeriod == null)?"<null>":this.reportPeriod));
        sb.append(',');
        sb.append("updated");
        sb.append('=');
        sb.append(((this.updated == null)?"<null>":this.updated));
        sb.append(',');
        sb.append("accumulatedOtherComprehensiveIncome");
        sb.append('=');
        sb.append(((this.accumulatedOtherComprehensiveIncome == null)?"<null>":this.accumulatedOtherComprehensiveIncome));
        sb.append(',');
        sb.append("assets");
        sb.append('=');
        sb.append(((this.assets == null)?"<null>":this.assets));
        sb.append(',');
        sb.append("assetsAverage");
        sb.append('=');
        sb.append(((this.assetsAverage == null)?"<null>":this.assetsAverage));
        sb.append(',');
        sb.append("assetsCurrent");
        sb.append('=');
        sb.append(((this.assetsCurrent == null)?"<null>":this.assetsCurrent));
        sb.append(',');
        sb.append("assetTurnover");
        sb.append('=');
        sb.append(((this.assetTurnover == null)?"<null>":this.assetTurnover));
        sb.append(',');
        sb.append("assetsNonCurrent");
        sb.append('=');
        sb.append(((this.assetsNonCurrent == null)?"<null>":this.assetsNonCurrent));
        sb.append(',');
        sb.append("bookValuePerShare");
        sb.append('=');
        sb.append(((this.bookValuePerShare == null)?"<null>":this.bookValuePerShare));
        sb.append(',');
        sb.append("capitalExpenditure");
        sb.append('=');
        sb.append(((this.capitalExpenditure == null)?"<null>":this.capitalExpenditure));
        sb.append(',');
        sb.append("cashAndEquivalents");
        sb.append('=');
        sb.append(((this.cashAndEquivalents == null)?"<null>":this.cashAndEquivalents));
        sb.append(',');
        sb.append("cashAndEquivalentsUSD");
        sb.append('=');
        sb.append(((this.cashAndEquivalentsUSD == null)?"<null>":this.cashAndEquivalentsUSD));
        sb.append(',');
        sb.append("costOfRevenue");
        sb.append('=');
        sb.append(((this.costOfRevenue == null)?"<null>":this.costOfRevenue));
        sb.append(',');
        sb.append("consolidatedIncome");
        sb.append('=');
        sb.append(((this.consolidatedIncome == null)?"<null>":this.consolidatedIncome));
        sb.append(',');
        sb.append("currentRatio");
        sb.append('=');
        sb.append(((this.currentRatio == null)?"<null>":this.currentRatio));
        sb.append(',');
        sb.append("debtToEquityRatio");
        sb.append('=');
        sb.append(((this.debtToEquityRatio == null)?"<null>":this.debtToEquityRatio));
        sb.append(',');
        sb.append("debt");
        sb.append('=');
        sb.append(((this.debt == null)?"<null>":this.debt));
        sb.append(',');
        sb.append("debtCurrent");
        sb.append('=');
        sb.append(((this.debtCurrent == null)?"<null>":this.debtCurrent));
        sb.append(',');
        sb.append("debtNonCurrent");
        sb.append('=');
        sb.append(((this.debtNonCurrent == null)?"<null>":this.debtNonCurrent));
        sb.append(',');
        sb.append("debtUSD");
        sb.append('=');
        sb.append(((this.debtUSD == null)?"<null>":this.debtUSD));
        sb.append(',');
        sb.append("deferredRevenue");
        sb.append('=');
        sb.append(((this.deferredRevenue == null)?"<null>":this.deferredRevenue));
        sb.append(',');
        sb.append("depreciationAmortizationAndAccretion");
        sb.append('=');
        sb.append(((this.depreciationAmortizationAndAccretion == null)?"<null>":this.depreciationAmortizationAndAccretion));
        sb.append(',');
        sb.append("deposits");
        sb.append('=');
        sb.append(((this.deposits == null)?"<null>":this.deposits));
        sb.append(',');
        sb.append("dividendYield");
        sb.append('=');
        sb.append(((this.dividendYield == null)?"<null>":this.dividendYield));
        sb.append(',');
        sb.append("dividendsPerBasicCommonShare");
        sb.append('=');
        sb.append(((this.dividendsPerBasicCommonShare == null)?"<null>":this.dividendsPerBasicCommonShare));
        sb.append(',');
        sb.append("earningBeforeInterestTaxes");
        sb.append('=');
        sb.append(((this.earningBeforeInterestTaxes == null)?"<null>":this.earningBeforeInterestTaxes));
        sb.append(',');
        sb.append("earningsBeforeInterestTaxesDepreciationAmortization");
        sb.append('=');
        sb.append(((this.earningsBeforeInterestTaxesDepreciationAmortization == null)?"<null>":this.earningsBeforeInterestTaxesDepreciationAmortization));
        sb.append(',');
        sb.append("eBITDAMargin");
        sb.append('=');
        sb.append(((this.eBITDAMargin == null)?"<null>":this.eBITDAMargin));
        sb.append(',');
        sb.append("earningsBeforeInterestTaxesDepreciationAmortizationUSD");
        sb.append('=');
        sb.append(((this.earningsBeforeInterestTaxesDepreciationAmortizationUSD == null)?"<null>":this.earningsBeforeInterestTaxesDepreciationAmortizationUSD));
        sb.append(',');
        sb.append("earningBeforeInterestTaxesUSD");
        sb.append('=');
        sb.append(((this.earningBeforeInterestTaxesUSD == null)?"<null>":this.earningBeforeInterestTaxesUSD));
        sb.append(',');
        sb.append("earningsBeforeTax");
        sb.append('=');
        sb.append(((this.earningsBeforeTax == null)?"<null>":this.earningsBeforeTax));
        sb.append(',');
        sb.append("earningsPerBasicShare");
        sb.append('=');
        sb.append(((this.earningsPerBasicShare == null)?"<null>":this.earningsPerBasicShare));
        sb.append(',');
        sb.append("earningsPerDilutedShare");
        sb.append('=');
        sb.append(((this.earningsPerDilutedShare == null)?"<null>":this.earningsPerDilutedShare));
        sb.append(',');
        sb.append("earningsPerBasicShareUSD");
        sb.append('=');
        sb.append(((this.earningsPerBasicShareUSD == null)?"<null>":this.earningsPerBasicShareUSD));
        sb.append(',');
        sb.append("shareholdersEquity");
        sb.append('=');
        sb.append(((this.shareholdersEquity == null)?"<null>":this.shareholdersEquity));
        sb.append(',');
        sb.append("averageEquity");
        sb.append('=');
        sb.append(((this.averageEquity == null)?"<null>":this.averageEquity));
        sb.append(',');
        sb.append("shareholdersEquityUSD");
        sb.append('=');
        sb.append(((this.shareholdersEquityUSD == null)?"<null>":this.shareholdersEquityUSD));
        sb.append(',');
        sb.append("enterpriseValue");
        sb.append('=');
        sb.append(((this.enterpriseValue == null)?"<null>":this.enterpriseValue));
        sb.append(',');
        sb.append("enterpriseValueOverEBIT");
        sb.append('=');
        sb.append(((this.enterpriseValueOverEBIT == null)?"<null>":this.enterpriseValueOverEBIT));
        sb.append(',');
        sb.append("enterpriseValueOverEBITDA");
        sb.append('=');
        sb.append(((this.enterpriseValueOverEBITDA == null)?"<null>":this.enterpriseValueOverEBITDA));
        sb.append(',');
        sb.append("freeCashFlow");
        sb.append('=');
        sb.append(((this.freeCashFlow == null)?"<null>":this.freeCashFlow));
        sb.append(',');
        sb.append("freeCashFlowPerShare");
        sb.append('=');
        sb.append(((this.freeCashFlowPerShare == null)?"<null>":this.freeCashFlowPerShare));
        sb.append(',');
        sb.append("foreignCurrencyUSDExchangeRate");
        sb.append('=');
        sb.append(((this.foreignCurrencyUSDExchangeRate == null)?"<null>":this.foreignCurrencyUSDExchangeRate));
        sb.append(',');
        sb.append("grossProfit");
        sb.append('=');
        sb.append(((this.grossProfit == null)?"<null>":this.grossProfit));
        sb.append(',');
        sb.append("grossMargin");
        sb.append('=');
        sb.append(((this.grossMargin == null)?"<null>":this.grossMargin));
        sb.append(',');
        sb.append("goodwillAndIntangibleAssets");
        sb.append('=');
        sb.append(((this.goodwillAndIntangibleAssets == null)?"<null>":this.goodwillAndIntangibleAssets));
        sb.append(',');
        sb.append("interestExpense");
        sb.append('=');
        sb.append(((this.interestExpense == null)?"<null>":this.interestExpense));
        sb.append(',');
        sb.append("investedCapital");
        sb.append('=');
        sb.append(((this.investedCapital == null)?"<null>":this.investedCapital));
        sb.append(',');
        sb.append("investedCapitalAverage");
        sb.append('=');
        sb.append(((this.investedCapitalAverage == null)?"<null>":this.investedCapitalAverage));
        sb.append(',');
        sb.append("inventory");
        sb.append('=');
        sb.append(((this.inventory == null)?"<null>":this.inventory));
        sb.append(',');
        sb.append("investments");
        sb.append('=');
        sb.append(((this.investments == null)?"<null>":this.investments));
        sb.append(',');
        sb.append("investmentsCurrent");
        sb.append('=');
        sb.append(((this.investmentsCurrent == null)?"<null>":this.investmentsCurrent));
        sb.append(',');
        sb.append("investmentsNonCurrent");
        sb.append('=');
        sb.append(((this.investmentsNonCurrent == null)?"<null>":this.investmentsNonCurrent));
        sb.append(',');
        sb.append("totalLiabilities");
        sb.append('=');
        sb.append(((this.totalLiabilities == null)?"<null>":this.totalLiabilities));
        sb.append(',');
        sb.append("currentLiabilities");
        sb.append('=');
        sb.append(((this.currentLiabilities == null)?"<null>":this.currentLiabilities));
        sb.append(',');
        sb.append("liabilitiesNonCurrent");
        sb.append('=');
        sb.append(((this.liabilitiesNonCurrent == null)?"<null>":this.liabilitiesNonCurrent));
        sb.append(',');
        sb.append("marketCapitalization");
        sb.append('=');
        sb.append(((this.marketCapitalization == null)?"<null>":this.marketCapitalization));
        sb.append(',');
        sb.append("netCashFlow");
        sb.append('=');
        sb.append(((this.netCashFlow == null)?"<null>":this.netCashFlow));
        sb.append(',');
        sb.append("netCashFlowBusinessAcquisitionsDisposals");
        sb.append('=');
        sb.append(((this.netCashFlowBusinessAcquisitionsDisposals == null)?"<null>":this.netCashFlowBusinessAcquisitionsDisposals));
        sb.append(',');
        sb.append("issuanceEquityShares");
        sb.append('=');
        sb.append(((this.issuanceEquityShares == null)?"<null>":this.issuanceEquityShares));
        sb.append(',');
        sb.append("issuanceDebtSecurities");
        sb.append('=');
        sb.append(((this.issuanceDebtSecurities == null)?"<null>":this.issuanceDebtSecurities));
        sb.append(',');
        sb.append("paymentDividendsOtherCashDistributions");
        sb.append('=');
        sb.append(((this.paymentDividendsOtherCashDistributions == null)?"<null>":this.paymentDividendsOtherCashDistributions));
        sb.append(',');
        sb.append("netCashFlowFromFinancing");
        sb.append('=');
        sb.append(((this.netCashFlowFromFinancing == null)?"<null>":this.netCashFlowFromFinancing));
        sb.append(',');
        sb.append("netCashFlowFromInvesting");
        sb.append('=');
        sb.append(((this.netCashFlowFromInvesting == null)?"<null>":this.netCashFlowFromInvesting));
        sb.append(',');
        sb.append("netCashFlowInvestmentAcquisitionsDisposals");
        sb.append('=');
        sb.append(((this.netCashFlowInvestmentAcquisitionsDisposals == null)?"<null>":this.netCashFlowInvestmentAcquisitionsDisposals));
        sb.append(',');
        sb.append("netCashFlowFromOperations");
        sb.append('=');
        sb.append(((this.netCashFlowFromOperations == null)?"<null>":this.netCashFlowFromOperations));
        sb.append(',');
        sb.append("effectOfExchangeRateChangesOnCash");
        sb.append('=');
        sb.append(((this.effectOfExchangeRateChangesOnCash == null)?"<null>":this.effectOfExchangeRateChangesOnCash));
        sb.append(',');
        sb.append("netIncome");
        sb.append('=');
        sb.append(((this.netIncome == null)?"<null>":this.netIncome));
        sb.append(',');
        sb.append("netIncomeCommonStock");
        sb.append('=');
        sb.append(((this.netIncomeCommonStock == null)?"<null>":this.netIncomeCommonStock));
        sb.append(',');
        sb.append("netIncomeCommonStockUSD");
        sb.append('=');
        sb.append(((this.netIncomeCommonStockUSD == null)?"<null>":this.netIncomeCommonStockUSD));
        sb.append(',');
        sb.append("netLossIncomeFromDiscontinuedOperations");
        sb.append('=');
        sb.append(((this.netLossIncomeFromDiscontinuedOperations == null)?"<null>":this.netLossIncomeFromDiscontinuedOperations));
        sb.append(',');
        sb.append("netIncomeToNonControllingInterests");
        sb.append('=');
        sb.append(((this.netIncomeToNonControllingInterests == null)?"<null>":this.netIncomeToNonControllingInterests));
        sb.append(',');
        sb.append("profitMargin");
        sb.append('=');
        sb.append(((this.profitMargin == null)?"<null>":this.profitMargin));
        sb.append(',');
        sb.append("operatingExpenses");
        sb.append('=');
        sb.append(((this.operatingExpenses == null)?"<null>":this.operatingExpenses));
        sb.append(',');
        sb.append("operatingIncome");
        sb.append('=');
        sb.append(((this.operatingIncome == null)?"<null>":this.operatingIncome));
        sb.append(',');
        sb.append("tradeAndNonTradePayables");
        sb.append('=');
        sb.append(((this.tradeAndNonTradePayables == null)?"<null>":this.tradeAndNonTradePayables));
        sb.append(',');
        sb.append("payoutRatio");
        sb.append('=');
        sb.append(((this.payoutRatio == null)?"<null>":this.payoutRatio));
        sb.append(',');
        sb.append("priceToBookValue");
        sb.append('=');
        sb.append(((this.priceToBookValue == null)?"<null>":this.priceToBookValue));
        sb.append(',');
        sb.append("priceEarnings");
        sb.append('=');
        sb.append(((this.priceEarnings == null)?"<null>":this.priceEarnings));
        sb.append(',');
        sb.append("priceToEarningsRatio");
        sb.append('=');
        sb.append(((this.priceToEarningsRatio == null)?"<null>":this.priceToEarningsRatio));
        sb.append(',');
        sb.append("propertyPlantEquipmentNet");
        sb.append('=');
        sb.append(((this.propertyPlantEquipmentNet == null)?"<null>":this.propertyPlantEquipmentNet));
        sb.append(',');
        sb.append("preferredDividendsIncomeStatementImpact");
        sb.append('=');
        sb.append(((this.preferredDividendsIncomeStatementImpact == null)?"<null>":this.preferredDividendsIncomeStatementImpact));
        sb.append(',');
        sb.append("sharePriceAdjustedClose");
        sb.append('=');
        sb.append(((this.sharePriceAdjustedClose == null)?"<null>":this.sharePriceAdjustedClose));
        sb.append(',');
        sb.append("priceSales");
        sb.append('=');
        sb.append(((this.priceSales == null)?"<null>":this.priceSales));
        sb.append(',');
        sb.append("priceToSalesRatio");
        sb.append('=');
        sb.append(((this.priceToSalesRatio == null)?"<null>":this.priceToSalesRatio));
        sb.append(',');
        sb.append("tradeAndNonTradeReceivables");
        sb.append('=');
        sb.append(((this.tradeAndNonTradeReceivables == null)?"<null>":this.tradeAndNonTradeReceivables));
        sb.append(',');
        sb.append("accumulatedRetainedEarningsDeficit");
        sb.append('=');
        sb.append(((this.accumulatedRetainedEarningsDeficit == null)?"<null>":this.accumulatedRetainedEarningsDeficit));
        sb.append(',');
        sb.append("revenues");
        sb.append('=');
        sb.append(((this.revenues == null)?"<null>":this.revenues));
        sb.append(',');
        sb.append("revenuesUSD");
        sb.append('=');
        sb.append(((this.revenuesUSD == null)?"<null>":this.revenuesUSD));
        sb.append(',');
        sb.append("researchAndDevelopmentExpense");
        sb.append('=');
        sb.append(((this.researchAndDevelopmentExpense == null)?"<null>":this.researchAndDevelopmentExpense));
        sb.append(',');
        sb.append("returnOnAverageAssets");
        sb.append('=');
        sb.append(((this.returnOnAverageAssets == null)?"<null>":this.returnOnAverageAssets));
        sb.append(',');
        sb.append("returnOnAverageEquity");
        sb.append('=');
        sb.append(((this.returnOnAverageEquity == null)?"<null>":this.returnOnAverageEquity));
        sb.append(',');
        sb.append("returnOnInvestedCapital");
        sb.append('=');
        sb.append(((this.returnOnInvestedCapital == null)?"<null>":this.returnOnInvestedCapital));
        sb.append(',');
        sb.append("returnOnSales");
        sb.append('=');
        sb.append(((this.returnOnSales == null)?"<null>":this.returnOnSales));
        sb.append(',');
        sb.append("shareBasedCompensation");
        sb.append('=');
        sb.append(((this.shareBasedCompensation == null)?"<null>":this.shareBasedCompensation));
        sb.append(',');
        sb.append("sellingGeneralAndAdministrativeExpense");
        sb.append('=');
        sb.append(((this.sellingGeneralAndAdministrativeExpense == null)?"<null>":this.sellingGeneralAndAdministrativeExpense));
        sb.append(',');
        sb.append("shareFactor");
        sb.append('=');
        sb.append(((this.shareFactor == null)?"<null>":this.shareFactor));
        sb.append(',');
        sb.append("shares");
        sb.append('=');
        sb.append(((this.shares == null)?"<null>":this.shares));
        sb.append(',');
        sb.append("weightedAverageShares");
        sb.append('=');
        sb.append(((this.weightedAverageShares == null)?"<null>":this.weightedAverageShares));
        sb.append(',');
        sb.append("weightedAverageSharesDiluted");
        sb.append('=');
        sb.append(((this.weightedAverageSharesDiluted == null)?"<null>":this.weightedAverageSharesDiluted));
        sb.append(',');
        sb.append("salesPerShare");
        sb.append('=');
        sb.append(((this.salesPerShare == null)?"<null>":this.salesPerShare));
        sb.append(',');
        sb.append("tangibleAssetValue");
        sb.append('=');
        sb.append(((this.tangibleAssetValue == null)?"<null>":this.tangibleAssetValue));
        sb.append(',');
        sb.append("taxAssets");
        sb.append('=');
        sb.append(((this.taxAssets == null)?"<null>":this.taxAssets));
        sb.append(',');
        sb.append("incomeTaxExpense");
        sb.append('=');
        sb.append(((this.incomeTaxExpense == null)?"<null>":this.incomeTaxExpense));
        sb.append(',');
        sb.append("taxLiabilities");
        sb.append('=');
        sb.append(((this.taxLiabilities == null)?"<null>":this.taxLiabilities));
        sb.append(',');
        sb.append("tangibleAssetsBookValuePerShare");
        sb.append('=');
        sb.append(((this.tangibleAssetsBookValuePerShare == null)?"<null>":this.tangibleAssetsBookValuePerShare));
        sb.append(',');
        sb.append("workingCapital");
        sb.append('=');
        sb.append(((this.workingCapital == null)?"<null>":this.workingCapital));
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
        result = ((result* 31)+((this.earningsPerBasicShareUSD == null)? 0 :this.earningsPerBasicShareUSD.hashCode()));
        result = ((result* 31)+((this.returnOnAverageAssets == null)? 0 :this.returnOnAverageAssets.hashCode()));
        result = ((result* 31)+((this.dividendYield == null)? 0 :this.dividendYield.hashCode()));
        result = ((result* 31)+((this.reportPeriod == null)? 0 :this.reportPeriod.hashCode()));
        result = ((result* 31)+((this.bookValuePerShare == null)? 0 :this.bookValuePerShare.hashCode()));
        result = ((result* 31)+((this.debtUSD == null)? 0 :this.debtUSD.hashCode()));
        result = ((result* 31)+((this.cashAndEquivalentsUSD == null)? 0 :this.cashAndEquivalentsUSD.hashCode()));
        result = ((result* 31)+((this.tradeAndNonTradePayables == null)? 0 :this.tradeAndNonTradePayables.hashCode()));
        result = ((result* 31)+((this.earningBeforeInterestTaxesUSD == null)? 0 :this.earningBeforeInterestTaxesUSD.hashCode()));
        result = ((result* 31)+((this.netIncomeCommonStockUSD == null)? 0 :this.netIncomeCommonStockUSD.hashCode()));
        result = ((result* 31)+((this.accumulatedRetainedEarningsDeficit == null)? 0 :this.accumulatedRetainedEarningsDeficit.hashCode()));
        result = ((result* 31)+((this.freeCashFlowPerShare == null)? 0 :this.freeCashFlowPerShare.hashCode()));
        result = ((result* 31)+((this.investedCapitalAverage == null)? 0 :this.investedCapitalAverage.hashCode()));
        result = ((result* 31)+((this.period == null)? 0 :this.period.hashCode()));
        result = ((result* 31)+((this.enterpriseValueOverEBIT == null)? 0 :this.enterpriseValueOverEBIT.hashCode()));
        result = ((result* 31)+((this.operatingExpenses == null)? 0 :this.operatingExpenses.hashCode()));
        result = ((result* 31)+((this.workingCapital == null)? 0 :this.workingCapital.hashCode()));
        result = ((result* 31)+((this.tradeAndNonTradeReceivables == null)? 0 :this.tradeAndNonTradeReceivables.hashCode()));
        result = ((result* 31)+((this.salesPerShare == null)? 0 :this.salesPerShare.hashCode()));
        result = ((result* 31)+((this.tangibleAssetsBookValuePerShare == null)? 0 :this.tangibleAssetsBookValuePerShare.hashCode()));
        result = ((result* 31)+((this.netCashFlow == null)? 0 :this.netCashFlow.hashCode()));
        result = ((result* 31)+((this.netCashFlowFromFinancing == null)? 0 :this.netCashFlowFromFinancing.hashCode()));
        result = ((result* 31)+((this.shareBasedCompensation == null)? 0 :this.shareBasedCompensation.hashCode()));
        result = ((result* 31)+((this.shareholdersEquityUSD == null)? 0 :this.shareholdersEquityUSD.hashCode()));
        result = ((result* 31)+((this.shareholdersEquity == null)? 0 :this.shareholdersEquity.hashCode()));
        result = ((result* 31)+((this.enterpriseValue == null)? 0 :this.enterpriseValue.hashCode()));
        result = ((result* 31)+((this.returnOnSales == null)? 0 :this.returnOnSales.hashCode()));
        result = ((result* 31)+((this.averageEquity == null)? 0 :this.averageEquity.hashCode()));
        result = ((result* 31)+((this.priceEarnings == null)? 0 :this.priceEarnings.hashCode()));
        result = ((result* 31)+((this.interestExpense == null)? 0 :this.interestExpense.hashCode()));
        result = ((result* 31)+((this.investments == null)? 0 :this.investments.hashCode()));
        result = ((result* 31)+((this.issuanceDebtSecurities == null)? 0 :this.issuanceDebtSecurities.hashCode()));
        result = ((result* 31)+((this.tangibleAssetValue == null)? 0 :this.tangibleAssetValue.hashCode()));
        result = ((result* 31)+((this.effectOfExchangeRateChangesOnCash == null)? 0 :this.effectOfExchangeRateChangesOnCash.hashCode()));
        result = ((result* 31)+((this.shares == null)? 0 :this.shares.hashCode()));
        result = ((result* 31)+((this.freeCashFlow == null)? 0 :this.freeCashFlow.hashCode()));
        result = ((result* 31)+((this.sellingGeneralAndAdministrativeExpense == null)? 0 :this.sellingGeneralAndAdministrativeExpense.hashCode()));
        result = ((result* 31)+((this.assetsNonCurrent == null)? 0 :this.assetsNonCurrent.hashCode()));
        result = ((result* 31)+((this.profitMargin == null)? 0 :this.profitMargin.hashCode()));
        result = ((result* 31)+((this.depreciationAmortizationAndAccretion == null)? 0 :this.depreciationAmortizationAndAccretion.hashCode()));
        result = ((result* 31)+((this.eBITDAMargin == null)? 0 :this.eBITDAMargin.hashCode()));
        result = ((result* 31)+((this.priceSales == null)? 0 :this.priceSales.hashCode()));
        result = ((result* 31)+((this.earningsBeforeInterestTaxesDepreciationAmortizationUSD == null)? 0 :this.earningsBeforeInterestTaxesDepreciationAmortizationUSD.hashCode()));
        result = ((result* 31)+((this.grossProfit == null)? 0 :this.grossProfit.hashCode()));
        result = ((result* 31)+((this.netIncomeToNonControllingInterests == null)? 0 :this.netIncomeToNonControllingInterests.hashCode()));
        result = ((result* 31)+((this.investmentsNonCurrent == null)? 0 :this.investmentsNonCurrent.hashCode()));
        result = ((result* 31)+((this.paymentDividendsOtherCashDistributions == null)? 0 :this.paymentDividendsOtherCashDistributions.hashCode()));
        result = ((result* 31)+((this.operatingIncome == null)? 0 :this.operatingIncome.hashCode()));
        result = ((result* 31)+((this.preferredDividendsIncomeStatementImpact == null)? 0 :this.preferredDividendsIncomeStatementImpact.hashCode()));
        result = ((result* 31)+((this.debtToEquityRatio == null)? 0 :this.debtToEquityRatio.hashCode()));
        result = ((result* 31)+((this.issuanceEquityShares == null)? 0 :this.issuanceEquityShares.hashCode()));
        result = ((result* 31)+((this.accumulatedOtherComprehensiveIncome == null)? 0 :this.accumulatedOtherComprehensiveIncome.hashCode()));
        result = ((result* 31)+((this.earningsPerDilutedShare == null)? 0 :this.earningsPerDilutedShare.hashCode()));
        result = ((result* 31)+((this.netCashFlowFromOperations == null)? 0 :this.netCashFlowFromOperations.hashCode()));
        result = ((result* 31)+((this.liabilitiesNonCurrent == null)? 0 :this.liabilitiesNonCurrent.hashCode()));
        result = ((result* 31)+((this.earningsBeforeTax == null)? 0 :this.earningsBeforeTax.hashCode()));
        result = ((result* 31)+((this.priceToBookValue == null)? 0 :this.priceToBookValue.hashCode()));
        result = ((result* 31)+((this.returnOnInvestedCapital == null)? 0 :this.returnOnInvestedCapital.hashCode()));
        result = ((result* 31)+((this.enterpriseValueOverEBITDA == null)? 0 :this.enterpriseValueOverEBITDA.hashCode()));
        result = ((result* 31)+((this.netIncomeCommonStock == null)? 0 :this.netIncomeCommonStock.hashCode()));
        result = ((result* 31)+((this.taxLiabilities == null)? 0 :this.taxLiabilities.hashCode()));
        result = ((result* 31)+((this.debtCurrent == null)? 0 :this.debtCurrent.hashCode()));
        result = ((result* 31)+((this.netCashFlowInvestmentAcquisitionsDisposals == null)? 0 :this.netCashFlowInvestmentAcquisitionsDisposals.hashCode()));
        result = ((result* 31)+((this.priceToSalesRatio == null)? 0 :this.priceToSalesRatio.hashCode()));
        result = ((result* 31)+((this.propertyPlantEquipmentNet == null)? 0 :this.propertyPlantEquipmentNet.hashCode()));
        result = ((result* 31)+((this.assets == null)? 0 :this.assets.hashCode()));
        result = ((result* 31)+((this.investedCapital == null)? 0 :this.investedCapital.hashCode()));
        result = ((result* 31)+((this.returnOnAverageEquity == null)? 0 :this.returnOnAverageEquity.hashCode()));
        result = ((result* 31)+((this.shareFactor == null)? 0 :this.shareFactor.hashCode()));
        result = ((result* 31)+((this.revenuesUSD == null)? 0 :this.revenuesUSD.hashCode()));
        result = ((result* 31)+((this.earningsPerBasicShare == null)? 0 :this.earningsPerBasicShare.hashCode()));
        result = ((result* 31)+((this.marketCapitalization == null)? 0 :this.marketCapitalization.hashCode()));
        result = ((result* 31)+((this.foreignCurrencyUSDExchangeRate == null)? 0 :this.foreignCurrencyUSDExchangeRate.hashCode()));
        result = ((result* 31)+((this.totalLiabilities == null)? 0 :this.totalLiabilities.hashCode()));
        result = ((result* 31)+((this.costOfRevenue == null)? 0 :this.costOfRevenue.hashCode()));
        result = ((result* 31)+((this.earningsBeforeInterestTaxesDepreciationAmortization == null)? 0 :this.earningsBeforeInterestTaxesDepreciationAmortization.hashCode()));
        result = ((result* 31)+((this.deposits == null)? 0 :this.deposits.hashCode()));
        result = ((result* 31)+((this.netLossIncomeFromDiscontinuedOperations == null)? 0 :this.netLossIncomeFromDiscontinuedOperations.hashCode()));
        result = ((result* 31)+((this.taxAssets == null)? 0 :this.taxAssets.hashCode()));
        result = ((result* 31)+((this.payoutRatio == null)? 0 :this.payoutRatio.hashCode()));
        result = ((result* 31)+((this.researchAndDevelopmentExpense == null)? 0 :this.researchAndDevelopmentExpense.hashCode()));
        result = ((result* 31)+((this.updated == null)? 0 :this.updated.hashCode()));
        result = ((result* 31)+((this.debt == null)? 0 :this.debt.hashCode()));
        result = ((result* 31)+((this.assetsCurrent == null)? 0 :this.assetsCurrent.hashCode()));
        result = ((result* 31)+((this.earningBeforeInterestTaxes == null)? 0 :this.earningBeforeInterestTaxes.hashCode()));
        result = ((result* 31)+((this.investmentsCurrent == null)? 0 :this.investmentsCurrent.hashCode()));
        result = ((result* 31)+((this.weightedAverageShares == null)? 0 :this.weightedAverageShares.hashCode()));
        result = ((result* 31)+((this.goodwillAndIntangibleAssets == null)? 0 :this.goodwillAndIntangibleAssets.hashCode()));
        result = ((result* 31)+((this.inventory == null)? 0 :this.inventory.hashCode()));
        result = ((result* 31)+((this.netCashFlowBusinessAcquisitionsDisposals == null)? 0 :this.netCashFlowBusinessAcquisitionsDisposals.hashCode()));
        result = ((result* 31)+((this.priceToEarningsRatio == null)? 0 :this.priceToEarningsRatio.hashCode()));
        result = ((result* 31)+((this.currentLiabilities == null)? 0 :this.currentLiabilities.hashCode()));
        result = ((result* 31)+((this.weightedAverageSharesDiluted == null)? 0 :this.weightedAverageSharesDiluted.hashCode()));
        result = ((result* 31)+((this.consolidatedIncome == null)? 0 :this.consolidatedIncome.hashCode()));
        result = ((result* 31)+((this.currentRatio == null)? 0 :this.currentRatio.hashCode()));
        result = ((result* 31)+((this.capitalExpenditure == null)? 0 :this.capitalExpenditure.hashCode()));
        result = ((result* 31)+((this.ticker == null)? 0 :this.ticker.hashCode()));
        result = ((result* 31)+((this.dividendsPerBasicCommonShare == null)? 0 :this.dividendsPerBasicCommonShare.hashCode()));
        result = ((result* 31)+((this.revenues == null)? 0 :this.revenues.hashCode()));
        result = ((result* 31)+((this.cashAndEquivalents == null)? 0 :this.cashAndEquivalents.hashCode()));
        result = ((result* 31)+((this.assetTurnover == null)? 0 :this.assetTurnover.hashCode()));
        result = ((result* 31)+((this.sharePriceAdjustedClose == null)? 0 :this.sharePriceAdjustedClose.hashCode()));
        result = ((result* 31)+((this.deferredRevenue == null)? 0 :this.deferredRevenue.hashCode()));
        result = ((result* 31)+((this.debtNonCurrent == null)? 0 :this.debtNonCurrent.hashCode()));
        result = ((result* 31)+((this.netCashFlowFromInvesting == null)? 0 :this.netCashFlowFromInvesting.hashCode()));
        result = ((result* 31)+((this.netIncome == null)? 0 :this.netIncome.hashCode()));
        result = ((result* 31)+((this.incomeTaxExpense == null)? 0 :this.incomeTaxExpense.hashCode()));
        result = ((result* 31)+((this.grossMargin == null)? 0 :this.grossMargin.hashCode()));
        result = ((result* 31)+((this.assetsAverage == null)? 0 :this.assetsAverage.hashCode()));
        result = ((result* 31)+((this.calendarDate == null)? 0 :this.calendarDate.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StockFinancials) == false) {
            return false;
        }
        StockFinancials rhs = ((StockFinancials) other);
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.earningsPerBasicShareUSD == rhs.earningsPerBasicShareUSD)||((this.earningsPerBasicShareUSD!= null)&&this.earningsPerBasicShareUSD.equals(rhs.earningsPerBasicShareUSD)))&&((this.returnOnAverageAssets == rhs.returnOnAverageAssets)||((this.returnOnAverageAssets!= null)&&this.returnOnAverageAssets.equals(rhs.returnOnAverageAssets))))&&((this.dividendYield == rhs.dividendYield)||((this.dividendYield!= null)&&this.dividendYield.equals(rhs.dividendYield))))&&((this.reportPeriod == rhs.reportPeriod)||((this.reportPeriod!= null)&&this.reportPeriod.equals(rhs.reportPeriod))))&&((this.bookValuePerShare == rhs.bookValuePerShare)||((this.bookValuePerShare!= null)&&this.bookValuePerShare.equals(rhs.bookValuePerShare))))&&((this.debtUSD == rhs.debtUSD)||((this.debtUSD!= null)&&this.debtUSD.equals(rhs.debtUSD))))&&((this.cashAndEquivalentsUSD == rhs.cashAndEquivalentsUSD)||((this.cashAndEquivalentsUSD!= null)&&this.cashAndEquivalentsUSD.equals(rhs.cashAndEquivalentsUSD))))&&((this.tradeAndNonTradePayables == rhs.tradeAndNonTradePayables)||((this.tradeAndNonTradePayables!= null)&&this.tradeAndNonTradePayables.equals(rhs.tradeAndNonTradePayables))))&&((this.earningBeforeInterestTaxesUSD == rhs.earningBeforeInterestTaxesUSD)||((this.earningBeforeInterestTaxesUSD!= null)&&this.earningBeforeInterestTaxesUSD.equals(rhs.earningBeforeInterestTaxesUSD))))&&((this.netIncomeCommonStockUSD == rhs.netIncomeCommonStockUSD)||((this.netIncomeCommonStockUSD!= null)&&this.netIncomeCommonStockUSD.equals(rhs.netIncomeCommonStockUSD))))&&((this.accumulatedRetainedEarningsDeficit == rhs.accumulatedRetainedEarningsDeficit)||((this.accumulatedRetainedEarningsDeficit!= null)&&this.accumulatedRetainedEarningsDeficit.equals(rhs.accumulatedRetainedEarningsDeficit))))&&((this.freeCashFlowPerShare == rhs.freeCashFlowPerShare)||((this.freeCashFlowPerShare!= null)&&this.freeCashFlowPerShare.equals(rhs.freeCashFlowPerShare))))&&((this.investedCapitalAverage == rhs.investedCapitalAverage)||((this.investedCapitalAverage!= null)&&this.investedCapitalAverage.equals(rhs.investedCapitalAverage))))&&((this.period == rhs.period)||((this.period!= null)&&this.period.equals(rhs.period))))&&((this.enterpriseValueOverEBIT == rhs.enterpriseValueOverEBIT)||((this.enterpriseValueOverEBIT!= null)&&this.enterpriseValueOverEBIT.equals(rhs.enterpriseValueOverEBIT))))&&((this.operatingExpenses == rhs.operatingExpenses)||((this.operatingExpenses!= null)&&this.operatingExpenses.equals(rhs.operatingExpenses))))&&((this.workingCapital == rhs.workingCapital)||((this.workingCapital!= null)&&this.workingCapital.equals(rhs.workingCapital))))&&((this.tradeAndNonTradeReceivables == rhs.tradeAndNonTradeReceivables)||((this.tradeAndNonTradeReceivables!= null)&&this.tradeAndNonTradeReceivables.equals(rhs.tradeAndNonTradeReceivables))))&&((this.salesPerShare == rhs.salesPerShare)||((this.salesPerShare!= null)&&this.salesPerShare.equals(rhs.salesPerShare))))&&((this.tangibleAssetsBookValuePerShare == rhs.tangibleAssetsBookValuePerShare)||((this.tangibleAssetsBookValuePerShare!= null)&&this.tangibleAssetsBookValuePerShare.equals(rhs.tangibleAssetsBookValuePerShare))))&&((this.netCashFlow == rhs.netCashFlow)||((this.netCashFlow!= null)&&this.netCashFlow.equals(rhs.netCashFlow))))&&((this.netCashFlowFromFinancing == rhs.netCashFlowFromFinancing)||((this.netCashFlowFromFinancing!= null)&&this.netCashFlowFromFinancing.equals(rhs.netCashFlowFromFinancing))))&&((this.shareBasedCompensation == rhs.shareBasedCompensation)||((this.shareBasedCompensation!= null)&&this.shareBasedCompensation.equals(rhs.shareBasedCompensation))))&&((this.shareholdersEquityUSD == rhs.shareholdersEquityUSD)||((this.shareholdersEquityUSD!= null)&&this.shareholdersEquityUSD.equals(rhs.shareholdersEquityUSD))))&&((this.shareholdersEquity == rhs.shareholdersEquity)||((this.shareholdersEquity!= null)&&this.shareholdersEquity.equals(rhs.shareholdersEquity))))&&((this.enterpriseValue == rhs.enterpriseValue)||((this.enterpriseValue!= null)&&this.enterpriseValue.equals(rhs.enterpriseValue))))&&((this.returnOnSales == rhs.returnOnSales)||((this.returnOnSales!= null)&&this.returnOnSales.equals(rhs.returnOnSales))))&&((this.averageEquity == rhs.averageEquity)||((this.averageEquity!= null)&&this.averageEquity.equals(rhs.averageEquity))))&&((this.priceEarnings == rhs.priceEarnings)||((this.priceEarnings!= null)&&this.priceEarnings.equals(rhs.priceEarnings))))&&((this.interestExpense == rhs.interestExpense)||((this.interestExpense!= null)&&this.interestExpense.equals(rhs.interestExpense))))&&((this.investments == rhs.investments)||((this.investments!= null)&&this.investments.equals(rhs.investments))))&&((this.issuanceDebtSecurities == rhs.issuanceDebtSecurities)||((this.issuanceDebtSecurities!= null)&&this.issuanceDebtSecurities.equals(rhs.issuanceDebtSecurities))))&&((this.tangibleAssetValue == rhs.tangibleAssetValue)||((this.tangibleAssetValue!= null)&&this.tangibleAssetValue.equals(rhs.tangibleAssetValue))))&&((this.effectOfExchangeRateChangesOnCash == rhs.effectOfExchangeRateChangesOnCash)||((this.effectOfExchangeRateChangesOnCash!= null)&&this.effectOfExchangeRateChangesOnCash.equals(rhs.effectOfExchangeRateChangesOnCash))))&&((this.shares == rhs.shares)||((this.shares!= null)&&this.shares.equals(rhs.shares))))&&((this.freeCashFlow == rhs.freeCashFlow)||((this.freeCashFlow!= null)&&this.freeCashFlow.equals(rhs.freeCashFlow))))&&((this.sellingGeneralAndAdministrativeExpense == rhs.sellingGeneralAndAdministrativeExpense)||((this.sellingGeneralAndAdministrativeExpense!= null)&&this.sellingGeneralAndAdministrativeExpense.equals(rhs.sellingGeneralAndAdministrativeExpense))))&&((this.assetsNonCurrent == rhs.assetsNonCurrent)||((this.assetsNonCurrent!= null)&&this.assetsNonCurrent.equals(rhs.assetsNonCurrent))))&&((this.profitMargin == rhs.profitMargin)||((this.profitMargin!= null)&&this.profitMargin.equals(rhs.profitMargin))))&&((this.depreciationAmortizationAndAccretion == rhs.depreciationAmortizationAndAccretion)||((this.depreciationAmortizationAndAccretion!= null)&&this.depreciationAmortizationAndAccretion.equals(rhs.depreciationAmortizationAndAccretion))))&&((this.eBITDAMargin == rhs.eBITDAMargin)||((this.eBITDAMargin!= null)&&this.eBITDAMargin.equals(rhs.eBITDAMargin))))&&((this.priceSales == rhs.priceSales)||((this.priceSales!= null)&&this.priceSales.equals(rhs.priceSales))))&&((this.earningsBeforeInterestTaxesDepreciationAmortizationUSD == rhs.earningsBeforeInterestTaxesDepreciationAmortizationUSD)||((this.earningsBeforeInterestTaxesDepreciationAmortizationUSD!= null)&&this.earningsBeforeInterestTaxesDepreciationAmortizationUSD.equals(rhs.earningsBeforeInterestTaxesDepreciationAmortizationUSD))))&&((this.grossProfit == rhs.grossProfit)||((this.grossProfit!= null)&&this.grossProfit.equals(rhs.grossProfit))))&&((this.netIncomeToNonControllingInterests == rhs.netIncomeToNonControllingInterests)||((this.netIncomeToNonControllingInterests!= null)&&this.netIncomeToNonControllingInterests.equals(rhs.netIncomeToNonControllingInterests))))&&((this.investmentsNonCurrent == rhs.investmentsNonCurrent)||((this.investmentsNonCurrent!= null)&&this.investmentsNonCurrent.equals(rhs.investmentsNonCurrent))))&&((this.paymentDividendsOtherCashDistributions == rhs.paymentDividendsOtherCashDistributions)||((this.paymentDividendsOtherCashDistributions!= null)&&this.paymentDividendsOtherCashDistributions.equals(rhs.paymentDividendsOtherCashDistributions))))&&((this.operatingIncome == rhs.operatingIncome)||((this.operatingIncome!= null)&&this.operatingIncome.equals(rhs.operatingIncome))))&&((this.preferredDividendsIncomeStatementImpact == rhs.preferredDividendsIncomeStatementImpact)||((this.preferredDividendsIncomeStatementImpact!= null)&&this.preferredDividendsIncomeStatementImpact.equals(rhs.preferredDividendsIncomeStatementImpact))))&&((this.debtToEquityRatio == rhs.debtToEquityRatio)||((this.debtToEquityRatio!= null)&&this.debtToEquityRatio.equals(rhs.debtToEquityRatio))))&&((this.issuanceEquityShares == rhs.issuanceEquityShares)||((this.issuanceEquityShares!= null)&&this.issuanceEquityShares.equals(rhs.issuanceEquityShares))))&&((this.accumulatedOtherComprehensiveIncome == rhs.accumulatedOtherComprehensiveIncome)||((this.accumulatedOtherComprehensiveIncome!= null)&&this.accumulatedOtherComprehensiveIncome.equals(rhs.accumulatedOtherComprehensiveIncome))))&&((this.earningsPerDilutedShare == rhs.earningsPerDilutedShare)||((this.earningsPerDilutedShare!= null)&&this.earningsPerDilutedShare.equals(rhs.earningsPerDilutedShare))))&&((this.netCashFlowFromOperations == rhs.netCashFlowFromOperations)||((this.netCashFlowFromOperations!= null)&&this.netCashFlowFromOperations.equals(rhs.netCashFlowFromOperations))))&&((this.liabilitiesNonCurrent == rhs.liabilitiesNonCurrent)||((this.liabilitiesNonCurrent!= null)&&this.liabilitiesNonCurrent.equals(rhs.liabilitiesNonCurrent))))&&((this.earningsBeforeTax == rhs.earningsBeforeTax)||((this.earningsBeforeTax!= null)&&this.earningsBeforeTax.equals(rhs.earningsBeforeTax))))&&((this.priceToBookValue == rhs.priceToBookValue)||((this.priceToBookValue!= null)&&this.priceToBookValue.equals(rhs.priceToBookValue))))&&((this.returnOnInvestedCapital == rhs.returnOnInvestedCapital)||((this.returnOnInvestedCapital!= null)&&this.returnOnInvestedCapital.equals(rhs.returnOnInvestedCapital))))&&((this.enterpriseValueOverEBITDA == rhs.enterpriseValueOverEBITDA)||((this.enterpriseValueOverEBITDA!= null)&&this.enterpriseValueOverEBITDA.equals(rhs.enterpriseValueOverEBITDA))))&&((this.netIncomeCommonStock == rhs.netIncomeCommonStock)||((this.netIncomeCommonStock!= null)&&this.netIncomeCommonStock.equals(rhs.netIncomeCommonStock))))&&((this.taxLiabilities == rhs.taxLiabilities)||((this.taxLiabilities!= null)&&this.taxLiabilities.equals(rhs.taxLiabilities))))&&((this.debtCurrent == rhs.debtCurrent)||((this.debtCurrent!= null)&&this.debtCurrent.equals(rhs.debtCurrent))))&&((this.netCashFlowInvestmentAcquisitionsDisposals == rhs.netCashFlowInvestmentAcquisitionsDisposals)||((this.netCashFlowInvestmentAcquisitionsDisposals!= null)&&this.netCashFlowInvestmentAcquisitionsDisposals.equals(rhs.netCashFlowInvestmentAcquisitionsDisposals))))&&((this.priceToSalesRatio == rhs.priceToSalesRatio)||((this.priceToSalesRatio!= null)&&this.priceToSalesRatio.equals(rhs.priceToSalesRatio))))&&((this.propertyPlantEquipmentNet == rhs.propertyPlantEquipmentNet)||((this.propertyPlantEquipmentNet!= null)&&this.propertyPlantEquipmentNet.equals(rhs.propertyPlantEquipmentNet))))&&((this.assets == rhs.assets)||((this.assets!= null)&&this.assets.equals(rhs.assets))))&&((this.investedCapital == rhs.investedCapital)||((this.investedCapital!= null)&&this.investedCapital.equals(rhs.investedCapital))))&&((this.returnOnAverageEquity == rhs.returnOnAverageEquity)||((this.returnOnAverageEquity!= null)&&this.returnOnAverageEquity.equals(rhs.returnOnAverageEquity))))&&((this.shareFactor == rhs.shareFactor)||((this.shareFactor!= null)&&this.shareFactor.equals(rhs.shareFactor))))&&((this.revenuesUSD == rhs.revenuesUSD)||((this.revenuesUSD!= null)&&this.revenuesUSD.equals(rhs.revenuesUSD))))&&((this.earningsPerBasicShare == rhs.earningsPerBasicShare)||((this.earningsPerBasicShare!= null)&&this.earningsPerBasicShare.equals(rhs.earningsPerBasicShare))))&&((this.marketCapitalization == rhs.marketCapitalization)||((this.marketCapitalization!= null)&&this.marketCapitalization.equals(rhs.marketCapitalization))))&&((this.foreignCurrencyUSDExchangeRate == rhs.foreignCurrencyUSDExchangeRate)||((this.foreignCurrencyUSDExchangeRate!= null)&&this.foreignCurrencyUSDExchangeRate.equals(rhs.foreignCurrencyUSDExchangeRate))))&&((this.totalLiabilities == rhs.totalLiabilities)||((this.totalLiabilities!= null)&&this.totalLiabilities.equals(rhs.totalLiabilities))))&&((this.costOfRevenue == rhs.costOfRevenue)||((this.costOfRevenue!= null)&&this.costOfRevenue.equals(rhs.costOfRevenue))))&&((this.earningsBeforeInterestTaxesDepreciationAmortization == rhs.earningsBeforeInterestTaxesDepreciationAmortization)||((this.earningsBeforeInterestTaxesDepreciationAmortization!= null)&&this.earningsBeforeInterestTaxesDepreciationAmortization.equals(rhs.earningsBeforeInterestTaxesDepreciationAmortization))))&&((this.deposits == rhs.deposits)||((this.deposits!= null)&&this.deposits.equals(rhs.deposits))))&&((this.netLossIncomeFromDiscontinuedOperations == rhs.netLossIncomeFromDiscontinuedOperations)||((this.netLossIncomeFromDiscontinuedOperations!= null)&&this.netLossIncomeFromDiscontinuedOperations.equals(rhs.netLossIncomeFromDiscontinuedOperations))))&&((this.taxAssets == rhs.taxAssets)||((this.taxAssets!= null)&&this.taxAssets.equals(rhs.taxAssets))))&&((this.payoutRatio == rhs.payoutRatio)||((this.payoutRatio!= null)&&this.payoutRatio.equals(rhs.payoutRatio))))&&((this.researchAndDevelopmentExpense == rhs.researchAndDevelopmentExpense)||((this.researchAndDevelopmentExpense!= null)&&this.researchAndDevelopmentExpense.equals(rhs.researchAndDevelopmentExpense))))&&((this.updated == rhs.updated)||((this.updated!= null)&&this.updated.equals(rhs.updated))))&&((this.debt == rhs.debt)||((this.debt!= null)&&this.debt.equals(rhs.debt))))&&((this.assetsCurrent == rhs.assetsCurrent)||((this.assetsCurrent!= null)&&this.assetsCurrent.equals(rhs.assetsCurrent))))&&((this.earningBeforeInterestTaxes == rhs.earningBeforeInterestTaxes)||((this.earningBeforeInterestTaxes!= null)&&this.earningBeforeInterestTaxes.equals(rhs.earningBeforeInterestTaxes))))&&((this.investmentsCurrent == rhs.investmentsCurrent)||((this.investmentsCurrent!= null)&&this.investmentsCurrent.equals(rhs.investmentsCurrent))))&&((this.weightedAverageShares == rhs.weightedAverageShares)||((this.weightedAverageShares!= null)&&this.weightedAverageShares.equals(rhs.weightedAverageShares))))&&((this.goodwillAndIntangibleAssets == rhs.goodwillAndIntangibleAssets)||((this.goodwillAndIntangibleAssets!= null)&&this.goodwillAndIntangibleAssets.equals(rhs.goodwillAndIntangibleAssets))))&&((this.inventory == rhs.inventory)||((this.inventory!= null)&&this.inventory.equals(rhs.inventory))))&&((this.netCashFlowBusinessAcquisitionsDisposals == rhs.netCashFlowBusinessAcquisitionsDisposals)||((this.netCashFlowBusinessAcquisitionsDisposals!= null)&&this.netCashFlowBusinessAcquisitionsDisposals.equals(rhs.netCashFlowBusinessAcquisitionsDisposals))))&&((this.priceToEarningsRatio == rhs.priceToEarningsRatio)||((this.priceToEarningsRatio!= null)&&this.priceToEarningsRatio.equals(rhs.priceToEarningsRatio))))&&((this.currentLiabilities == rhs.currentLiabilities)||((this.currentLiabilities!= null)&&this.currentLiabilities.equals(rhs.currentLiabilities))))&&((this.weightedAverageSharesDiluted == rhs.weightedAverageSharesDiluted)||((this.weightedAverageSharesDiluted!= null)&&this.weightedAverageSharesDiluted.equals(rhs.weightedAverageSharesDiluted))))&&((this.consolidatedIncome == rhs.consolidatedIncome)||((this.consolidatedIncome!= null)&&this.consolidatedIncome.equals(rhs.consolidatedIncome))))&&((this.currentRatio == rhs.currentRatio)||((this.currentRatio!= null)&&this.currentRatio.equals(rhs.currentRatio))))&&((this.capitalExpenditure == rhs.capitalExpenditure)||((this.capitalExpenditure!= null)&&this.capitalExpenditure.equals(rhs.capitalExpenditure))))&&((this.ticker == rhs.ticker)||((this.ticker!= null)&&this.ticker.equals(rhs.ticker))))&&((this.dividendsPerBasicCommonShare == rhs.dividendsPerBasicCommonShare)||((this.dividendsPerBasicCommonShare!= null)&&this.dividendsPerBasicCommonShare.equals(rhs.dividendsPerBasicCommonShare))))&&((this.revenues == rhs.revenues)||((this.revenues!= null)&&this.revenues.equals(rhs.revenues))))&&((this.cashAndEquivalents == rhs.cashAndEquivalents)||((this.cashAndEquivalents!= null)&&this.cashAndEquivalents.equals(rhs.cashAndEquivalents))))&&((this.assetTurnover == rhs.assetTurnover)||((this.assetTurnover!= null)&&this.assetTurnover.equals(rhs.assetTurnover))))&&((this.sharePriceAdjustedClose == rhs.sharePriceAdjustedClose)||((this.sharePriceAdjustedClose!= null)&&this.sharePriceAdjustedClose.equals(rhs.sharePriceAdjustedClose))))&&((this.deferredRevenue == rhs.deferredRevenue)||((this.deferredRevenue!= null)&&this.deferredRevenue.equals(rhs.deferredRevenue))))&&((this.debtNonCurrent == rhs.debtNonCurrent)||((this.debtNonCurrent!= null)&&this.debtNonCurrent.equals(rhs.debtNonCurrent))))&&((this.netCashFlowFromInvesting == rhs.netCashFlowFromInvesting)||((this.netCashFlowFromInvesting!= null)&&this.netCashFlowFromInvesting.equals(rhs.netCashFlowFromInvesting))))&&((this.netIncome == rhs.netIncome)||((this.netIncome!= null)&&this.netIncome.equals(rhs.netIncome))))&&((this.incomeTaxExpense == rhs.incomeTaxExpense)||((this.incomeTaxExpense!= null)&&this.incomeTaxExpense.equals(rhs.incomeTaxExpense))))&&((this.grossMargin == rhs.grossMargin)||((this.grossMargin!= null)&&this.grossMargin.equals(rhs.grossMargin))))&&((this.assetsAverage == rhs.assetsAverage)||((this.assetsAverage!= null)&&this.assetsAverage.equals(rhs.assetsAverage))))&&((this.calendarDate == rhs.calendarDate)||((this.calendarDate!= null)&&this.calendarDate.equals(rhs.calendarDate))));
    }

}
