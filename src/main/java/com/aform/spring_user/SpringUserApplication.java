package com.aform.spring_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

//@OpenAPIDefinition(servers = {@Server(url = "/", description = "https://도메인.com")}) // 스웨거는 기본적으로 http에 요청하기 때문에 https쓰고 싶으면 설정하기
@EnableJpaAuditing
@SpringBootApplication
public class SpringUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringUserApplication.class, args);
	}

}
