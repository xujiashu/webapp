package com.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webapp.dao.NewsRepository;
import com.webapp.domain.News;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	private NewsRepository newsRepository;

	@Override
	public void save(News n) throws Exception {
		try {
			newsRepository.save(n);			
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public Page<News> findAll(String kw, Pageable pageable) {
		return newsRepository.findByKeyword(kw, pageable);
	}

	@Override
	public News findById(Integer nid) {
		return newsRepository.findById(nid).get();
	}

	@Override
	public void delete(News n) {
		newsRepository.delete(n);
		
	}

	@Override
	public void deleteById(Integer nid) {
		newsRepository.deleteById(nid);
		
	}

	@Override
	public void deletes(List<News> newss) {
		for(News n : newss) {
			newsRepository.delete(n);
		}
		
	}
	
	
	
}
