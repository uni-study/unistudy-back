package unistudy.unistudy.domain;

import javax.persistence.*;
import java.util.Date;

// Comment Entity on blog posts
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Post post;
    @ManyToOne(cascade = CascadeType.REMOVE)

    private User writer;

    private String mainText;
    private Date postedAt;

    // Default constructor
    public Comment() {
    }

    // Constructor with parameters
    public Comment(Integer id, Post post, String mainText, Date postedAt) {
        this.id = id;
        this.post = post;
        this.mainText = mainText;
        this.postedAt = postedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    public User getWriter() {
        return writer;
    }
    public void setWriter(User writer) {
        this.writer = writer;
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
