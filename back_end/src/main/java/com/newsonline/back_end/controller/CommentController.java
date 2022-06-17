package com.newsonline.back_end.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newsonline.back_end.utils.JsonResult;
import com.newsonline.back_end.model.Comments;
import com.newsonline.back_end.mapper.CommentMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Resource
    CommentMapper commentMapper;

    @GetMapping("/comment/{id}")
    public JsonResult<List<Comments>> getCommentById(@PathVariable Integer id) {
        QueryWrapper<Comments> wrapper = new QueryWrapper<>();
        wrapper.select("cid", "cnid", "ccontent", "cdate", "cauthor", "cip").eq("cnid", id);
        List<Comments> comments = commentMapper.selectList(wrapper);
        if (comments != null) {
            return new JsonResult<>(comments);
        }
        return new JsonResult<>();
    }

    @PostMapping("/comment/append")
    public JsonResult<String> appendComment(@RequestBody Comments comments) {
//        System.out.println(comments.toString());
        int res = commentMapper.insert(comments);
        if (res > 0) return new JsonResult<>("");
        return new JsonResult<>();
    }

    @PostMapping("/comment/delete")
    public JsonResult<String> deleteComments(@RequestParam(value = "list")String list) {
//        System.out.println(list);
        String[] cidList = list.substring(1, list.length() - 1).split(",");
        QueryWrapper<Comments> wrapper = new QueryWrapper<>();
        for (int i = 0; i < cidList.length; i++) {
            wrapper.eq("cid", Integer.parseInt(cidList[i]));
            if (i != cidList.length - 1) wrapper.or();
        }
        if (commentMapper.delete(wrapper) > 0) return new JsonResult<>("");
        return new JsonResult<>();
    }
}
