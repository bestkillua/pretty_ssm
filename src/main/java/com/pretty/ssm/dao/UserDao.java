package com.pretty.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pretty.ssm.entity.User;

public interface UserDao {

	/**
     * 根据手机号查询用户对象
     */
    User queryByPhone(long userPhone);
    
    
    /**
     * 根据偏移量查询用户列表
     */
    List<User> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
}
