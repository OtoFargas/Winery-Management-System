package cz.muni.fi.pa165.dto;

/**
 * DTO class for authenticate User
 *
 * @author Oto Fargas
 */
public class UserAuthenticateDTO {
    private Long id;
    private String passwordHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
