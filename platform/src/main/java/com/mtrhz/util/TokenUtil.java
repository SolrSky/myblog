package com.mtrhz.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * token令牌工具类
 * @author Solrsky
 * @date 2019/1/14
 */
public class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    private static final char[] randomChars	= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    private static final String SEED = "methz";

    /**
     * 获得随机16位盐值
     * @return
     */
    private static String getSalt(){
        return RandomStringUtils.random(16,randomChars);
    }

    /**
     * 令牌生成器<br/>
     * 规则: userid + 16位随机字符 + seed + username + 当前时间（yyyyMMddHHmmss） 并使用MD5加密
     * @param userId 用户id
     * @param username 用户名
     * @return
     */
    public static String createToken(Integer userId, String username){
        StringBuilder sb = new StringBuilder();
        sb.append(userId);
        sb.append(getSalt());
        sb.append(SEED);
        sb.append(username);
        sb.append(new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date()));
        return MD5Util.encryptMD5(sb.toString());
    }



    public static void main(String[] args) {
        TokenUtil tokenUtil = new TokenUtil();
        System.out.println(createToken(1234, "beibei"));
    }

}
