package unistudy.unistudy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StudygroupMember {
    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private int studygroupId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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