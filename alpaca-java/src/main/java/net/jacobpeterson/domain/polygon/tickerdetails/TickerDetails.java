
package net.jacobpeterson.domain.polygon.tickerdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v1_meta_symbols_symbol_company">https://polygon.io/docs/#!/Reference/get_v1_meta_symbols_symbol_company</a>
 * <p>
 * 
 * 
 */
public class TickerDetails implements Serializable
{

    /**
     * URL of the entities logo.
     * <p>
     * 
     * 
     */
    @SerializedName("logo")
    @Expose
    private String logo;
    /**
     * Symbols primary exchange
     * <p>
     * 
     * 
     */
    @SerializedName("exchange")
    @Expose
    private String exchange;
    /**
     * Name of the company/entity
     * <p>
     * 
     * 
     */
    @SerializedName("name")
    @Expose
    private String name;
    /**
     * symbol
     * <p>
     * 
     * 
     */
    @SerializedName("symbol")
    @Expose
    private String symbol;
    /**
     * Date this symbol was listed on the exchange.
     * <p>
     * 
     * 
     */
    @SerializedName("listdate")
    @Expose
    private String listdate;
    /**
     * Official CIK guid used for SEC database / filings.
     * <p>
     * 
     * 
     */
    @SerializedName("cik")
    @Expose
    private String cik;
    /**
     * Bloomberg guid for this symbol
     * <p>
     * 
     * 
     */
    @SerializedName("bloomberg")
    @Expose
    private String bloomberg;
    /**
     * guid for the OpenFigi project ( https://openfigi.com/ )
     * <p>
     * 
     * 
     */
    @SerializedName("figi")
    @Expose
    private String figi;
    /**
     * Legal Entity Identifier (LEI) guid for symbol ( https://en.wikipedia.org/wiki/Legal_Entity_Identifier )
     * <p>
     * 
     * 
     */
    @SerializedName("lei")
    @Expose
    private String lei;
    /**
     * Standard Industrial Classification (SIC) id for symbol ( https://en.wikipedia.org/wiki/Standard_Industrial_Classification )
     * <p>
     * 
     * 
     */
    @SerializedName("sic")
    @Expose
    private Integer sic;
    /**
     * Country in which this company is registered
     * <p>
     * 
     * 
     */
    @SerializedName("country")
    @Expose
    private String country;
    /**
     * Industry this company operates in
     * <p>
     * 
     * 
     */
    @SerializedName("industry")
    @Expose
    private String industry;
    /**
     * Sector of the indsutry in which this symbol operates in
     * <p>
     * 
     * 
     */
    @SerializedName("sector")
    @Expose
    private String sector;
    /**
     * Current market cap for this company
     * <p>
     * 
     * 
     */
    @SerializedName("marketcap")
    @Expose
    private Double marketcap;
    /**
     * Approximate number of employees
     * <p>
     * 
     * 
     */
    @SerializedName("employees")
    @Expose
    private Integer employees;
    /**
     * Phone number for this company. Usually corporate contact number.
     * <p>
     * 
     * 
     */
    @SerializedName("phone")
    @Expose
    private String phone;
    /**
     * Name of the companies current CEO
     * <p>
     * 
     * 
     */
    @SerializedName("ceo")
    @Expose
    private String ceo;
    /**
     * URL of the companies website
     * <p>
     * 
     * 
     */
    @SerializedName("url")
    @Expose
    private String url;
    /**
     * A description of the company and what they do/offer
     * <p>
     * 
     * 
     */
    @SerializedName("title")
    @Expose
    private String title;
    /**
     * similar
     * <p>
     * 
     * 
     */
    @SerializedName("similar")
    @Expose
    private ArrayList<String> similar;
    /**
     * tags
     * <p>
     * 
     * 
     */
    @SerializedName("tags")
    @Expose
    private ArrayList<String> tags;
    /**
     * Last time this company record was updated.
     * <p>
     * 
     * 
     */
    @SerializedName("updated")
    @Expose
    private String updated;
    private final static long serialVersionUID = -7777617428510779654L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TickerDetails() {
    }

