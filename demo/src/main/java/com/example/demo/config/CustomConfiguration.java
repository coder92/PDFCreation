//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
///**
// * The type Custom configuration.
// *
// * @author Ankit.Dixit
// * @version 1.0.0.0
// * @since 1.0.0.0
// */
//@Configuration
//@EnableSwagger2
//public class CustomConfiguration extends WebMvcConfigurationSupport {
//
//    /**
//     * Version 1 docket.
//     *
//     * @return the docket
//     */
//    @Bean
//    public Docket version1() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
//                .paths(regex(".*" + ".*")).build().enable(true)
//                .groupName("Demo-1.1.0.0")
//                .apiInfo(new ApiInfoBuilder().description("Demo APIs")
//                        .title("demo-1.0.0.0").version("1.0.0.0").build());
//    }
//
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//    }
//
//}
