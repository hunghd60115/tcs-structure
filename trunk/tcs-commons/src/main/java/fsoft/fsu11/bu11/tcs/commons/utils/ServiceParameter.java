package fsoft.fsu11.bu11.tcs.commons.utils;

/**
 * Parameter name in request.
 * @author HungHD1
 */
public interface ServiceParameter {

    public final String HTTP_RESPONSE_ERROR_BODY_JSON_FORMAT = "{ \"errorType\": \"%s\", \"errorMessage\": \"%s\" }";

    public final String ID = "id";
    public final String VIEW_LOG_PATH = "/log/{" + ID + "}";
    public final String ADD_LOG_PATH = "/log/";
    public final String UPDATE_LOG_PATH = "/log/{" + ID + "}";
    public final String DELETE_LOG_PATH = "/log/{" + ID + "}";

    public final String HEALTH_CHECK_PATH = "/health";

}
