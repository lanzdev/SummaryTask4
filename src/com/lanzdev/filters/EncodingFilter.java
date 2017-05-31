package com.lanzdev.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        LOGGER.debug("Entering encoding filter init()");

        encoding = filterConfig.getInitParameter("encoding");
        LOGGER.trace("Encoding: " + encoding);

        LOGGER.debug("Leaving encoding filter init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LOGGER.debug("Entering encoding filter doFilter()");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        LOGGER.trace("Request url: " + request.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null || !requestEncoding.equals(encoding)) {
            LOGGER.trace("Set request encoding to '" + encoding + "'.");
            request.setCharacterEncoding(encoding);
        }

        LOGGER.debug("Leaving encoding filter doFilter()");
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy( ) {
        /*NOP*/
    }
}
