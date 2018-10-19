package com.mm.dao;

import com.mm.pojo.Indent;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IndentRepository extends JpaRepository<Indent, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update Indent set picurl = ?1 where id = ?2")
    void updateByIdAndPicurl(String picurl,int id);

}
