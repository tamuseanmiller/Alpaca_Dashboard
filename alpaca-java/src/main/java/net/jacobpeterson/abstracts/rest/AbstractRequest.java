package net.jacobpeterson.abstracts.rest;

import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import net.jacobpeterson.util.gson.GsonUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * {@link AbstractRequest} contains methods for HTTP requests.
 */
public abstract class AbstractRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRequest.class);

    protected final Map<String, String> headers;

    /**
     * Instantiates a new {@link AbstractRequest}.
     */
    public AbstractRequest() {
        headers = new HashMap<>();
    }

    /**
     * Invoke GET request.
     *
     * @param abstractRequestBuilder the {@link AbstractRequestBuilder}
     *
     * @return the {@link HttpResponse}
     *
     * @throws UnirestException thrown for {@link UnirestException}s
     */
    public InputStream invokeGet(AbstractRequestBuilder abstractRequestBuilder)
            throws UnirestException, IOException {
        String url = abstractRequestBuilder.buildURL();

        LOGGER.debug("GET URL {}", url);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", headers.get("Authorization"))
                .build();
        Response response = okHttpClient.newCall(request).execute();

        // GetRequest request = Unirest.get(url);

        if (!headers.isEmpty()) {
            // request.headers(headers);

            LOGGER.debug("GET Headers: {}", headers);
        }

        return Objects.requireNonNull(response.body()).byteStream();
        // return request.asBinary();
    }

    /**
     * Invoke HEAD request.
     *
     * @param abstractRequestBuilder the {@link AbstractRequestBuilder}
     *
     * @return the {@link HttpResponse}
     *
     * @throws UnirestException thrown for {@link UnirestException}s
     */
    public InputStream invokeHead(AbstractRequestBuilder abstractRequestBuilder) throws UnirestException, IOException {
        String url = abstractRequestBuilder.buildURL();

        LOGGER.debug("HEAD URL {}", url);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", headers.get("Authorization"))
                .head()
                .build();
        Response response = okHttpClient.newCall(request).execute();

        // GetRequest request = Unirest.head(url);

        if (!headers.isEmpty()) {
            // request.headers(headers);

            LOGGER.debug("HEAD Headers: {}", headers);
        }

        return response.body().byteStream();
    }

    /**
     * Invoke POST request.
     *
     * @param abstractRequestBuilder the {@link AbstractRequestBuilder}
     *
     * @return the {@link HttpResponse}
     *
     * @throws UnirestException thrown for {@link UnirestException}s
     */
    public InputStream invokePost(AbstractRequestBuilder abstractRequestBuilder) throws UnirestException, IOException {
        String url = abstractRequestBuilder.buildURL();

        LOGGER.debug("POST URL: {}", url);

        RequestBody requestBody = RequestBody.create(abstractRequestBuilder.buildBody().getBytes());
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", headers.get("Authorization"))
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();

        if (!headers.isEmpty()) {
            // request.headers(headers);a

            LOGGER.debug("POST Headers: {}", headers);
        }

        String body = abstractRequestBuilder.buildBody();
        if (body != null) {
            // request.body(body);

            LOGGER.debug("POST Body: {}", body);
        }

        return Objects.requireNonNull(response.body()).byteStream();
    }

    /**
     * Invoke PATCH request.
     *
     * @param abstractRequestBuilder the {@link AbstractRequestBuilder}
     *
     * @return the {@link HttpResponse}
     *
     * @throws UnirestException thrown for {@link UnirestException}s
     */
    public InputStream invokePatch(AbstractRequestBuilder abstractRequestBuilder)
            throws UnirestException, IOException {
        String url = abstractRequestBuilder.buildURL();

        LOGGER.debug("PATCH URL {}", url);

        RequestBody requestBody = RequestBody.create(abstractRequestBuilder.buildBody(), MediaType.parse("application/json"));
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", headers.get("Authorization"))
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();

        if (!headers.isEmpty()) {
            // request.headers(headers);

            LOGGER.debug("PATCH Headers: {}", headers);
        }

        String body = abstractRequestBuilder.buildBody();
        if (body != null) {
            // request.body(body);

            LOGGER.debug("PATCH Body: {}", body);
        }

        return Objects.requireNonNull(response.body()).byteStream();
    }

    /**
     * Invoke PUT request.
     *
     * @param abstractRequestBuilder the {@link AbstractRequestBuilder}
     *
     * @return the {@link HttpResponse}
     *
     * @throws UnirestException thrown for {@link UnirestException}s
     */
    public InputStream invokePut(AbstractRequestBuilder abstractRequestBuilder) throws UnirestException, IOException {
        String url = abstractRequestBuilder.buildURL();

        LOGGER.debug("PUT URL {}", url);

        RequestBody requestBody = RequestBody.create(abstractRequestBuilder.buildBody(), MediaType.parse("application/json"));
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", headers.get("Authorization"))
                .put(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();

        if (!headers.isEmpty()) {
            // request.headers(headers);

            LOGGER.debug("PUT Headers: {}", headers);
        }

        String body = abstractRequestBuilder.buildBody();
        if (body != null) {
            // request.body(body);

            LOGGER.debug("PUT Body: " + body);
        }

        return Objects.requireNonNull(response.body()).byteStream();
    }

    /**
     * Invoke DELETE request.
     *
     * @param abstractRequestBuilder the {@link AbstractRequestBuilder}
     *
     * @return the {@link HttpResponse}
     *
     * @throws UnirestException thrown for {@link UnirestException}s
     */
    public InputStream invokeDelete(AbstractRequestBuilder abstractRequestBuilder)
            throws UnirestException, IOException {
        String url = abstractRequestBuilder.buildURL();

        LOGGER.debug("DELETE URL {}", url);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", headers.get("Authorization"))
                .delete()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        // HttpRequestWithBody request = Unirest.delete(url);

        // if (!headers.isEmpty()) {
        //     request.headers(headers);
        //
        //     LOGGER.debug("DELETE Headers: {}", headers);
        // }
        //
        // String body = abstractRequestBuilder.buildBody();
        // if (body != null) {
        //     request.body(body);
        //
        //     LOGGER.debug("DELETE Body: {}", body);
        // }

        return Objects.requireNonNull(response.body()).byteStream();
    }

    /**
     * Invoke OPTIONS request.
     *
     * @param abstractRequestBuilder the {@link AbstractRequestBuilder}
     *
     * @return the {@link HttpResponse}
     *
     * @throws UnirestException thrown for {@link UnirestException}s
     */
    public InputStream invokeOptions(AbstractRequestBuilder abstractRequestBuilder)
            throws UnirestException {
        String url = abstractRequestBuilder.buildURL();

        LOGGER.debug("OPTIONS URL {}", url);

        HttpRequestWithBody request = Unirest.options(url);

        if (!headers.isEmpty()) {
            request.headers(headers);

            LOGGER.debug("OPTIONS Headers: {}", headers);
        }

        String body = abstractRequestBuilder.buildBody();
        if (body != null) {
            request.body(body);

            LOGGER.debug("OPTIONS Body: {}", body);
        }

        return request.asBinary().getBody();
    }

    /**
     * Gets a parsed Object with {@link GsonUtil#GSON} given {@link HttpResponse#getRawBody()} JSON.
     *
     * @param <T>          the generic type
     * @param httpResponse the {@link HttpResponse}
     * @param type         the type of object
     *
     * @return the {@link HttpResponse#getRawBody()} parsed Object
     */
    public <T> T getResponseObject(InputStream httpResponse, Type type) {
        try (JsonReader jsonReader = new JsonReader(new InputStreamReader(httpResponse))) {
            return GsonUtil.GSON.fromJson(jsonReader, type);
        } catch (IOException ioException) {
            LOGGER.error("Could not parse response JSON object", ioException);
            return null;
        }
    }

    /**
     * Gets a {@link JsonElement} with {@link GsonUtil#GSON} given {@link HttpResponse#getRawBody()} JSON.
     *
     * @param httpResponse the {@link HttpResponse}
     *
     * @return the {@link HttpResponse#getRawBody()} {@link JsonElement}
     */
    public JsonElement getResponseJSON(InputStream httpResponse) {
        try (JsonReader jsonReader = new JsonReader(new InputStreamReader(httpResponse))) {
            return GsonUtil.JSON_PARSER.parse(jsonReader);
        } catch (IOException ioException) {
            LOGGER.error("Could not parse response JSON object", ioException);
            return null;
        }
    }
}
