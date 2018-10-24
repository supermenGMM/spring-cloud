package com.mm.controller;

import com.mm.dao.IndentRepository;
import com.mm.pojo.Indent;
import com.mm.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "indent")
public class IndentController {
    @Autowired
    private IndentRepository indentRepository;
    @Autowired
   private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping(value = "/find/{id}")
    public Product findById(@PathVariable(name = "id") int id) {
        Indent order = indentRepository.findById(id).get();
        Product product = restTemplate.postForObject("http://localhost:8083/product/find/" + order.getProductId(), null, Product.class);
        return  product;
    }

    @GetMapping(value = "/service-instance/{applicationName}")
    public List<ServiceInstance> serviceInstanceByApplication(@PathVariable() String applicationName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(applicationName);
        ServiceInstance serviceInstance = instances.get(0);
        return instances;
    }

    @GetMapping(value = "/eureka/find/{id}")
    public Product findByIdEureka(@PathVariable() String id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("PRODUCT-EUREKA");
        ServiceInstance serviceInstance = instances.get(0);
        return  restTemplate.postForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/find/1", (Object) null, Product.class);
    }



}
