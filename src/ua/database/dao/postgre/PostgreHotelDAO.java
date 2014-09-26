package ua.database.dao.postgre;

import org.apache.log4j.Logger;
import ua.database.dao.DAOFactory;
import ua.database.dao.HotelDAO;
import ua.entities.Hotel;

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
public class PostgreHotelDAO extends PostgreEntityDAO implements HotelDAO {
    private static final Logger logger = Logger.getLogger(PostgreHotelDAO.class);
    public static final String ID="ID", COUNTRY="country", CITY="city", STARS="stars",NAME="name";

    @Override
    public boolean addHotel(Hotel hotel) {
        String query = properties.getProperty("add_hotel");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, hotel.getStars());
            preparedStatement.setString(2, hotel.getCountry());
            preparedStatement.setString(3, hotel.getCity());
            preparedStatement.setString(4, hotel.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                hotel.setId(resultSet.getInt(ID));
                return true;
            } else
                return false;
        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query + "] "+e.getMessage());
            return false;
        }
    }

    @Override
    public Hotel getHotel(int id) {
        String query = properties.getProperty("get_hotel");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Hotel res;
            if (rs.next()) {
                res=getHotel(rs);
                return res;
            } else
                return null;
        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query + "] "+e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList getAllHotels() {
        String query = properties.getProperty("get_all_hotels");
        ArrayList<Hotel> res = new ArrayList<Hotel>();
        HotelDAO hotelDAO = PostgreDAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL).getHotelDAO();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Hotel hotel = getHotel(rs);
                res.add(hotel);
            }
            return res;
        } catch (SQLException e) {
            logger.error("Can't get statement or execute query [" + query + "] "+e.getMessage());
            return res;
        }
    }

    @Override
    public boolean removeHotel(int id) {
        String query = properties.getProperty("remove_hotel");
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

    private Hotel getHotel(ResultSet rs) throws SQLException {
        return new Hotel(rs.getInt(STARS),rs.getInt(ID),rs.getString(COUNTRY)
                ,rs.getString(CITY),rs.getString(NAME));
    }
}
