package unistudy.unistudy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;
    private int writerId; // Foreign Key referencing User table
    private int studyGroupId; // Foreign Key referencing Studygroup table
    private String title;
    private String mainText;
    private Date postedAt;
    private Date updatedAt;
    private Date expiredAt;
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setStudyGroupId(int studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

    public int getStudyGroupId() {
        return studyGroupId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getMainText() {
        return mainText;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

}
