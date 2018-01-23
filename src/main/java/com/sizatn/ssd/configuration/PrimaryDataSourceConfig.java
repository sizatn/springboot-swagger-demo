package com.sizatn.ssd.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * 
 * @desc 主数据源配置
 * @author sizatn
 * @date Aug 9, 2017
 */
@Configuration
public class PrimaryDataSourceConfig {

	/** 精确到 primary 目录，以便跟其他数据源隔离 */ 
	private static final String PACKAGE = "com.sizatn.ssd.dao.primary";
	private static final String MAPPER_LOCATION = "classpath:mapper/primary/*.xml";
	private static final String SESSION_FACTORY_BEAN_NAME = "primarySqlSessionFactory";
	private static final String TYPE_ALIASES_PACKAGE = "com.sizatn.ssd.entity";
	
//	private String url;
//	private String username;
//	private String password;
//	private String driverClassName;
//	private Class<DataSource> type;
	
//	@Primary
//	@Bean(name = "primaryDataSourceProperties")
//	public DataSourceProperties primaryDataSourceProperties() {
//		DataSourceProperties properties = new DataSourceProperties();
//		properties.setUrl(url);
//		properties.setUsername(username);
//		properties.setPassword(password);
//		properties.setDriverClassName(driverClassName);
//		properties.setType(type);
//	    return properties;
//	}

	@Primary
	@Bean(name = "primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
//		return primaryDataSourceProperties().initializeDataSourceBuilder().type(DruidDataSource.class).build();
	}

	@Primary
	@Bean(name = "primaryTransactionManager")
	public DataSourceTransactionManager masterTransactionManager() {
		return new DataSourceTransactionManager(primaryDataSource());
	}

	@Primary
	@Bean(name = "primarySqlSessionFactory")
	public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(primaryDataSource);
		sessionFactory.setTypeAliasesPackage(PrimaryDataSourceConfig.TYPE_ALIASES_PACKAGE);
		
//		PageInterceptor pageInterceptor = new PageInterceptor();
//		Properties properties = new Properties();
//		properties.setProperty("helperDialect", "mysql");
//		properties.setProperty("reasonable", "true");
//		properties.setProperty("supportMethodsArguments", "true");
//		properties.setProperty("params", "count=countSql");
//		pageInterceptor.setProperties(properties);
//		sessionFactory.setPlugins(new Interceptor[] { pageInterceptor });
		
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(PrimaryDataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}
	
	@Primary
	@Bean(name = "primaryMapperScannerConfigurer")
	public MapperScannerConfigurer primaryMapperScannerConfigurer() {
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName(PrimaryDataSourceConfig.SESSION_FACTORY_BEAN_NAME);
        // 与接口上的@Mapper注解作用相同，选一种配置方式即可
        msc.setBasePackage(PrimaryDataSourceConfig.PACKAGE);
        return msc;
    }
	
	@Primary
	@Bean(name = "primaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource primaryDataSource) {
		return new JdbcTemplate(primaryDataSource);
	}
}
