package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-09-19
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> listHot();

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
