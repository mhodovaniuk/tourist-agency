package ua.entities;

import org.apache.log4j.Logger;
import ua.database.dao.DAOFactory;
import ua.database.dao.HistoryDAO;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 12:55 PM
 */
public class User implements Serializable {
    private String email, firstName, lastName, password;
    private boolean admin;
    private static int REGULAR_CUSTOMER_MIN_SPENT;
    private static final Logger logger= Logger.getLogger(History.class);
    protected static Properties properties=new Properties();
    static {
        try {
            properties.load(new FileReader("/home/mychajlo/server.properties"));
            REGULAR_CUSTOMER_MIN_SPENT =Integer.parseInt(properties.getProperty("regular_customer_min_money_spent"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.fatal("Can't load properties file");
        }
    }


    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        admin =false;
    }

    public User(String email, String firstName, String lastName, String password, boolean admin) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.admin = admin;
    }

    public boolean isRegularCustomer(){
        DAOFactory factory=DAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL);
        HistoryDAO history=factory.getHistoryDAO();
        return history.getTotalSpentByUser(this)>= REGULAR_CUSTOMER_MIN_SPENT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
