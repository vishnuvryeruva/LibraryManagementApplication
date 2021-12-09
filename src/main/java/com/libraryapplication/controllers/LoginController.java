package com.libraryapplication.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libraryapplication.entities.User;
import com.libraryapplication.services.UserService;
import com.libraryapplication.utils.FineCalculator;

@Controller
public class LoginController {

	@Autowired
	FineCalculator fineCalculator;

	@Autowired
	UserService userService;

	@RequestMapping("/githublogin")
	public String githubLogin(Model model) {
		User currentUser = userService.getCurrentUser();
		if (currentUser != null) {
			if (currentUser.getRole().equals("ROLE_ADMIN")) {
				System.out.println("-------------------------Inside" + currentUser.getUserName());
				System.out.println("-------------------------Inside" + currentUser.getFirstName());
				model.addAttribute("currentUser", currentUser);
				return "admin/admin-home.html";

			} else if (currentUser.getRole().equals("ROLE_EMPLOYEE")) {
				System.out.println("-------------------------Inside" + currentUser.getUserName());
				System.out.println("-------------------------Inside" + currentUser.getFirstName());
				model.addAttribute("currentUser", currentUser);

				return "employee/employee-home.html";

			} else {
				System.out.println("-------------------------Inside" + currentUser.getUserName());
				System.out.println("-------------------------Inside" + currentUser.getFirstName());
				model.addAttribute("currentUser", currentUser);
				model.addAttribute("booksWithFines", fineCalculator.selectBooksWithFines(currentUser.getBooks()));
				return "user/user-home.html";
			}
		}
		else {
			currentUser = userService.getNewCurrentUser();
			model.addAttribute("newAccount", currentUser);
			return "user/user-new.html";
			
		}
	}
	
	@RequestMapping("/")
	public String newUser(Model model) {
		return "/login.html";	
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "/login.html";	
	}
	
	
	
	@RequestMapping("/loginuser")
	public String newLoginUser(User user, Model model) {
		
		User currentUser = userService.authenticateUser(user.getUserName(), user.getPassword());
		if(currentUser==null)
		{
			return "/";
		}
		else {
			
			if (currentUser.getRole().equals("ROLE_ADMIN")) {
				System.out.println("-------------------------Inside" + currentUser.getFirstName());
				model.addAttribute("currentUser", currentUser);
				return "admin/admin-home.html";

			} else if (currentUser.getRole().equals("ROLE_EMPLOYEE")) {
				System.out.println("-------------------------Inside" + currentUser.getFirstName());
				model.addAttribute("currentUser", currentUser);

				return "employee/employee-home.html";

			} else {
				System.out.println("-------------------------Inside" + currentUser.getFirstName());
				model.addAttribute("currentUser", currentUser);
				model.addAttribute("booksWithFines", fineCalculator.selectBooksWithFines(currentUser.getBooks()));
				return "user/user-home.html";
			}
		}
	}
	
	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("newUser", new User());
		return "/register.html";	
	}
	
	@RequestMapping("/registernewuser")
	public String registerNewUser(User newUserAccount) {
		
		userService.save(newUserAccount);
		return "/login";	
	}
	
	
	@RequestMapping("/logout")
	public String logOut(Model model) {
		return "/login.html";	
	}

}

