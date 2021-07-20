package com.erik5594.apivr.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author erik_
 * Data Criacao: 20/07/2021 - 10:59
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig  implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.erik5594.apivr.resources"))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Pautas", "Gerenciar pautas."), new Tag("Votos", "Registrar votos."));
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Registro de Votos API")
                .description("API - Para registro de votos de uma pauta de assembleia")
                .version("1")
                .contact(new Contact("Erik5594", "https://github.com/Erik5594/", "erik.derick74@gmail.com"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

}