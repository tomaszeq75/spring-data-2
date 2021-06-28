package pl.sda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("pl.sda")
@EnableJpaRepositories("pl.sda.repository")
@EnableTransactionManagement
public class ApplicationConfiguration {

    @Bean
    public JpaTransactionManager transactionManager(){
        return new JpaTransactionManager(
                entityManagerFactory().getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSource());
        lcemfb.setPersistenceUnitName("myDatabase");
        LoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
        lcemfb.setLoadTimeWeaver(loadTimeWeaver);
        return lcemfb;
    }

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource ret = new DriverManagerDataSource();
        ret.setDriverClassName("org.h2.Driver");
        ret.setUsername("sa");
        ret.setPassword("");
        ret.setUrl("jdbc:h2:tcp://localhost/~/test");
        return ret;
    }

}
