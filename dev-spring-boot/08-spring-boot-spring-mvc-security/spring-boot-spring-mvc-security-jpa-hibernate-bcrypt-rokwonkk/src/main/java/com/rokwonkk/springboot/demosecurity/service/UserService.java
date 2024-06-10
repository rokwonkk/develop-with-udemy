package com.rokwonkk.springboot.demosecurity.service;

import com.rokwonkk.springboot.demosecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

}
