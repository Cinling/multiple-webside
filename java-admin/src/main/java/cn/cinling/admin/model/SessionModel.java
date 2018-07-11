package cn.cinling.admin.model;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SessionModel {
    private static SessionModel shareInstance = null;
    private SessionModel() {
        this.session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }
    public static SessionModel GetInstance() {
        if (SessionModel.shareInstance == null) {
            SessionModel.shareInstance = new SessionModel();
        }
        return SessionModel.shareInstance;
    }

    /**
     * session 对象
     */
    private HttpSession session;

    /**
     * 用户id
     */
    public static final String KEY_USER_ID = "ui";

    /**
     *
     * @param key session key
     * @param value session value
     */
    public void Set(String key, Object value) {
        this.session.setAttribute(key, value);
    }

    /**
     *
     * @param key session key
     * @param defaultValue 默认值
     * @return
     */
    public Object Get(String key, Object defaultValue) {
        Object value = this.session.getAttribute(key);
        if (value == null) {
            return defaultValue;
        }

        return value;
    }
}
