package com.lx.eims.entity.assets;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-04-01
 * time: 12:31
 * description:返回对象,指定泛型.
 */
@Setter
@Getter
public class AssetsMessage<T> {
    private Integer code;
    private String msg;
    private List<T> list;
}
