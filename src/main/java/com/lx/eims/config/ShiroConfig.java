package com.lx.eims.config;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.lx.eims.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-13
 * time: 12:22
 * description:Shiro配置.
 */
@Configuration
public class ShiroConfig {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilter.setSecurityManager(securityManager);
        // 设置Shiro的内置过滤器
        /**
         * anon:无需认证登录即可访问!
         * authc:必须认证才可以访问!
         * perms:该资源必须得到资源权限才可以访问!
         */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        // 放行静态资源请求以及Swagger
        filterMap.put("/css/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/fonts/**", "anon");
        filterMap.put("/libs/**", "anon");
        filterMap.put("/plugins/**", "anon");
        filterMap.put("/img/**", "anon");
        // 验证码图片
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/login.html", "anon");
        // 系统登录,放行
        filterMap.put("/sys/login", "anon");
        // 权授权过滤器,当前授权拦截后,Shrio会自动跳转至未授权页面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");
        filterMap.put("/user/search", "perms[user:search]");
        filterMap.put("/user/*", "authc");
        // 拦截所有未经访问的页面至登录页面
        filterMap.put("/**", "authc");
        // 调整登录页面
        shiroFilter.setLoginUrl("/login.html");
        // 设置未授权页面
        shiroFilter.setUnauthorizedUrl("/unauth");
        // 设置
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /**
     * 配置SimpleCookie
     */
    @Bean
    public SimpleCookie cookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(604800);
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        return simpleCookie;
    }

    /**
     * 配置CookieRememberMeManager
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(cookie());
        // 设置Cookie的加密算法(ASE),每个项目不一样.
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联Realm
        securityManager.setRealm(userRealm);
        // 设置CookieRememberMe
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 自定义Realm
     * @param hashedCredentialsMatcher
     * @return
     */
    @Bean("userRealm")
    public UserRealm userRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
       UserRealm userRealm=new UserRealm();
       userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
       return userRealm;
    }
    /**
     * Session交给Shiro管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        // Session有效一小时
        sessionManager.setSessionValidationInterval(3600 * 1000);
        sessionManager.setGlobalSessionTimeout(3600 * 1000);
        return sessionManager;
    }

    /**
     * 配置ShiroDialect
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 注解访问授权动态拦截,不然不会执行doGetAuthenticationInfo
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    /**
     * 配置凭证匹配器
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

}
