package com.example.icecreamservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class IceCreamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IceCreamServiceApplication.class, args);
	}

	@Configuration
	static class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.authorizeRequests().anyRequest().authenticated()
					.and()
					.oauth2ResourceServer().jwt();
		}

	}

	@Bean
	ApplicationRunner init(IceCreamRepository repository) {
		return args -> {
			Stream.of("Chocolate", "Fruit yogurt", "Mochi", "Blueberry", "Gelato",
					"Sherbet", "Banana", "Double Stick", "Lemon").forEach(name -> {
				IceCream iceCream = new IceCream();
				iceCream.setName(name);
				repository.save(iceCream);
			});
			repository.findAll().forEach(System.out::println);
		};
	}

}
