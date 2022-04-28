package com.formbuilder.cmmn.distribute;

import java.lang.reflect.InvocationTargetException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.formbuilder.domain.FormSet;

@Component("distributeUtil")
public class DistributeUtil {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${jasypt.encryptor.password}") 
	private String encryptKey;//설정파일의 암호
	
	@Value("${jasypt.encryptor.algorithm}") 
	private String algorithm;//설정파일의 알고리즘
	
	@Value("${formbuilder.dbutils.ip}") 
	private String dbIp;//db접속 아이피
	
	@Value("${formbuilder.dbutils.port}") 
	private String dbPort;//db접속 포트
	
	@Value("${formbuilder.dbutils.db-name}") 
	private String dbName;//접속 database이름
	
	@Value("${formbuilder.dbutils.db-type}") 
	private String dbType;//디비타입(ORACLE, MYSQL, MSSQL...)
	
	@Value("${formbuilder.dbutils.username}") 
	private String dbUserName;//db접속 아이디
	
	@Value("${formbuilder.dbutils.password}") 
	private String dbPassword;//db접속 비밀번호
	
	private StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	
	public boolean createTable(FormSet formSetInfo) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isSuccess 			= true;
		
		//DB type에 맞춰서 생성 쿼리문 가지고 오는 class 호출
		ActionQueryContext actionQueryContext = new ActionQueryContext();
		actionQueryContext.setActionQuery(dbType, formSetInfo);
		
		//ActionQuery actionQuery 	= new MysqlActionQuery();//나중에 확장이 필요한 경우 db type에 맞춰서 처리 추가(현재는 MYSQL만 구현)
		String actionQueryStr 		= actionQueryContext.getQueryStr();
		
		//DB접속 정보 암/복호화 관련 설정
		setStandardPBEStringEncryptor();
		
		//DB접속 및 쿼리문 실행
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.execute(actionQueryStr);
		
		return isSuccess;
	}
	
	/**
	 * 암호화 된 계정/비밀번호 복호화 하는 설정 정의
	 * */
	public void setStandardPBEStringEncryptor() {
		SimpleStringPBEConfig config = new SimpleStringPBEConfig(); 
		
		config.setPassword(encryptKey); 
		config.setAlgorithm(algorithm); 
		config.setKeyObtentionIterations("1000"); 
		config.setPoolSize("1"); 
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); 
		config.setStringOutputType("base64"); 
		
		encryptor.setConfig(config); 
	}
	
	/**
	 * JdbcTemplate 에서 사용할 DB접속 정보 설정
	 * */
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://"+dbIp+":"+dbPort+"/"+dbName);
        dataSource.setUsername(encryptor.decrypt(dbUserName));
        dataSource.setPassword(encryptor.decrypt(dbPassword));
        
        return dataSource;
	}
}
