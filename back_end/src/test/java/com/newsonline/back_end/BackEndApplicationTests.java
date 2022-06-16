package com.newsonline.back_end;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsonline.back_end.model.News;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BackEndApplicationTests {

    @Resource
    ObjectMapper mapper;

    @Test
    void contextLoads() throws JsonProcessingException {
        String json = "{\"nid\":null,\"ntid\":1,\"ntitle\":\"test\",\"nauthor\":\"admin\",\"ncreatedate\":\"2022-06-14T04:15:47.135Z\",\"npic\":null,\"ncontent\":\"test\",\"nmodifydate\":null,\"nsummary\":\"test\"}";
        News news = new News();
        news = mapper.readValue(json, News.class);
        System.out.println(news.toString());
    }

}
