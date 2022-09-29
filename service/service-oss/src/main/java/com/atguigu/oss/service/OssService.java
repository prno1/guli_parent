package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author pengr
 * @date 2022/9/21 15:13
 */
public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
