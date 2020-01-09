package com.aspirecn.hutooldemo.controller;

import com.aspirecn.hutooldemo.common.Result;
import com.aspirecn.hutooldemo.common.StatusCode;
import com.aspirecn.hutooldemo.entity.User;
import com.aspirecn.hutooldemo.service.TeacherInfoService;
import com.aspirecn.hutooldemo.utils.ExcelUtils;
import com.aspirecn.hutooldemo.utils.MultipartFileToFile;
import com.sargeraswang.util.ExcelUtil.ExcelLogs;
import com.sargeraswang.util.ExcelUtil.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description TeacherInfoController
 * @Author Fuyufeng
 * @Date 2020/1/7 11:52
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/select")
public class TeacherInfoController {
    @Autowired
    private TeacherInfoService teacherInfoService;

    /**
     * 查询所有教师信息
     * @return
     */
    @GetMapping
    public Result<User> findAll() {
        List<User> userList = teacherInfoService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", userList);
    }

    /**
     * 添加教师信息
     * @return
     */
    @PostMapping("/add")
    public Result<User> add(@RequestBody User user) {
        Integer add = teacherInfoService.add(user);
        if (add>0) {
            return new Result<>(true, StatusCode.OK, "添加成功");
        }
        return new Result<>(false, StatusCode.ERROR, "添加失败");
    }

    /**
     * 删除教师信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<User> delete(@PathVariable Integer id ){
        Integer delete = teacherInfoService.delete(id);
        if (delete>0) {
            return new Result<>(true, StatusCode.OK, "删除成功");
        }
        return new Result<>(false, StatusCode.ERROR, "删除失败");
    }

    /**
     * 根据id查询教师信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Integer id) {
        User user = teacherInfoService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", user);
    }

    /**
     * 批量导出
     *
     * @param response
     * @return
     */
    @RequestMapping("/export")
    public Result<?> importUser(HttpServletResponse response) {
        teacherInfoService.findAllUser(response);
        return new Result<>(true, StatusCode.OK, "导出成功");
    }
    /**
     * hutoo工具包批量导入
     *
     * @param
     * @return
     */
    @RequestMapping("/import")
    public Result<?> importUser(MultipartFile file) throws Exception {
        teacherInfoService.importUser(file);
        return new Result<>(true, StatusCode.OK, "导入成功");
    }

    /**
     * 批量导入
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadExcel")
    public Object uploadExcel(@RequestParam MultipartFile file) throws Exception {
        teacherInfoService.uploadExcel(file);
        return new Result<>(true, StatusCode.OK, "导入成功");
    }
}
