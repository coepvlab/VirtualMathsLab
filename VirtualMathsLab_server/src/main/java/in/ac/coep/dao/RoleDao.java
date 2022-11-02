package in.ac.coep.dao;

import in.ac.coep.entity.Roles;

public interface RoleDao {
	
	
	public Roles getRoleByRoleName(String roleName)throws Exception;
	
	

}
