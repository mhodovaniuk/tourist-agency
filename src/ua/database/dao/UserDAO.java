package ua.database.dao;

import ua.entities.User;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 12:45 PM
 */
public interface UserDAO {
    User getUser(String email, String password);
    boolean addUser(User user);
}
