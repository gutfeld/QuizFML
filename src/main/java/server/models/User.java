package server.models;


public class User {

    private int UserId;
    private String username;
    private String password;
    private int type;
    private String firstName;
    private String lastName;


    public User(String username, String password, int type, String firstName, String lastName) {

        this.username = username;
        this.password = password;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public User() {

    }


    public int getUserId() {
        return UserId;
    }

    public void setUserId(int id) {
        this.UserId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

