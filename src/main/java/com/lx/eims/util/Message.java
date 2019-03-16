package com.lx.eims.util;
import java.util.HashMap;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-13
 * time: 11:01
 * description:自定义返回异常信息
 */
public class Message extends HashMap<String, Object> {

    public Message(){
        put("code" ,0);
        put("msg", "Success");
    }

    public static Message error(){
        return error(500, "未知异常,请联系管理员吧!");
    }

    public static Message error(String msg){
        return error(500, msg);
    }

    public static Message error(int code, String msg){
        Message message=new Message();
        message.put("code", code);
        message.put("msg", msg);
        return message;
    }


    public static  Message ok(String msg){
        Message message=new Message();
        message.put("msg",msg);
        return message;
    }


    public static Message ok(Map<String,Object> map){
        Message message=new Message();
        message.putAll(map);
        return message;
    }

    public static Message ok(){
        return new Message();
    }

    /**
     * 重写Map的put()方法.
     * @param key
     * @param value
     * @return
     */
    @Override
    public  Message put(String key, Object value){
        super.put(key,value);
        return this;
    }

}
