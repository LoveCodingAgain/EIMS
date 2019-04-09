package com.lx.eims.entity.contract;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author: lixing
 * date: 2019-04-08
 * time: 20:59
 * description:合同实体类
 */
@TableName("constract")
@Setter
@Getter
public class Constract {
    /**
     * 合同表ID
     */
    private Integer constractId;
    /**
     * 合同甲方
     */
    private  String customerUnit;
    /**
     * 合同乙方
      */
    private String constractNameSecond;

    /**
     * 合同内容
     */
    private  String constractContent;

    /**
     * 合同类型
     */
    private String constractType;
    /**
     * 合同状态
     */
    private String constractStatus;
    /**
     * 合同金额
     */
    private BigDecimal constractAmount;

    /**
     * 合同签订时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date constractSignTime;

    /**
     * 合同结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date constractEndTime;

    @Override
    public String toString() {
        return "Constract{" +
                "constractId=" + constractId +
                ", customerUnit='" + customerUnit + '\'' +
                ", constractNameSecond='" + constractNameSecond + '\'' +
                ", constractContent='" + constractContent + '\'' +
                ", constractType='" + constractType + '\'' +
                ", constractStatus='" + constractStatus + '\'' +
                ", constractAmount=" + constractAmount +
                ", constractSignTime=" + constractSignTime +
                ", constractEndTime=" + constractEndTime +
                '}';
    }
}
