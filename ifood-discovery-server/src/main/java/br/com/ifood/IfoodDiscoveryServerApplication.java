package br.com.ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class IfoodDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfoodDiscoveryServerApplication.class, args);
	}

}
