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
@EnableJpaRepositories(basePackages="com.javasetup.springboot.repository.db2",
                       entityManagerFactoryRef="db2EntityManagerFactory",
                       transactionManagerRef="db2TransactionManager")
public class DB2Configuration {

	@Bean
	@ConfigurationProperties("spring.datasource.db2")
	public DataSourceProperties db2DataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource.db2.configuration")
	public DataSource db2Datasource() {
		return db2DataSourceProperties()
				.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}
	
	@Bean(name="db2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
			   .dataSource(db2Datasource())
			   .packages("com.javasetup.springboot.entity.db2")
			   .build();
	}
	
	@Bean(name="db2TransactionManager")
	public PlatformTransactionManager db2TransactionManager(final @Qualifier("db2EntityManagerFactory")LocalContainerEntityManagerFactoryBean db2EntityManagerFactory) {
		return new JpaTransactionManager(db2EntityManagerFactory.getObject());
	}
}
