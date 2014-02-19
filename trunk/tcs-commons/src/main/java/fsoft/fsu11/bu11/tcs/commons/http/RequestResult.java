package fsoft.fsu11.bu11.tcs.commons.http;

import com.google.common.io.Closeables;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/19/14
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestResult {

    private int statusCode;
    private Header[] headers;
    private String responseAsString;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public String getResponseAsString() {
        return responseAsString;
    }

    public void setResponseAsString(String responseAsString) {
        this.responseAsString = responseAsString;
    }

    public RequestResult(HttpResponse response) throws IOException {
        this.statusCode = response.getStatusLine().getStatusCode();
        this.headers = response.getAllHeaders();

        HttpEntity entity = response.getEntity();

        responseAsString = EntityUtils.toString(entity);

        EntityUtils.consume(entity);
    }
}
