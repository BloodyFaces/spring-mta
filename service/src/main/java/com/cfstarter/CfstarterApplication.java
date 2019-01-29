package com.cfstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan("com.sap.cloud.sdk")
public class CfstarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfstarterApplication.class, args);
	}

}