package com.lx.eims.entity.system;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 13:23
 * description:系统角色
 */
@Data
@TableName
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色名称
     */
    @NotBlank(message="角色名称不能为空")
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    @NotNull(message="部门不能为空")
    private Long deptId;

    /**
     * 部门名称
     */
    @TableField(exist=false)
    private String deptName;
    /**
     * 菜单列表
     */
    @TableField(exist=false)
    private List<Integer> menuIdList;
    /**
     * 部门列表
     */
    @TableField(exist=false)
    private List<Integer> deptIdList;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
