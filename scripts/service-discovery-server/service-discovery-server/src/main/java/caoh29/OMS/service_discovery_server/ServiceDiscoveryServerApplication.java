package caoh29.OMS.service_discovery_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDiscoveryServerApplication.class, args);
	}

}
