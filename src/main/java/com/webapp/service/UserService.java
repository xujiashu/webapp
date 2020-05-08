package com.webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.domain.User;
import com.webapp.domain.UserLogin;

public interface UserService {
	public void save(User u) throws Exception;
	public Page<User> findAll(String kw, Pageable pageable);
	public User findById(Integer uid);
	public void delete(User u);
	public void deleteById(Integer uid);
	public void deletes(List<User> users);
	public User checkUser(UserLogin user);

}
