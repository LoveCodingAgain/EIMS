package com.lx.eims.entity.assets;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: lixing
 * date: 2019-03-30
 * time: 11:35
 * description:资产类目实体类
 */
@TableName("assets")
@Setter
@Getter
public class AssetsCategory {
    /**
     * 资产类目ID
     */
    @TableId(value="assets_id",type=IdType.AUTO)
    private Integer assetsId;
    /**
     * 资产大类目
     */
    private String largeCategory;
    /**
     * 资产小类目
     */
    private String smallCategory;
    /**
     * 资产类目状态
     */
    private Integer status;

    /**
     * 资产新增时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 资产更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
