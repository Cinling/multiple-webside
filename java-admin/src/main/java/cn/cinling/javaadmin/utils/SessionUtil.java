package cn.cinling.javaadmin.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class SessionUtil {
    /**
     * 用户id
     */
    public static final String KEY_USER_ID = "ui";

    /**
     * 获取 session 中的值
     * @param key session key
     * @param defaultValue 如果 session 没有找到数据 或 出现异常，则返回这个值
     * @return value
     */
    public static Object Get(String key, Object defaultValue) {
        HttpSession session = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes(), "Request为空")).getRequest().getSession();

        Object value = defaultValue;
        try {
            value = session.getAttribute(key);
        } catch (IllegalStateException ise) {
            return value;
        }

        return value;
    }

    public static void Set(String key, Object value) {
        HttpSession session = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes(), "Request为空")).getRequest().getSession();

        session.setAttribute(key, value);
    }
}
