package com.lx.eims.config;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author: lixing
 * date: 2019-03-14
 * time: 22:47
 * description:Swagger文档
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    /**
     * 创建项目API文档
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类,生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //包下的类,生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.lx.eims.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    /**
     * 描述项目信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("EIMS企业信息管理系统项目开发接口RESTfulAPI文档")
                .contact(new Contact("李星","(CSDN)https://blog.csdn.net/HcJsJqJSSM","2212640110@qq.com"))
                .description("EIMS项目文档RESTful API")
                .version("1.0.0")
                .build();
    }
}
