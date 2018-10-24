package com.mm.controller;

import com.mm.client.ProductClient;
import com.mm.client.UploadClient;
import com.mm.dao.IndentRepository;
import com.mm.pojo.Indent;
import com.mm.pojo.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping(value = "/indent")
public class IndentController {
    @Autowired
    private IndentRepository indentRepository;
    @Autowired
   private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping(value = "/find/{id}")
    public Product findById(@PathVariable(name = "id") int id) {
        Indent order = indentRepository.findById(id).get();
        Product product = restTemplate.postForObject("http://127.0.0.1:8081/product/find/" + order.getProductId(), null, Product.class);
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

    @Autowired
    private ProductClient productClient;
    /**

     * feign 配置消费者
     */
    @GetMapping(value = "/feign/find/{id}")
    public Product findByIdRibbon(@PathVariable() String id) {
        return  productClient.findById(id);
    }

    @Autowired
    UploadClient uploadClient;
    /**
     * feign 上传文件
     */
    @GetMapping(value = "/upload")
    public void uploadFile(){
        File file = new File("d:/lianpay.txt");
        FileItem fileItem = new DiskFileItemFactory().createItem("file", MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());
        try (InputStream in = new FileInputStream(file)){
            IOUtils.copy(in, fileItem.getOutputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        uploadClient.upload(multipartFile,1);
    }

}
