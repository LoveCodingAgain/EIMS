package com.lx.eims.shiro;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.eims.entity.system.SysStaff;
import com.lx.eims.mapper.system.SysMenuMapper;
import com.lx.eims.mapper.system.SysStaffMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 系统授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
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
