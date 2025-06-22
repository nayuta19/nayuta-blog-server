package org.nayutablogserver.controller;

import lombok.RequiredArgsConstructor;
import org.nayutablogserver.pojo.Result;
import org.nayutablogserver.pojo.UploadResult;
import org.nayutablogserver.service.CosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {
    private final CosService cosService;

    @PostMapping("/image")
    public Result uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return   Result.error("请选择要上传的文件");
        }
        // 检查文件类型
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return  Result.error("仅支持上传图片文件");
        }

        try {
            UploadResult uploadResult = new UploadResult();
            uploadResult.url = cosService.uploadImage(file);
            return Result.success(uploadResult);
        } catch (Exception e) {
            return  Result.error("文件上传失败: " + e.getMessage());
        }
    }
}