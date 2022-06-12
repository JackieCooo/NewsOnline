package com.newsonline.back_end.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsUsers {
    @TableId
    private Integer usid;
    private String uname;
    private String upwd;
}
