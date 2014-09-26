package ua.database;

import org.apache.log4j.Logger;
import org.postgresql.Driver;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;


/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/8/13
 * Time: 7:17 PM
 */
public class DBConnectionPool {
    private static final String DB_PROPERTIES_PATH = "/home/mychajlo/database.properties";
    private static final Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(DBConnectionPool.class);
    private static DBConnectionPool ourInstance = new DBConnectionPool();
    private LinkedList<Connection> freeConnections=new LinkedList<Connection>();
    private String url, userName, password,driverName;
    private int maxConnectionCount;

    public synchronized static DBConnectionPool getInstance() {
        return ourInstance;
    }

    private DBConnectionPool() {
        try {
            //logger.fatal(System.getProperty("user.dir"));
            properties.load(new FileReader(DB_PROPERTIES_PATH));
            driverName=properties.getProperty("driverName");
            url=properties.getProperty("url");
            userName=properties.getProperty("userName");
            password=properties.getProperty("password");
            maxConnectionCount=Integer.parseInt(properties.getProperty("maxConnectionCount"));
            loadDriver();
        } catch (IOException e) {
            logger.fatal("Can't load properties file " + DB_PROPERTIES_PATH);
        }
    }

    private void loadDriver() {
        try {
            Driver driver = (Driver)Class.forName(driverName).newInstance();
            DriverManager.registerDriver(driver);
            logger.info("Registered JDBC driver");
        }
        catch (Exception e) {
            logger.fatal("Can't register JDBC driver");
        }
    }

    public synchronized Connection getConnection(){
        Connection connection = null;
        if (!freeConnections.isEmpty()) {
            connection = freeConnections.pop();
            try {
                if (connection.isClosed()) {
                    logger.warn("Removed bad connection");
                    // Try again recursively
                    connection = getConnection();
                }
            }
            catch (SQLException e) {
                logger.warn("Removed bad connection ");
                // Try again recursively
                connection = getConnection();
            }
            catch (Exception e) {
                logger.warn("Removed bad connection ");
                // Try again recursively
                connection = getConnection();
            }
        } else {
            connection = newConnection();
            if (connection==null)
                logger.fatal("Can't create and get connection for " + url);
        }
        return connection;
    }

    private Connection newConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
            logger.info("Created a new connection in pool");
        }
        catch (SQLException e) {
            logger.error("Can't create a new connection for " + url);
            return null;
        }
        return connection;
    }

    public synchronized void freeConnection(Connection connection) {
        // Put the connection at the end of the List
        if ( (connection != null) && (freeConnections.size() <= maxConnectionCount) )  {
            freeConnections.add(connection);
        }
    }

    public synchronized void release() {
        Iterator<Connection> allConnections = freeConnections.iterator();
        while (allConnections.hasNext()) {
            Connection con = allConnections.next();
            try {
                con.close();
                logger.info("Closed connection for pool");
            }
            catch (SQLException e) {
                logger.info("Can't close connection for pool");
            }
        }
        freeConnections.clear();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        release();
    }
}