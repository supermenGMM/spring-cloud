package com.mm.controller;

import com.mm.dao.IndentRepository;
import com.mm.pojo.Indent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class UploadController {
    @Autowired
    IndentRepository repository;
    @PostMapping(value = "/uploadFile/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Indent update(@RequestPart(value = "file") MultipartFile file, @PathVariable int  id) throws IOException {
        long startTime = System.currentTimeMillis();
        String filePath = "d:/upload/"+file.getOriginalFilename();
        file.transferTo(new File(filePath));
        long endTime = System.currentTimeMillis();
        System.out.println("使用时间为:"+(endTime-startTime)+"ms");

        repository.updateByIdAndPicurl(filePath,id);
        return repository.findById(id).get();
    }
}
