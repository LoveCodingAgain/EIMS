package com.lx.eims.entity.contract;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
/**
 * @author: lixing
 * date: 2019-04-10
 * time: 15:19
 * description:合同客户VO
 */
@Setter
@Getter
public class ConstractVO {
    private String customerUnit;

    private String constractNameSecond;

    private String constractContent;

    private String constractType;

    private String constractStatus;

    private String constractAmount;
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

    private String customerAddress;

    private String customerEmail;

    private String customerPhone;

    private String customerProfile;

    @Override
    public String toString() {
        return "ConstractVO{" +
                "customerUnit='" + customerUnit + '\'' +
                ", constractNameSecond='" + constractNameSecond + '\'' +
                ", constractContent='" + constractContent + '\'' +
                ", constractType='" + constractType + '\'' +
                ", constractStatus='" + constractStatus + '\'' +
                ", constractAmount='" + constractAmount + '\'' +
                ", constractSignTime=" + constractSignTime +
                ", constractEndTime=" + constractEndTime +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerProfile='" + customerProfile + '\'' +
                '}';
    }
}
