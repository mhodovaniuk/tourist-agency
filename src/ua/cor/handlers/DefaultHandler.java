package ua.cor.handlers;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/17/13
 * Time: 6:46 PM
 */
public abstract class DefaultHandler implements Command {
    private DefaultHandler nextHandler;
    public static final String MAIN_PAGE="/index.jsp",LOGIN_PAGE="/login.jsp",CONFIRM_PAGE="/confirm.jsp"
            ,REGISTER_PAGE="/register.jsp",BUY_PAGE="/buy.jsp",CABINET_PAGE="/cabinet.jsp";
    protected String action;

    public void setNextHandler(DefaultHandler nextHandler){
        this.nextHandler=nextHandler;
    }

    public DefaultHandler handle(String action){
        if (this.action.equals(action)){
            return this;
        } else {
            return nextHandler==null?null:nextHandler.handle(action);
        }
    }
}
