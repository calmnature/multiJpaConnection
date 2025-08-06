package com.jpaproject.jpa_prj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableJpaRepositories (
        basePackages = "com.jpaproject.jpa_prj.main.mainDbRepository",
        entityManagerFactoryRef = "mainEntityManager",
        transactionManagerRef = "mainTransactionManager"
)
@Configuration
public class MainConfig {
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.main")
    protected DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean mainEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mainDataSource());
        em.setPackagesToScan(new String[] {"com.jpaproject.jpa_prj.main.mainDbEntity"});
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());


        // yml 파일의 spring.jpa.* 설정은 기본 데이터소스 하나에만 적용이 된다고 함
        // 현재는 멀티 데이터소스 구성을 했기 때문에 db1EntityManager에 명시를 해줘야한다고 함
        // 아래 설정을 해주고 나서야, 프로젝트가 실행될 때 테이블을 생성해 줌
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager mainTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mainEntityManager().getObject());

        return transactionManager;
    }
}
