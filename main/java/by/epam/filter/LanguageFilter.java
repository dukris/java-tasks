package by.epam.filter;

import by.epam.utils.ConstantsJSP;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();
        String sessionLanguage = (String) session.getAttribute(ConstantsJSP.LANGUAGE);
        String requestLanguage = httpRequest.getParameter(ConstantsJSP.LANGUAGE);
        if (sessionLanguage == null || requestLanguage == null) {
            session.setAttribute(ConstantsJSP.LANGUAGE, ConstantsJSP.EN);
        }
        if (requestLanguage != null) {
            session.setAttribute(ConstantsJSP.LANGUAGE, requestLanguage);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


}
