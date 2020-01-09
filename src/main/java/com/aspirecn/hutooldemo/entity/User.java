package com.aspirecn.hutooldemo.entity;

import com.sargeraswang.util.ExcelUtil.ExcelCell;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @Description User
 * @Author Fuyufeng
 * @Date 2020/1/7 14:23
 * @since JDK 1.8
 */

@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ExcelCell(index = 0)
    private Integer id;

    @Column(name = "name")
    @ExcelCell(index = 1)
    private String name;

    @Column(name = "age")
    @ExcelCell(index = 2)
    private Integer age;

    @Column(name = "sex")
    @ExcelCell(index = 3)
    private String sex;

    @Column(name = "birthday")
    @ExcelCell(index = 4)
    private String birthday;

    @Column(name = "address")
    @ExcelCell(index = 5)
    private String address;

    @Column(name = "outlook")
    @ExcelCell(index = 6)
    private String outlook;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", outlook='" + outlook + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOutlook() {
        return outlook;
    }

    public void setOutlook(String outlook) {
        this.outlook = outlook;
    }
}
