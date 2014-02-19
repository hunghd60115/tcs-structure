package fsoft.fsu11.bu11.tcs.commons.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/15/14
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Log {

    private Integer id;
    private String channelCode;
    private String startTime;
    private String endTime;
    private String type;
    private String detail;
    private Integer score;

    public Integer getId() {
        return id;
    }
    @JsonProperty(value = "id")
    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelCode() {
        return channelCode;
    }
    @JsonProperty(value = "channelCode")
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getStartTime() {
        return startTime;
    }
    @JsonProperty(value = "startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    @JsonProperty(value = "endTime")
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }
    @JsonProperty(value = "type")
    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }
    @JsonProperty(value = "detail")
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getScore() {
        return score;
    }
    @JsonProperty(value = "score")
    public void setScore(Integer score) {
        this.score = score;
    }
}
