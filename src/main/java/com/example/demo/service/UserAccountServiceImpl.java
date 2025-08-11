package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepo;

@Service
public class UserAccountServiceImpl implements UserAccountService{
	
	@Autowired
	private UserAccountRepo accountRepo;

	@Override
	public String saveOrUpdateUserAcc(UserAccount userAccount) {
		// TODO Auto-generated method stub
		
//		UpperSet Intert Or Update
		
		Integer userId=userAccount.getUserId();
		
		if(userId==null) {
			userAccount.setActiveSw("Y");
		}
		
		accountRepo.save(userAccount);
		
		if(userId==null) {
			
			return "user record saved :";
		}
		else {
			return "user record not saved";
		}
	}

	@Override
	public List<UserAccount> getAllusers() {
		// TODO Auto-generated method stub
		
		List<UserAccount> account=accountRepo.findAll();
		return account;
	}

	@Override
	public UserAccount getUserAccount(Integer userId) {
		// TODO Auto-generated method stub
		Optional<UserAccount> findById=accountRepo.findById(userId);
		return findById.get();
	}

	@Override
	public boolean deleteUserAcc(Integer userId) {
		// TODO Auto-generated method stub
		try {
			accountRepo.deleteById(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//	@Override
//	public boolean updateUserAccStatus(Integer userId, String status) {
//		// TODO Auto-generated method stub
//		boolean exitById=accountRepo.existsById(userId);
//		try {
//			
//			if(exitById) {
//				accountRepo.deleteById(userId);
//				return true;
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	@Override
	public boolean updateUserAccStatus(Integer userId, String status) {
	    boolean existsById = accountRepo.existsById(userId);

	    if (existsById) {
	        try {
	            // Fetch the user entity by ID
	            UserAccount userAccount = accountRepo.findById(userId).orElse(null);
	            
	            if (userAccount != null) {
	                // Update the status field (Y for active, N for inactive)
	                userAccount.setStatus(status);
	                
	                // Save the updated user entity
	                accountRepo.save(userAccount);
	                return true;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return false;
	}


}
