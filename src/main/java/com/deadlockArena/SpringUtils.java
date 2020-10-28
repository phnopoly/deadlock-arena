package com.deadlockArena;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deadlockArena.backEnd.service.ServiceImpl;

@Component
public class SpringUtils {

	public static ServiceImpl serviceImpl;

	/**
	 * A quick workaround if @autowired is delayed in the spring bean creation
	 * queue.
	 *
	 * Make Spring inject the application context and save it on a static variable,
	 * so that it can be accessed from any point in the application.
	 */
	@Autowired
	private void setJpaGetData(ServiceImpl serviceImpl) {
		serviceImpl = serviceImpl;
	}
}