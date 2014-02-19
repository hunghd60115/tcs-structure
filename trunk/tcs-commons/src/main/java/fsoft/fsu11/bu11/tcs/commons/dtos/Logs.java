package fsoft.fsu11.bu11.tcs.commons.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/15/14
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Logs {

    private List<Log> logList;


    public Logs() {
    }

    public Logs(List<Log> logList) {
        this.logList = logList;
    }

    public List<Log> getLogList() {
        return logList;
    }
    @JsonProperty(value = "logs")
    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }
}
