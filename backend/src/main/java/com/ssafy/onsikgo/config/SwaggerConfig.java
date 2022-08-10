//package com.ssafy.onsikgo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//@EnableWebMvc
//public class SwaggerConfig {
//
//    private ApiInfo swaggerInfo() {
//        return new ApiInfoBuilder().title("Onsikgo API")
//                .description("Onsikgo API Docs").build();
//    }
//
//    @Bean
//    public Docket swaggerApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .ignoredParameterTypes(java.sql.Date.class)
//                .forCodeGeneration(true)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(swaggerInfo())
//                .enable(true);
//    }
//}
