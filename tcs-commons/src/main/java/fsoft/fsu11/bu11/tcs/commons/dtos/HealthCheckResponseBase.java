package fsoft.fsu11.bu11.tcs.commons.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/18/14
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthCheckResponseBase {

    private String version;
    private Boolean canAccessToDatabase;
    private Boolean allOk;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getCanAccessToDatabase() {
        return canAccessToDatabase;
    }

    public void setCanAccessToDatabase(Boolean canAccessToDatabase) {
        this.canAccessToDatabase = canAccessToDatabase;
    }

    public Boolean getAllOk() {
        return allOk;
    }

    public void setAllOk(Boolean allOk) {
        this.allOk = allOk;
    }
}
