package ua.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/20/13
 * Time: 1:06 AM
 */

public class RequestEncodeFilter implements Filter {
    private FilterConfig filterConfig = null;

    public RequestEncodeFilter() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        response.setContentType("text/html; charset=UTF-8");
    }

    public void destroy() {
        this.filterConfig = null;
    }
}