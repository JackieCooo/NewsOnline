package com.newsonline.back_end.controller;

import com.newsonline.back_end.service.IUploadService;
import com.newsonline.back_end.utils.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping("/api")
public class UploadController {

    @Resource
    private IUploadService uploadService;

    @PostMapping("/upload")
    public JsonResult<String> uploadNewsPic(@RequestParam("file")MultipartFile file) {
        System.out.println("文件名：" + file.getOriginalFilename() + ", 文件大小：" + file.getSize());
        String path = UploadController.class.getResource("/").getFile();
        File basePath = new File(path + "temp");
        if (!basePath.exists()) {
            basePath.mkdir();
        }
        uploadService.storeFile(file, basePath.getPath());
        return new JsonResult<>("");
    }

}
