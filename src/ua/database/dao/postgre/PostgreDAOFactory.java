package ua.database.dao.postgre;

import ua.database.dao.*;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 12:48 PM
 */
public class PostgreDAOFactory extends DAOFactory {
    @Override
    public HistoryDAO getHistoryDAO() {
        return new PostgreHistoryDAO();
    }

    @Override
    public HotelDAO getHotelDAO() {
        return new PostgreHotelDAO();
    }

    @Override
    public TourDAO getTourDAO() {
        return new PostgreTourDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        return new PostgreUserDAO();
    }
}
