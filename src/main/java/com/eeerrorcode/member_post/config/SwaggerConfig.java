package com.eeerrorcode.member_post.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "회원제 게시판", version = "1.0", description = "servlet/JSP migration to spring boot ver 3.4.1.SNAPSHOT"))
public class SwaggerConfig {
  
}
