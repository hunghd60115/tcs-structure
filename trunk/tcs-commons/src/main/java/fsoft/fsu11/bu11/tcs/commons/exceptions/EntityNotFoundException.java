package fsoft.fsu11.bu11.tcs.commons.exceptions;

import java.net.HttpURLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/17/14
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntityNotFoundException extends AbstractTcsBackendException {

    private static final long serialVersionUID = -8794501923695680267L;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public int getStatusCode() {
        return HttpURLConnection.HTTP_NOT_FOUND;
    }
}
