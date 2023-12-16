package unistudy.unistudy.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String email;
    private String pw;
    private String name;

    // A user can write many posts
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    // A user can join many studygroups (As User-Studygroup relation is many to many, middle table StudygroupMembers is used)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudygroupMember> studygroupMembers;

    // getter, setter

    public Integer getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getPw() {
        return pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setStudygroupMembers(List<StudygroupMember> studygroupMembers) {this.studygroupMembers = studygroupMembers;}

    public List<StudygroupMember> getStudygroupMembers() {return studygroupMembers;}
}
