package by.borisevich.studentCatalog.filter;

import by.borisevich.studentCatalog.model.Constant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {

    private Logger log = Logger.getLogger(LogFilter.class);

    public LogFilter() {
        Constant.loggerConfig(log);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        log.info("LogFilter init!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String servletPath = req.getServletPath();

        log.info("Got request: " + new Date() + " - ServletPath :" + servletPath //
                + ", URL =" + req.getRequestURL());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("LogFilter destroy!");
    }
}
