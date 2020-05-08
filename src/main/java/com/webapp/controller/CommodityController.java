package com.webapp.controller;

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
import com.webapp.domain.Commodity;
import com.webapp.service.CommodityService;

@Controller
public class CommodityController {
	@Autowired
	private CommodityService commodityService;
	
	@RequestMapping("/listcommodities")
	public String list(String kw, Model model, Pageable pageable) {
		model.addAttribute("kw", kw);
		if(kw!=null) kw = "%"+kw+"%";
		if(kw==null) kw = "%%";
		
		Page<Commodity> ppc = commodityService.findAll(kw, pageable);
		model.addAttribute("pages", ppc);
		return "listcommodities";

	}
	
	@GetMapping(value={"/editcommodities","/editcommodities/{cid}"})
	public String edit(@PathVariable(name="cid",required=false) Integer cid, Model model) {
		Commodity c = new Commodity();
		if(cid!=null && cid>0) {
			c = commodityService.findById(cid);
		}
		model.addAttribute("ctypes", Commodity.Types.toList());
		model.addAttribute("commodity", c);
		
		return "editcommodity";
	}
	
	@PostMapping("/savecommodity")
	public String save(@Valid Commodity commodity,BindingResult result, Model model, RedirectAttributes att) {
		//使用valid检验，检验结果用BindingResult存放
		//valid无法检测数据库错误，使用try catch处理
		try {
			if(result.hasErrors()) {
				return "redirect:/editcommodities";
			}

			commodityService.save(commodity);
			att.addFlashAttribute("ok", "保存成功");
			return "redirect:/listcommodities";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:/editcommodities";
		}
		
	}
	
	
	@GetMapping("/deletecommodity/{cid}")
	public String delete(@PathVariable(name="cid") Integer cid) {
		commodityService.deleteById(cid);
		return "redirect:/listcommodities";
	}
	
	@PostMapping("/deletecommodities")
	public String deletes(String cuids) {
		//cids为json格式的字符串
		//@PathVariable默认要求参数不为空
		List<Commodity> commodities = new ArrayList<>();
		JSONObject json = JSONObject.parseObject(cuids);
		JSONArray arr = json.getJSONArray("uids");//前端传递数据时，使用”uids“作为键
		int ilen = arr.size();
		for(int i=0; i<ilen; i++) {
			commodities.add(commodityService.findById(arr.getInteger(i)) );
		}
		commodityService.deletes(commodities);
		return "redirect:/listcommodities";
	}
	
	
	
}
