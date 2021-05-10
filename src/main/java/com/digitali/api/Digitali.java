package com.digitali.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.digitali.api.entity.User;
import com.digitali.api.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Digitali {
	@Autowired
	private UserRepository repository;

	@PostConstruct
	public void initUsers() {
		List<User> users = Stream
				.of(new User(101, "digitali", "password", "digitali@gmail.com", "Eduardo 1", "819959990608", null,true, 1L),
						new User(102, "user1", "pwd1", "user1@gmail.com", "Eduardo 2", "819959990608", null, true, 1L),
						new User(103, "user2", "pwd2", "user2@gmail.com", "Eduardo 3", "819959990608", null, true, 1L),
						new User(104, "user3", "pwd3", "user3@gmail.com", "Eduardo 4", "819959990608", null, true, 1L))
				.collect(Collectors.toList());
		repository.saveAll(users);
	}

	@Bean
	public WebMvcConfigurer corsConfigure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowCredentials(true);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Digitali.class, args);
	}
}
