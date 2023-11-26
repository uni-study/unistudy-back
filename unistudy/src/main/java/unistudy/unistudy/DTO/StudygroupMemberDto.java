package unistudy.unistudy.DTO;

import java.util.Date;

public class StudygroupMemberDto {
    private int id;
    private int userId;
    private int studygroupId;
    private Date joinedDate;

    // Default constructor
    public StudygroupMemberDto() {
    }

    // Constructor with parameters
    public StudygroupMemberDto(int id, int userId, int studygroupId, Date joinedDate) {
        this.id = id;
        this.userId = userId;
        this.studygroupId = studygroupId;
        this.joinedDate = joinedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStudygroupId() {
        return studygroupId;
    }

    public void setStudygroupId(int studygroupId) {
        this.studygroupId = studygroupId;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }
}
