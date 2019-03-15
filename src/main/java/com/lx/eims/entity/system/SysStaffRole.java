package com.lx.eims.entity.system;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 13:18
 * description:员工角色关系
 */
@Data
@TableName("sys_staff_role")
public class SysStaffRole implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 员工角色id
     */
    @TableId(type= IdType.AUTO)
    private Integer id;

    /**
     * 员工id
     */
   private Integer staffId;

    /**
     * 角色id
     */
    private Integer roleId;


}
