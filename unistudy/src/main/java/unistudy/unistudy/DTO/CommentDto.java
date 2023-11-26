package unistudy.unistudy.DTO;

import java.util.Date;

public class CommentDto {
    private Integer id;
    private Integer postId;
    private Integer writerId;
    private String mainText;
    private Date postedAt;

    // Default constructor
    public CommentDto() {
    }

    // Constructor with parameters
    public CommentDto(Integer id, Integer postId,Integer writerId, String mainText, Date postedAt) {
        this.id = id;
        this.postId = postId;
        this.writerId = writerId;
        this.mainText = mainText;
        this.postedAt = postedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId){
        this.writerId = writerId;
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
}
