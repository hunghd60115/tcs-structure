package fsoft.fsu11.bu11.tcs.commons.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/18/14
 * Time: 5:13 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthCheckResponseService1 extends HealthCheckResponseBase{

    private Boolean canAccessService2;

    public Boolean getCanAccessService2() {
        return canAccessService2;
    }

    public void setCanAccessService2(Boolean canAccessService2) {
        this.canAccessService2 = canAccessService2;
    }
}
