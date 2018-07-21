package cn.cinling.admin.http.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有控制器的基类
 */
public class BaseController {
    /**
     * @return HTTP REQUEST 对象
     */
    protected HttpServletRequest GetRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return attrs.getRequest();
    }

    /**
     * @return RESPONSE 对象
     */
    protected HttpServletResponse GetResponse() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return attrs.getResponse();
    }

//    /**
//     * @return HTTP SESSION 对象
//     */
//    protected HttpSession GetSession() {
//        return this.GetRequest().getSession();
//    }
}
