package com.boubyan.orderportal.OPAuthentication;


import org.springframework.stereotype.Component;
import com.boubyan.orderportal.OPAuthentication.models.User;


@Component
public interface UserDAO  {


	User findUserByEmailAndPassword(String email, String password);
	int registerByEmailAndPassword(String id,String email, String name, String password, String role);
	
//	void updateUser(User user);
//	void deleteUser(User user);
}
