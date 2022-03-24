package com.gg.ggback.filter;

import javax.servlet.*;
import java.io.IOException;


//사용하지 않는 클래스

public class MyFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터2");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
