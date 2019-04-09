package com.lx.eims.entity.contract;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
/**
 * @author: lixing
 * date: 2019-04-08
 * time: 20:59
 * description:合同实体类
 */
@TableName("contract")
public class Contract {
    /**
     * 合同表ID
     */
    private Integer contractId;
    /**
     * 合同甲方
     */
    private  String customerUnit;
    /**
     * 合同乙方
      */
    private String contractNameSecond;

    /**
     * 合同内容
     */
    private  String constractContent;

    /**
     * 合同状态
     */
    private String constractStatus;

    /**
     * 合同签订时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date contractSignTime;

    /**
     * 合同结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date contractEndTime;

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", customerUnit='" + customerUnit + '\'' +
                ", contractNameSecond='" + contractNameSecond + '\'' +
                ", constractContent='" + constractContent + '\'' +
                ", constractStatus='" + constractStatus + '\'' +
                '}';
    }
}
