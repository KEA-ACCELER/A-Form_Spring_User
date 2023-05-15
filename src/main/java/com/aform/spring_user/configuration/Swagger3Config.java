package com.aform.spring_user.configuration;

//import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class Swagger3Config {

    @Value("${springdoc.version}")
    private String springdocVersion;

  @Bean
  public OpenAPI openAPI() {
    Info info = new Info()
        .title("타이틀 입력")
        .version(springdocVersion)
        .description("API에 대한 설명 부분");

    return new OpenAPI()
        .components(new Components())
        .info(info);
  }
}