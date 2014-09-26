package ua.cor.handlers;

import ua.database.dao.DAOFactory;
import ua.database.dao.HotelDAO;
import ua.database.dao.TourDAO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/19/13
 * Time: 8:30 PM
 */
public class RemoveHotelHandler extends DefaultHandler {
    public RemoveHotelHandler() {
        action="removeHotel";
    }

    @Override
    public ExecuteStatus execute(HttpServletRequest request){
        HotelDAO hotelDAO= DAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL).getHotelDAO();
        Integer id=Integer.parseInt(request.getParameter("id"));
        if (hotelDAO.removeHotel(id))
            return new ExecuteStatus(ExecuteStatus.OK,CABINET_PAGE);
        else
            return new ExecuteStatus(ExecuteStatus.FAIL,"wrong_data",CABINET_PAGE,request.getLocale());
    }
}
