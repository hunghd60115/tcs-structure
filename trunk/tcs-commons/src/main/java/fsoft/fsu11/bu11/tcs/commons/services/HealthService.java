package fsoft.fsu11.bu11.tcs.commons.services;

import fsoft.fsu11.bu11.tcs.commons.dtos.HealthCheckResponseBase;
import fsoft.fsu11.bu11.tcs.commons.dtos.HealthCheckResponseService1;
import fsoft.fsu11.bu11.tcs.commons.dtos.HealthCheckResponseService3;
import fsoft.fsu11.bu11.tcs.commons.http.RequestBuilder;
import fsoft.fsu11.bu11.tcs.commons.http.RequestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/18/14
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class HealthService extends AbstractTcsBackendService{

    private Logger LOG = LoggerFactory.getLogger(HealthService.class);

    @Override
    protected Logger getLog() {
        return LOG;
    }

    public HealthCheckResponseService1 checkService1(){
        HealthCheckResponseService1 response = new HealthCheckResponseService1();
        Boolean canAccessService2 = canAccessToService2();
        response.setVersion("1.0.0-SNAPSHOT");
        response.setCanAccessToDatabase(canAccessToDataBase());
        response.setCanAccessService2(canAccessService2);
        response.setAllOk(canAccessToDataBase() && canAccessService2);
        return response;
    }

    public HealthCheckResponseBase checkService2(){
        HealthCheckResponseBase response = new HealthCheckResponseBase();
        response.setVersion("1.0.0-SNAPSHOT");
        response.setCanAccessToDatabase(canAccessToDataBase());
        response.setAllOk(canAccessToDataBase());
        return response;
    }

    public HealthCheckResponseService3 checkService3(){
        HealthCheckResponseService3 response = new HealthCheckResponseService3();
        Boolean canAccessService1 = canAccessToService1();
        response.setVersion("1.0.0-SNAPSHOT");
        response.setCanAccessToDatabase(canAccessToDataBase());
        response.setCanAccessService1(canAccessService1);
        response.setAllOk(canAccessToDataBase() && canAccessService1);
        return response;
    }

    /**
     * Check access to Database.
     * @return
     */
    public Boolean canAccessToDataBase(){
        try {
            logDAO.findById(1);
        } catch (Exception ex){
            LOG.error("Access to database error!");
            return false;
        }
        return true;
    }

    public Boolean canAccessToService2(){

        String url = config.getService2HealthCheckUrl();

        RequestBuilder requestBuilder = new RequestBuilder(url);
        RequestResult result = null;
        try {
            result = requestBuilder.getAndCloseConnection();
        } catch (IOException e) {
            LOG.error("Call HealthCheck of service2 fail!: " + e.getMessage());
            return false;
        }

        // Status must be HTTP_OK ( 200 )
        if(result.getStatusCode() != HttpURLConnection.HTTP_OK){
            return false;
        }

        return true;

    }

    public Boolean canAccessToService1(){

        String url = config.getService1HealthCheckUrl();

        RequestBuilder requestBuilder = new RequestBuilder(url);
        RequestResult result = null;
        try {
            result = requestBuilder.getAndCloseConnection();
        } catch (IOException e) {
            LOG.error("Call HealthCheck of service1 fail!: " + e.getMessage());
            return false;
        }

        // Status must be HTTP_OK ( 200 )
        if(result.getStatusCode() != HttpURLConnection.HTTP_OK){
            return false;
        }

        return true;

    }

}
