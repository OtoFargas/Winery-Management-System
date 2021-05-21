package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the UserService.
 *
 * @author Oto Fargas
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private final PasswordEncoder encoder = new Pbkdf2PasswordEncoder();

    @Override
    public void registerUser(User u, String unencryptedPassword) {
        u.setPasswordHash(encoder.encode(unencryptedPassword));
        userDao.create(u);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public boolean authenticate(User u, String password) {
        return encoder.matches(password, u.getPasswordHash());
    }

    @Override
    public boolean isAdmin(User u) {
        User foundUser = findUserById(u.getId());
        return foundUser.isAdmin();
    }

    @Override
    public User findUserById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

}
