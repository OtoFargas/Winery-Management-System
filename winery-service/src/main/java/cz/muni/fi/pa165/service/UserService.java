package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An interface that defines a service access to the User entity.
 *
 * @author Oto Fargas
 */
@Service
public interface UserService {

	/**
	 * Register the given user with the given unencrypted password.
	 */
	void registerUser(User u, String unencryptedPassword);

	/**
	 * Get all registered users
	 */
	List<User> getAllUsers();

	/**
	 * Try to authenticate a user. Return true only if the hashed password matches the records.
	 */
	boolean authenticate(User u, String password);

	/**
	 * Check if the given user is admin.
	 */
	boolean isAdmin(User u);

	/**
	 * Finds user with given id.
	 *
	 * @param userId to be looked for
	 * @return user with given id
	 */
	User findUserById(Long userId);

	/**
	 * Finds user with given name.
	 *
	 * @param userName to be looked for
	 * @return user with given name
	 */
	User findUserByUserName(String userName);
}
