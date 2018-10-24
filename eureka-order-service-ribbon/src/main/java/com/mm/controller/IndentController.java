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
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;



    /**
     * 使用discoverLoadBalancer
     * @param id
     * @return
     */
    @GetMapping(value = "/loadbalancer/find/{id}")
    public Product findByIdLoadBalancer(@PathVariable() String id) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("product-eureka");
        System.out.println(serviceInstance.getHost()+",,"+serviceInstance.getPort());
        return  restTemplate.postForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/find/1", (Object) null, Product.class);
    }

    /**
     * 使用ribbin
     * 轮询
     */
    @GetMapping(value = "/ribbon/find/{id}")
    public Product findByIdRibbon(@PathVariable() String id) {

        return  restTemplate.postForObject("http://product-eureka/product/find/"+id, (Object) null, Product.class);
    }
}
