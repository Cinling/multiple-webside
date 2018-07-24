package cn.cinling.javaadmin.config;

import cn.cinling.javaadmin.http.interceptor.AuthInterceptor;
import cn.cinling.javaadmin.http.interceptor.InjectionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.ContentVersionStrategy;
import org.springframework.web.servlet.resource.VersionResourceResolver;

/**
 * 拦截器配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    private AuthInterceptor authInterceptor;
    private InjectionInterceptor injectionInterceptor;

    @Autowired
    public WebMvcConfig(AuthInterceptor authInterceptor, InjectionInterceptor injectionInterceptor) {
        this.authInterceptor = authInterceptor;
        this.injectionInterceptor = injectionInterceptor;
    }

    /**
     * 程序所需实现的拦截器
     * @param registry 拦截器注册消息
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加权限拦截器
        registry.addInterceptor(this.authInterceptor).addPathPatterns("").addPathPatterns("/**").addPathPatterns("/**/**").addPathPatterns("/**/**/**").addPathPatterns("/**/**/**/**");

        // 添加对象注入器
        registry.addInterceptor(this.injectionInterceptor).addPathPatterns("").addPathPatterns("/**").addPathPatterns("/**/**").addPathPatterns("/**/**/**").addPathPatterns("/**/**/**/**");

        super.addInterceptors(registry);
    }

    /**
     * 设置静态资源的路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        VersionResourceResolver versionResourceResolver = new VersionResourceResolver()
                .addVersionStrategy(new ContentVersionStrategy(), "/**");

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
            .setCachePeriod(86400 * 30)     // 缓存30天
            .resourceChain(true)    // 开启md5匹配后缀
            .addResolver(versionResourceResolver);

        super.addResourceHandlers(registry);
    }
}