    /**
     * 
     * @param symbol
     * @param country
     * @param marketcap
     * @param similar
     * @param cik
     * @param bloomberg
     * @param figi
     * @param industry
     * @param sic
     * @param ceo
     * @param title
     * @param url
     * @param tags
     * @param listdate
     * @param lei
     * @param phone
     * @param name
     * @param logo
     * @param exchange
     * @param employees
     * @param sector
     * @param updated
     */
    public TickerDetails(String logo, String exchange, String name, String symbol, String listdate, String cik, String bloomberg, String figi, String lei, Integer sic, String country, String industry, String sector, Double marketcap, Integer employees, String phone, String ceo, String url, String title, ArrayList<String> similar, ArrayList<String> tags, String updated) {
        super();
        this.logo = logo;
        this.exchange = exchange;
        this.name = name;
        this.symbol = symbol;
        this.listdate = listdate;
        this.cik = cik;
        this.bloomberg = bloomberg;
        this.figi = figi;
        this.lei = lei;
        this.sic = sic;
        this.country = country;
        this.industry = industry;
        this.sector = sector;
        this.marketcap = marketcap;
        this.employees = employees;
        this.phone = phone;
        this.ceo = ceo;
        this.url = url;
        this.title = title;
        this.similar = similar;
        this.tags = tags;
        this.updated = updated;
    }

    /**
     * URL of the entities logo.
     * <p>
     * 
     * 
     */
    public String getLogo() {
        return logo;
    }

    /**
     * URL of the entities logo.
     * <p>
     * 
     * 
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * Symbols primary exchange
     * <p>
     * 
     * 
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * Symbols primary exchange
     * <p>
     * 
     * 
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    /**
     * Name of the company/entity
     * <p>
     * 
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * Name of the company/entity
     * <p>
     * 
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * symbol
     * <p>
     * 
     * 
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * symbol
     * <p>
     * 
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Date this symbol was listed on the exchange.
     * <p>
     * 
     * 
     */
    public String getListdate() {
        return listdate;
    }

    /**
     * Date this symbol was listed on the exchange.
     * <p>
     * 
     * 
     */
    public void setListdate(String listdate) {
        this.listdate = listdate;
    }

    /**
     * Official CIK guid used for SEC database / filings.
     * <p>
     * 
     * 
     */
    public String getCik() {
        return cik;
    }

    /**
     * Official CIK guid used for SEC database / filings.
     * <p>
     * 
     * 
     */
    public void setCik(String cik) {
        this.cik = cik;
    }

    /**
     * Bloomberg guid for this symbol
     * <p>
     * 
     * 
     */
    public String getBloomberg() {
        return bloomberg;
    }

    /**
     * Bloomberg guid for this symbol
     * <p>
     * 
     * 
     */
    public void setBloomberg(String bloomberg) {
        this.bloomberg = bloomberg;
    }

    /**
     * guid for the OpenFigi project ( https://openfigi.com/ )
     * <p>
     * 
     * 
     */
    public String getFigi() {
        return figi;
    }

    /**
     * guid for the OpenFigi project ( https://openfigi.com/ )
     * <p>
     * 
     * 
     */
    public void setFigi(String figi) {
        this.figi = figi;
    }

    /**
     * Legal Entity Identifier (LEI) guid for symbol ( https://en.wikipedia.org/wiki/Legal_Entity_Identifier )
     * <p>
     * 
     * 
     */
    public String getLei() {
        return lei;
    }

    /**
     * Legal Entity Identifier (LEI) guid for symbol ( https://en.wikipedia.org/wiki/Legal_Entity_Identifier )
     * <p>
     * 
     * 
     */
    public void setLei(String lei) {
        this.lei = lei;
    }

    /**
     * Standard Industrial Classification (SIC) id for symbol ( https://en.wikipedia.org/wiki/Standard_Industrial_Classification )
     * <p>
     * 
     * 
     */
    public Integer getSic() {
        return sic;
    }

    /**
     * Standard Industrial Classification (SIC) id for symbol ( https://en.wikipedia.org/wiki/Standard_Industrial_Classification )
     * <p>
     * 
     * 
     */
    public void setSic(Integer sic) {
        this.sic = sic;
    }

    /**
     * Country in which this company is registered
     * <p>
     * 
     * 
     */
    public String getCountry() {
        return country;
    }

