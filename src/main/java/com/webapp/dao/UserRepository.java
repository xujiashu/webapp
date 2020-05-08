package com.webapp.dao;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webapp.domain.User;

public interface UserRepository extends JpaRepository <User, Integer>{

	@Query("select u from User u where account like ?1 or mobile like ?1 or email like ?1")//?1指的是第一个参数
	public Page<User> findByKeyword(String kw,Pageable page);
	
	@Query("update User u set u.password=?1 where u.uid=?2")//?1指的是第一个参数
	public void modifypassword(String pwd, Integer uid);
	
	public Optional<User> findByAccount(String account);

	
}
