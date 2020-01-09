package com.aspirecn.hutooldemo.service;

import com.aspirecn.hutooldemo.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description TeacherInfo
 * @Author Fuyufeng
 * @Date 2020/1/7 11:40
 * @since JDK 1.8
 */
public interface TeacherInfoService {
    List<User> findAll();

    User findById(Integer id);

    void findAllUser(HttpServletResponse response);

    Integer add(User user);

    Integer delete(Integer id);

    void importUser(MultipartFile file) throws Exception;

    void uploadExcel(MultipartFile file) throws Exception;
}
