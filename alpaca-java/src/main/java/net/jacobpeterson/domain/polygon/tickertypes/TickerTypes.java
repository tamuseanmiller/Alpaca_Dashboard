
package net.jacobpeterson.domain.polygon.tickertypes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_types">https://polygon.io/docs/#!/Reference/get_v2_reference_types</a>
 * <p>
 * 
 * 
 */
public class TickerTypes implements Serializable
{

    /**
     * Types
     * <p>
     * 
     * 
     */
    @SerializedName("types")
    @Expose
    private HashMap<String, String> types;
    /**
     * Index Types
     * <p>
     * 
     * 
     */
    @SerializedName("indexTypes")
    @Expose
    private HashMap<String, String> indexTypes;
    private final static long serialVersionUID = 8905688407664989990L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TickerTypes() {
    }

    /**
     * 
     * @param types
     * @param indexTypes
     */
    public TickerTypes(HashMap<String, String> types, HashMap<String, String> indexTypes) {
        super();
        this.types = types;
        this.indexTypes = indexTypes;
    }

    /**
     * Types
     * <p>
     * 
     * 
     */
    public HashMap<String, String> getTypes() {
        return types;
    }

    /**
     * Types
     * <p>
     * 
     * 
     */
    public void setTypes(HashMap<String, String> types) {
        this.types = types;
    }

    /**
     * Index Types
     * <p>
     * 
     * 
     */
    public HashMap<String, String> getIndexTypes() {
        return indexTypes;
    }

    /**
     * Index Types
     * <p>
     * 
     * 
     */
    public void setIndexTypes(HashMap<String, String> indexTypes) {
        this.indexTypes = indexTypes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TickerTypes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("types");
        sb.append('=');
        sb.append(((this.types == null)?"<null>":this.types));
        sb.append(',');
        sb.append("indexTypes");
        sb.append('=');
        sb.append(((this.indexTypes == null)?"<null>":this.indexTypes));
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
        result = ((result* 31)+((this.indexTypes == null)? 0 :this.indexTypes.hashCode()));
        result = ((result* 31)+((this.types == null)? 0 :this.types.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TickerTypes) == false) {
            return false;
        }
        TickerTypes rhs = ((TickerTypes) other);
        return (((this.indexTypes == rhs.indexTypes)||((this.indexTypes!= null)&&this.indexTypes.equals(rhs.indexTypes)))&&((this.types == rhs.types)||((this.types!= null)&&this.types.equals(rhs.types))));
    }

}
