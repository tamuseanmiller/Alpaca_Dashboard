package net.jacobpeterson.alpaca.enums.api;

/**
 * {@link DataAPIType} defines the types of Data APIs to use with Alpaca.
 */
public enum DataAPIType {

    /** The IEX {@link DataAPIType}. */
    IEX("wss://stream.data.alpaca.markets/v2/iex", "iex"),

    /** The SIP {@link DataAPIType}. */
    SIP("wss://stream.data.alpaca.markets/v2/sip", "sip");

    private final String url;
    private final String propertyName;

    /**
     * Instantiates a new {@link DataAPIType}.
     *
     * @param url          the URL
     * @param propertyName the property name
     */
    DataAPIType(String url, String propertyName) {
        this.url = url;
        this.propertyName = propertyName;
    }

    /**
     * Gets a {@link DataAPIType} from its {@link DataAPIType#getPropertyName()}.
     *
     * @param propertyName the property name
     *
     * @return the {@link DataAPIType}
     */
    public static DataAPIType fromPropertyName(String propertyName) {
        for (DataAPIType dataAPIType : values()) {
            if (dataAPIType.getPropertyName().equals(propertyName)) {
                return dataAPIType;
            }
        }

        return null;
    }

    /**
     * Gets URL.
     *
     * @return the URL
     */
    public String getURL() {
        return url;
    }

    /**
     * Gets property name.
     *
     * @return the property name
     */
    public String getPropertyName() {
        return propertyName;
    }
}
