package com.mapper;

import com.po.User;

public interface UserMapper {
	public User selectUserByUsername(String username);
	
	public void insertUsetByUsername(User user) throws Exception;
	
	
}
