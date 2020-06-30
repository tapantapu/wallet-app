package com.tkp.poc.common.factory;

import com.tkp.poc.entity.User;

public class ThreadContainer {

	private static ThreadLocal<User> context = new ThreadLocal<>();

	public static void setContext(User user) {
		context.set(user);
	}

	public static void clean() {
		if (context != null) {
			context.remove();
		}
	}

	public static User getUserContext() {
		return context.get();
	}

}
