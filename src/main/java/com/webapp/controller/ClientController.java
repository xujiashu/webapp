package com.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webapp.domain.Commodity;
import com.webapp.domain.News;
import com.webapp.service.CommodityService;
import com.webapp.service.NewsService;

@Controller
@RequestMapping("/c")
public class ClientController {
	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping("commodities")
	@ResponseBody
	public List<Commodity> list(String kw, Pageable pageable) {
		if(kw!=null) kw = "%"+kw+"%";
		if(kw==null) kw = "%%";
		
		Page page = commodityService.findAll(kw, pageable);
		return page.getContent();
	}
	
	@GetMapping("/newss")
	@ResponseBody
	public List<News> lnews(String kw, Pageable pageable) {

		if(kw!=null) kw = "%"+kw+"%";
		if(kw==null) kw = "%%";
		
		Page page = newsService.findAll(kw, pageable);
		return page.getContent();
	}
	
	@GetMapping("/newss/{nid}")
	public String lonenews(@PathVariable(name="nid",required=false) Integer nid, Model model) {
		if(nid!=null && nid>0) {
			News n = newsService.findById(nid);
			model.addAttribute("paper", n);
			return "onepage";
		}
		return "redirect:/newss";

	}
}
