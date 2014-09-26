package ua.cor.handlers;

import ua.database.dao.DAOFactory;
import ua.database.dao.HotelDAO;
import ua.entities.Hotel;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/19/13
 * Time: 8:29 PM
 */
public class AddHotelHandler extends DefaultHandler {
    public AddHotelHandler() {
        action="addHotel";
    }

    @Override
    public ExecuteStatus execute(HttpServletRequest request){
//        Country: <input type="checkbox" name="country"><br>
//                City: <input type="text" name="city"><br>
//                Name: <input type="text" name="name"><br>
//                Stars: <input type="text" name="stars"><br>
        String country=request.getParameter("country"), city=request.getParameter("city")
                ,name=request.getParameter("name");
        int stars=Integer.parseInt(request.getParameter("stars"));
        Hotel hotel =new Hotel(stars,country,city,name);
        HotelDAO hotelDAO= DAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL).getHotelDAO();
        if (hotelDAO.addHotel(hotel))
            return new ExecuteStatus(ExecuteStatus.OK,CABINET_PAGE);
        else return new ExecuteStatus(ExecuteStatus.FAIL,"wrong_data",CABINET_PAGE,request.getLocale());
    }
}
