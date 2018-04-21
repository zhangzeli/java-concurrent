package com.test.version3future;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zhangzeli
 * @date 10:07 2018/4/21
 * <P></P>
 */
public class T {

    public static void main(String[] args) {
        ConcurrentHashMap c = new ConcurrentHashMap();
        System.out.println(c.put("1", "1"));
        System.out.println(c.put("1", "2"));
        System.out.println(c.get("1"));
    }
}
