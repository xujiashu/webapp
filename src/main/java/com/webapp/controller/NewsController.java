package com.webapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webapp.domain.News;
import com.webapp.service.NewsService;

@Controller
public class NewsController {
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/listnewss")
	public String list(String kw, Model model, Pageable pageable) {
		model.addAttribute("kw", kw);
		if(kw!=null) kw = "%"+kw+"%";
		if(kw==null) kw = "%%";
		
		Page<News> ppn = newsService.findAll(kw, pageable);
		model.addAttribute("pages", ppn);
		return "listnewss";

	}
	
	@GetMapping(value={"/editnewss","/editnewss/{nid}"})
	public String edit(@PathVariable(name="nid",required=false) Integer nid, Model model) {
		News n = new News();
		if(nid!=null && nid>0) {
			n = newsService.findById(nid);
		}
		model.addAttribute("ntypes", News.Types.toList());
		model.addAttribute("news", n);
		
		return "editnews";
	}
	
	@PostMapping("/savenews")
	public String save(@Valid News news,BindingResult result, Model model, RedirectAttributes att) {
		//使用valid检验，检验结果用BindingResult存放
		//valid无法检测数据库错误，使用try catch处理
		try {
			if(result.hasErrors()) {
				return "redirect:/editnewss";
			}

			news.setLastupdate(LocalDate.now());
			newsService.save(news);
			att.addFlashAttribute("ok", "保存成功");
			return "redirect:/listnewss";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:/editnewss";
		}
		
	}
	
	
	@GetMapping("/deletenews/{nid}")
	public String delete(@PathVariable(name="nid") Integer nid) {
		newsService.deleteById(nid);
		return "redirect:/listnewss";
	}
	
	@PostMapping("/deletenewss")
	public String deletes(String nuids) {
		//cids为json格式的字符串
		//@PathVariable默认要求参数不为空
		List<News> newss = new ArrayList<>();
		JSONObject json = JSONObject.parseObject(nuids);
		JSONArray arr = json.getJSONArray("uids");//前端传递数据时，使用”uids“作为键
		int ilen = arr.size();
		for(int i=0; i<ilen; i++) {
			newss.add(newsService.findById(arr.getInteger(i)) );
		}
		newsService.deletes(newss);
		return "redirect:/listnewss";
	}
	
	
}
