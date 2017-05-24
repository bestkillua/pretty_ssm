package com.pretty.ssm.service;

import java.util.List;

import com.pretty.ssm.entity.User;

public interface UserService {
	
	List<User> getUserList(int offset, int limit);
}
