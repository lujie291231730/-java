package com.seed.utils;

import java.sql.SQLException;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.alibaba.druid.pool.DruidDataSource;

public class SeedDataSourceFactory extends UnpooledDataSourceFactory {

	public SeedDataSourceFactory() throws SQLException {
		
		// 进行基础配置
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setValidationQuery("SELECT 'seed'");
		
		StringBuilder dburl = new StringBuilder();
		switch (ToolUtils.GetDataBaseType()) {
			case SQLSERVER:
				// Druid自动匹配的sqlserver driver不匹配maven库版本的sqljdbc包class名，手动设置
				dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				
				dburl.append("jdbc:sqlserver://");
				dburl.append(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_URL));
				dburl.append(":");
				dburl.append(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_PORT));
				dburl.append("; DatabaseName=");
				dburl.append(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_NAME));
				
				dataSource.setPoolPreparedStatements(true);
				dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
				break;
				
			case Oracle10:
				dataSource.setValidationQuery("SELECT 'seed' from DUAL");
				
				dburl.append("jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = ");
				dburl.append(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_URL));
				dburl.append(")(PORT = ");
				dburl.append(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_PORT));
				dburl.append("))(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = ");
				dburl.append(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_NAME));
				dburl.append(")))");
				
				dataSource.setPoolPreparedStatements(true);
				dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
				break;
				
			case MYSQL:
				dburl.append("jdbc:mysql://");
				dburl.append(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_URL));
				dburl.append(":");
				dburl.append(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_PORT));
				dburl.append("/");
				dburl.append(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_NAME));				

				dataSource.setPoolPreparedStatements(false);
				break;
				
			default:
				break;			
		}
		
		if (StringUtils.StringIsEmpty(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_JDBC_URL)))
			dataSource.setUrl(dburl.toString());
		else
			dataSource.setUrl(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_JDBC_URL));
			
		
		dataSource.setUsername(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_USERID));
		dataSource.setPassword(PropertiesUtils.GetString(Consts.CONFIG_DATABASE_PASSWORD));

		// 可选配置
		dataSource.setInitialSize(PropertiesUtils.GetInt(Consts.CONFIG_DATABASE_INITIAL_SIZE) > 0 ? 
				PropertiesUtils.GetInt(Consts.CONFIG_DATABASE_INITIAL_SIZE) : 1);
		dataSource.setMinIdle(PropertiesUtils.GetInt(Consts.CONFIG_DATABASE_MIN_IDLE) > 0 ? 
				PropertiesUtils.GetInt(Consts.CONFIG_DATABASE_MIN_IDLE) : 1);
		dataSource.setMaxActive(PropertiesUtils.GetInt(Consts.CONFIG_DATABASE_MAX_ACTIVE) > 0 ? 
				PropertiesUtils.GetInt(Consts.CONFIG_DATABASE_MAX_ACTIVE) : 50);

		// 固定配置
		dataSource.setMaxWait(60000);
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setTimeBetweenLogStatsMillis(1800000); // 每60分钟输出一次连接池状态到日志。
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);

		dataSource.setRemoveAbandoned(true);
		dataSource.setRemoveAbandonedTimeout(180);
		dataSource.setLogAbandoned(true);
		dataSource.setFilters("stat,log4j");

		this.dataSource = dataSource;
	}

	
}
