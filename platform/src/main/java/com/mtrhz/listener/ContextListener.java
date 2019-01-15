package com.mtrhz.listener;

import com.alibaba.fastjson.JSON;
import com.mtrhz.datasource.DynamicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

/**
 * @author Solrsky
 * @date 2019/1/9
 */
public class ContextListener implements ServletContextListener {

    private ApplicationContext ctx;

    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("初始化容器开始...");
        ServletContext context = sce.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
//        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
//        Map<String, Object> map = query();
//        logger.info(JSON.toJSONString(map));
        logger.info("初始化容器结束...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("销毁容器...");
    }

    private Map<String, Object> query(){
        DynamicDataSource.clearCustomerType();
        DynamicDataSource.setCustomerType(DynamicDataSource.DATA_SOURCE_MYSQL_MASTER);
        return jdbcTemplate.queryForMap("SELECT * FROM TEST");
    }
}
