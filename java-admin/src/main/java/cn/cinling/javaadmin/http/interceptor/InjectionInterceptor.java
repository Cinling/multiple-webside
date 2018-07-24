package cn.cinling.javaadmin.http.interceptor;


import cn.cinling.javaadmin.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InjectionInterceptor implements HandlerInterceptor {

    private final UrlUtil urlUtil;

    @Autowired
    public InjectionInterceptor(UrlUtil urlUtil) {
        this.urlUtil = urlUtil;
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前
     * @param httpServletRequest http请求
     * @param httpServletResponse http应答
     * @param o 未知
     * @return 是否允许继续进行后续的操作
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     * @param request http请求
     * @param response http应答
     * @param handler 未知
     * @param modelAndView 返回的 视图对象
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView != null) {
            modelAndView.addObject("url", this.urlUtil);
        }
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     * @param request http请求
     * @param response http应答
     * @param handler 未知
     * @param ex 未知
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
