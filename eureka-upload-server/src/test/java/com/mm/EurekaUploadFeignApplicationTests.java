package com.mm;

import com.mm.dao.IndentRepository;
import com.mm.pojo.Indent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Id;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaUploadFeignApplicationTests {

	@Autowired
	private IndentRepository indentRepository;

	@Test
	public void contextLoads() {
//		indentRepository.updateByIdAndPicurl("c:/111.jpg",1);
        Indent indent = indentRepository.findById(1).get();
        System.out.println(indent);
    }

}
