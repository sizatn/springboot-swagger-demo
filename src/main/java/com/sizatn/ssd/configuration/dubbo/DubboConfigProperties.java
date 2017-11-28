package com.sizatn.ssd.configuration.dubbo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dubbo")
public class DubboConfigProperties {

	private String scan;
	private String applicationName;
	private String registryProtocol;
	private String registryAddress;
	private String registryPort;
	private String protocolName;
	private String protocolPort;

	public DubboConfigProperties() {
	}

	public DubboConfigProperties(String scan, String applicationName, String registryProtocol, String registryAddress,
			String registryPort, String protocolName, String protocolPort) {
		this.scan = scan;
		this.applicationName = applicationName;
		this.registryProtocol = registryProtocol;
		this.registryAddress = registryAddress;
		this.registryPort = registryPort;
		this.protocolName = protocolName;
		this.protocolPort = protocolPort;
	}

	public String getScan() {
		return scan;
	}

	public void setScan(String scan) {
		this.scan = scan;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public String getProtocolName() {
		return protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public String getProtocolPort() {
		return protocolPort;
	}

	public void setProtocolPort(String protocolPort) {
		this.protocolPort = protocolPort;
	}

	public String getRegistryProtocol() {
		return registryProtocol;
	}

	public void setRegistryProtocol(String registryProtocol) {
		this.registryProtocol = registryProtocol;
	}

	public String getRegistryPort() {
		return registryPort;
	}

	public void setRegistryPort(String registryPort) {
		this.registryPort = registryPort;
	}

}
