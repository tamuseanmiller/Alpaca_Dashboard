
package net.jacobpeterson.domain.polygon.locales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v2_reference_locales">https://polygon.io/docs/#!/Reference/get_v2_reference_locales</a>
 * <p>
 * 
 * 
 */
public class Locale implements Serializable
{

    /**
     * Locale
     * <p>
     * 
     * 
     */
    @SerializedName("locale")
    @Expose
    private String locale;
    /**
     * Name
     * <p>
     * 
     * 
     */
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = -3335124468213389847L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Locale() {
    }

    /**
     * 
     * @param name
     * @param locale
     */
    public Locale(String locale, String name) {
        super();
        this.locale = locale;
        this.name = name;
    }

    /**
     * Locale
     * <p>
     * 
     * 
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Locale
     * <p>
     * 
     * 
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Name
     * <p>
     * 
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * Name
     * <p>
     * 
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Locale.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("locale");
        sb.append('=');
        sb.append(((this.locale == null)?"<null>":this.locale));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
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
        result = ((result* 31)+((this.locale == null)? 0 :this.locale.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Locale) == false) {
            return false;
        }
        Locale rhs = ((Locale) other);
        return (((this.locale == rhs.locale)||((this.locale!= null)&&this.locale.equals(rhs.locale)))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))));
    }

}
