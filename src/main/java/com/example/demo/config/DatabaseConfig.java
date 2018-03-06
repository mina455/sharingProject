package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.example.demo.dao",
        sqlSessionFactoryRef="demoSqlSessionFactory")
@EnableTransactionManagement
public class DatabaseConfig {
	
	@Bean(name="demoDataSource")
	@Primary
	@ConfigurationProperties(prefix="demo.datasource")
	public DataSource demoDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	@Bean(name="demoSqlSessionFactory")
	@Primary
	public SqlSessionFactory demoSqlSessionFactory(
	        @Qualifier("demoDataSource") DataSource demoDataSource
	        , ApplicationContext applicationContext) throws Exception {
	    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	    sqlSessionFactoryBean.setDataSource(demoDataSource);
	    sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/demo/**/*.xml"));
	    return sqlSessionFactoryBean.getObject();
	}

	@Bean(name="demoSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate demoSqlSessionTemplate(SqlSessionFactory demoSqlSessionFactory) throws Exception {
	    return new SqlSessionTemplate(demoSqlSessionFactory);
	}


}
