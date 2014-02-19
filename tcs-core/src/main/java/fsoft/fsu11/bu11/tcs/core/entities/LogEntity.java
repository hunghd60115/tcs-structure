package fsoft.fsu11.bu11.tcs.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/17/14
 * Time: 11:22 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "tbl_logs")
public class LogEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "channelCode", nullable = false)
    private String channelCode;

    @Column(name = "startTime", nullable = false)
    private String startTime;

    @Column(name = "endTime", nullable = false)
    private String endTime;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "detail")
    private String detail;

    @Column(name = "score")
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof LogEntity))
            return false;
        LogEntity castOther = (LogEntity) other;

        return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null &&
                this.getId().equals(castOther.getId())));
    }
}
