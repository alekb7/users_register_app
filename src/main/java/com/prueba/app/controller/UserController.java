package com.prueba.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.app.entity.ResponseCreateUser;
import com.prueba.app.entity.User;
import com.prueba.app.entity.UserConstants;
import com.prueba.app.service.UserService;
import com.prueba.app.service.UserUtilService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	

	@Autowired
	private UserService userService;
	@Autowired
	private UserUtilService userUtilService;
	
	private Map<String, Object> response = new HashMap<>();
	
	
	// Create a new User
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user){
		Optional<User> oUser = userService.findByEmail(user.getEmail());
		
		if(!userUtilService.emailValidation(user.getEmail())) {
			response.put("error", UserConstants.MSG_ERROR_EMAIL_FORMAT);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		} else if(oUser.isPresent()) {
			response.put("error", UserConstants.MSG_ERROR_EMAIL_CREATED);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		} else if(!userUtilService.passwordValidation(user.getPassword())) {
			response.put("error", UserConstants.MSG_ERROR_PASS_FORMAT);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		String passEncode = UUID.nameUUIDFromBytes(user.getPassword().getBytes()).toString();
		user.setPassword(passEncode);
		user.setEnabled(true);
		userService.save(user);

		ResponseCreateUser userRet = createResponse(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userRet);
	}

	private ResponseCreateUser createResponse(User user) {
		ResponseCreateUser userRet = new ResponseCreateUser();
		userRet.setCreated(user.getCreated());
		userRet.setId(user.getId());
		userRet.setIsactive(user.getEnabled());
		userRet.setLast_login(user.getCreated());
		userRet.setModified(user.getModified());
		return userRet;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<User> oUser = userService.findById(id);
		if(!oUser.isPresent()) {
			response.put("error", UserConstants.MSG_ERROR_USER_UNREGISTRED);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(oUser);	
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> readEmail(@PathVariable String email){
		
		Optional<User> oUser = userService.findByEmail(email);
		if(!oUser.isPresent()) {
			response.put("error", UserConstants.MSG_ERROR_EMAIL_UNREGISTRED);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		Long id = oUser.get().getId();
		System.out.println("UserId:" + id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(id);	
	}
	
	// Update an User
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User userData, @PathVariable Long id){
		Optional<User> oUser = userService.findById(id);
		if(!oUser.isPresent()) {
			response.put("error", UserConstants.MSG_ERROR_USER_UNREGISTRED);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		oUser.get().setName(userData.getName());
		oUser.get().setEmail(userData.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
	}
	
	//Delete an User
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		if(!userService.findById(id).isPresent()) {
			response.put("error", UserConstants.MSG_ERROR_USER_UNREGISTRED);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		userService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
