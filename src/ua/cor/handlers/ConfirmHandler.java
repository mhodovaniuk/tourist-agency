package ua.cor.handlers;

import ua.database.dao.DAOFactory;
import ua.database.dao.HistoryDAO;
import ua.entities.History;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/18/13
 * Time: 5:08 PM
 */
public class ConfirmHandler extends DefaultHandler {
    public ConfirmHandler() {
        action="confirm";
    }

    @Override
    public ExecuteStatus execute(HttpServletRequest request){
        String uid=request.getParameter("uid");
        History history=(History)request.getSession().getAttribute(uid);
        request.getSession().removeAttribute(uid);
        if (history!=null) {
            DAOFactory factory=DAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL);
            HistoryDAO historyDAO=factory.getHistoryDAO();
            if (historyDAO.addHistory(history))
                return new ExecuteStatus(ExecuteStatus.OK,"buy_ok",MAIN_PAGE,request.getLocale());
            else return new  ExecuteStatus(ExecuteStatus.FAIL,"buy_error",MAIN_PAGE,request.getLocale());

        } else
            return new ExecuteStatus(ExecuteStatus.FAIL,MAIN_PAGE);
    }
}
