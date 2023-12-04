package unistudy.unistudy.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StudygroupMember {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "STUDYGROUP_ID")
    private Studygroup studygroup;

    private Date joinedDate;

    private Boolean accepted;

    public StudygroupMember() {
        this.accepted = false; // 생성자에서 accepted를 false로 초기화
    }

    public StudygroupMember(User user, Studygroup studygroup, Date joinedDate) {
        this.user = user;
        this.studygroup = studygroup;
        this.joinedDate = joinedDate;
        this.accepted = false; // 생성자에서 accepted를 false로 초기화
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
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

    public void setAccepted(Boolean accepted){
        this.accepted = accepted;
    }

    public Boolean getAccepted() {
        return accepted;
    }
}