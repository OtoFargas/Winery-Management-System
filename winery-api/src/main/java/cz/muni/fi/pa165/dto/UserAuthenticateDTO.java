package cz.muni.fi.pa165.dto;

/**
 * DTO class for authenticate User
 *
 * @author Oto Fargas
 */
public class UserAuthenticateDTO {
    private Long id;
    private String password;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserAuthenticateDTO{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
