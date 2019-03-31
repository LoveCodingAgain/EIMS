package com.lx.eims.entity.assets;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author: lixing
 * date: 2019-03-29
 * time: 12:26
 * description:资产管理实体类
 */
@TableName("ass_information")
@Setter
@Getter
public class AssetsInfo{
    /**
     * 资产信息ID
     */
    @TableId(type= IdType.AUTO)
    private Integer assInforId;
    /**
     * 资产大类
     */
    private String largeCategory;
    /**
     * 资产小类
     */
    private String smallCategory;
    /**
     * 资产名称
     */
    private String assInforName;
    /**
     * 资产数量
     */
    private Integer assInforNum;

    /**
     * 资产状态
     */
    private Integer assInforStatus;
    /**
     * 资产备注(1:可用,0禁止)
     */
    private String assInforRemark;
    /**
     * 资产单价
     */
    private BigDecimal assInforPrice;
    /**
     * 资产新增时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assCreateTime;

    /**
     * 资产更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assUpdateTime;


    @Override
    public String toString() {
        return "AssetsInfo{" +
                "assInforId=" + assInforId +
                ", largeCategory='" + largeCategory + '\'' +
                ", smallCategory='" + smallCategory + '\'' +
                ", assInforName='" + assInforName + '\'' +
                ", assInforNum=" + assInforNum +
                ", assInforStatus=" + assInforStatus +
                ", assInforRemark='" + assInforRemark + '\'' +
                ", assInforPrice=" + assInforPrice +
                ", assCreateTime=" + assCreateTime +
                ", assUpdateTime=" + assUpdateTime +
                '}';
    }
}
