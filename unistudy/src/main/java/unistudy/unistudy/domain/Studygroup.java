package unistudy.unistudy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Studygroup {

    @Id
    @GeneratedValue
    private int id;
    private int leaderId;
    private String title;
    private String description;
    private int department;
    private int numOfPeople;
    private int studyMethod;
    private int studyPeriod;
    private Date recruitmentDeadline;
    private int currentState;
    private String contact;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getDepartment() {
        return department;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setStudyMethod(int studyMethod) {
        this.studyMethod = studyMethod;
    }

    public int getStudyMethod() {
        return studyMethod;
    }

    public void setStudyPeriod(int studyPeriod) {
        this.studyPeriod = studyPeriod;
    }

    public int getStudyPeriod() {
        return studyPeriod;
    }

    public void setRecruitmentDeadline(Date recruitmentDeadline) {
        this.recruitmentDeadline = recruitmentDeadline;
    }

    public Date getRecruitmentDeadline() {
        return recruitmentDeadline;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }
}