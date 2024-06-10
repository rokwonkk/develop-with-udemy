package com.rokwonkk.springboot.demosecurity.dao;

import com.rokwonkk.springboot.demosecurity.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
