/*
 *
 */

package com.resourcemanager;

import java.util.Properties;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.base.Preconditions;
import com.resourcemanager.converter.SkillConverter;

/**
 * The Class Application. This is the entry point for this Spring Boot Application, it contains the main method and initial
 * configuration.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	/**
	 * The Class WebConfig. Configures the Spring Boot Application for the Model View Controller pattern and loads the properties
	 * to initialize it.
	 */
	@Configuration
	@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
	@EnableTransactionManagement
	public class WebConfig implements WebMvcConfigurer {

		/** The skill converter. */
		@Lazy
		@Autowired
		SkillConverter	skillConverter;

		/** The Java Database Connector driver class name. */
		@Value("${spring.datasource.driver-class-name}")
		private String	jdbcDriverClassName;

		/** The Java Database Connector Url. */
		@Value("${spring.datasource.url}")
		private String	jdbcURl;

		/** The database username. */
		@Value("${spring.datasource.username}")
		private String	dbUsername;

		/** The database password. */
		@Value("${spring.datasource.password}")
		private String	dbPassword;

		/** The hibernate schema generator setting. */
		@Value("${hibernate.hbm2ddl.auto}")
		private String	hbm2ddlAuto;

		/** The hibernate database import script setting. */
		@Value("${hibernate.hbm2ddl.import_files}")
		private String	hbm2ddlImport_files;

		/** The hibernate dialect. InnoDB etc. */
		@Value("${hibernate.dialect}")
		private String	dialect;

		/*
		 * (non-Javadoc)
		 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addFormatters(org.springframework.format.
		 * FormatterRegistry)
		 */
		@Override
		public void addFormatters(FormatterRegistry registry) {
			registry.addConverter(skillConverter);
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addViewControllers(org.springframework.web.servlet.
		 * config.annotation.ViewControllerRegistry)
		 */
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/login").setViewName("login");
		}

		/**
		 * The data source, i.e database layer.
		 *
		 * @return the data source
		 */
		@Bean
		public DataSource dataSource() {
			final BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(Preconditions.checkNotNull(jdbcDriverClassName));
			dataSource.setUrl(Preconditions.checkNotNull(jdbcURl));
			dataSource.setUsername(Preconditions.checkNotNull(dbUsername));
			dataSource.setPassword(Preconditions.checkNotNull(dbPassword));

			return dataSource;
		}

		/**
		 * Hibernate properties.
		 *
		 * @return the properties
		 */
		private final Properties hibernateProperties() {
			final Properties hibernateProperties = new Properties();
			hibernateProperties.setProperty("hibernate.hbm2ddl.auto", Preconditions.checkNotNull(hbm2ddlAuto));
			hibernateProperties.setProperty("hibernate.hbm2ddl.import_files", Preconditions.checkNotNull(hbm2ddlImport_files));
			hibernateProperties.setProperty("hibernate.dialect", Preconditions.checkNotNull(dialect));

			return hibernateProperties;
		}

		/**
		 * Hibernate transaction manager.
		 *
		 * @return the platform transaction manager
		 */
		@Bean
		public PlatformTransactionManager hibernateTransactionManager() {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(sessionFactory().getObject());
			return transactionManager;
		}

		/**
		 * Session factory.
		 *
		 * @return the local session factory bean
		 */
		@Bean
		public LocalSessionFactoryBean sessionFactory() {
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			sessionFactory.setDataSource(dataSource());
			sessionFactory.setPackagesToScan(
				"com.resourcemanager.model");
			sessionFactory.setHibernateProperties(hibernateProperties());

			return sessionFactory;
		}
	}

	/**
	 * The main method. Entry point for the application.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Australia/NSW"));
		SpringApplication.run(Application.class, args);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.web.servlet.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.
	 * SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(
		SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}