package com.curso;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ClienteApplication implements HealthCheckHandler {
	private int counter=-1;
	@Override
	public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus instanceStatus) {
		counter ++;
		switch (counter){
			case 0:
				return InstanceInfo.InstanceStatus.OUT_OF_SERVICE;
			case 1:
				return InstanceInfo.InstanceStatus.DOWN;
			case 2:
				return InstanceInfo.InstanceStatus.STARTING;
			case 3:
				return InstanceInfo.InstanceStatus.UNKNOWN;
			default:
				return InstanceInfo.InstanceStatus.UP;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
	}

}
