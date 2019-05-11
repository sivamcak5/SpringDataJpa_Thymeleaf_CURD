package com.q.beans;

public abstract class ResponseBean {
	
	public Boolean ok=true;
	public String response="";
	public String prevRes="";
	
	 
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getPrevRes() {
		return prevRes;
	}
	public void setPrevRes(String prevRes) {
		this.prevRes = prevRes;
	}
	public Boolean getOk() {
		return ok;
	}
	public void setOk(Boolean ok) {
		this.ok = ok;
	}
	

}
