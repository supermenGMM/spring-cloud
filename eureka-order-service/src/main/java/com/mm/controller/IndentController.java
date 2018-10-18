package com.mm.controller;

import com.mm.dao.IndentRepository;
import com.mm.pojo.Indent;
import com.mm.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "indent")
public class IndentController {
    @Autowired
    private IndentRepository indentRepository;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping(value = "/find/{id}")
    public Product findById(@PathVariable(name = "id") int id) {
        Indent order = indentRepository.findById(id).get();
        Product product = restTemplate.postForObject("http://127.0.0.1:8080/product/find/" + order.getProductId(), null, Product.class);
        return  product;
    }

}
