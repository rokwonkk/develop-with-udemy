package com.rokwonkk.springboot.demosecurity.dao;

import com.rokwonkk.springboot.demosecurity.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
}
