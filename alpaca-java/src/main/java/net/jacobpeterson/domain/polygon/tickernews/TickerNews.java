
package net.jacobpeterson.domain.polygon.tickernews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;


/**
 * See <a href="https://polygon.io/docs/#!/Reference/get_v1_meta_symbols_symbol_news">https://polygon.io/docs/#!/Reference/get_v1_meta_symbols_symbol_news</a>
 * <p>
 * 
 * 
 */
public class TickerNews implements Serializable
{

    /**
     * symbols
     * <p>
     * 
     * 
     */
    @SerializedName("symbols")
    @Expose
    private ArrayList<String> symbols;
    /**
     * Name of the article
     * <p>
     * 
     * 
     */
    @SerializedName("title")
    @Expose
    private String title;
    /**
     * URL of this article
     * <p>
     * 
     * 
     */
    @SerializedName("url")
    @Expose
    private String url;
    /**
     * Source of this article
     * <p>
     * 
     * 
     */
    @SerializedName("source")
    @Expose
    private String source;
    /**
     * Short summary of the article
     * <p>
     * 
     * 
     */
    @SerializedName("summary")
    @Expose
    private String summary;
    /**
     * URL of the image for this article, if found
     * <p>
     * 
     * 
     */
    @SerializedName("image")
    @Expose
    private String image;
    /**
     * Timestamp of the article
     * <p>
     * 
     * 
     */
    @SerializedName("timestamp")
    @Expose
    private ZonedDateTime timestamp;
    /**
     * keywords
     * <p>
     * 
     * 
     */
    @SerializedName("keywords")
    @Expose
    private ArrayList<String> keywords;
    private final static long serialVersionUID = -8640156159869669131L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TickerNews() {
    }

    /**
     * 
     * @param summary
     * @param image
     * @param keywords
     * @param source
     * @param title
     * @param symbols
     * @param url
     * @param timestamp
     */
    public TickerNews(ArrayList<String> symbols, String title, String url, String source, String summary, String image, ZonedDateTime timestamp, ArrayList<String> keywords) {
        super();
        this.symbols = symbols;
        this.title = title;
        this.url = url;
        this.source = source;
        this.summary = summary;
        this.image = image;
        this.timestamp = timestamp;
        this.keywords = keywords;
    }

    /**
     * symbols
     * <p>
     * 
     * 
     */
    public ArrayList<String> getSymbols() {
        return symbols;
    }

    /**
     * symbols
     * <p>
     * 
     * 
     */
    public void setSymbols(ArrayList<String> symbols) {
        this.symbols = symbols;
    }

    /**
     * Name of the article
     * <p>
     * 
     * 
     */
    public String getTitle() {
        return title;
    }

    /**
     * Name of the article
     * <p>
     * 
     * 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * URL of this article
     * <p>
     * 
     * 
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL of this article
     * <p>
     * 
     * 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Source of this article
     * <p>
     * 
     * 
     */
    public String getSource() {
        return source;
    }

    /**
     * Source of this article
     * <p>
     * 
     * 
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Short summary of the article
     * <p>
     * 
     * 
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Short summary of the article
     * <p>
     * 
     * 
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * URL of the image for this article, if found
     * <p>
     * 
     * 
     */
    public String getImage() {
        return image;
    }

    /**
     * URL of the image for this article, if found
     * <p>
     * 
     * 
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Timestamp of the article
     * <p>
     * 
     * 
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp of the article
     * <p>
     * 
     * 
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * keywords
     * <p>
     * 
     * 
     */
    public ArrayList<String> getKeywords() {
        return keywords;
    }

    /**
     * keywords
     * <p>
     * 
     * 
     */
    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TickerNews.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbols");
        sb.append('=');
        sb.append(((this.symbols == null)?"<null>":this.symbols));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("source");
        sb.append('=');
        sb.append(((this.source == null)?"<null>":this.source));
        sb.append(',');
        sb.append("summary");
        sb.append('=');
        sb.append(((this.summary == null)?"<null>":this.summary));
        sb.append(',');
        sb.append("image");
        sb.append('=');
        sb.append(((this.image == null)?"<null>":this.image));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
        sb.append(',');
        sb.append("keywords");
        sb.append('=');
        sb.append(((this.keywords == null)?"<null>":this.keywords));
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
        result = ((result* 31)+((this.summary == null)? 0 :this.summary.hashCode()));
        result = ((result* 31)+((this.image == null)? 0 :this.image.hashCode()));
        result = ((result* 31)+((this.keywords == null)? 0 :this.keywords.hashCode()));
        result = ((result* 31)+((this.source == null)? 0 :this.source.hashCode()));
        result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
        result = ((result* 31)+((this.symbols == null)? 0 :this.symbols.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TickerNews) == false) {
            return false;
        }
        TickerNews rhs = ((TickerNews) other);
        return (((((((((this.summary == rhs.summary)||((this.summary!= null)&&this.summary.equals(rhs.summary)))&&((this.image == rhs.image)||((this.image!= null)&&this.image.equals(rhs.image))))&&((this.keywords == rhs.keywords)||((this.keywords!= null)&&this.keywords.equals(rhs.keywords))))&&((this.source == rhs.source)||((this.source!= null)&&this.source.equals(rhs.source))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.symbols == rhs.symbols)||((this.symbols!= null)&&this.symbols.equals(rhs.symbols))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
    }

}
