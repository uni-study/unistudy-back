package unistudy.unistudy.domain;

// login field(email, pw)
public class LoginRequest {
    private String email;
    private String pw;

    public String getPw() {
        return pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
