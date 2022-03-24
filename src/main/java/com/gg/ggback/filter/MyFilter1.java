
package com.gg.ggback.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//사용하지 않는 클래스

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse rep = (HttpServletResponse) servletResponse;

        servletRequest.setCharacterEncoding("UTF-8");

        if (req.getMethod().equals("POST")) {
            System.out.println("POST 요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);

            System.out.println("필터1");
            if (headerAuth.equals("cors")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                PrintWriter out = servletResponse.getWriter();
                out.println("Valid Token");
            }
        }
    }
}




