package com.sizatn.ssd.datasource;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.github.pagehelper.PageHelper;

@Configuration
public class PrimaryDataSourceConfig {

	// 精确到 primary 目录，以便跟其他数据源隔离
	private static final String PACKAGE = "com.sizatn.ssd.dao.primary";
	private static final String MAPPER_LOCATION = "classpath:mapper/primary/*.xml";
	private static final String SESSION_FACTORY_BEAN_NAME = "primarySqlSessionFactory";
	private static final String TYPE_ALIASES_PACKAGE = "com.sizatn.ssd.entity";

	@Primary
	@Bean(name = "primaryDataSource")
	@ConfigurationProperties(prefix="primary.datasource")
	public DataSource primaryDataSource() {
//		return new DruidDataSource();
		return DataSourceBuilder.create().build();
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
		
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("dialect", "mysql");
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);
//		sessionFactory.setPlugins(new Interceptor[] { (Interceptor) pageHelper });
		
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(PrimaryDataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}
	
	@Bean(name = "primaryMapperScannerConfigurer")
	public MapperScannerConfigurer primaryMapperScannerConfigurer() {
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName(PrimaryDataSourceConfig.SESSION_FACTORY_BEAN_NAME);
        msc.setBasePackage(PrimaryDataSourceConfig.PACKAGE);
        return msc;
    }
}
