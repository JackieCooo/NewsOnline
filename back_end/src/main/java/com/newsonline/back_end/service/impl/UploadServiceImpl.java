package com.newsonline.back_end.service.impl;

import com.newsonline.back_end.service.IUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service("UploadService")
public class UploadServiceImpl implements IUploadService {

    @Override
    public void storeFile(MultipartFile file, String path) {
//        System.out.println(file.getSize() + "Bytes");
        File filepath = new File(path + File.separator + file.getOriginalFilename());
//        System.out.println(filepath.getPath());
        try {
            file.transferTo(filepath);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
