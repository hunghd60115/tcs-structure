package fsoft.fsu11.bu11.tcs.commons.exceptions;

import java.net.HttpURLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/17/14
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class BadRequestException extends AbstractTcsBackendException{

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    @Override
    public int getStatusCode() {
        return HttpURLConnection.HTTP_BAD_REQUEST;
    }
}
