package cinling.admin.http.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
     * @return HTTP SESSION 对象
     */
    protected HttpSession GetSession() {
        return this.GetRequest().getSession();
    }
}
