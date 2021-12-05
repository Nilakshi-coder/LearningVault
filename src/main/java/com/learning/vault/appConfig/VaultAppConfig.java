package com.learning.vault.appConfig;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.learning.vault")
@PropertySource({"classpath:persistence-mysql.properties"})
public class VaultAppConfig {
	// setup a variable to hold the properties
	@Autowired
	private Environment env;
	
	private Logger logger=Logger.getLogger(this.getClass().getName());

	// define a bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	private int getIntProperty(String prop) {
		String propVal = env.getProperty(prop);
		
		return Integer.parseInt(propVal);
	}
	
	// define a data source
		@Bean
		public DataSource VaultDataSource() throws PropertyVetoException {
			// create connection pool
			ComboPooledDataSource vaultDataSource = new ComboPooledDataSource();
			
			vaultDataSource.setDriverClass(env.getProperty("jdbc.driver"));
			
			// log the connection props
			logger.info(">>> jdbc.url: "+env.getProperty("jdbc.url"));
			logger.info(">>> jdbc.user: "+env.getProperty("jdbc.user"));
			
			// set the database connection props
			vaultDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			vaultDataSource.setUser(env.getProperty("jdbc.user"));
			vaultDataSource.setPassword(env.getProperty("jdbc.password"));
			
			// set the connection pool props
			vaultDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
			vaultDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
			vaultDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
			vaultDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
			
			
			return vaultDataSource;
			
		}

}
