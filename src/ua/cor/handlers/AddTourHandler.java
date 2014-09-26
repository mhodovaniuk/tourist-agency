package ua.cor.handlers;

import ua.database.dao.DAOFactory;
import ua.database.dao.HotelDAO;
import ua.database.dao.TourDAO;
import ua.entities.Tour;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/19/13
 * Time: 8:08 PM
 */
public class AddTourHandler extends DefaultHandler {
    public AddTourHandler() {
        action="addTour";
    }

    @Override
    public ExecuteStatus execute(HttpServletRequest request){
//         action="/controller?action=addTour">
//                Hot: <input type="checkbox" name="hot"><br>
//                Hote ID: <input type="text" name="hotelId"><br>
//                Start Date: <input type="text" name="startDate"><br>
//                Days Count: <input type="text" name="dayCount"><br>
//                Nights Count: <input type="text" name="nightCount"><br>
//                Adult Cost: <input type="text" name="adultCost"><br>
//                Children Cost: <input type="text" name="childrenCost"><br>
//                Discount for regular customer: <input type="text" name="discount"><br>
        Boolean hot=Boolean.valueOf("on".equals(request.getParameter("hot"))?true:false);
        Integer hotelId=Integer.valueOf(request.getParameter("hotelId"));
        Date date= Date.valueOf(request.getParameter("startDate"));
        Integer dayCount=Integer.valueOf(request.getParameter("dayCount")),nightCount=Integer.valueOf(request.getParameter("nightCount"))
                ,adultCost=Integer.valueOf(request.getParameter("adultCost")),childrenCost=Integer.valueOf(request.getParameter("childrenCost"))
                ,discount=Integer.valueOf(request.getParameter("discount"));
        HotelDAO hotelDAO= DAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL).getHotelDAO();
        Tour tour=new Tour(date,hotelDAO.getHotel(hotelId),dayCount,nightCount,adultCost,childrenCost,discount,hot);
        TourDAO tourDAO=DAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL).getTourDAO();
        if (tourDAO.addTour(tour))
            return new ExecuteStatus(ExecuteStatus.OK,CABINET_PAGE);
        else
            return new ExecuteStatus(ExecuteStatus.FAIL,"wrong_data",CABINET_PAGE,request.getLocale());
    }
}
