package ua.database.dao.postgre;

import org.apache.log4j.Logger;
import ua.database.DBConnectionPool;
import ua.database.dao.postgre.PostgreUserDAO;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 2:28 PM
 */
public class PostgreEntityDAO {
    private static final Logger logger= Logger.getLogger(PostgreUserDAO.class);
    protected static Properties properties=new Properties();
    protected Connection connection;
    static {
        try {
            properties.load(new FileReader("/home/mychajlo/queries.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.fatal("Can't load properties file");
        }
    }

    public PostgreEntityDAO(){
        connection=DBConnectionPool.getInstance().getConnection();
    }

    @Override
    protected void finalize() throws Throwable {
        DBConnectionPool.getInstance().freeConnection(connection);
    }
}
