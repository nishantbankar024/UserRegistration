package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountServiceImpl;


@Controller
public class UserAccountController {
	
	@Autowired
	UserAccountServiceImpl service;

	@GetMapping("/") 
	public String index(Model model) {
		
		model.addAttribute("user", new UserAccount());
		return "index";
		
	}
	
	@PostMapping("/save-user")
	public String handleSubmitButton(@ModelAttribute("user") UserAccount user,Model model) {
		
		System.out.println(user);
		
		String msg=this.service.saveOrUpdateUserAcc(user);
		model.addAttribute("msg", msg);
		
		return "redirect:/users";
	}
	
	@GetMapping("/users")
	public String getUser(Model model) {
		List<UserAccount> userList=service.getAllusers();
		model.addAttribute("users", userList);
		return "view-user";
		
	}
	
	@GetMapping("/edit")
	public String editUser(@RequestParam("id") Integer id, Model model) {
		UserAccount userAcc=service.getUserAccount(id);
		model.addAttribute("user", userAcc);
		return "index";
		
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") Integer id, Model model) {
		
		boolean accDelete=service.deleteUserAcc(id);
		model.addAttribute("msg","record is deleted sucessfully :");
		return "forward:/users";
		
	}
	
	@GetMapping("/update")
	public String updateStatus(@RequestParam("id") Integer uid, @RequestParam("status") String status,Model model) {
	    
		service.updateUserAccStatus(uid, status);
	    
		if("Y".equals(status)) {
			model.addAttribute("msg","User Account is De-Activated :");
		}else {
		   model.addAttribute("msg","User Account is Activated :");
		}
		
	    return "forward:/users";  // Redirects to the list of users after status update
	}

}
