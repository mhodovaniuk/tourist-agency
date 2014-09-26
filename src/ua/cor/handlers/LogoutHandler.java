package ua.cor.handlers;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/17/13
 * Time: 7:15 PM
 */
public class LogoutHandler extends DefaultHandler {
    public LogoutHandler() {
        action ="logout";
    }

    @Override
    public ExecuteStatus execute(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return new ExecuteStatus(ExecuteStatus.OK,MAIN_PAGE);
    }
}
