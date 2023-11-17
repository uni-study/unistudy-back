package unistudy.unistudy.domain;

public class StudygroupMember {
    private int studygroupMemberId;
    private int userId;
    private int studygroupId;

    public void setStudygroupMemberId(int studygroupMemberId) {
        this.studygroupMemberId = studygroupMemberId;
    }

    public int getStudygroupMemberId() {
        return studygroupMemberId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setStudygroupId(int studygroupId) {
        this.studygroupId = studygroupId;
    }

    public int getStudygroupId() {
        return studygroupId;
    }
}