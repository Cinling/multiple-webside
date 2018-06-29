package cinling.admin.config;

import cinling.admin.http.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {

    /**
     * 拦截器配置
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .addPathPatterns("/**/**")
                .addPathPatterns("/**/**/**");
        super.addInterceptors(registry);
    }
}
