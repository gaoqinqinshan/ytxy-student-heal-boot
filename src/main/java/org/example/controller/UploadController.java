package org.example.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.example.advice.ExceptionEnums;
import org.example.advice.MyException;
import org.example.untils.TenxunUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(tags = "文件上传接口")
@RestController
@RequestMapping(value = "api/uploadFile")


public class UploadController {
    @Autowired
    private TenxunUtils tenxunUtils;

    @PostMapping("upload")
    public ResponseEntity<String> upload(MultipartFile file, HttpServletRequest request){
        String fileName = tenxunUtils.ContentCOS(file, request);
        if (!"".equals(fileName)){
            return ResponseEntity.ok(fileName);
        }
        throw new MyException(ExceptionEnums.CHOOSE_FILE);
    }
}
