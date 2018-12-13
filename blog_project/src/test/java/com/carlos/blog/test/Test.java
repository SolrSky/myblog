package com.carlos.blog.test;

import java.util.Set;
import java.util.TreeMap;

/**
 * @author Solrsky
 * @date 2018/12/12
 */
public class Test {
    public static void main(String[] args) {
        TreeMap<String, Object> map = new TreeMap<>();
        map.put("business", "1");
        map.put("align", 2);
        map.put("test", "3");
        map.put("main", true);

        for (String s : map.keySet()) {
            System.out.println(s);
        }

    }
}
