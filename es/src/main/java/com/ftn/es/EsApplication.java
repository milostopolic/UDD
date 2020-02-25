package com.ftn.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@EnableWebMvc
@SpringBootApplication
public class EsApplication {
	
	@Bean
    public RestTemplate restTemplate() throws Exception{
        RestTemplate template = new RestTemplate();
        return template;
    }

	public static void main(String[] args) {
		SpringApplication.run(EsApplication.class, args);
	}

}
