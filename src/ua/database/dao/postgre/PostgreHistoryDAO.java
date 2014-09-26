package ua.database.dao.postgre;

import org.apache.log4j.Logger;
import ua.database.dao.DAOFactory;
import ua.database.dao.HistoryDAO;
import ua.database.dao.TourDAO;
import ua.entities.History;

import ua.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 1:16 PM
 */
public class PostgreHistoryDAO extends PostgreEntityDAO implements HistoryDAO {
    private static final Logger logger= Logger.getLogger(PostgreHistoryDAO.class);
    public static final String ID="ID";
    public static final String USER_ID="userID";
    public static final String TOUR_ID="tourID";


    public static final String ADULT_COUNT="adultCount";
    public static final String CHILDREN_COUNT="childrenCount";
    public static final String TOTAL_COST="totalCost";

    @Override
    public ArrayList<History> getHistoryByUser(User user) {
        String query = properties.getProperty("get_history_by_user");
        ArrayList<History> res = new ArrayList<History>();
        TourDAO tourDAO = PostgreDAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL).getTourDAO();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                History history=new History(tourDAO.getTour(rs.getInt(TOUR_ID)),user
                        ,rs.getInt(ID),rs.getInt(ADULT_COUNT),rs.getInt(CHILDREN_COUNT)
                        ,rs.getInt(TOTAL_COST));
                res.add(history);
            }
            return res;
        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query + "] "+e.getMessage());
            return res;
        }
    }

    @Override
    public boolean addHistory(History history) {
        String query = properties.getProperty("add_history");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, history.getUser().getEmail());
            preparedStatement.setInt(2, history.getTour().getId());
            preparedStatement.setInt(3, history.getAdultCount());
            preparedStatement.setInt(4, history.getChildrenCount());
            preparedStatement.setInt(5, history.getTotalCost());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                history.setId(rs.getInt(1));
                return true;
            } else return false;
        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query + "] "+e.getMessage());
            return false;
        }
    }

    @Override
    public int getTotalSpentByUser(User user) {
        String query = properties.getProperty("get_total_spent_by_user");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else return 0;
        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query + "] "+e.getMessage());
            return 0;
        }
    }

}