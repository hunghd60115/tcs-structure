package fsoft.fsu11.bu11.tcs.commons.dtos;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/18/14
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class HealthCheckResponseService3 extends HealthCheckResponseBase{

    private Boolean canAccessService1;

    public Boolean getCanAccessService1() {
        return canAccessService1;
    }

    public void setCanAccessService1(Boolean canAccessService1) {
        this.canAccessService1 = canAccessService1;
    }
}
