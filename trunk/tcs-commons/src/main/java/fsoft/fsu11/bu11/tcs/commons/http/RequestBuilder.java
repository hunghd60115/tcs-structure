package fsoft.fsu11.bu11.tcs.commons.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/19/14
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestBuilder {

    private Logger LOG = LoggerFactory.getLogger(RequestBuilder.class);

    private String url;
    private HttpClient client;

    public RequestBuilder() {
    }

    public RequestBuilder(HttpClient client, String url){
        this.client = client;
        this.url = url;
    }

    public RequestBuilder(String url){
        this(new DefaultHttpClient(new PoolingClientConnectionManager()), url);
    }

    public RequestResult getAndCloseConnection() throws IOException {
        HttpGet getMethod = new HttpGet(this.url);
        HttpResponse response = null;
        try {
            response = client.execute(getMethod);

            RequestResult result = new RequestResult(response);
            if (LOG.isDebugEnabled()) {
                LOG.debug("GET '" + url + "' return status code : " + result.getStatusCode());
            }

            return result;
        } finally {

            if (response != null && response.getEntity() != null) {
                response.getEntity().getContent().close();
            }

            // Release the connection.
            getMethod.releaseConnection();

            // Close the connection.
            if (client != null && client.getConnectionManager() != null) {
                client.getConnectionManager().shutdown();
            }
        }
    }
}
