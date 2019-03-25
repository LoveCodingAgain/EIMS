package com.lx.eims.shiro;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.eims.entity.system.SysMenu;
import com.lx.eims.entity.system.SysStaff;
import com.lx.eims.mapper.system.SysMenuMapper;
import com.lx.eims.mapper.system.SysStaffMapper;
import com.lx.eims.util.Constant;
import com.lx.eims.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: lixing
 * date: 2019-03-13
 * time: 12:44
 * description:用户数据源,自定义Realm.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysStaffMapper sysStaffMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 系统授权 principals
     * @param
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Integer staffId= ShiroUtils.getStaffId();
        System.out.println("员工ID"+staffId);
        // 权限集合
        List<String> permsList=null;
        // 系统管理员拥有最高权限
        if(staffId== Constant.SUPER_ADMIN){
            List<SysMenu> menuList = sysMenuMapper.selectList(null);
            for(SysMenu menu: menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            // 非管理员要查询一下
            permsList = sysStaffMapper.queryAllPerms(staffId);
        }
        // 用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }
    /**
     * 系统认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        // 获取用户信息.
        QueryWrapper<SysStaff> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",token.getUsername());
        SysStaff staff=sysStaffMapper.selectOne(queryWrapper);
        System.out.println("password:"+staff.getPassword());
        // 判断用户是否存在
        if(staff==null){
            throw new UnknownAccountException("账号或密码不正确!");
        }
        // 判断账户锁定
        if(staff.getStatus()==0){
            throw new LockedAccountException("账号已被锁定,请联系系统管理员!");
        }
        // 判断用户密码
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(staff,
                staff.getPassword(), ByteSource.Util.bytes(staff.getSalt()),getName());
        return info;
    }
}
