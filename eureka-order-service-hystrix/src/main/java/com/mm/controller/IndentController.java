package com.mm.controller;

import com.mm.pojo.Product;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 如何controller ，service都配置了服务降级。会使用controller的服务降级返回
 */
@DefaultProperties(defaultFallback = "defaultFallback")//默认的降级方法
@RestController
@RequestMapping(value = "/indent")
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
        return  restTemplate.postForObject("http://" + serviceInstance.getServiceId()+"/product/find/1", (Object) null, Product.class);
    }

    /**
     * 使用ribbin
     * 轮询
     */
    @GetMapping(value = "/ribbon/find/{id}")
    public Product findByIdRibbon(@PathVariable() String id) {

        return  restTemplate.postForObject("http://product-eureka/product/find/"+id, (Object) null, Product.class);
    }

    @Autowired
    ProductService productService;

    @HystrixCommand
    @GetMapping(value = "/product/{id}")
    public String product(@PathVariable String id)  {
        return productService.find(id);
    }

    public String defaultFallback() {
        return "对不起，网络拥挤，请稍后重试";
    }

    @Service
    class ProductService {
        @Autowired
        private RestTemplate restTemplate;

         @HystrixCommand(fallbackMethod = "fallback")
        public String find(String id) {
            return restTemplate.postForObject("http://PRODUCT-EUREKA/product/find/"+id, null, Product.class).toString();
        }

        /**
         * 注/参数必须和方法参数一致
         * @param id
         * @return
         */
        public String fallback(String id) {
            return "fallback"+id;
        }

    }


}
