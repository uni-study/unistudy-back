package unistudy.unistudy.DTO;

import java.util.Date;

public class StudygroupMemberDto {
    private Integer id;
    private Integer userId;
    private Integer studygroupId;
    private Date joinedDate;

    private Boolean accepted;

    // Default constructor
    public StudygroupMemberDto() {
    }

    // Constructor with parameters
    public StudygroupMemberDto(Integer id, Integer userId, Integer studygroupId, Date joinedDate, Boolean accepted) {
        this.id = id;
        this.userId = userId;
        this.studygroupId = studygroupId;
        this.joinedDate = joinedDate;
        this.accepted = accepted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStudygroupId() {
        return studygroupId;
    }

    public void setStudygroupId(Integer studygroupId) {
        this.studygroupId = studygroupId;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted){
        this.accepted = accepted;
    }
}
