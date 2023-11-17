package unistudy.unistudy.domain;

public class User {
    private int id;
    private String email;
    private String pw;
    private String name;


    // setId 만들 필요 없나?

    public void setId(Integer id){
        this.id = id;
    }
    public int getId() {
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
}