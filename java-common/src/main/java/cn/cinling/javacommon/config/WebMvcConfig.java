package cn.cinling.javacommon.config;

import cn.cinling.javacommon.http.interceptor.AuthInterceptor;
import cn.cinling.javacommon.http.interceptor.InjectionInterceptor;
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

    /**
     * 程序所需实现的拦截器
     * @param registry 拦截器注册消息
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加权限拦截器
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("").addPathPatterns("/**").addPathPatterns("/**/**").addPathPatterns("/**/**/**").addPathPatterns("/**/**/**/**");

        // 添加对象注入器
        registry.addInterceptor(new InjectionInterceptor())
                .addPathPatterns("").addPathPatterns("/**").addPathPatterns("/**/**").addPathPatterns("/**/**/**").addPathPatterns("/**/**/**/**");

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
