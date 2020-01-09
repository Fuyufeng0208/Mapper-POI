package com.aspirecn.hutooldemo.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.aspirecn.hutooldemo.dao.TeacherInfoMapper;
import com.aspirecn.hutooldemo.entity.User;
import com.aspirecn.hutooldemo.service.TeacherInfoService;
import com.aspirecn.hutooldemo.utils.ExcelUtils;
import com.aspirecn.hutooldemo.utils.MultipartFileToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @Description TeacherInfoImpl
 * @Author Fuyufeng
 * @Date 2020/1/7 11:47
 * @since JDK 1.8
 */
@Service
public class TeacherInfoImpl implements TeacherInfoService {
    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    /**
     * 查询所有教师信息
     * @return
     */
    @Override
    public List<User> findAll() {
        List<User> userList = teacherInfoMapper.selectAll();
        return userList;
    }

    /**
     * 根据id查询教师信息
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        User user = teacherInfoMapper.selectByPrimaryKey(id);
        return user;
    }

    /**
     * 教师信息导出
     * @param response
     */
    @Override
    public void findAllUser(HttpServletResponse response) {
        //查询出所有列表信息
        List<User> userList = teacherInfoMapper.selectAll();
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("id", "编号");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("sex", "性别");
        writer.addHeaderAlias("birthday", "出生日期");
        writer.addHeaderAlias("address", "家庭住址");
        writer.addHeaderAlias("outlook", "政治面貌");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(6, "人员信息");

        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(userList, true);

        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");

        //教师名单.xls 是弹出下载对话框的文件名，不能为中文，中文要进行编码转换
        String name = toUtf8String("教师名单");
        response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        //关闭输出Servlet流
        IoUtil.close(out);
    }

    /**
     * 添加教师信息
     * @param user
     */
    @Override
    public Integer add(User user) {
        int insert = teacherInfoMapper.insert(user);
        return insert;
    }

    /**
     * 删除教师信息
     * @param id
     * @return
     */
    @Override
    public Integer delete(Integer id) {
        int i = teacherInfoMapper.deleteByPrimaryKey(id);
        return i;
    }

    /**
     * 使用Hutool工具包批量导入
     * @param file
     * @throws Exception
     */
    @Override
    public void importUser(MultipartFile file) throws Exception {
        File f = MultipartFileToFile.multipartFileToFile(file);
        ExcelReader reader = ExcelUtil.getReader(f);
        List<Map<String,Object>> readAll = reader.readAll();
        List<User> all = reader.readAll(User.class);
        for (Map<String, Object> stringObjectMap : readAll) {
            System.out.println("读取为Map列表"+stringObjectMap);
        }
        for (User user : all) {
            System.out.println("读取为Bean列表"+user);
        }
    }

    /**
     * 批量导入
     * @param file
     * @throws Exception
     */
    @Override
    public void uploadExcel(MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        List<User>list= ExcelUtils.readExcelToEntity(User.class,file.getInputStream(),name);
        for (User user : list) {
            teacherInfoMapper.insert(user);
        }
    }

    /**
     * 转换UTF-8
     *
     * @param string
     * @return
     */
    public static String toUtf8String(String string) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c >= 0 && c <= 255) {
                stringBuffer.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    stringBuffer.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return stringBuffer.toString();
    }

}

