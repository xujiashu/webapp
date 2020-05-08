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
import com.webapp.domain.User;
import com.webapp.service.UserService;

/**
 * Spring MVC Controller
 * 1、查询所有的符合条件的用户信息，并分页传递给界面
 * 2、编辑方法从数据库中查询需要的数据
 * 3、存储方法把新增的用户或编辑后的用户信息存储到数据库
 * 4、删除用户
 * 5、锁定用户、有效状态
 * @author Administrator
 *
 */
@Controller
public class UserController {
	@Autowired
	private UserService userservice;

	/**
	 *  根据条件查询用户信息，接受多种请求方式get、post
	 * 
	 * @param kw       查询关键字即条件
	 * @param model    模型对象，视图（界面）的上下文环境对象
	 * @param pageable 分页信息对象，包含了分页需要的基本信息，如当前页码、每页条数
	 * @return 字符串，代表返回页面
	 *
	 */	
	@RequestMapping("/listusers")
	public String list(String kw, Model model, Pageable pageable) {
		model.addAttribute("kw", kw);
		if(kw!=null) kw = "%"+kw+"%";
		if(kw==null) kw = "%%";
		
		Page<User> ppu = userservice.findAll(kw, pageable);
		model.addAttribute("pages", ppu);
		return "listusers";

	}

	@GetMapping(value={"/editusers","/editusers/{uid}"})
	public String edit(@PathVariable(name="uid",required=false) Integer uid, Model model) {
		User u = new User();
		if(uid!=null && uid>0) {
			u = userservice.findById(uid);
		}
		model.addAttribute("sexes", User.Sex.toList());
		model.addAttribute("user", u);
		
		return "edituser";
	}
	
	
	//Post方法，模板通过表单方式访问
	@PostMapping("/saveuser")
	public String save(@Valid User user,BindingResult result, Model model, RedirectAttributes att) {
		//使用valid检验，检验结果用BindingResult存放
		//valid无法检测数据库错误，使用try catch处理
		try {
			if(result.hasErrors()) {
				return "redirect:/editusers";
			}
			user.setLasttime(LocalDate.now());
			userservice.save(user);
			att.addFlashAttribute("ok", "保存成功");
			return "redirect:/listusers";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:/editusers";
		}


	}
	
	@GetMapping("/deleteuser/{uid}")
	public String delete(@PathVariable(name="uid") Integer uid) {
		userservice.deleteById(uid);
		return "redirect:/listusers";
	}
	
	@PostMapping("/deleteusers")
	public String deletes(String uids) {
		//uids为json格式的字符串
		//@PathVariable默认要求参数不为空
		List<User> users = new ArrayList<>();
		JSONObject json = JSONObject.parseObject(uids);
		JSONArray arr = json.getJSONArray("uids");//前端传递数据时，使用”uids“作为键
		int ilen = arr.size();
		for(int i=0; i<ilen; i++) {
			users.add(userservice.findById(arr.getInteger(i)) );
		}
		userservice.deletes(users);
		return "redirect:/listusers";
	}
	
	
	@GetMapping("/validuser/{uid}")
	public String valid(@PathVariable("uid") Integer uid) {
		User user = userservice.findById(uid);
		user.setValidstate(1-user.getValidstate());
		
		
		return "redirect:/listusers";
	}
	
}
