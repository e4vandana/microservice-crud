package io.github.vandana.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.vandana.entity.UserAccounts;
import io.github.vandana.entity.Users;
import io.github.vandana.exception.UserNotFoundException;
import io.github.vandana.model.AccountDetails;
import io.github.vandana.model.MessageDetail;
import io.github.vandana.model.UserModel;
import io.github.vandana.model.UserRequest;
import io.github.vandana.model.UserResponse;
import io.github.vandana.repository.UserAccountsRepository;
import io.github.vandana.repository.UsersRepository;
import io.github.vandana.util.BaseConstants;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepository usersRepo;
	
	@Autowired
	UserAccountsRepository userAccountsRepo;

	@Override
	public UserResponse createUser(UserRequest userRequest) {

		UserResponse response = new UserResponse();

		UserModel userReq = userRequest.getUserInfo();

		Users user = new Users();

		user.setFirstName(userReq.getFirstName());
		user.setLastName(userReq.getLastName());
		user.setEmail(userReq.getEmail());
		user.setPhoneNumber(userReq.getPhoneNumber());
		user.setAddressLine1(userReq.getAddressLine1());
		user.setAddressLine2(userReq.getAddressLine2());
		user.setIsActive("Y");
		user.setCreatedBy("VANDANA");
		user.setCreatedOn(new Date());

		Set<UserAccounts> userAccounts = new HashSet<>();

		for (AccountDetails accountDetail : userReq.getAccountDetails()) {

			UserAccounts userAccount = new UserAccounts();
			userAccount.setAccountNumber(accountDetail.getAccountNumber());
			userAccount.setUser(user);
			userAccount.setAccountType(accountDetail.getAccountType());
			userAccount.setBankName(accountDetail.getBankName());
			userAccount.setBranchName(accountDetail.getBranchName());
			userAccount.setBalance(accountDetail.getBalance());
			userAccount.setAccountStatus("A");
			userAccount.setCreatedBy("VANDANA");
			userAccount.setCreatedOn(new Date());
			userAccounts.add(userAccount);
		}

		user.setUserAccounts(userAccounts);
		Users insertedUser = usersRepo.save(user);

		MessageDetail messageDetail = new MessageDetail();

		if (insertedUser != null) {

			UserModel userModel = new UserModel();
			userModel.setUserId(insertedUser.getUserId());
			response.setUserInfo(userModel);
			messageDetail.setStatusCode(BaseConstants.SUCCESS_CODE);
			messageDetail.setStatus(BaseConstants.SUCCESS);
			messageDetail.setStatusMessage("User Created Successfully");

		} else {
			messageDetail.setStatusCode(BaseConstants.FAILURE_CODE);
			messageDetail.setStatus(BaseConstants.FAILURE);
			messageDetail.setStatusMessage("Internal Error !! Please try again afetr some time.");
		}

		response.setMessage(messageDetail);
		return response;
	}

	@Override
	public UserResponse deleteUser(Long userId) throws UserNotFoundException {
		UserResponse response = new UserResponse();
		Users user = usersRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("Employee not found for this id :: " + userId));

		usersRepo.delete(user);
		MessageDetail messageDetail = new MessageDetail();

		messageDetail.setStatusCode(BaseConstants.SUCCESS_CODE);
		messageDetail.setStatus(BaseConstants.SUCCESS);
		messageDetail.setStatusMessage("User Deleted Successfully");
		response.setMessage(messageDetail);
		return response;
	}

	@Override
	public UserResponse getUserById(Long userId) throws UserNotFoundException {
		Users user = usersRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found for the id :: " + userId));

		UserResponse response = new UserResponse();

		UserModel userResp = new UserModel();
		userResp.setUserId(user.getUserId());
		userResp.setFirstName(user.getFirstName());
		userResp.setLastName(user.getLastName());
		userResp.setEmail(user.getEmail());
		userResp.setPhoneNumber(user.getPhoneNumber());
		userResp.setAddressLine1(user.getAddressLine1());
		userResp.setAddressLine2(user.getAddressLine2());
		userResp.setIsActive(user.getIsActive());
		userResp.setCreatedBy(user.getCreatedBy());
		userResp.setCreatedOn(user.getCreatedOn());

		Set<AccountDetails> userAccounts = new HashSet<>();

		for (UserAccounts accountDetail : user.getUserAccounts()) {

			AccountDetails userAccount = new AccountDetails();
			userAccount.setAccountNumber(accountDetail.getAccountNumber());
			userAccount.setAccountType(accountDetail.getAccountType());
			userAccount.setBankName(accountDetail.getBankName());
			userAccount.setBranchName(accountDetail.getBranchName());
			userAccount.setBalance(accountDetail.getBalance());
			userAccount.setAccountStatus("A");
			userAccounts.add(userAccount);
		}

		userResp.setAccountDetails(userAccounts);

		MessageDetail messageDetail = new MessageDetail();
		messageDetail.setStatusCode(BaseConstants.SUCCESS_CODE);
		messageDetail.setStatus(BaseConstants.SUCCESS);
		messageDetail.setStatusMessage("User details returned Successfully");

		response.setUserInfo(userResp);
		response.setMessage(messageDetail);

		return response;
	}

	@Override
	public UserResponse getAllUsers() {

		UserResponse response = new UserResponse();

		List<Users> users = usersRepo.findAll();

		List<UserModel> allUsers = new ArrayList<>();

		for (Users user : users) {

			UserModel userResp = new UserModel();
			userResp.setUserId(user.getUserId());
			userResp.setFirstName(user.getFirstName());
			userResp.setLastName(user.getLastName());
			userResp.setEmail(user.getEmail());
			userResp.setPhoneNumber(user.getPhoneNumber());
			userResp.setAddressLine1(user.getAddressLine1());
			userResp.setAddressLine2(user.getAddressLine2());
			userResp.setIsActive(user.getIsActive());
			userResp.setCreatedBy(user.getCreatedBy());
			userResp.setCreatedOn(user.getCreatedOn());

			Set<AccountDetails> userAccounts = new HashSet<>();

			for (UserAccounts accountDetail : user.getUserAccounts()) {

				AccountDetails userAccount = new AccountDetails();
				userAccount.setAccountNumber(accountDetail.getAccountNumber());
				userAccount.setAccountType(accountDetail.getAccountType());
				userAccount.setBankName(accountDetail.getBankName());
				userAccount.setBranchName(accountDetail.getBranchName());
				userAccount.setBalance(accountDetail.getBalance());
				userAccount.setAccountStatus("A");
				userAccounts.add(userAccount);
			}

			userResp.setAccountDetails(userAccounts);

			allUsers.add(userResp);

		}

		MessageDetail messageDetail = new MessageDetail();
		messageDetail.setStatusCode(BaseConstants.SUCCESS_CODE);
		messageDetail.setStatus(BaseConstants.SUCCESS);
		messageDetail.setStatusMessage("All Users details returned Successfully");

		response.setAllUsersInfo(allUsers);
		response.setMessage(messageDetail);

		return response;
	}

	@Override
	public UserResponse updateUser(Long userId, UserRequest userRequest) throws UserNotFoundException {
		Users editUser = usersRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found for the id :: " + userId));

		UserModel userReq = userRequest.getUserInfo();
		UserResponse response = new UserResponse();

		editUser.setFirstName(userReq.getFirstName());
		editUser.setLastName(userReq.getLastName());
		editUser.setEmail(userReq.getEmail());
		editUser.setPhoneNumber(userReq.getPhoneNumber());
		editUser.setAddressLine1(userReq.getAddressLine1());
		editUser.setAddressLine2(userReq.getAddressLine2());
		editUser.setIsActive(userReq.getIsActive());
		editUser.setModifiedBy("VANDANA");
		editUser.setModifiedOn(new Date());
		
		Set<UserAccounts> userAccounts = new HashSet<>();

		for (AccountDetails accountDetail : userReq.getAccountDetails()) {
			
			UserAccounts userAccount = new UserAccounts();
			
			if(accountDetail.getAccountNumber() != null) {
				 userAccount = userAccountsRepo.findByAccountNumber(accountDetail.getAccountNumber()).orElse(new UserAccounts());
			}
			
			userAccount.setAccountNumber(accountDetail.getAccountNumber());
			userAccount.setUser(editUser);
			userAccount.setAccountType(accountDetail.getAccountType());
			userAccount.setBankName(accountDetail.getBankName());
			userAccount.setBranchName(accountDetail.getBranchName());
			userAccount.setBalance(accountDetail.getBalance());
			userAccount.setAccountStatus("A");
			userAccount.setCreatedBy("VANDANA");
			userAccount.setCreatedOn(new Date());
			userAccounts.add(userAccount);
		}

		editUser.setUserAccounts(userAccounts);

		final Users updatedUser = usersRepo.save(editUser);

		MessageDetail messageDetail = new MessageDetail();

		if (updatedUser != null) {
			UserModel userModel = new UserModel();
			userModel.setUserId(updatedUser.getUserId());
			response.setUserInfo(userModel);
			messageDetail.setStatusCode(BaseConstants.SUCCESS_CODE);
			messageDetail.setStatus(BaseConstants.SUCCESS);
			messageDetail.setStatusMessage("User Updated Successfully");

		} else {
			messageDetail.setStatusCode(BaseConstants.FAILURE_CODE);
			messageDetail.setStatus(BaseConstants.FAILURE);
			messageDetail.setStatusMessage("Internal Error !! Please try again afetr some time.");
		}

		response.setMessage(messageDetail);

		return response;

	}

}
