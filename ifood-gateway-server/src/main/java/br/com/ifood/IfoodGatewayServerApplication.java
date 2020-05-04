package br.com.ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class IfoodGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfoodGatewayServerApplication.class, args);
	}

}
