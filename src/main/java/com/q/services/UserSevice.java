package com.q.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.q.beans.JobBean;
import com.q.beans.UserBean;
import com.q.constants.Messages;
import com.q.entities.User;
import com.q.repositories.UserRepository;

@Service("userService")
public class UserSevice extends BaseService<UserBean> {

	@Autowired
	UserRepository userRepository;
	
	public List<UserBean> list(){
		List<UserBean> userList = new ArrayList<UserBean>();
		 
		Iterable<User> lst = userRepository.findAll();
		UserBean bean = null;
		for(User user:lst){
			bean = new UserBean();
			bean.setUserId(user.getUserId());
			bean.setEmail(user.getEmail());
			bean.setLastName(user.getLastName());
			bean.setFirstName(user.getFirstName());
			userList.add(bean);
		}
		return userList;
	}
	
	public UserBean saveOrUpdate(UserBean bean){
		try{
			User user = new User();
			user.setEmail(bean.getEmail());
			user.setFirstName(bean.getFirstName());
			user.setLastName(bean.getLastName());
			if(bean.getUserId()!=null && bean.getUserId()>0){
				user.setUserId(bean.getUserId());
			} 
			
			user = userRepository.save(user);
			bean.setUserId(user.getUserId());
			
		}catch(Exception e){
			bean.setOk(false);
		}
		return bean;
		
		
	}
	
	public UserBean get(UserBean bean){
		User user =  userRepository.findOne(bean.getUserId());
		bean.setEmail(user.getEmail());
		bean.setFirstName(user.getFirstName());
		bean.setLastName(user.getLastName());
		
		return bean;
	}
	
	public UserBean remove( UserBean bean){
		try{
			userRepository.delete(bean.getUserId());
			bean.setResponse(Messages.SUCCESS_remove);
		}catch(Exception e){
			bean.setOk(false);
			bean.setResponse(Messages.ERROR);
		}
		return bean;
	}
	
	
}
