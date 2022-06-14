package com.newsonline.back_end.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsonline.back_end.utils.JsonResult;
import com.newsonline.back_end.dao.Comments;
import com.newsonline.back_end.dao.News;
import com.newsonline.back_end.dao.Topic;
import com.newsonline.back_end.mapper.CommentMapper;
import com.newsonline.back_end.mapper.NewsMapper;
import com.newsonline.back_end.mapper.TopicMapper;
import com.newsonline.back_end.struct.NewsStruct;
import com.newsonline.back_end.struct.PaginationStruct;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NewsController {

    @Resource
    NewsMapper newsMapper;
    @Resource
    TopicMapper topicMapper;
    @Resource
    CommentMapper commentMapper;
    @Resource
    ObjectMapper objectMapper;

    @GetMapping("/news/{id}")
    public JsonResult<News> getNewsById(@PathVariable Integer id) {
        NewsStruct newsStruct = new NewsStruct();
        QueryWrapper<News> newsWrapper = new QueryWrapper<>();
        newsWrapper.eq("nid", id);
        News news = newsMapper.selectOne(newsWrapper);
        return new JsonResult<>(news);
    }

    @GetMapping("/title/{id}")
    public JsonResult<List<News>> getNewsTitleById(@PathVariable Integer id) {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        if (id == -1) {
            wrapper.select("nid", "ntitle");
        }
        else {
            wrapper.select("nid", "ntid", "ntitle").eq("ntid", id);
        }
        List<News> list = newsMapper.selectList(wrapper);
        if (list != null) {
            return new JsonResult<>(list);
        }
        return new JsonResult<>();
    }

    @GetMapping("/title")
    public JsonResult<PaginationStruct> getAllNewsTitles(@RequestParam(required = false, defaultValue = "-1") Integer page, @RequestParam(required = false, defaultValue = "-1") Integer tid) {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        wrapper.select("nid", "ntitle", "nauthor", "ncreatedate");
        if (tid != -1) {
            wrapper.eq("ntid", tid);
        }

        PaginationStruct paginationStruct = new PaginationStruct();
        List<News> news;
        if (page != -1) {
            IPage<News> iPage = new Page<>(page, 15);
            iPage = newsMapper.selectPage(iPage, wrapper);
            news = iPage.getRecords();
        }
        else {
            news = newsMapper.selectList(wrapper);
        }

        if (news != null) {
            paginationStruct.setNews(news);
        }

        if (tid != -1) {
            wrapper.clear();
            wrapper.eq("ntid", tid);
            paginationStruct.setCnt(newsMapper.selectCount(wrapper));
        }
        else {
            paginationStruct.setCnt(newsMapper.selectCount(null));
        }

        return new JsonResult<>(paginationStruct);
    }

    @PostMapping("/news/append")
    public JsonResult<String> appendNews(@RequestParam(value = "news")String rawNews, @RequestParam(value = "file", required = false, defaultValue = "null")MultipartFile file) {
        System.out.println(rawNews);
        News news;
        try {
            news = objectMapper.readValue(rawNews, News.class);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(news.toString());
//        System.out.println("文件名：" + file.getOriginalFilename() + ", 文件大小：" + file.getSize());
        if (file != null) {
            try {
                news.setNpic(file.getBytes());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int res = newsMapper.insert(news);
        if (res > 0) return new JsonResult<>("");
        return new JsonResult<>();
    }

    @PostMapping("/news/modify")
    public JsonResult<String> modifyNews(@RequestBody News newNews) {
        int res = newsMapper.updateById(newNews);
        if (res > 0) return new JsonResult<>("");
        return new JsonResult<>();
    }

}
