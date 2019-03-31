package com.lx.eims.controller.system;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.lx.eims.annotation.RecordSysLog;
import com.lx.eims.entity.system.SysStaff;
import com.lx.eims.util.Message;
import com.lx.eims.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * @author: lixing
 * date: 2019-03-16
 * time: 15:55
 * description:系统登录控制器
 */
@Controller
public class SysLoginController {

    @Autowired
    private Producer producer;

    /**
     * 生成图片验证码
     * @param response
     * @throws IOException
     */
    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 员工登录
      * @param staff
     * @return
     */
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    //@RecordSysLog("员工登录")
    public Message login(@RequestBody SysStaff staff){
         // 从Session中获取验证码.
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        System.out.println(staff.getPassword());
        if(!staff.getCaptcha().equals(kaptcha)){
            return Message.error("验证码不正确!");
        }
        // 处理登录逻辑
        try {
            Subject subject=ShiroUtils.getSubject();
            System.out.println(staff.isRememberMe());
            UsernamePasswordToken token = new UsernamePasswordToken(staff.getUsername(), staff.getPassword());
            // 设置Token,记住我的功能.
            token.setRememberMe(staff.isRememberMe());
            subject.login(token);
        } catch (UnknownAccountException e) {
            return Message.error(e.getMessage());
        } catch(IncorrectCredentialsException e){
            return Message.error("账户或密码不正确!");
        } catch(LockedAccountException e){
            return Message.error("账号已被锁定,请联系系统管理员!");
        } catch (AuthenticationException e){
            return Message.error("账号验证失败!");
        }
        return Message.ok();
    }

    /**
     * 系统退出登录.
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return "redirect:/login";
    }
}
