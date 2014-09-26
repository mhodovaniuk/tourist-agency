package ua.database.dao.postgre;

import org.apache.log4j.Logger;
import ua.database.dao.UserDAO;
import ua.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 1:31 PM
 */
public class PostgreUserDAO extends PostgreEntityDAO implements UserDAO {
    private static final Logger logger = Logger.getLogger(PostgreUserDAO.class);
    public static final String EMAIL="email",FIRST_NAME="firstName",LAST_NAME="lastName"
            ,PASSWORD="password",IS_ADMIN="isAdmin";

    @Override
    public User getUser(String email, String password) {
        String query = properties.getProperty("get_user");
        try {
            PreparedStatement preparedStatemen = connection.prepareStatement(query);
            preparedStatemen.setString(1, email);
            preparedStatemen.setString(2, password);
            ResultSet resultSet = preparedStatemen.executeQuery();
            User res = null;
            if (resultSet.next())
                res = new User(resultSet.getString(EMAIL), resultSet.getString(FIRST_NAME)
                        , resultSet.getString(LAST_NAME), resultSet.getString(PASSWORD)
                        , resultSet.getBoolean(IS_ADMIN));
            return res;
        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query+"] "+e.getMessage());
            return null;
        }
    }

    @Override
    public boolean addUser(User user) {
        String query=properties.getProperty("add_user");
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query+"] "+e.getMessage());
            return false;
        }
    }
}
