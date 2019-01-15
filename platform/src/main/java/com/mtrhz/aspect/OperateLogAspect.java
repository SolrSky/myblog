package com.mtrhz.aspect;

import com.mtrhz.annotation.OperateLog;
import com.mtrhz.base.BaseResponse;
import com.mtrhz.temp.WebLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Solrsky
 * @date 2019/1/14
 */
@Aspect
@Component
public class OperateLogAspect {

    private static Logger LOGGER = LoggerFactory.getLogger(OperateLogAspect.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    ThreadLocal<Long> startTime = new ThreadLocal<>();


    @Pointcut("@annotation(com.mtrhz.annotation.OperateLog)")
    public void logPointCut(){}


    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 请求开始前
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 执行方法前调用
        doBefore(joinPoint, request); //TODO: 返回确定的日志信息
        // 执行方法
        Object result = joinPoint.proceed();
        // 执行方法后调用
//        webLog = doAfter(joinPoint, webLog, result); //TODO: 执行方法后进行分析并进行数据库操作
//        logService.save(webLog);

        return result;
    }

    /**
     * 在进入真正方法前调用，记录访问的方法，参数信息
     * @param joinPoint
     * @param request
     */
    public WebLog doBefore(ProceedingJoinPoint joinPoint, HttpServletRequest request){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        WebLog webLog = new WebLog();
        webLog.setClassName(className);
        webLog.setMethodName(methodName);
        webLog.setVisitTime(new Date(startTime.get()));
        webLog.setCreatTime(new Date());
        Object[] args = joinPoint.getArgs();
        if(null != args && args.length >0){
            webLog.setParams(Arrays.toString(args));
        }
        String ip = getRemoteHost(request);
        webLog.setIp(ip);
        return webLog;
    }

    /**
     *
     * @param joinPoint
     * @param webLog
     * @param result
     * @return
     */
    public WebLog doAfter(ProceedingJoinPoint joinPoint, WebLog webLog, Object result){
        long expendTime = System.currentTimeMillis() - startTime.get();
        webLog.setExpendTime(expendTime);
        if(result instanceof BaseResponse){
        }
        return webLog;
    }

    /**
     * 获取目标主机的ip
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
