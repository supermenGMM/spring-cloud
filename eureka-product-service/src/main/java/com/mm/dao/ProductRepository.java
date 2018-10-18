package com.mm.dao;

import com.mm.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface ProductRepository extends JpaRepository<Product, Integer> {

}
