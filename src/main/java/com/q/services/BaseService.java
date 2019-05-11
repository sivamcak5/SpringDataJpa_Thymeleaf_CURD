package com.q.services;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseService<T> implements ServiceWrapper<T> {

	@Override
	public T saveOrUpdate(T t) {
		// TODO Auto-generated method stub
		return t;
	}

	@Override
	public T save(T t) {
		// TODO Auto-generated method stub
		return t;
	}

	@Override
	public T get(T t) {
		// TODO Auto-generated method stub
		return t;
	}

	@Override
	public List<T> list() {
		// TODO Auto-generated method stub
		return new ArrayList<T>();
	}
	
	@Override
	public T  remove(T t) {
		// TODO Auto-generated method stub
		return t;
	}
	
	

}
