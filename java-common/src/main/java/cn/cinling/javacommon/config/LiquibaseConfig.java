package cn.cinling.javacommon.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class LiquibaseConfig {
    @Bean
    @Primary
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:/config/liquibase/master.xml");
        liquibase.setContexts("development,test,production");
        liquibase.setShouldRun(true);

        return liquibase;
    }
}
