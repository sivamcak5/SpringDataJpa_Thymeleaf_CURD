package com.q.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.q.beans.JobBean;
import com.q.beans.UserBean;
import com.q.constants.Messages;
import com.q.services.JobService;
import com.q.services.UserSevice;


@Controller
public class HomeController {
	
	
	@Autowired
	protected UserSevice userService;
	
	@Autowired
	protected JobService  jobService;

	private final String TEMPLATE_INDEX = "index";

	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("users", userService.list());
		return TEMPLATE_INDEX;
	}
	
	@RequestMapping(value = "/user/{action}/{userId}")
    public String getUser(Model model,@PathVariable String action,UserBean bean){
		String view="user";
		if(action!=null && action.length()>0 ){
			if(action.equalsIgnoreCase("new")){
				model.addAttribute("user", new UserBean());
			}else if(action.equalsIgnoreCase("edit")){
				model.addAttribute("user", userService.get(bean));
			}else if(action.equalsIgnoreCase("remove")){
				bean =userService.remove(bean);
				model.addAttribute("user", bean);
				model.addAttribute("users", userService.list());
				view =TEMPLATE_INDEX;
			}
		}
        return view;
    }
	
	@RequestMapping(value = "/user/save",method = RequestMethod.POST)
    public String saveUser(Model model,UserBean userBean){
		boolean isAdd= userBean.getUserId()!=null && userBean.getUserId()>0 ? false:true; 
		userBean = userService.saveOrUpdate(userBean);
		String view= TEMPLATE_INDEX;
		if(userBean.getOk()){
			userBean = new UserBean();
			userBean.setResponse(isAdd?Messages.SUCCESS_ADD:Messages.SUCCESS_edit);
			model.addAttribute("users", userService.list());
		}else{
			userBean.setResponse(Messages.ERROR);
			view= "user";
		}
		model.addAttribute("user", userBean);
        return view;
    }
	
	@RequestMapping(value = "/job/{action}/{jobId}")
    public String getJob(Model model,@PathVariable String action,JobBean bean){

		String view ="job";
		if(action!=null && action.length()>0 ){
			if(action.equalsIgnoreCase("new")){
				 bean = new JobBean();
				 bean.setUsers(userService.list());
				 model.addAttribute("job", bean);
			}else if(action.equalsIgnoreCase("edit")){
				bean =jobService.get(bean);
				bean.setUsers(userService.list());
				model.addAttribute("job", bean);
			}else if(action.equalsIgnoreCase("remove")){
				bean =jobService.remove(bean);
				model.addAttribute("job",bean);
				model.addAttribute("jobs", jobService.list());
				view = "jobs";
			} 
			
		}
        return view;
		 
    }
	
	@RequestMapping(value = "/job/save",method = RequestMethod.POST)
    public String saveJob(Model model,JobBean bean){
		boolean isAdd= bean.getJobId()!=null && bean.getJobId()>0 ? false:true; 

		String view ="jobs";
		bean = jobService.saveOrUpdate(bean);
		if(bean.getOk()){
			bean = new JobBean();
			bean.setResponse(isAdd?Messages.SUCCESS_ADD:Messages.SUCCESS_edit);
			model.addAttribute("jobs", jobService.list());
			model.addAttribute("job", bean);
		}else{
			bean.setResponse(Messages.ERROR);
			bean.setUsers(userService.list());
			view = "job";
			model.addAttribute("job", bean);
		}
        return view;
    }
	
	@RequestMapping(value = "/jobs")
    public String getJobs(Model model){
    	model.addAttribute("jobs", jobService.list());
        return "jobs";
    }
	
	

}