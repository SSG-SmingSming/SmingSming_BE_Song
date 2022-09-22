package com.smingsming.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SongApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongApplication.class, args);
	}

}
