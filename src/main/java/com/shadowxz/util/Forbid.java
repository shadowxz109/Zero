package com.shadowxz.util;

import com.shadowxz.domain.Constant;
import com.shadowxz.domain.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by xz on 2017/5/10.
 */
public class Forbid {
    public static void checkForbid(User user,Map<String,Object> result){
        result.put("resultCode", Constant.RETURN_CODE_ERR);
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date forbidTime = user.getForbidTime();
        String fbtStr = sdt.format(forbidTime);
        result.put("msg","您由于违反了社区规定，目前处于禁言状态，无法发表回复\n禁言截至时间:"+fbtStr);
    }
}
