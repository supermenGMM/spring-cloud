package com.mm.dao;

import com.mm.pojo.Indent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface IndentRepository extends JpaRepository<Indent, Integer> {

}
