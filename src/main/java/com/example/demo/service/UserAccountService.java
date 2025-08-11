package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {
	
	public String saveOrUpdateUserAcc(UserAccount userAccount);
	
	public List<UserAccount> getAllusers();
	
	public UserAccount getUserAccount(Integer userId);
	
	public boolean deleteUserAcc(Integer userId);
	
	public boolean updateUserAccStatus(Integer userId,String status);

}
