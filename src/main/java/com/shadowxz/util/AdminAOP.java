package com.shadowxz.util;

import com.shadowxz.domain.Admin;
import com.shadowxz.domain.Constant;
import com.shadowxz.service.AdminService;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xz on 2017/5/7.
 */

@Component
@Aspect
public class AdminAOP {

    @Autowired
    AdminService adminService;

    @Pointcut("execution(* com.shadowxz.controller.*.postLogin())")
    public void joinPoint(){

    }

    //@Around(value = "joinPoint()",argNames = "")
    public Map<String,Object> postLogin(Joinpoint joinPoint, HttpServletRequest request){
        Integer sesstionId = (Integer) request.getSession().getAttribute("sesstionId");
        Map<String,Object> result = new HashMap<>();
        if(sesstionId == null) {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        } else{
            Admin admin = adminService.findById(sesstionId);
            if(admin.getState() == 1){
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                result.put("resultCode", Constant.RETURN_CODE_SUCC);
                result.put("msg","添加管理员成功");
            }else {
                result.put("resultCode",Constant.RETURN_CODE_ERR);
                result.put("msg","你没有添加该权限");
            }
        }
        return result;
    }
}
