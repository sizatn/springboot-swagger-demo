package com.sizatn.ssd.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 
 * @desc 次数据源配置
 * @author sizatn
 * @date Aug 9, 2017
 */
@Configuration
public class SecondaryDataSourceConfig {

	// 精确到 secondary 目录，以便跟其他数据源隔离
	static final String PACKAGE = "org.spring.springboot.dao.master";
	static final String MAPPER_LOCATION = "classpath:mapper/secondary/*.xml";

	@Bean(name = "secondaryDataSource")
	@ConfigurationProperties(prefix = "secondary.datasource")
	public DataSource secondaryDataSource() {
//		return new DruidDataSource();
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "secondaryTransactionManager")
	public DataSourceTransactionManager masterTransactionManager() {
		return new DataSourceTransactionManager(secondaryDataSource());
	}

	@Bean(name = "secondarySqlSessionFactory")
	public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource secondaryDataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(secondaryDataSource);
//		sessionFactory.setPlugins(new Interceptor[] {});
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(SecondaryDataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}
}
