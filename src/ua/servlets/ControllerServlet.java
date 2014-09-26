package ua.servlets;

import ua.cor.CoR;
import ua.cor.handlers.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 11:17 PM
 */
public class ControllerServlet extends HttpServlet {
    private static final Pattern emailPattern=Pattern.compile("[a-zA-Z_]+[0-9a-zA-Z\\-\\._]*@[a-zA-Z_]+([.][a-zA-Z]+)+")
            ,passwordPattern=Pattern.compile("[0-9a-zA-Z]*"),numberPattern=Pattern.compile("[0-9]+")
            ,wordPattern=Pattern.compile("[a-zA-Z а-яА-Я]+"),datePattern=Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");

    private static final String ERROR_PAGE = "/error.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        ExecuteStatus status = null;
        DefaultHandler handler = CoR.getInstance().handle(action);
        if (handler == null)
            status = new ExecuteStatus(ExecuteStatus.FAIL, "wrong_action", ERROR_PAGE, request.getLocale());
        else
            status = checkData(request, action);
        if (status.getStatus()==ExecuteStatus.OK)
            status = handler.execute(request);
        request.setAttribute("message", status.getMessage());
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(status.getForwardPage());
        requestDispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("logout".equals(request.getParameter("action"))) {
            DefaultHandler handler = new LogoutHandler();
            ExecuteStatus status = handler.execute(request);
            request.setAttribute("message", status.getMessage());
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(status.getForwardPage());
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("message", "wrong_action");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }

    private ExecuteStatus checkData(HttpServletRequest request, String action) {
        ExecuteStatus status;
        switch (action) {
            case "login":
                status= getFail(checkEmail(request.getParameter("email"),request), checkPassword(request.getParameter("password"),request));
                return processStatus(status,DefaultHandler.LOGIN_PAGE,request);
            case "register":
                if (request.getParameter("password")== null || !request.getParameter("password").equals(request.getParameter("password2")))
                    return new ExecuteStatus(ExecuteStatus.FAIL,"passwords_not_matched",DefaultHandler.REGISTER_PAGE,request.getLocale());
                status=getFail(checkEmail(request.getParameter("email"),request),checkPassword(request.getParameter("password"),request)
                        ,checkWord(request.getParameter("firstName"),request),checkWord(request.getParameter("lastName"),request));
                return processStatus(status,DefaultHandler.REGISTER_PAGE,request);
            case "buy":
                status=getFail(checkNumber(request.getParameter("adultCount"),request),checkNumber(request.getParameter("childrenCount"),request));
                status=processStatus(status,DefaultHandler.MAIN_PAGE,request);
                if (status.getStatus()==ExecuteStatus.OK
                        && Integer.parseInt(request.getParameter("adultCount"))==0
                        && Integer.parseInt(request.getParameter("childrenCount"))==0)
                    return new ExecuteStatus("wrong_data",request.getLocale());
                else return status;

            case "addTour":
                status=getFail(checkNumber(request.getParameter("hotelId"),request),checkDate(request.getParameter("startDate"),request)
                        , checkNumber(request.getParameter("dayCount"),request),checkNumber(request.getParameter("nightCount"),request)
                        , checkNumber(request.getParameter("adultCost"),request),checkNumber(request.getParameter("childrenCost"),request)
                        ,checkNumber(request.getParameter("discount"),request));
                return processStatus(status,DefaultHandler.CABINET_PAGE,request);
            case "addHotel":
                status=getFail(checkWord(request.getParameter("country"),request),checkWord(request.getParameter("city"),request)
                        , checkWord(request.getParameter("name"),request),checkNumber(request.getParameter("stars"),request));
                return processStatus(status,DefaultHandler.CABINET_PAGE,request);
            case "removeTour":
                status=getFail(checkNumber(request.getParameter("id"),request));
                return processStatus(status,DefaultHandler.CABINET_PAGE,request);
            case "removeHotel":
                status=getFail(checkNumber(request.getParameter("id"),request));
                return processStatus(status,DefaultHandler.CABINET_PAGE,request);
            case "confirm":
                status=getFail(checkNumber(request.getParameter("uid"),request));
                return processStatus(status,DefaultHandler.CABINET_PAGE,request);
            default:return new ExecuteStatus(ExecuteStatus.FAIL);
        }
    }

    private ExecuteStatus processStatus(ExecuteStatus status,String errorPage,HttpServletRequest request){
        return processStatus(status, errorPage, request,true);
    }


    private ExecuteStatus processStatus(ExecuteStatus status,String errorPage,HttpServletRequest request,boolean test){
        if (status.getStatus()==ExecuteStatus.OK && test)
            return new ExecuteStatus(ExecuteStatus.OK);
        else
            return new ExecuteStatus(ExecuteStatus.FAIL,status.getMessageKey(),errorPage,request.getLocale());
    }

    private ExecuteStatus checkNumber(String number,HttpServletRequest request) {
        if (number==null)
            return new ExecuteStatus("null_data",request.getLocale());
        number=number.trim();
        Matcher matcher=numberPattern.matcher(number);
        if (!matcher.matches())
            return new ExecuteStatus("wrong_data",request.getLocale());
        return new ExecuteStatus(ExecuteStatus.OK);
    }

    private ExecuteStatus checkWord(String word,HttpServletRequest request) {
        if (word==null)
            return new ExecuteStatus("null_data",request.getLocale());
        word=word.trim();
        Matcher matcher=wordPattern.matcher(word);
        if (!matcher.matches())
            return new ExecuteStatus("wrong_data",request.getLocale());
        return new ExecuteStatus(ExecuteStatus.OK);
    }

    private ExecuteStatus checkEmail(String email,HttpServletRequest request){
        if (email==null)
            return new ExecuteStatus("null_data",request.getLocale());
        email=email.trim();
        Matcher matcher=emailPattern.matcher(email);
        if (!matcher.matches())
            return new ExecuteStatus("wrong_data",request.getLocale());
        return new ExecuteStatus(ExecuteStatus.OK);
    }

    private ExecuteStatus checkPassword(String password,HttpServletRequest request){
        if (password==null)
            return new ExecuteStatus("null_data",request.getLocale());
        password=password.trim();
        Matcher passwordMatcher=passwordPattern.matcher(password);
        if (!passwordMatcher.matches())
            return new ExecuteStatus("wrong_data",request.getLocale());
        else
        if (password.length()<6)
            return new ExecuteStatus("password_to_short",request.getLocale());
        return new ExecuteStatus(ExecuteStatus.OK);
    }

    private ExecuteStatus checkDate(String date,HttpServletRequest request){
        if (date==null)
            return new ExecuteStatus("null_data",request.getLocale());
        date=date.trim();
        Matcher passwordMatcher=datePattern.matcher(date);
        if (!passwordMatcher.matches())
            return new ExecuteStatus("wrong_data",request.getLocale());
        else
        if (Integer.valueOf(date.substring(5,7))>12 || Integer.valueOf(date.substring(8,10))>31)
            return new ExecuteStatus("wrong_data",request.getLocale());
        return new ExecuteStatus(ExecuteStatus.OK);
    }

    private ExecuteStatus getFail(ExecuteStatus... array) {
        for(ExecuteStatus o:array)
            if (o.getStatus()==ExecuteStatus.FAIL)
                return o;
        return array[0];
    }
}
