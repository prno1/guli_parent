package com.atguigu.msmservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.utils.RandomUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author pengr
 * @date 2022/9/26 20:47
 */
@RestController
@RequestMapping("/edumsm/msm")
@Api("短信模块")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //发送短信的方法
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        //1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //2 如果redis获取 不到，进行阿里云发送
        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("content",code);
        //调用service发送短信的方法
        boolean isSend = msmService.send(code,phone);
        if(isSend) {
            //发送成功，把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }
    }
}
