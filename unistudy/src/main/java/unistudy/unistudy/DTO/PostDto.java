package unistudy.unistudy.DTO;

import java.util.Date;

public class PostDto {
    private Integer id;
    private String title;
    private String mainText;
    private Date postedAt;
    private Date updatedAt;
    private Date expiredAt;
    private Integer studygroupId;
    private Integer writerId;

    // Default constructor
    public PostDto() {
    }

    // Constructor with parameters
    public PostDto(Integer id, String title, String mainText, Date postedAt, Date updatedAt, Date expiredAt, Integer studygroupId, Integer writerId) {
        this.id = id;
        this.title = title;
        this.mainText = mainText;
        this.postedAt = postedAt;
        this.updatedAt = updatedAt;
        this.expiredAt = expiredAt;
        this.studygroupId = studygroupId;
        this.writerId = writerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Integer getStudygroupId() {
        return studygroupId;
    }

    public void setStudygroupId(Integer studygroupId) {
        this.studygroupId = studygroupId;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }
}
