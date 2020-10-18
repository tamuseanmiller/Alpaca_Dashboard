package net.jacobpeterson.polygon.rest.exception;

import com.mashape.unirest.http.HttpResponse;
import net.jacobpeterson.abstracts.rest.exception.AbstractAPIRequestException;

import java.io.InputStream;

/**
 * The Class PolygonAPIException.
 */
public class PolygonAPIRequestException extends AbstractAPIRequestException {

    /**
     * Instantiates a new Polygon api request exception.
     *
     * @param httpResponse the http response
     */
    public PolygonAPIRequestException(HttpResponse<InputStream> httpResponse) {
        super("Polygon", httpResponse);
    }

    @Override
    protected void parseAPIExceptionMessage() {
        super.parseStandardAPIExceptionResponse();
    }
}
