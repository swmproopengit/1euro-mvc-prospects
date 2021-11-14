package com.euro.prospects;

import com.euro.prospects.dao.ProspectRepository;
import com.euro.prospects.entities.Prospect;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProspectRepository prospectRepository){
		return args -> {
			Stream.of("MERLIN", "JEAN", "CEDRIC", "GEORGE").forEach(n->{
				prospectRepository.save(new Prospect(UUID.randomUUID().toString(),n,n+"@"+"gmail.com"));
			});
		};
	}

}
