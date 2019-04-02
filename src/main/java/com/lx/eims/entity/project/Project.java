package com.lx.eims.entity.project;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;
/**
 * @author: lixing
 * date: 2019-03-31
 * time: 17:43
 * description:项目基本信息表
 */
@TableName("project")
@Setter
@Getter
public class Project {
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 项目名称
     */
    private String proName;
    /**
     * 项目负责人
     */
    @NotBlank(message = "项目负责人不能为空!")
    private String principal;
    /**
     * 项目描述
     */
    private String proDesci;
    /**
     * 项目类型
     */
    private String proType;

    /**
     * 项目状态
     */
    private Integer proStatus;

    /**
     * 项目开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 项目结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @Override
    public String toString() {
        return "Project{" +
                "pro_id=" + projectId +
                ", proName='" + proName + '\'' +
                ", principal='" + principal + '\'' +
                ", proDesci='" + proDesci + '\'' +
                ", proType='" + proType + '\'' +
                ", proStatus=" + proStatus +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
