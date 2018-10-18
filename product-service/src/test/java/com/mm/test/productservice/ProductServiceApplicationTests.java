package com.mm.test.productservice;

import com.mm.dao.ProductRepository;
import com.mm.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void contextLoads() {
		Product product = new Product();
		product.setName("草莓");
		product.setNum(2);
		productRepository.save(product);
		Product product2 = new Product();
		product2.setNum(4);
		product2.setName("芒果");
		productRepository.save(product2);
	}

}
