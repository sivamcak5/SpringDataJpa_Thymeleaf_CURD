package com.q.services;

import java.util.List;

public interface ServiceWrapper<T> {
	
	public T saveOrUpdate(T t);
	public T save(T t);
	public T get(T t);
	public List<T> list();
	public T remove(T t);

}
