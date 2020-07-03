package com.xdy.controller;

import com.xdy.bean.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class controller {
    @Autowired
    RedisTemplate redisTemplate;
    //增加学生信息
    @PostMapping("/set")
    public void set(@RequestBody student student){
redisTemplate.opsForValue().set("student",student);
    }
    //获取学生信息
    @GetMapping("/get/{key}")
    public student get(@PathVariable("key") String key){
    return     (student)redisTemplate.opsForValue().get(key);
    }
    //删除学生信息
    @DeleteMapping("/delete/{key}")
    public boolean delete(@PathVariable("key") String key){
        redisTemplate.delete(key);
        return  redisTemplate.hasKey(key);
    }
    //操作字符串
    @GetMapping("/setstr")
    public String str(){
        redisTemplate.opsForValue().set("str","hello world");
        return  (String)redisTemplate.opsForValue().get("str");
    }
    //操作list
    @GetMapping("/setlist")
    public List<String> list(){
        ListOperations<String,String> ls=  redisTemplate.opsForList();
        ls.leftPush("list","hello");
        ls.leftPush("list","world");
        ls.leftPush("list","jisnuanji");
      return   ls.range("list",0,2);
    }
}
