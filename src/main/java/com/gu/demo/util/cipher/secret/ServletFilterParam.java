package com.gu.demo.util.cipher.secret;


import com.gu.demo.util.cipher.CipherProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description 使用
 * @author gumingyang
 **/
public class ServletFilterParam implements Filter {
    @Autowired
    private CipherProperty cipherProperty;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       // if(cipherProperty!=null&&cipherProperty.getDev()!=null && cipherProperty.getDev()){
            //加密解密
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
            //入参解密
            CipherServletRequestWrapper cipherServletRequestWrapper = null;
            try {
                cipherServletRequestWrapper = new CipherServletRequestWrapper(httpServletRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
            filterChain.doFilter(cipherServletRequestWrapper, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
