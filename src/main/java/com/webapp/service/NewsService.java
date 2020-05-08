package com.webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.domain.News;

public interface NewsService {
	public void save(News n) throws Exception;
	public Page<News> findAll(String kw, Pageable pageable);
	public News findById(Integer nid);
	public void delete(News n);
	public void deleteById(Integer nid);
	public void deletes(List<News> newss);
}
