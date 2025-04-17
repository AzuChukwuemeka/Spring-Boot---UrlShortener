package com.urlshortener.shortened;

import com.urlshortener.shortened.utils.UrlShortenerHash;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShortenedApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortenedApplication.class, args);
	}
}
