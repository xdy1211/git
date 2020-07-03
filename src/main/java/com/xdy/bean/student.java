package com.xdy.bean;

import lombok.Data;

import java.io.Serializable;

@Data
//创建实体类实现序列化接口，否则无法写入redis数据库
public class student implements Serializable {
    private Integer id;
    private String name;
    private Integer score;
}
