package unistudy.unistudy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Studygroup {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer leaderId;
    private String name;
    private String description;
    /* 선택 int - value 맵핑 프론트에서! */
    private Integer department;
    private Integer numOfPeople;
    private Integer studyMethod;
    private Integer studyPeriod;
    private Date recruitmentDeadline;
    private Integer currentState;
    private String contact;


    public Integer getId() {
        return id;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setNumOfPeople(Integer numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public Integer getNumOfPeople() {
        return numOfPeople;
    }

    public void setStudyMethod(Integer studyMethod) {
        this.studyMethod = studyMethod;
    }

    public Integer getStudyMethod() {
        return studyMethod;
    }

    public void setStudyPeriod(Integer studyPeriod) {
        this.studyPeriod = studyPeriod;
    }

    public Integer getStudyPeriod() {
        return studyPeriod;
    }

    public void setRecruitmentDeadline(Date recruitmentDeadline) {
        this.recruitmentDeadline = recruitmentDeadline;
    }

    public Date getRecruitmentDeadline() {
        return recruitmentDeadline;
    }

    public void setCurrentState(Integer currentState) {
        this.currentState = currentState;
    }

    public Integer getCurrentState() {
        return currentState;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }
}