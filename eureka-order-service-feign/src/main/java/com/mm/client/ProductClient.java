package com.mm.client;

import com.mm.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("product-eureka")
public interface ProductClient {
    @PostMapping("/product/find/{id}")
    public Product findById(@PathVariable( name = "id") String id);
}
