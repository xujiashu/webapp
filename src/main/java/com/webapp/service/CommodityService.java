package com.webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.domain.Commodity;


public interface CommodityService {

	public void save(Commodity c) throws Exception;
	public Page<Commodity> findAll(String kw, Pageable pageable);
	public Commodity findById(Integer cid);
	public void delete(Commodity c);
	public void deleteById(Integer cid);
	public void deletes(List<Commodity> commodities);

}
