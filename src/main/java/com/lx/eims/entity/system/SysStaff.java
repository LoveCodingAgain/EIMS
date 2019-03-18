package com.lx.eims.entity.system;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 12:39
 * description:系统员工
 */
@Data
@TableName("sys_staff")
public class SysStaff implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 员工ID
     */
    @TableId(type = IdType.AUTO)
    private Integer staffId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 验证码
     */
    @TableField(exist = false)
    private String captcha;

    /**
     * 记住我
     */
    @TableField(exist = false)
    private boolean rememberMe;

    /**
     * 员工密码
     */
    private String password;

    /**
     * 员工密码盐值
     */
    private String salt;

    /**
     * 员工工号
     */
    private Integer jobNumber;

    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 员工邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 员工手机号
     */
    private String mobile;

    /**
     * 员工年龄
     */
    private Integer staffAge;

    /**
     * 员工教育背景
     */
    private String education;

    /**
     * 员工专业
     */
    private String profession;

    /**
     * 员工籍贯
     */
    private String birthplace;

    /**
     * 员工政治面貌
     */
    private String political;

    /**
     * 员工职位
     */
    private String position;

    /**
     * 员工性别
     */
    private Character sex;

    /**
     * 员工状态,状态(0:离职,1:在职)
     */
    private Integer status;

    /**
     * 部门id
     */
    @NotBlank(message="部门不能为空")
    private Integer deptId;

    /**
     * 工资id
     */
    private Integer wageId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 部门名称
     */
    @TableField(exist=false)
    private String deptName;

    /**
     * 角色ID列表
     */
    @TableField(exist=false)
    private List<Integer> roleIdList;
}
