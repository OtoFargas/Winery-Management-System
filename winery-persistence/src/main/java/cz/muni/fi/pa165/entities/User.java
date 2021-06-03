package cz.muni.fi.pa165.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Entity class for user
 *
 * @author Oto Fargas
 */
@Entity
@Table(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String passwordHash;
	
	@Column(nullable=false,unique=true)
	@NotNull
	private String userName;
	@NotNull
	private String FirstName;
	@NotNull
	private String surname;

    private boolean admin;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getFirstName() {
		return FirstName;
	}


	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return Objects.equals(getUserName(), user.getUserName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserName());
	}

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", surname='" + surname + '\'' +
                ", admin=" + admin +
                '}';
    }
}
