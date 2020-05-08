package com.webapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webapp.domain.News;

public interface NewsRepository extends JpaRepository <News, Integer>{

	@Query("select n from News n where title like ?1 or content like ?1 or author like ?1")//?1指的是第一个参数
	public Page<News> findByKeyword(String kw,Pageable page);


}
