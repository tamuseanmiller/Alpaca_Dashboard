package net.jacobpeterson.alpaca.properties;

import java.util.StringJoiner;

import static net.jacobpeterson.util.properties.PropertyUtil.getProperty;

/**
 * The Class AlpacaProperties.
 */
public class AlpacaProperties {

    private static final String ALPACA_PROPERTIES_FILE = "src/main/resources/alpaca.properties";

    /** The Constant API_VERSION_KEY. */
    private static final String API_VERSION_KEY = "api_version";

    /** The Constant API_VERSION_VALUE. */
    public static final String API_VERSION_VALUE = "v2";

    /** The Constant KEY_ID_KEY. */
    private static final String KEY_ID_KEY = "key_id";

    /** The Constant KEY_ID_VALUE. */
    public static final String KEY_ID_VALUE = getProperty(ALPACA_PROPERTIES_FILE, KEY_ID_KEY);

    /** The Constant SECRET_KEY. */
    private static final String SECRET_KEY = "secret";

    /** The Constant SECRET_VALUE. */
    public static final String SECRET_VALUE = getProperty(ALPACA_PROPERTIES_FILE, SECRET_KEY);

    /** The Constant BASE_API_URL_KEY. */
    private static final String BASE_API_URL_KEY = "base_api_url";

    /** The Constant BASE_API_URL_VALUE. */
    public static final String BASE_API_URL_VALUE = "https://paper-api.alpaca.markets";

    /** The Constant BASE_DATA_URL_KEY. */
    private static final String BASE_DATA_URL_KEY = "base_data_url";

    /** The base data url value. */
    public static final String BASE_DATA_URL_VALUE = "https://data.alpaca.markets";

    /** The Constant USER_AGENT_KEY. */
    private static final String USER_AGENT_KEY = "user_agent";

    /** The Constant USER_AGENT_VALUE. */
    public static final String USER_AGENT_VALUE = getProperty(ALPACA_PROPERTIES_FILE, USER_AGENT_KEY);

    /**
     * Static to string string.
     *
     * @return the string
     */
    public static String staticToString() {
        return new StringJoiner(", ", AlpacaProperties.class.getSimpleName() + "[", "]")
                .add("ALPACA_PROPERTIES_FILE = " + ALPACA_PROPERTIES_FILE)
                .add("API_VERSION_KEY = " + API_VERSION_KEY)
                .add("API_VERSION_VALUE = " + API_VERSION_VALUE)
                .add("BASE_API_URL_KEY = " + BASE_API_URL_KEY)
                .add("BASE_API_URL_VALUE = " + BASE_API_URL_VALUE)
                .add("BASE_DATA_URL_KEY = " + BASE_DATA_URL_KEY)
                .add("BASE_DATA_URL_VALUE = " + BASE_DATA_URL_VALUE)
                .add("KEY_ID_KEY = " + KEY_ID_KEY)
                .add("KEY_ID_VALUE = " + KEY_ID_VALUE)
                .add("SECRET_KEY = " + SECRET_KEY)
                .add("SECRET_VALUE = " + SECRET_VALUE)
                .add("USER_AGENT_KEY = " + USER_AGENT_KEY)
                .add("USER_AGENT_VALUE = " + USER_AGENT_VALUE)
                .toString();
    }
}
