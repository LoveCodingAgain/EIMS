package com.lx.eims.entity.data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
/**
 * @author: lixing
 * date: 2019-04-08
 * time: 23:57
 * description:数据管理模块
 */
@Setter
@Getter
public class Data {
    private Integer dataId;

    private String operator;

    private String formsName;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operatorTime;
}
