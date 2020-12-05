
package net.jacobpeterson.domain.polygon.exchanges;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Stocks--Equities/get_v1_meta_exchanges">https://polygon.io/docs/#!/Stocks--Equities/get_v1_meta_exchanges</a>
 * <p>
 * 
 * 
 */
public class Exchange implements Serializable
{

    /**
     * ID of the exchange
     * <p>
     * 
     * 
     */
    @SerializedName("id")
    @Expose
    private Integer id;
    /**
     * The type of exchange this is.<br>- TRF = Trade Reporting Facility<br>- exchange = Reporting exchange on the tape<br>Can be TRF or exchange
     * <p>
     * 
     * 
     */
    @SerializedName("type")
    @Expose
    private String type;
    /**
     * Market data type this exchange contains. Can be equities or indices
     * <p>
     * 
     * 
     */
    @SerializedName("market")
    @Expose
    private String market;
    /**
     * Market Identification Code
     * <p>
     * 
     * 
     */
    @SerializedName("mic")
    @Expose
    private String mic;
    /**
     * Name of the exchange
     * <p>
     * 
     * 
     */
    @SerializedName("name")
    @Expose
    private String name;
    /**
     * Tape id of the exchange
     * <p>
     * 
     * 
     */
    @SerializedName("tape")
    @Expose
    private String tape;
    private final static long serialVersionUID = 4212318344254959017L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Exchange() {
    }

    /**
     * 
     * @param market
     * @param tape
     * @param mic
     * @param name
     * @param id
     * @param type
     */
    public Exchange(Integer id, String type, String market, String mic, String name, String tape) {
        super();
        this.id = id;
        this.type = type;
        this.market = market;
        this.mic = mic;
        this.name = name;
        this.tape = tape;
    }

    /**
     * ID of the exchange
     * <p>
     * 
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID of the exchange
     * <p>
     * 
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The type of exchange this is.<br>- TRF = Trade Reporting Facility<br>- exchange = Reporting exchange on the tape<br>Can be TRF or exchange
     * <p>
     * 
     * 
     */
    public String getType() {
        return type;
    }

    /**
     * The type of exchange this is.<br>- TRF = Trade Reporting Facility<br>- exchange = Reporting exchange on the tape<br>Can be TRF or exchange
     * <p>
     * 
     * 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Market data type this exchange contains. Can be equities or indices
     * <p>
     * 
     * 
     */
    public String getMarket() {
        return market;
    }

    /**
     * Market data type this exchange contains. Can be equities or indices
     * <p>
     * 
     * 
     */
    public void setMarket(String market) {
        this.market = market;
    }

    /**
     * Market Identification Code
     * <p>
     * 
     * 
     */
    public String getMic() {
        return mic;
    }

    /**
     * Market Identification Code
     * <p>
     * 
     * 
     */
    public void setMic(String mic) {
        this.mic = mic;
    }

    /**
     * Name of the exchange
     * <p>
     * 
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * Name of the exchange
     * <p>
     * 
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Tape id of the exchange
     * <p>
     * 
     * 
     */
    public String getTape() {
        return tape;
    }

    /**
     * Tape id of the exchange
     * <p>
     * 
     * 
     */
    public void setTape(String tape) {
        this.tape = tape;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Exchange.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("market");
        sb.append('=');
        sb.append(((this.market == null)?"<null>":this.market));
        sb.append(',');
        sb.append("mic");
        sb.append('=');
        sb.append(((this.mic == null)?"<null>":this.mic));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("tape");
        sb.append('=');
        sb.append(((this.tape == null)?"<null>":this.tape));
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
        result = ((result* 31)+((this.market == null)? 0 :this.market.hashCode()));
        result = ((result* 31)+((this.tape == null)? 0 :this.tape.hashCode()));
        result = ((result* 31)+((this.mic == null)? 0 :this.mic.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Exchange) == false) {
            return false;
        }
        Exchange rhs = ((Exchange) other);
        return (((((((this.market == rhs.market)||((this.market!= null)&&this.market.equals(rhs.market)))&&((this.tape == rhs.tape)||((this.tape!= null)&&this.tape.equals(rhs.tape))))&&((this.mic == rhs.mic)||((this.mic!= null)&&this.mic.equals(rhs.mic))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))));
    }

}
