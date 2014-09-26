package ua.database.dao.postgre;

import org.apache.log4j.Logger;
import ua.database.dao.DAOFactory;
import ua.database.dao.HotelDAO;
import ua.database.dao.TourDAO;
import ua.entities.Tour;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 1:30 PM
 */
public class PostgreTourDAO extends PostgreEntityDAO implements TourDAO {
    private static final Logger logger = Logger.getLogger(PostgreTourDAO.class);
    public static final String START_DATE = "startDate", ID = "ID", HOTEL_ID = "hotelID", DAY_COUNT = "dayCount", NIGHT_COUNT = "nightCount", ADULT_COST = "adultCost", CHILDREN_COST = "childrenCost", IS_HOT = "isHot", DISCOUNT = "discountForRegularCustomer";

    @Override
    public boolean addTour(Tour tour) {
        String query = properties.getProperty("add_tour");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tour.getHotel().getId());
            preparedStatement.setDate(2, tour.getStartDate());
            preparedStatement.setInt(3, tour.getDayCount());
            preparedStatement.setInt(4, tour.getNightCount());
            preparedStatement.setDouble(5, tour.getAdultCost());
            preparedStatement.setDouble(6, tour.getChildrenCost());
            preparedStatement.setBoolean(7, tour.isHot());
            preparedStatement.setDouble(8, tour.getDiscountForRegularCustomer());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                tour.setId(rs.getInt(1));
                return true;
            } else return false;

        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query + "] "+e.getMessage());
            return false;
        }
    }

    @Override
    public Tour getTour(int id) {
        HotelDAO hotelDAO = PostgreDAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL).getHotelDAO();
        String query = properties.getProperty("get_tour");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Tour tour = null;
            if (rs.next())
                tour = getTour(rs, hotelDAO);
            return tour;

        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query + "] "+e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Tour> getAllTours() {
        String query = properties.getProperty("get_all_tours");
        ArrayList<Tour> res = new ArrayList<Tour>();
        HotelDAO hotelDAO = PostgreDAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL).getHotelDAO();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Tour tour = getTour(rs, hotelDAO);
                res.add(tour);
            }
            return res;
        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query + "] "+e.getMessage());
            return res;
        }
    }

    @Override
    public boolean removeTour(int id) {
        String query = properties.getProperty("remove_tour");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query + "] "+e.getMessage());
            return false;
        }
    }

    private Tour getTour(ResultSet rs, HotelDAO hotelDAO) throws SQLException {
        return new Tour(rs.getDate(START_DATE), hotelDAO.getHotel(rs.getInt(HOTEL_ID)), rs.getInt(ID)
                , rs.getInt(DAY_COUNT), rs.getInt(NIGHT_COUNT), rs.getInt(ADULT_COST)
                , rs.getInt(CHILDREN_COST), rs.getInt(DISCOUNT), rs.getBoolean(IS_HOT));
    }

}
