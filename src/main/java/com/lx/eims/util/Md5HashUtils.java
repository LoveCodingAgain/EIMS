package com.lx.eims.util;
import org.apache.shiro.crypto.hash.Md5Hash;
/**
 * @author: lixing
 * date: 2019-03-17
 * time: 13:51
 * description:Mの5加密
 */
public class Md5HashUtils {
    public static void main(String[] args) {
        Md5Hash md5=new Md5Hash("90c3c677041f5d3c8457d6ccf7c51a19","tomcat",2);
        System.out.println(md5.toString());

    }
}
