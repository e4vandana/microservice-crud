package io.github.vandana.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Class UserResponse.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1001999497980028003L;

	/** The user id. */
	private UserModel userInfo;

	private List<UserModel> allUsersInfo;

	/** The message detail. */
	private MessageDetail message;

	public UserModel getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserModel userInfo) {
		this.userInfo = userInfo;
	}

	public MessageDetail getMessage() {
		return message;
	}

	public void setMessage(MessageDetail message) {
		this.message = message;
	}

	public List<UserModel> getAllUsersInfo() {
		return allUsersInfo;
	}

	public void setAllUsersInfo(List<UserModel> allUsersInfo) {
		this.allUsersInfo = allUsersInfo;
	}

}
