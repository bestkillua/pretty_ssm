package com.pretty.ssm.usertest;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pretty.ssm.entity.User;
import com.pretty.ssm.service.UserService;
import com.pretty.ssm.web.UserController;

public class UserTest {

	@Autowired
	public UserService user;
	
	@Test
	public void test(){
		List<User> userList = user.getUserList(0,10);
		System.out.println(userList.size());
	}
}
