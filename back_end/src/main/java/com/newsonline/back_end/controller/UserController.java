package com.newsonline.back_end.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newsonline.back_end.JsonResult;
import com.newsonline.back_end.dao.NewsUsers;
import com.newsonline.back_end.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @PostMapping("/login")
    public JsonResult<NewsUsers> userLogin(@RequestParam String username, @RequestParam String password) {
        QueryWrapper<NewsUsers> wrapper = new QueryWrapper<>();
        wrapper.select("uname", "upwd", "usid").eq("uname", username).eq("upwd", password);
        NewsUsers users = userMapper.selectOne(wrapper);
        if (users != null) {
            return new JsonResult<>(users);
        }
        return new JsonResult<>();
    }
}
