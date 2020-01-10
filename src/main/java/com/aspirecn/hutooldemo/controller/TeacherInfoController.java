package com.aspirecn.hutooldemo.controller;

import com.aspirecn.hutooldemo.common.Result;
import com.aspirecn.hutooldemo.common.StatusCode;
import com.aspirecn.hutooldemo.entity.User;
import com.aspirecn.hutooldemo.service.TeacherInfoService;
import com.aspirecn.hutooldemo.utils.ExcelUtils;
import com.aspirecn.hutooldemo.utils.MultipartFileToFile;
import com.sargeraswang.util.ExcelUtil.ExcelLogs;
import com.sargeraswang.util.ExcelUtil.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "TeacherInfoController", description = "教师信息管理")
public class TeacherInfoController {
    @Autowired
    private TeacherInfoService teacherInfoService;

    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ApiOperation("查询所有教师信息")
    public Result<User> findAll() {
        List<User> userList = teacherInfoService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", userList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("添加教师信息")
    public Result<User> add(@RequestBody User user) {
        Integer add = teacherInfoService.add(user);
        if (add>0) {
            return new Result<>(true, StatusCode.OK, "添加成功");
        }
        return new Result<>(false, StatusCode.ERROR, "添加失败");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation("删除教师信息")
    public Result<User> delete(@PathVariable Integer id ){
        Integer delete = teacherInfoService.delete(id);
        if (delete>0) {
            return new Result<>(true, StatusCode.OK, "删除成功");
        }
        return new Result<>(false, StatusCode.ERROR, "删除失败");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("根据id查询教师信息")
    public Result<User> findById(@PathVariable Integer id) {
        User user = teacherInfoService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", user);
    }


    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ApiOperation("批量导出")
    public Result<?> importUser(HttpServletResponse response) {
        teacherInfoService.findAllUser(response);
        return new Result<>(true, StatusCode.OK, "导出成功");
    }


    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ApiOperation("hutoo工具包批量导入")
    public Result<?> importUser(MultipartFile file) throws Exception {
        teacherInfoService.importUser(file);
        return new Result<>(true, StatusCode.OK, "导入成功");
    }

    @ApiOperation("批量导入")
    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
    public Object uploadExcel(@RequestParam MultipartFile file) throws Exception {
        teacherInfoService.uploadExcel(file);
        return new Result<>(true, StatusCode.OK, "导入成功");
    }
}
