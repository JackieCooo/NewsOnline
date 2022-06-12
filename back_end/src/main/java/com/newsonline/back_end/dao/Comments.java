package com.newsonline.back_end.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @TableId
    private Integer cid;
    private Integer cnid;
    private String ccontent;
    private LocalDateTime cdate;
    private String cip;
    private String cauthor;
}
