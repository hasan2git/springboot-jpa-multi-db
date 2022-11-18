package com.javasetup.springboot.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages="com.javasetup.springboot.repository.db1",
                       entityManagerFactoryRef="db1EntityManagerFactory",
                       transactionManagerRef="db1TransactionManager")
public class DB1Configuration {

	@Bean
	@ConfigurationProperties("spring.datasource.db1")
	public DataSourceProperties db1DataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource.db1.configuration")
	public DataSource db1Datasource() {
		return db1DataSourceProperties()
				.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}
	
	@Bean(name="db1EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
			   .dataSource(db1Datasource())
			   .packages("com.javasetup.springboot.entity.db1")
			   .build();
	}
	
	@Bean(name="db1TransactionManager")
	public PlatformTransactionManager db1TransactionManager(final @Qualifier("db1EntityManagerFactory")LocalContainerEntityManagerFactoryBean db1EntityManagerFactory) {
		return new JpaTransactionManager(db1EntityManagerFactory.getObject());
	}
}