    /**
     * Country in which this company is registered
     * <p>
     * 
     * 
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Industry this company operates in
     * <p>
     * 
     * 
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * Industry this company operates in
     * <p>
     * 
     * 
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * Sector of the indsutry in which this symbol operates in
     * <p>
     * 
     * 
     */
    public String getSector() {
        return sector;
    }

    /**
     * Sector of the indsutry in which this symbol operates in
     * <p>
     * 
     * 
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * Current market cap for this company
     * <p>
     * 
     * 
     */
    public Double getMarketcap() {
        return marketcap;
    }

    /**
     * Current market cap for this company
     * <p>
     * 
     * 
     */
    public void setMarketcap(Double marketcap) {
        this.marketcap = marketcap;
    }

    /**
     * Approximate number of employees
     * <p>
     * 
     * 
     */
    public Integer getEmployees() {
        return employees;
    }

    /**
     * Approximate number of employees
     * <p>
     * 
     * 
     */
    public void setEmployees(Integer employees) {
        this.employees = employees;
    }

    /**
     * Phone number for this company. Usually corporate contact number.
     * <p>
     * 
     * 
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Phone number for this company. Usually corporate contact number.
     * <p>
     * 
     * 
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Name of the companies current CEO
     * <p>
     * 
     * 
     */
    public String getCeo() {
        return ceo;
    }

    /**
     * Name of the companies current CEO
     * <p>
     * 
     * 
     */
    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    /**
     * URL of the companies website
     * <p>
     * 
     * 
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL of the companies website
     * <p>
     * 
     * 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * A description of the company and what they do/offer
     * <p>
     * 
     * 
     */
    public String getTitle() {
        return title;
    }

    /**
     * A description of the company and what they do/offer
     * <p>
     * 
     * 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * similar
     * <p>
     * 
     * 
     */
    public ArrayList<String> getSimilar() {
        return similar;
    }

    /**
     * similar
     * <p>
     * 
     * 
     */
    public void setSimilar(ArrayList<String> similar) {
        this.similar = similar;
    }

