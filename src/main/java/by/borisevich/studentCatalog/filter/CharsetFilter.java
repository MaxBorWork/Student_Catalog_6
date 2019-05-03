package by.borisevich.studentCatalog.filter;

import by.borisevich.studentCatalog.model.Constant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class CharsetFilter implements Filter {
    private Logger log = Logger.getLogger(LogFilter.class);

    public CharsetFilter() {
        Constant.loggerConfig(log);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        log.info("CharsetFilter init!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                            throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html; charset=UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("CharsetFilter destroy!");
    }
}
