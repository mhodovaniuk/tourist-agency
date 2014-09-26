package ua.cor.handlers;

import ua.database.dao.DAOFactory;
import ua.database.dao.TourDAO;
import ua.entities.History;
import ua.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/18/13
 * Time: 3:32 PM
 */
public class BuyHandler extends DefaultHandler {

    public BuyHandler() {
        action="buy";
    }

    @Override
    public ExecuteStatus execute(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        int tourId,adultCount,childrenCount;
        try{
            tourId=Integer.parseInt(request.getParameter("id"))                 ;
            adultCount=Integer.parseInt(request.getParameter("adultCount"));
            childrenCount=Integer.parseInt(request.getParameter("childrenCount"));
        } catch (NumberFormatException e){
            return new ExecuteStatus(ExecuteStatus.FAIL,"wrong_data",BUY_PAGE,request.getLocale());
        }
        if (user!=null){
            DAOFactory factory=DAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL);
            TourDAO tourDAO= factory.getTourDAO();
            History history=new History(tourDAO.getTour(tourId),user,adultCount,childrenCount);
            String uid=history.getUID();
            request.setAttribute("uid",uid);
            request.setAttribute("cost",history.getTotalCost());
            request.getSession().setAttribute(uid,history);
            return new ExecuteStatus(ExecuteStatus.OK,CONFIRM_PAGE);
        } else {
            return new ExecuteStatus(ExecuteStatus.FAIL,"please_login",LOGIN_PAGE,request.getLocale());
        }
    }
}
