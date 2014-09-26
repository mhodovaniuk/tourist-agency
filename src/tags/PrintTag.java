package tags;

import org.apache.commons.beanutils.PropertyUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/18/13
 * Time: 12:34 AM
 */
public class PrintTag extends SimpleTagSupport {
    private ArrayList list;
    private String properties;

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (list!=null && properties!=null){
            String prop[]=properties.split(";");
            for (Object o:list){

                getJspContext().getOut().print("<tr>");
                for (String property:prop)  {
                    try {
                        getJspContext().getOut().print("<td>"+ PropertyUtils.getProperty(o,property)+"</td>");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                getJspContext().getOut().print("</tr>");
            }
        }
    }
}
