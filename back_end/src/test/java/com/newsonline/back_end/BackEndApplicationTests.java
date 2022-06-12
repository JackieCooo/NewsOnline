package com.newsonline.back_end;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newsonline.back_end.dao.News;
import com.newsonline.back_end.mapper.NewsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BackEndApplicationTests {

    @Resource
    NewsMapper newsMapper;

    @Test
    void contextLoads() {
        IPage<News> page = new Page<>(1, 5);
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        wrapper.select("ntitle");
        page = newsMapper.selectPage(page, wrapper);
        List<News> records = page.getRecords();
        records.forEach(System.out::println);
    }

}
