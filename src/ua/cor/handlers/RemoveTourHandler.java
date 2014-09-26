package ua.cor.handlers;

import ua.database.dao.DAOFactory;
import ua.database.dao.TourDAO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/19/13
 * Time: 8:22 PM
 */
public class RemoveTourHandler extends DefaultHandler {

    public RemoveTourHandler() {
        action="removeTour";
    }

    @Override
    public ExecuteStatus execute(HttpServletRequest request){
        TourDAO tourDAO= DAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL).getTourDAO();
        Integer id=Integer.parseInt(request.getParameter("id"));
        if (tourDAO.removeTour(id))
            return new ExecuteStatus(ExecuteStatus.OK,CABINET_PAGE);
        else
            return new ExecuteStatus(ExecuteStatus.FAIL,"wrong_data",CABINET_PAGE,request.getLocale());

    }
}
