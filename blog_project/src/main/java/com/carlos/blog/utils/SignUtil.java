package com.carlos.blog.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * 签名工具类
 * @author Solrsky
 * @date 2018/12/12
 */
public class SignUtil {

    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

    /**
     * 根据参数map获得签名
     * @param param
     * @return
     */
    public static String getSign(TreeMap<String, Object> param) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            sb.append(entry.getKey()).append(entry.getValue());
        }
        return MD5Util.encryptMD5(sb.toString());
    }

    /**
     * 签名校验
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    public static boolean validSign(HttpServletRequest request) throws UnsupportedEncodingException {
        // 获得客户端传过来的sign参数值
        String sign = request.getParameter("sign");
        // 根据参数生成计算sign值
        TreeMap<String, Object> treeMap = new TreeMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()){
            String paramName = enumeration.nextElement().trim();
            if(!paramName.equals("sign")){
                treeMap.put(paramName, URLDecoder.decode(request.getParameter(paramName), "UTF-8"));
            }
        }
        // 根据参数生成的sign值
        String realSign = getSign(treeMap);
        return sign.equals(realSign);
    }
}
