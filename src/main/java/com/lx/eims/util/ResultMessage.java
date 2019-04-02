package com.lx.eims.util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author: lixing
 * date: 2019-04-01
 * time: 12:58
 * description:定义接口返回统一格式对象
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultMessage<T> {

    private Integer code;

    private String msg;

    private T data;

    @Override
    public String toString() {
        return "ResultMessage{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
