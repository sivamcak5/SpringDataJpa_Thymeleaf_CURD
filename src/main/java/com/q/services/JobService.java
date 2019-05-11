package com.q.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.q.beans.JobBean;
import com.q.constants.Messages;
import com.q.entities.Job;
import com.q.entities.User;
import com.q.repositories.JobRespository;
import com.q.repositories.UserRepository;

@Service("jobService")
public class JobService extends BaseService<JobBean> {

	@Autowired
	JobRespository jobRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	
	public List<JobBean> list(){
		List<JobBean> jobList = new ArrayList<JobBean>();
		 
		Iterable<Job> lst = jobRepository.findAll();
		JobBean bean = null;
		for(Job job:lst){
			bean = new JobBean();
			bean.setJobId(job.getJobId());
			bean.setJobName(job.getJobName());
			bean.setLocation(job.getLocation());
			User user = job.getUser();
			bean.setUser(user!=null?((user.getFirstName()!=null?user.getFirstName():"")+" "+(user.getLastName()!=null?user.getLastName():"")):"");
			jobList.add(bean);
		}
		return jobList;
	}
	
	public JobBean saveOrUpdate(JobBean bean){
		try{
			Job job = new Job();
			job.setJobName(bean.getJobName()); 
			job.setLocation(bean.getLocation());
			if(bean.getUserId()!=null && bean.getUserId()>0){
				job.setUser(userRepository.findOne(bean.getUserId()));
			} 
			
			if(bean.getJobId()!=null && bean.getJobId()>0){
				job.setJobId(bean.getJobId());
			}
			job = jobRepository.save(job);
			bean.setJobId(job.getJobId());
			
		}catch(Exception e){
			bean.setOk(false);
		}
		return bean;
		
		
	}
	
	public JobBean get(JobBean bean){
		Job job =  jobRepository.findOne(bean.getJobId());
		bean.setJobId(job.getJobId());
		bean.setJobName(job.getJobName());
		bean.setLocation(job.getLocation());
		User user = job.getUser();
		bean.setUser(user!=null?(user.getFirstName()!=null?user.getFirstName():""+" "+user.getLastName()!=null?user.getLastName():""):"");
		bean.setUserId(user!=null ? user.getUserId():0);
		return bean;
	}
	
	public JobBean remove( JobBean bean){
		try{
			jobRepository.delete(bean.getJobId());
			bean.setResponse(Messages.SUCCESS_remove);
		}catch(Exception e){
			bean.setOk(false);
			bean.setResponse(Messages.ERROR);
		}
		return bean;
	}
	
	
	
}
