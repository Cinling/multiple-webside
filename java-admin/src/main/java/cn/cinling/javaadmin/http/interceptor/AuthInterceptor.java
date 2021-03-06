package cn.cinling.javaadmin.http.interceptor;

import cn.cinling.javaadmin.manager.AuthManager;
import cn.cinling.javaadmin.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthManager authManager;
    private final UrlUtil urlUtil;

    @Autowired
    public AuthInterceptor(UrlUtil urlUtil, AuthManager authManager) {
        this.urlUtil = urlUtil;
        this.authManager = authManager;
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

        String projectUri = urlUtil.ToProjectUri(httpServletRequest.getRequestURI());
        System.out.println(projectUri);


        String uri = urlUtil.ToProjectUri(httpServletRequest.getRequestURI());

        if (uri.equals("/error")) {
            return true;
        }

        // 放行静态资源
        if (this.IsStaticResourceURI(uri)) {
            return true;
        }

        // 如果没有登陆，则判断是否已经初始化管理员账号
        if (!authManager.IsLogin()) {

            // 判断是否需要进行初始化管理员账号
            if (!authManager.IsInitAdminUserAccount()) {

                // 如果当前页面不是合法的，则跳转到初始化页面
                if (!this.IsInitAdminUserURI(uri)) {
                    httpServletResponse.sendRedirect(urlUtil.ToClientRequestUrl("/admin-user/init-page"));
                    return false;
                }
            } else {
                if (!this.IsInNotLoginAllowURI(uri)) {
                    // 跳转到登陆页面
                    httpServletResponse.sendRedirect(urlUtil.ToClientRequestUrl("/admin-user/login-page"));
                    return false;
                }
            }
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

            this.initAdminUserURISet.add("admin-user/init-page");
            this.initAdminUserURISet.add("admin-user/init-admin-user");
        }

        return this.initAdminUserURISet;
    }


    /**
     * 未登录时允许访问的 URI Set
     */
    private HashSet<String> allowURIInNotLoginSet = null;

    /**
     * @return 获取  未登录时允许访问的 URI Set
     */
    private HashSet<String> GetAllowURIInNotLoginSet() {
        if (this.allowURIInNotLoginSet == null) {
            this.allowURIInNotLoginSet = new HashSet<>();

            this.allowURIInNotLoginSet.add("admin-user/login-page");
            this.allowURIInNotLoginSet.add("admin-user/login");
        }

        return this.allowURIInNotLoginSet;
    }

    /**
     * @param uri 判断这个 uri 是否允许在未登录是访问
     * @return true：允许， false：不允许
     */
    private boolean IsInNotLoginAllowURI(String uri) {
        HashSet<String> allowUriSet = this.GetAllowURIInNotLoginSet();

        return allowUriSet.contains(uri);
    }



    /**
     * @param uri 请求的 uri 部分
     * @return 判断 uri 是否是静态资源
     */
    private boolean IsStaticResourceURI(String uri) {
        return (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png"));
    }


}

