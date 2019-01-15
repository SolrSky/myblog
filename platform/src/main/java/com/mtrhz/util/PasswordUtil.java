package com.mtrhz.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 密码工具类
 * @author Solrsky
 * @date 2018/12/11
 */
public class PasswordUtil {

    private static final char[] randomChars	= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    /**
     * 获得随机16位盐值
     * @return
     */
    public static String getSalt(){
        return RandomStringUtils.random(16,randomChars);
    }

    /**
     * 生成16位随机盐值，并和真实密码进行拼接后，使用md5加密，得到32位密文
     * 然后将16位盐值按规则插入到32位密文中，形成48位的最终密文.
     * @param password 真实用户密码
     * @return
     */
    public static String generate(String password){
        String salt = RandomStringUtils.random(16, randomChars);
        return generate(password, salt);
    }

    /**
     * 使用指定的盐值和密码生成密文
     * @param password
     * @param salt
     * @return
     */
    public static String generate(String password, String salt){
        String md5Password = MD5Util.encryptMD5(password + salt);
        int charSize = 48;
        char[] cs = new char[charSize];
        for (int i = 0; i < charSize; i += 3) {
            cs[i] = md5Password.charAt(i / 3 * 2);
            cs[i + 1] = salt.charAt(i / 3);
            cs[i + 2] = md5Password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 校验密码
     * @param md5Password 密文
     * @param password    真实密码
     * @param salt        密码盐值
     * @return
     */
    public static boolean validPassword(String md5Password, String password, String salt){
        String md5 = generate(password, salt);
        return md5.equals(md5Password);
    }





    public static void main(String[] args) {
        String password = "123456";
        String salt = getSalt();
        String md5 = generate(password, salt);
        System.out.println(validPassword(md5, password, salt));
    }

}
