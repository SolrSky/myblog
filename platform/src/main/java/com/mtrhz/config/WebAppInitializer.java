//package com.mtrhz.config;
//
//import com.mtrhz.listener.ContextListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.request.RequestContextListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import javax.servlet.*;
//import java.util.EnumSet;
//
///**
// * @author Solrsky
// * @date 2019/1/9
// */
//public class WebAppInitializer
//        implements WebApplicationInitializer {
//
//    private static final Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        logger.info("web应用开始初始化...");
//        initializeSpringConfig(servletContext);
//        logger.info("初始化springMVC...");
//        initializeSpringMVCConfig(servletContext);
//
////        registerServlet(servletContext);
//
//        logger.info("注册监听器...");
//        registerListener(servletContext);
//
//        logger.info("注册过滤器");
//        registerFilter(servletContext);
//
//    }
//
//
//    private void initializeSpringConfig(ServletContext container) {
//        // Create the 'root' Spring application context
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
////        rootContext.register(AppConfiguration.class);
//        // Manage the life cycle of the root application context
//        rootContext.register(JdbcConfig.class);
//        container.addListener(new ContextLoaderListener(rootContext));
//    }
//
//    private void initializeSpringMVCConfig(ServletContext container) {
//        // Create the spring rest servlet's Spring application context
//        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
//        dispatcherContext.register(WebMvcConfig.class);
//
//        // Register and map the spring rest servlet
//        ServletRegistration.Dynamic dispatcher = container.addServlet("SpringMvc",
//                new DispatcherServlet(dispatcherContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.setAsyncSupported(true);
//        dispatcher.addMapping("/");
//    }
//
////    private void registerServlet(ServletContext container) {
////
////        initializeStaggingServlet(container);
////    }
////
////    private void initializeStaggingServlet(ServletContext container) {
////        StaggingServlet staggingServlet = new StaggingServlet();
////        ServletRegistration.Dynamic dynamic = container.addServlet("staggingServlet", staggingServlet);
////        dynamic.setLoadOnStartup(3);
////        dynamic.addMapping("*.stagging");
////    }
//
//    private void registerListener(ServletContext container) {
//        container.addListener(RequestContextListener.class);
//        container.addListener(ContextListener.class);
//    }
//
//    private void registerFilter(ServletContext container){
//        logger.info("开始设置字符集过滤器...");
//        /*
//         * 字符集过滤器
//         */
//        CharacterEncodingFilter utfEncodingFilter = new CharacterEncodingFilter();
//        utfEncodingFilter.setEncoding("UTF-8");
//        utfEncodingFilter.setForceEncoding(true);
//        FilterRegistration.Dynamic utfEncodingFilterReg = container.addFilter("utfEncodingFilter", utfEncodingFilter);
//        utfEncodingFilterReg.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");
//    }
//}
