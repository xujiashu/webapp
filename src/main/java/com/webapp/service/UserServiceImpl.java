package com.webapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.dao.UserRepository;
import com.webapp.domain.User;
import com.webapp.domain.UserLogin;

@Service
public class UserServiceImpl implements  UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User u) throws Exception{
		try {
			userRepository.save(u);			
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public Page<User> findAll(String kw, Pageable pageable) {
		return userRepository.findByKeyword(kw, pageable);
		
	}

	@Override
	public User findById(Integer uid) {
		return userRepository.findById(uid).get();
		
	}

	@Override
	public void delete(User u) {
		userRepository.delete(u);
		
	}

	@Override
	public void deleteById(Integer uid) {
		userRepository.deleteById(uid);
		
	}

	@Override
	@Transactional
	public void deletes(List<User> users) {
		for(User u : users) {
			delete(u);
		}
		
	}

	@Override
	public User checkUser(UserLogin user) {
		User u = null;
		Optional<User> ou = userRepository.findByAccount(user.getAccount());
		if(ou.isPresent()) {
			u=ou.get();
			if(u.getPassword().equals(user.getPassword())) {
				return u;
			}
		}
		
		return null;
	}


}
