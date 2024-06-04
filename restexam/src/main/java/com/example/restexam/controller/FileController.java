package com.example.restexam.controller;

import com.example.restexam.domain.UploadInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {
    // 파일 업로드
    @PostMapping("/upload")
    public ResponseEntity<String> fileUploadHandler(@RequestParam("file")MultipartFile file,
            @RequestPart("info")UploadInfo uploadInfo) {
        //
        System.out.println(file.getOriginalFilename()+"================");
        System.out.println(uploadInfo.getDescription()+"==========");
        System.out.println(uploadInfo.getTag()+"===============");

        String message = "";
        try {
            InputStream inputStream = file.getInputStream();
            StreamUtils.copy(inputStream, new FileOutputStream("C:/Techit/tmp/file/" + file.getOriginalFilename()));    // 서버의 저장 위치
            return ResponseEntity.ok().body(message);
        }
        catch (IOException e) {
            message = "FAIL UPLOAD!!: " + file.getOriginalFilename();
            return ResponseEntity.badRequest().body(message);
        }
    }

    // 파일 다운로드
    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response) {
        Path filePath = Paths.get("c:/Techit/tmp/file/cat.jpg");
        response.setContentType("image/jpeg");

        try {
            InputStream inputStream = Files.newInputStream(filePath);
            StreamUtils.copy(inputStream, response.getOutputStream());  // 입력 스트림에서 출력 스트림으로 데이터 복사
            response.flushBuffer();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
