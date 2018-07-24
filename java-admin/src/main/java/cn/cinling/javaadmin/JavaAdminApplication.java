package cn.cinling.javaadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("cn.cinling.javacommon.database.mapper") // 扫描数据库的 mapper 实例
@ComponentScan("cn.cinling.javacommon.config")  // 加载 common 的配置
public class JavaAdminApplication extends SpringBootServletInitializer {

    /**
     * tomcat 启动所需的方法
     */
    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JavaAdminApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(JavaAdminApplication.class, args);
    }
}
