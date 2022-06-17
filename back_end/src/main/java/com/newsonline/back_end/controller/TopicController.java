package com.newsonline.back_end.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newsonline.back_end.utils.JsonResult;
import com.newsonline.back_end.model.Topic;
import com.newsonline.back_end.mapper.TopicMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TopicController {

    @Resource
    TopicMapper topicMapper;

    @GetMapping("/topic")
    public JsonResult<List<Topic>> getTopicById(@RequestParam(required = false, defaultValue = "-1") Integer id) {
        List<Topic> topics;
        if (id != -1) {
            QueryWrapper<Topic> wrapper = new QueryWrapper<>();
            wrapper.select("tid", "tname").eq("tid", id);
            topics = topicMapper.selectList(wrapper);
        }
        else {
            topics = topicMapper.selectList(null);
        }

        if (topics != null) {
            return new JsonResult<>(topics);
        }
        return new JsonResult<>();
    }

    @PostMapping("/topic/append")
    public JsonResult<String> appendTopic(@RequestParam String name) {
        Topic topic = new Topic(null, name);
        int res = topicMapper.insert(topic);
        if (res > 0) return new JsonResult<>("");
        return new JsonResult<>();
    }

    @PostMapping("/topic/modify")
    public JsonResult<String> updateTopic(@RequestBody Topic newTopic) {
        int res = topicMapper.updateById(newTopic);
        if (res > 0) return new JsonResult<>("");
        return new JsonResult<>();
    }

    @PostMapping("/topic/delete")
    public JsonResult<String> deleteTopic(@RequestParam Integer tid) {
        int res = topicMapper.deleteById(tid);
        if (res > 0) return new JsonResult<>("");
        return new JsonResult<>();
    }
}
