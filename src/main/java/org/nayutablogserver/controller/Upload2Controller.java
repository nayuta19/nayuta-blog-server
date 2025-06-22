package org.nayutablogserver.controller;
import lombok.RequiredArgsConstructor;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.nayutablogserver.pojo.Result;
import org.nayutablogserver.pojo.UploadResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload2")
@RequiredArgsConstructor
public class Upload2Controller {

    @PostMapping("/image")
    public Result uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件为空！");
        }

        String originalFileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));

        // FTP配置（建议放在配置文件中）
        String ftpHost = "42.194.130.37";
        int ftpPort = 21;
        String ftpUsername = "images";
        String ftpPassword = "5GxeSKfdd6n8JTfw";
        String remoteDir = "/www/wwwroot/images/";

        try {
            // 1. 上传到FTP服务器
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUsername, ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // 2. 上传文件
            try (InputStream inputStream = file.getInputStream()) {
                boolean success = ftpClient.storeFile(remoteDir + newFileName, inputStream);
                if (!success) {
                    return Result.error("FTP上传失败！");
                }
            }catch (IOException e) {
                System.out.println(e+"失败信息");
                e.printStackTrace();
            }

            // 3. 返回访问URL
            UploadResult uploadResult = new UploadResult();
            uploadResult.url = "http://42.194.130.37:66/" + newFileName;
            return Result.success(uploadResult);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败：" + e.getMessage());
        }
    }
}
