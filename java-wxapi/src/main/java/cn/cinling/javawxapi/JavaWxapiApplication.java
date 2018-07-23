package cn.cinling.javawxapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("cn.cinling.javacommon.database.mapper")
public class JavaWxapiApplication extends SpringBootServletInitializer {

    /**
     * tomcat 启动所需的方法
     */
    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JavaWxapiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(JavaWxapiApplication.class, args);
    }
}
