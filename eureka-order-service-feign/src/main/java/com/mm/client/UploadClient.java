package com.mm.client;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import feign.codec.Encoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "eureka-upload",configuration = UploadClient.MultipartSupportConfig.class)
public interface UploadClient {
    @PostMapping(value = "uploadFile/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestPart(value = "file")MultipartFile file, @PathVariable(value = "id") int id);

    @Configuration
    class  MultipartSupportConfig{
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}
