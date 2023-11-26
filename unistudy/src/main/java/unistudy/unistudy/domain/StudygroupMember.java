package unistudy.unistudy.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StudygroupMember {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "STUDYGROUP_ID")
    private Studygroup studygroup;

    private Date joinedDate;



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Studygroup getStudygroup() {
        return studygroup;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public User getUser() {
        return user;
    }

    public void setStudygroup(Studygroup studygroup) {
        this.studygroup = studygroup;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}