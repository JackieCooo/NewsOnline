package com.newsonline.back_end.struct;

import com.newsonline.back_end.model.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationStruct {
    private List<News> news = null;
    private Long cnt = 0L;
}
