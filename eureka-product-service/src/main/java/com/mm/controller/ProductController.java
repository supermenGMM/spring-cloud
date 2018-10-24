package com.mm.controller;

import com.mm.dao.ProductRepository;
import com.mm.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping(value = "/findall")
    public List<Product> findAll() {
        return  productRepository.findAll();
    }

    @PostMapping(value = "/find/{id}")
    public Product findById(@PathVariable(name = "id") int id) throws InterruptedException {
        Thread
                .sleep(1000L);

        return productRepository.findById(id).get();
    }


}
