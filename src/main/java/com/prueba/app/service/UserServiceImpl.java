package com.prueba.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.app.dao.UserDao;
import com.prueba.app.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<User> findAll() {
		
		return userDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<User> findAll(Pageable pegeable) {
		return userDao.findAll(pegeable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<User> findById(Long id) {
		return userDao.findById(id);
	}

	@Transactional
	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<User> findByEmail(String email){
		return userDao.findByEmail(email);
	}

}
