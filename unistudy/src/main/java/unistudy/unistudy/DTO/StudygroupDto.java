package unistudy.unistudy.DTO;

import java.util.Date;

public class StudygroupDto {
    private Integer id;
    private Integer leaderId;
    private String name;
    private String description;
    private Integer department;
    private Integer numOfPeople;
    private Integer studyMethod;
    private Integer studyPeriod;
    private Date recruitmentDeadline;
    private Integer currentState;
    private String contact;

    // Default constructor
    public StudygroupDto() {
    }

    // Constructor with parameters
    public StudygroupDto(Integer id, Integer leaderId, String name, String description, Integer department,
                         Integer numOfPeople, Integer studyMethod, Integer studyPeriod, Date recruitmentDeadline,
                         Integer currentState, String contact) {
        this.id = id;
        this.leaderId = leaderId;
        this.name = name;
        this.description = description;
        this.department = department;
        this.numOfPeople = numOfPeople;
        this.studyMethod = studyMethod;
        this.studyPeriod = studyPeriod;
        this.recruitmentDeadline = recruitmentDeadline;
        this.currentState = currentState;
        this.contact = contact;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(Integer numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public Integer getStudyMethod() {
        return studyMethod;
    }

    public void setStudyMethod(Integer studyMethod) {
        this.studyMethod = studyMethod;
    }

    public Integer getStudyPeriod() {
        return studyPeriod;
    }

    public void setStudyPeriod(Integer studyPeriod) {
        this.studyPeriod = studyPeriod;
    }

    public Date getRecruitmentDeadline() {
        return recruitmentDeadline;
    }

    public void setRecruitmentDeadline(Date recruitmentDeadline) {
        this.recruitmentDeadline = recruitmentDeadline;
    }

    public Integer getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Integer currentState) {
        this.currentState = currentState;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
