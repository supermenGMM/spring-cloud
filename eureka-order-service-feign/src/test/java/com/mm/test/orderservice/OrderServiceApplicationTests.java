package com.mm.test.orderservice;

import com.mm.client.UploadClient;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.ws.rs.core.MediaType;
import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceApplicationTests {

	@Autowired
	UploadClient uploadClient;
	@Test
	public void contextLoads() throws IOException {

		File file = new File("d:/lianpay.txt");
		FileItem fileItem = new DiskFileItemFactory().createItem("file", MediaType.MULTIPART_FORM_DATA, true, file.getName());
		try (InputStream in = new FileInputStream(file)){
			IOUtils.copy(in, fileItem.getOutputStream());
		}

		MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		uploadClient.upload(multipartFile,1);

	}

}
