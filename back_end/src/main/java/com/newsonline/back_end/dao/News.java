package com.newsonline.back_end.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @TableId
    private Integer nid;
    private Integer ntid;
    private String ntitle;
    private String nauthor;
    private LocalDateTime ncreatedate;
    private byte[] npic;
    private String ncontent;
    private LocalDateTime nmodifydate;
    private String nsummary;
}
