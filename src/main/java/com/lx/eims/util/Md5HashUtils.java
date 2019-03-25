package com.lx.eims.util;
import org.apache.shiro.crypto.hash.Md5Hash;
/**
 * @author: lixing
 * date: 2019-03-17
 * time: 13:51
 * description:Mの5加密工具类
 */
public class Md5HashUtils {

    /**
     * hash次数
      */
    private static final int HASH_ITERATIONS=2;
    /**
     * md5加盐加密
     * @param password
     * @param salt
     * @return
     */
    public static String md5(String password, String salt){
        Md5Hash md5=new Md5Hash(password, salt, HASH_ITERATIONS);
        return md5.toString();
    }

    public static void main(String[] args) {
        Md5Hash md5=new Md5Hash("90c3c677041f5d3c8457d6ccf7c51a19","tomcat",2);
        System.out.println(md5.toString());

    }
}
