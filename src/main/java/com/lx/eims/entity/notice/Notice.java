package com.lx.eims.entity.notice;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
/**
 * @author: lixing
 * date: 2019-04-05
 * time: 16:21
 * description:系统公告
 */
@Setter
@Getter
@TableName("notice")
public class Notice {
     private Integer noticeId;

     private String noticeTitle;

     private String  noticeContent;

     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private Date noticeCreateTime;

     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private Date noticeUpdateTime;

     private Integer noticeStatus;

     private Integer noticeOrder;

     @Override
     public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeCreateTime=" + noticeCreateTime +
                ", noticeUpdateTime=" + noticeUpdateTime +
                ", noticeStatus=" + noticeStatus +
                ", noticeOrder=" + noticeOrder +
                '}';
    }
}
