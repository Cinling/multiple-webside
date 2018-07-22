package cn.cinling.javaadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
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
