package com.lx.eims.entity.system;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 13:40
 * description:系统部门
 */
@Data
@TableName("sys_dept")
public class SysDept implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 部门ID
     */
    @TableId
    private Integer deptId;

    /**
     * 上级部门ID，一级部门为0
     */
     private Integer parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门名称
     */
    @TableField(exist=false)
    private String parentName;
    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 部门状态
     */
    @TableLogic
    private Integer delFlag;

    /**
     * 列表展开
     */
    @TableField(exist=false)
    private Boolean open;
    @TableField(exist=false)
    private List<?> list;


}
