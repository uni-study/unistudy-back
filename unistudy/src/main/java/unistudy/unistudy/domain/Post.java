package unistudy.unistudy.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne

    private User writer;

    @ManyToOne
    private Studygroup studygroup;
    private String title;
    private String mainText;
    private Date postedAt;
    private Date updatedAt;
    private Date expiredAt;

    public Integer getId() {
        return id;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public User getWriter() {
        return writer;
    }

    public void setStudygroup(Studygroup studygroup) {
        this.studygroup = studygroup;
    }

    public Studygroup getStudygroup() {
        return studygroup;
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
