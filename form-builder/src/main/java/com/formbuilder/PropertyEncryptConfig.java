package com.formbuilder;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration
@EnableEncryptableProperties
public class PropertyEncryptConfig {
	
	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${jasypt.encryptor.password}") 
	private String encryptKey;//설정파일의 암호
	
	@Value("${jasypt.encryptor.algorithm}") 
	private String algorithm;//설정파일의 알고리즘
	
    @Bean("jasyptStringEncryptor") 
    public StringEncryptor stringEncryptor() { 
    	StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); 
    	SimpleStringPBEConfig config = new SimpleStringPBEConfig(); 
    	config.setPassword(encryptKey); 
    	config.setAlgorithm(algorithm); 
    	config.setKeyObtentionIterations("1000"); 
    	config.setPoolSize("1"); 
    	config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); 
    	config.setStringOutputType("base64"); 
    	encryptor.setConfig(config); 
    	
    	return encryptor;
	}
}