    /**
     * tags
     * <p>
     * 
     * 
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * tags
     * <p>
     * 
     * 
     */
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    /**
     * Last time this company record was updated.
     * <p>
     * 
     * 
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * Last time this company record was updated.
     * <p>
     * 
     * 
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TickerDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("logo");
        sb.append('=');
        sb.append(((this.logo == null)?"<null>":this.logo));
        sb.append(',');
        sb.append("exchange");
        sb.append('=');
        sb.append(((this.exchange == null)?"<null>":this.exchange));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("listdate");
        sb.append('=');
        sb.append(((this.listdate == null)?"<null>":this.listdate));
        sb.append(',');
        sb.append("cik");
        sb.append('=');
        sb.append(((this.cik == null)?"<null>":this.cik));
        sb.append(',');
        sb.append("bloomberg");
        sb.append('=');
        sb.append(((this.bloomberg == null)?"<null>":this.bloomberg));
        sb.append(',');
        sb.append("figi");
        sb.append('=');
        sb.append(((this.figi == null)?"<null>":this.figi));
        sb.append(',');
        sb.append("lei");
        sb.append('=');
        sb.append(((this.lei == null)?"<null>":this.lei));
        sb.append(',');
        sb.append("sic");
        sb.append('=');
        sb.append(((this.sic == null)?"<null>":this.sic));
        sb.append(',');
        sb.append("country");
        sb.append('=');
        sb.append(((this.country == null)?"<null>":this.country));
        sb.append(',');
        sb.append("industry");
        sb.append('=');
        sb.append(((this.industry == null)?"<null>":this.industry));
        sb.append(',');
        sb.append("sector");
        sb.append('=');
        sb.append(((this.sector == null)?"<null>":this.sector));
        sb.append(',');
        sb.append("marketcap");
        sb.append('=');
        sb.append(((this.marketcap == null)?"<null>":this.marketcap));
        sb.append(',');
        sb.append("employees");
        sb.append('=');
        sb.append(((this.employees == null)?"<null>":this.employees));
        sb.append(',');
        sb.append("phone");
        sb.append('=');
        sb.append(((this.phone == null)?"<null>":this.phone));
        sb.append(',');
        sb.append("ceo");
        sb.append('=');
        sb.append(((this.ceo == null)?"<null>":this.ceo));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("similar");
        sb.append('=');
        sb.append(((this.similar == null)?"<null>":this.similar));
        sb.append(',');
        sb.append("tags");
        sb.append('=');
        sb.append(((this.tags == null)?"<null>":this.tags));
        sb.append(',');
        sb.append("updated");
        sb.append('=');
        sb.append(((this.updated == null)?"<null>":this.updated));
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
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.country == null)? 0 :this.country.hashCode()));
        result = ((result* 31)+((this.marketcap == null)? 0 :this.marketcap.hashCode()));
        result = ((result* 31)+((this.similar == null)? 0 :this.similar.hashCode()));
        result = ((result* 31)+((this.cik == null)? 0 :this.cik.hashCode()));
        result = ((result* 31)+((this.bloomberg == null)? 0 :this.bloomberg.hashCode()));
        result = ((result* 31)+((this.figi == null)? 0 :this.figi.hashCode()));
        result = ((result* 31)+((this.industry == null)? 0 :this.industry.hashCode()));
        result = ((result* 31)+((this.sic == null)? 0 :this.sic.hashCode()));
        result = ((result* 31)+((this.ceo == null)? 0 :this.ceo.hashCode()));
        result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.tags == null)? 0 :this.tags.hashCode()));
        result = ((result* 31)+((this.listdate == null)? 0 :this.listdate.hashCode()));
        result = ((result* 31)+((this.lei == null)? 0 :this.lei.hashCode()));
        result = ((result* 31)+((this.phone == null)? 0 :this.phone.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.logo == null)? 0 :this.logo.hashCode()));
        result = ((result* 31)+((this.exchange == null)? 0 :this.exchange.hashCode()));
        result = ((result* 31)+((this.employees == null)? 0 :this.employees.hashCode()));
        result = ((result* 31)+((this.sector == null)? 0 :this.sector.hashCode()));
        result = ((result* 31)+((this.updated == null)? 0 :this.updated.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TickerDetails) == false) {
            return false;
        }
        TickerDetails rhs = ((TickerDetails) other);
        return (((((((((((((((((((((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.country == rhs.country)||((this.country!= null)&&this.country.equals(rhs.country))))&&((this.marketcap == rhs.marketcap)||((this.marketcap!= null)&&this.marketcap.equals(rhs.marketcap))))&&((this.similar == rhs.similar)||((this.similar!= null)&&this.similar.equals(rhs.similar))))&&((this.cik == rhs.cik)||((this.cik!= null)&&this.cik.equals(rhs.cik))))&&((this.bloomberg == rhs.bloomberg)||((this.bloomberg!= null)&&this.bloomberg.equals(rhs.bloomberg))))&&((this.figi == rhs.figi)||((this.figi!= null)&&this.figi.equals(rhs.figi))))&&((this.industry == rhs.industry)||((this.industry!= null)&&this.industry.equals(rhs.industry))))&&((this.sic == rhs.sic)||((this.sic!= null)&&this.sic.equals(rhs.sic))))&&((this.ceo == rhs.ceo)||((this.ceo!= null)&&this.ceo.equals(rhs.ceo))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.tags == rhs.tags)||((this.tags!= null)&&this.tags.equals(rhs.tags))))&&((this.listdate == rhs.listdate)||((this.listdate!= null)&&this.listdate.equals(rhs.listdate))))&&((this.lei == rhs.lei)||((this.lei!= null)&&this.lei.equals(rhs.lei))))&&((this.phone == rhs.phone)||((this.phone!= null)&&this.phone.equals(rhs.phone))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.logo == rhs.logo)||((this.logo!= null)&&this.logo.equals(rhs.logo))))&&((this.exchange == rhs.exchange)||((this.exchange!= null)&&this.exchange.equals(rhs.exchange))))&&((this.employees == rhs.employees)||((this.employees!= null)&&this.employees.equals(rhs.employees))))&&((this.sector == rhs.sector)||((this.sector!= null)&&this.sector.equals(rhs.sector))))&&((this.updated == rhs.updated)||((this.updated!= null)&&this.updated.equals(rhs.updated))));
    }

}
