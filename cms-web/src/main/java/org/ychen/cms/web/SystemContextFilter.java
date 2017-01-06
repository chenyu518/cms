package org.ychen.cms.web;

import org.ychen.basic.model.SystemContext;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by cy on 16/12/7.
 */
public class SystemContextFilter implements Filter {
    private Integer pageSize;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            pageSize = Integer.parseInt(filterConfig.getInitParameter("pageSize"));
        }catch (NumberFormatException e){
            pageSize = 15;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Integer offset = 0;
        try{
            offset = Integer.parseInt(request.getParameter("pager.offset"));
        }catch (NumberFormatException e){}
        try {
            SystemContext.setOrder(request.getParameter("order"));
            SystemContext.setSort(request.getParameter("sort"));
            SystemContext.setPageOffset(offset);
            SystemContext.setPageSize(pageSize);
            chain.doFilter(request,response);
        }finally {
            SystemContext.removeOrder();
            SystemContext.removeSort();
            SystemContext.removePageOffset();
            SystemContext.removePageSize();
        }
    }

    @Override
    public void destroy() {

    }
}
