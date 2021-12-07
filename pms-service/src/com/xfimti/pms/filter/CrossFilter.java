package com.xfimti.pms.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-11-08 11:25
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
@WebFilter("/*")
public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","GET,POST");
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
