
package com.resourcemanager;

import java.util.Properties;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.base.Preconditions;
import com.resourcemanager.converter.SkillConverter;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Configuration
	@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
	@EnableTransactionManagement
	public class WebConfig implements WebMvcConfigurer {

		@Lazy
		@Autowired
		SkillConverter	skillConverter;

		@Value("${spring.datasource.driver-class-name}")
		private String	jdbcDriverClassName;

		@Value("${spring.datasource.url}")
		private String	jdbcURl;

		@Value("${spring.datasource.username}")
		private String	dbUsername;

		@Value("${spring.datasource.password}")
		private String	dbPassword;

		@Value("${hibernate.hbm2ddl.auto}")
		private String	hbm2ddlAuto;

		@Value("${hibernate.hbm2ddl.import_files}")
		private String	hbm2ddlImport_files;

		@Value("${hibernate.dialect}")
		private String	dialect;

		@Override
		public void addFormatters(FormatterRegistry registry) {
			registry.addConverter(skillConverter);
		}

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/login").setViewName("login");
		}

		@Bean
		public DataSource dataSource() {
			final BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(Preconditions.checkNotNull(jdbcDriverClassName));
			dataSource.setUrl(Preconditions.checkNotNull(jdbcURl));
			dataSource.setUsername(Preconditions.checkNotNull(dbUsername));
			dataSource.setPassword(Preconditions.checkNotNull(dbPassword));

			return dataSource;
		}

		private final Properties hibernateProperties() {
			final Properties hibernateProperties = new Properties();
			hibernateProperties.setProperty("hibernate.hbm2ddl.auto", Preconditions.checkNotNull(hbm2ddlAuto));
			hibernateProperties.setProperty("hibernate.hbm2ddl.import_files", Preconditions.checkNotNull(hbm2ddlImport_files));
			hibernateProperties.setProperty("hibernate.dialect", Preconditions.checkNotNull(dialect));

			return hibernateProperties;
		}

		@Bean
		public PlatformTransactionManager hibernateTransactionManager() {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(sessionFactory().getObject());
			return transactionManager;
		}

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

	public static void main(String[] args) {
		String password = "password";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
		SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}