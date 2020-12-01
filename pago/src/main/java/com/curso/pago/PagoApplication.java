package com.curso.pago;


import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class PagoApplication implements HealthCheckHandler {
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
		SpringApplication.run(PagoApplication.class, args);

	}


}
