package com.atguigu.msmservice.service;

import java.util.Map;

/**
 * @author pengr
 * @date 2022/9/26 20:48
 */
public interface MsmService {
    boolean send(String param, String phone);
}
