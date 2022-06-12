package com.newsonline.back_end.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newsonline.back_end.JsonResult;
import com.newsonline.back_end.dao.Comments;
import com.newsonline.back_end.mapper.CommentMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Resource
    CommentMapper commentMapper;

    @GetMapping("/comment/{id}")
    public JsonResult<List<Comments>> getCommentByNid(@PathVariable Integer id) {
        QueryWrapper<Comments> wrapper = new QueryWrapper<>();
        wrapper.select("cnid", "ccontent", "cdate", "cauthor", "cip").eq("cnid", id);
        List<Comments> comments = commentMapper.selectList(wrapper);
        if (comments != null) {
            return new JsonResult<>(comments);
        }
        return new JsonResult<>();
    }
}
