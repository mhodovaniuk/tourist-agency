package ua.database.dao;

import ua.database.dao.postgre.PostgreDAOFactory;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 12:38 PM
 */
public abstract class DAOFactory {
    public static enum FactoryType {POSTGRE_SQL}

    public abstract HistoryDAO getHistoryDAO();
    public abstract HotelDAO getHotelDAO();
    public abstract TourDAO getTourDAO();
    public abstract UserDAO getUserDAO();

    public static DAOFactory getFactory(FactoryType whichFactory){
        switch (whichFactory){
            case POSTGRE_SQL:
                return new PostgreDAOFactory();
            default:
                return null;
        }
    }
}
