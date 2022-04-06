package com.rakuten;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // This annotation makes a class as a rest controller or a spring controller.
@RequestMapping("/user")
public class UserController {
	
	List<User> users = new ArrayList<>();
	
	@PostMapping
	void saveUser(@RequestBody User user) {
		System.out.println(user.getName());
		System.out.println(user.getAge());
		users.add(user);
	}
	
	@GetMapping
	List<User> getUsers() {
		return users;
	}
	
	@GetMapping("/name/{name}")
	User getUserFromName(@PathVariable String name) {
		
		if(name.isBlank() || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		
//		users.forEach((User single_user) -> {
//			if(single_user.getName().equals(name)) {
//				return single_user;
//			}
//		});
		
		/*
		 * USING STREAMS!!
		 * 
		 * List<User> filteredUsers = users.stream().filter((user)->user.getName().equals(name)).collect(Collectors.toList());
			return filteredUsers;
		 */
		
		
		for(int i=0; i<users.size(); i++) {
			if(users.get(i).getName().equals(name)) {
				return users.get(i);
			}
		}
		
		return null;
	}
	
	@GetMapping("age/{age}")
	List<User> getUserFromAge(@PathVariable int age) {
		if(age<0) {
			throw new IllegalArgumentException("Age cannot be negative " + age);
		}
		 List<User> filteredUsers = users.stream().filter((user)->user.getAge() == age).collect(Collectors.toList());
		 return filteredUsers;
		
	}
}
