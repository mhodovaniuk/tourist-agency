package ua.cor.handlers;


import javax.servlet.http.HttpServletRequest;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/11/13
 * Time: 6:00 PM
 */
public interface Command {
    ExecuteStatus execute(HttpServletRequest request);
}
