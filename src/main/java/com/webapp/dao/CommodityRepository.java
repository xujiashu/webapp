package com.webapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webapp.domain.Commodity;




public interface CommodityRepository  extends JpaRepository <Commodity, Integer>{

	
	@Query("select c from Commodity c where name like ?1")//?1指的是第一个参数
	public Page<Commodity> findByKeyword(String kw,Pageable page);

}
