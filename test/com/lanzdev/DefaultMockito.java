package com.lanzdev;

import org.apache.struts.mock.MockServletConfig;
import org.apache.struts.mock.MockServletContext;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultMockito extends Mockito {

    protected static HttpServletRequest request;
    protected static HttpServletResponse response;
    protected static MockServletContext context;
    protected static MockServletConfig config;

    public static void initEnv() {

        context = new MockServletContext();
        config = new MockServletConfig(context);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }
}
