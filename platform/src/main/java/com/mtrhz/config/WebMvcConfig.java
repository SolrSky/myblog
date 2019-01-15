package com.mtrhz.config;

import com.mtrhz.interceptor.ApiForwardInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * spring mvc 相关配置，替代xml配置
 * @author Solrsky
 * @date 2019/1/8
 */
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.mtrhz")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        logger.info("开始加载属性文件application.properties...");
        PropertyPlaceholderConfigurer propertyPlaceholder = new PropertyPlaceholderConfigurer();
        ClassPathResource[] resources = new ClassPathResource[] { new ClassPathResource("application.properties") };
        propertyPlaceholder.setLocations(resources);
        propertyPlaceholder.setIgnoreUnresolvablePlaceholders(true);
        return propertyPlaceholder;
    }

    @Bean
    public ViewResolver viewResolver() {
        logger.info("开始注册viewResolver...");
        InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();
        resourceViewResolver.setPrefix("/WEB-INF/views/");
        resourceViewResolver.setSuffix(".jsp");
        resourceViewResolver.setExposeContextBeansAsAttributes(true);
        return resourceViewResolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
        super.configureViewResolvers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("开始配置资源处理器...");
        registry.addResourceHandler("/public/**/*").addResourceLocations("/WEB-INF/public/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiForwardInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
