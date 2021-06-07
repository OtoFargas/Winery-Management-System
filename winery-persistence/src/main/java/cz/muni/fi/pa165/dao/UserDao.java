package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.User;
import java.util.List;

/**
 * Representation of DAO for User
 *
 * @author Oto Fargas
 */
public interface UserDao {
	/**
	 * Creates persistent representation of user in database
	 *
	 * @param user to be created
	 */
	void create(User user);

	/**
	 * Finds user by id
	 *
	 * @param id of desired user
	 * @return found user
	 */
	 User findById(Long id);

	/**
	 * Finds wine by name
	 *
	 * @param userName of desired user
	 * @return found user
	 */
	 User findUserByUserName(String userName);

	/**
	 * Finds all users in database
	 *
	 * @return list of all users
	 */
	  List<User> findAll();
}
