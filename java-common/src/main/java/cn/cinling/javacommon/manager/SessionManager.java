//package cn.cinling.javacommon.manager;
//
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpSession;
//
//public class SessionManager {
//    private static SessionManager shareInstance = null;
//    private SessionManager() {
//
//    }
//
//    public static SessionManager GetInstance() {
//        if (SessionManager.shareInstance == null) {
//            SessionManager.shareInstance = new SessionManager();
//        }
//        return SessionManager.shareInstance;
//    }
//
//    /**
//     * 用户id
//     */
//    public static final String KEY_USER_ID = "ui";
//
//    /**
//     * 获取 session 中的值
//     * @param key session key
//     * @param defaultValue 如果 session 没有找到数据 或 出现异常，则返回这个值
//     * @return value
//     */
//    public Object Get(String key, Object defaultValue) {
//        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
//
//        Object value = defaultValue;
//        try {
//            value = session.getAttribute(key);
//        } catch (IllegalStateException ise) {
//            return value;
//        }
//
//        return value;
//    }
//
//    public void Set(String key, Object value) {
//        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
//
//        session.setAttribute(key, value);
//    }
//}
