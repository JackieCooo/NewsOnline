package com.newsonline.back_end.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    void storeFile(MultipartFile file, String path);
}
