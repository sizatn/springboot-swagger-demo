package com.sizatn.ssd.configuration.dubbo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;

import io.dubbo.springboot.DubboProperties;

@Configuration
public class DubboConfig {
	
	@Value("${dubbo.scan}")
	private String scan;
	
	@Value("${dubbo.applicationName}")
	private String applicationName;
	
	@Value("${dubbo.registryProtocol}")
	private String registryProtocol;
	
	@Value("${dubbo.registryAddress}")
	private String registryAddress;
	
	@Value("${dubbo.registryPort}")
	private String registryPort;
	
	@Value("${dubbo.protocolName}")
	private String protocolName;
	
	@Value("${dubbo.protocolPort}")
	private String protocolPort;

	public ApplicationConfig application() {
		ApplicationConfig ac = new ApplicationConfig();
		ac.setName(applicationName);
		return ac;
	}
	
	public RegistryConfig registry() {
		RegistryConfig rc = new RegistryConfig();
		rc.setProtocol(registryProtocol);
		rc.setAddress(registryAddress);
		rc.setPort(Integer.valueOf(registryPort));
		return rc;
	}

	public ProtocolConfig protocol() {
		ProtocolConfig pc = new ProtocolConfig();
		pc.setName(protocolName);
		pc.setPort(Integer.valueOf(protocolPort));
		return pc;
	}

	@Bean(name = "dubboProperties")
	public DubboProperties dubboProperties() {
		DubboProperties dp = new DubboProperties();
		dp.setApplication(application());
		dp.setRegistry(registry());
		dp.setProtocol(protocol());
		dp.setScan(scan);
		return dp;
	}
	
//	@Autowired
//	private DubboConfigProperties dubboConfigProperties;

//	public ApplicationConfig application() {
//		ApplicationConfig ac = new ApplicationConfig();
//		ac.setName(dubboConfigProperties.getApplicationName());
//		return ac;
//	}
//	
//	public RegistryConfig registry() {
//		RegistryConfig rc = new RegistryConfig();
//		rc.setProtocol(dubboConfigProperties.getRegistryProtocol());
//		rc.setAddress(dubboConfigProperties.getRegistryAddress());
//		rc.setPort(Integer.valueOf(dubboConfigProperties.getRegistryPort()));
//		return rc;
//	}
//	
//	public ProtocolConfig protocol() {
//		ProtocolConfig pc = new ProtocolConfig();
//		pc.setName(dubboConfigProperties.getProtocolName());
//		pc.setPort(Integer.valueOf(dubboConfigProperties.getProtocolPort()));
//		return pc;
//	}
//	
//	@Bean(name = "dubboProperties")
//	public DubboProperties dubboProperties() {
//		DubboProperties dp = new DubboProperties();
//		dp.setApplication(application());
//		dp.setRegistry(registry());
//		dp.setProtocol(protocol());
//		dp.setScan(dubboConfigProperties.getScan());
//		return dp;
//	}
	
	

}
