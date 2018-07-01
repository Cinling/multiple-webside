package cinling.admin.http.interceptor;

import cinling.admin.model.AdminUserModel;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前
     * @param httpServletRequest http请求
     * @param httpServletResponse http应答
     * @param o 未知
     * @return 是否允许继续进行后续的操作
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        String uri = httpServletRequest.getRequestURI();

        // 放行静态资源
        if (this.IsStaticResourceURI(uri)) {
            return true;
        }

        AdminUserModel adminUserModel = AdminUserModel.GetInstance();

        // 如果没有登陆，则判断是否已经初始化管理员账号
        if (!adminUserModel.IsLogin(session)) {

            // 判断是否需要进行初始化管理员账号
            if (!adminUserModel.IsInitAdminUserAccount()) {

                // 如果当前页面不是合法的，则跳转到初始化页面
                if (!this.IsInitAdminUserURI(uri)) {
                    httpServletResponse.sendRedirect("/admin-user/init");
                    return false;
                }
            }
        } else {
            // ########### 跳转到登陆页面
            return false;
        }

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
        System.out.println("postHandle() -- START");

        System.out.println("postHandle() -- END\n");
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
        System.out.println("afterCompletion() -- START");

        System.out.println("afterCompletion() -- END\n");
    }

    /**
     * @param uri 请求路径，如： "http://localhost:8080/abc/ccc" ，应该填入 "/abc/ccc"
     * @return 是否是初始化管理员账号的路径
     */
    private boolean IsInitAdminUserURI(String uri) {
        HashSet<String> initAdminUserURISet = this.GetAdminUserURIMap();

        return initAdminUserURISet.contains(uri);
    }

    /**
     * 初始化管理员账号的路径 Set
     */
    private HashSet<String> initAdminUserURISet = null;

    /**
     * 使用方法获取的好处是节省内存，因为不是每一个请求都需要知道初始化管理员账号 URI 路径，在不需要的请求时，这里的数据不会创建
     * @return 获取 初始化管理员账号的 URI Set
     */
    private HashSet<String> GetAdminUserURIMap() {
        if (this.initAdminUserURISet == null) {
            this.initAdminUserURISet = new HashSet<>();

            this.initAdminUserURISet.add("/admin-user/init");
            this.initAdminUserURISet.add("/admin-user/init-admin-user");
        }

        return this.initAdminUserURISet;
    }

    /**
     * @param uri 请求的 uri 部分
     * @return 判断 uri 是否是静态资源
     */
    private boolean IsStaticResourceURI(String uri) {
        return (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png"));
    }
}

