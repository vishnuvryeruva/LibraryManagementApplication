package com.libraryapplication.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.libraryapplication.DAO.UserRepository;
import com.libraryapplication.entities.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	

	public User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return findByUserName(username);
	}
	
	public User getNewCurrentUser() {
		User newUser = new User();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		newUser.setUserName(username);
		newUser.setRole("ROLE_USER");
		save(newUser);
		return newUser;
	}
	
	public User authenticateUser(String userName, String password) {
		
		User user = findByUserName(userName);
		if(user==null)
		{
			return null;
		}
		if(user.getPassword().equals(password))
		{
			return user;
		}else {

			return null;	
		}
		
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public void saveById(Long userId) {
		User user = userRepository.findById(userId).get();
		userRepository.save(user);
	}
	
	public User findById(long id) {
		User user = userRepository.findById(id).get();
		return user;
	}
	
	public User findByUserName(@NotNull String userName) {
		
		return userRepository.findByUserName(userName);
		
	}
	
	public List<User> findAll(){
		return (List<User>) userRepository.findAll();
	}
	
	public List<User> userSearcher(String firstName, String lastName){
		if (firstName != null && lastName != null) return getByFullName(firstName, lastName);
		else if (firstName == null && lastName != null) return getByLastName(lastName);
		else if (firstName != null && lastName == null) return getByFirstName(firstName);
		else return new ArrayList<User>();
	}
	
	public List<User> getByFirstName(String firstName){
		List<User> users = new ArrayList<User>();
		for (User user : userRepository.findAll()) {
			if (user.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
				users.add(user);
			}
		}
		return users;
	}
	
	public List<User> getByLastName(String lastName){
		List<User> users = new ArrayList<User>();
		for (User user : userRepository.findAll()) {
			if(user.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
				users.add(user);
			}
		}
		return users;
	}
	
	public List<User> getByFullName(String firstName, String lastName){
		List<User> users = new ArrayList<User>();
		for (User user : userRepository.findAll()) {
			if (user.getFirstName().toLowerCase().contains(firstName.toLowerCase()) &&
				user.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
				users.add(user);
			}
		}
		return users;
	}
	
}
