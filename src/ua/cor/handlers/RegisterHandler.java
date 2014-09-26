package ua.cor.handlers;

import ua.database.dao.DAOFactory;
import ua.database.dao.UserDAO;
import ua.entities.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/18/13
 * Time: 6:19 PM
 */
public class RegisterHandler extends DefaultHandler {
    public RegisterHandler() {
        action="register";
    }

    @Override
    public ExecuteStatus execute(HttpServletRequest request) {
        String email=request.getParameter("email"),paswword=request.getParameter("password"),firstName=request.getParameter("firstName")
                ,lastName=request.getParameter("lastName");
        User user=new User(email,firstName,lastName,paswword,false);
        DAOFactory factory=DAOFactory.getFactory(DAOFactory.FactoryType.POSTGRE_SQL);
        UserDAO userDAO=factory.getUserDAO();
        if (userDAO.addUser(user)){
            return new ExecuteStatus(ExecuteStatus.OK,"new_user",REGISTER_PAGE,request.getLocale());
        } else {
            return new ExecuteStatus(ExecuteStatus.FAIL,"wrong_login_data",REGISTER_PAGE,request.getLocale());
        }
    }
}
