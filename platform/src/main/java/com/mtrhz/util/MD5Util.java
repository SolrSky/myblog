package com.mtrhz.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * @author Solrsky
 * @date 2018/12/12
 */
public class MD5Util {

    private static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * md5加密算法
     * @param src
     * @return
     */
    public static String encryptMD5(String src){
        try{
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        }catch (Exception e){
            return null;
        }
    }
}
