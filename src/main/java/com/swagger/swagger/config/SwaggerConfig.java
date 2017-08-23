package com.swagger.swagger.config;

import static com.google.common.base.Predicates.or;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .paths(paths())
                .build();

    }

    @SuppressWarnings("unchecked")
    private Predicate<String> paths() {
        return or(regex("/api/account.*"),regex("/api/user.*"));
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Api document")
                .license("free open source.")
                .version("0.1v")
                .build();
    }

}
