package cinling.admin.config;

import cinling.admin.http.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    /**
     * 程序所需实现的拦截器
     * @param registry 拦截器注册消息
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加权限拦截器
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("").addPathPatterns("/**").addPathPatterns("/**/**").addPathPatterns("/**/**/**").addPathPatterns("/**/**/**/**");

        super.addInterceptors(registry);
    }

    /**
     * 设置静态资源的路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
