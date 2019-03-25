package com.lx.eims.util;
import com.lx.eims.exception.GlobalException;
import org.apache.commons.lang.StringUtils;
/**
 * @author: lixing
 * date: 2019-03-18
 * time: 22:49
 * description:数据校验工具类
 */
public class AssertUtils {
    /**
     * 校验是空的字符串
     * @param string
     * @param message
     */
    public static void isBlank(String string, String message){
        // 或者是isEmpty().
        if(StringUtils.isBlank(string)){
            throw  new GlobalException(message);
        }
    }

    /**
     * 校验数据为null
     * @param obejct
     * @param message
     */
    public static void isNull(Object obejct, String message){
        if(obejct==null){
            throw  new GlobalException(message);
        }
    }

}
