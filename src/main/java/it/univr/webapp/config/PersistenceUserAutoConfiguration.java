package it.univr.webapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:persistence-multiple-db-boot.properties"})
@EnableJpaRepositories(
        basePackages = "it.univr.webapp.repositories",
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager")
public class PersistenceUserAutoConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.second-datasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }
    // userEntityManager bean

    // userTransactionManager bean
}
