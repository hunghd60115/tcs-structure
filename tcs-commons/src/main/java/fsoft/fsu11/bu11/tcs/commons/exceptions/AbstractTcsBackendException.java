package fsoft.fsu11.bu11.tcs.commons.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/17/14
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractTcsBackendException extends Exception{

    private static final long serialVersionUID = 2358220252608767619L;

    protected AbstractTcsBackendException() {
    }

    protected AbstractTcsBackendException(String message) {
        super(message);
    }

    protected AbstractTcsBackendException(String message, Throwable cause) {
        super(message, cause);
    }

    protected AbstractTcsBackendException(Throwable cause) {
        super(cause);
    }

    /**
     * Please use constants in {@link java.net.HttpURLConnection}.
     *
     * @return HTTP status code to be returned to the front.
     */
    public abstract int getStatusCode();
}
