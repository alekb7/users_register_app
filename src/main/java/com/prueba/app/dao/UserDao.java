package com.prueba.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.app.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	
	public Optional<User> findByEmail(String email);

}
