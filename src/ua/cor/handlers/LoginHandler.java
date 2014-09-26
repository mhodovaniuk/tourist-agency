package ua.cor.handlers;

import ua.database.dao.DAOFactory;
import ua.database.dao.UserDAO;
import ua.database.dao.postgre.PostgreDAOFactory;
import ua.entities.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/17/13
 * Time: 7:00 PM
 */
public class LoginHandler extends DefaultHandler {
    public LoginHandler() {
        action ="login";
    }

    @Override
    public ExecuteStatus execute(HttpServletRequest request) {
        String email=request.getParameter("email"), password=request.getParameter("password");
        DAOFactory daoFactory=new PostgreDAOFactory();
        UserDAO userDAO=daoFactory.getUserDAO();
        User user=userDAO.getUser(email,password);
        if (user!=null){
            request.getSession().setAttribute("user",user);
            return new ExecuteStatus(ExecuteStatus.OK,MAIN_PAGE);
        } else {
            return new ExecuteStatus(ExecuteStatus.FAIL,"wrong_login_data",LOGIN_PAGE,request.getLocale());
        }
    }
}
