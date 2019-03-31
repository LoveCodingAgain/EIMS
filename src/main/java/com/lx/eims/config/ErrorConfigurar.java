package com.lx.eims.config;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
/**
 * @author: lixing
 * date: 2019-03-18
 * time: 17:45
 * description:系统错误页面
 */
@Configuration
public class ErrorConfigurar implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages=new ErrorPage[2];
        // 系统404错误页面和500错误页面
        errorPages[0]=new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        errorPages[1]=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
        registry.addErrorPages(errorPages);
    }
}
