package com.cfstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sap.cloud.sdk.cloudplatform.CloudPlatform;
import com.sap.cloud.sdk.cloudplatform.CloudPlatformAccessor;

@Configuration
public class CloudConfig {
	
	@Bean
	public CloudPlatform platform() {
		return CloudPlatformAccessor.getCloudPlatform();
	}
	
}
