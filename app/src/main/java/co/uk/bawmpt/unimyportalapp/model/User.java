package co.uk.bawmpt.unimyportalapp.model;

public class User {
    private String fullName;
    private String studentId;
    private String email;
    private String userId;
    private String username;



    public User() {
    }

    public User(String fullName, String studentId, String email, String userId, String username) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.email = email;
        this.userId = userId;
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String firstName) {
        this.fullName = fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
