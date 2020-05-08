package com.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.dao.CommodityRepository;
import com.webapp.domain.Commodity;

@Service
public class CommodityServiceImpl implements CommodityService{

	@Autowired
	private CommodityRepository commodityRepository;
	
	@Override
	public void save(Commodity c) throws Exception {
		try {
			commodityRepository.save(c);			
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public Page<Commodity> findAll(String kw, Pageable pageable) {
		return commodityRepository.findByKeyword(kw, pageable);
	}

	@Override
	public Commodity findById(Integer cid) {
		return commodityRepository.findById(cid).get();
	}

	@Override
	public void delete(Commodity c) {
		commodityRepository.delete(c);
		
	}

	@Override
	public void deleteById(Integer cid) {
		commodityRepository.deleteById(cid);
		
	}

	@Override
	@Transactional
	public void deletes(List<Commodity> commodities) {
		for(Commodity c : commodities) {
			commodityRepository.delete(c);
		}
		
	}

}
