package me.pixka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HetApplication {

	public static void main(String[] args) {
		SpringApplication.run(HetApplication.class, args);
	}
}
