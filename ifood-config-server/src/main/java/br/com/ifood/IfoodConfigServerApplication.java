package br.com.ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class IfoodConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfoodConfigServerApplication.class, args);
	}

}
