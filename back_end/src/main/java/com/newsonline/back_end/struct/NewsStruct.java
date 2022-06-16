package com.newsonline.back_end.struct;

import com.newsonline.back_end.model.Comments;
import com.newsonline.back_end.model.News;
import com.newsonline.back_end.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsStruct {
    private News news;
    private List<Comments> comments;
    private Topic topic;
}
