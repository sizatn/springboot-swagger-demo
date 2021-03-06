package com.sizatn.ssd.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * 
 * @desc 副数据源配置
 * @author sizatn
 * @date Aug 9, 2017
 */
@Configuration
public class SecondaryDataSourceConfig {

	/** 精确到 secondary 目录，以便跟其他数据源隔离 */ 
	private static final String PACKAGE = "com.sizatn.ssd.dao.secondary";
	private static final String MAPPER_LOCATION = "classpath:mapper/secondary/*.xml";
	private static final String SESSION_FACTORY_BEAN_NAME = "secondarySqlSessionFactory";
	private static final String TYPE_ALIASES_PACKAGE = "com.sizatn.ssd.entity";
	
//	private String url;
//	private String username;
//	private String password;
//	private String driverClassName;
//	private Class<DataSource> type;
	
//	@Primary
//	@Bean(name = "secondaryDataSourceProperties")
//	public DataSourceProperties secondaryDataSourceProperties() {
//		DataSourceProperties properties = new DataSourceProperties();
//		properties.setUrl(url);
//		properties.setUsername(username);
//		properties.setPassword(password);
//		properties.setDriverClassName(driverClassName);
//		properties.setType(type);
//	    return properties;
//	}

	@Bean(name = "secondaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid.secondary")
	public DataSource secondaryDataSource() {
		return DruidDataSourceBuilder.create().build();
//		return DataSourceBuilder.create().type(DruidDataSource.class).build();
//		return secondaryDataSourceProperties().initializeDataSourceBuilder().type(DruidDataSource.class).build();
	}

	@Bean(name = "secondaryTransactionManager")
	public DataSourceTransactionManager masterTransactionManager() {
		return new DataSourceTransactionManager(secondaryDataSource());
	}

	@Bean(name = "secondarySqlSessionFactory")
	public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource secondaryDataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(secondaryDataSource);
		sessionFactory.setTypeAliasesPackage(SecondaryDataSourceConfig.TYPE_ALIASES_PACKAGE);
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(SecondaryDataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}
	
	@Bean(name = "secondaryMapperScannerConfigurer")
	public MapperScannerConfigurer primaryMapperScannerConfigurer() {
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName(SecondaryDataSourceConfig.SESSION_FACTORY_BEAN_NAME);
        // 与接口上的@Mapper注解作用相同，选一种配置方式即可
        msc.setBasePackage(SecondaryDataSourceConfig.PACKAGE);
        return msc;
    }
	
	@Bean(name = "secondaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
		return new JdbcTemplate(secondaryDataSource);
	}
}
