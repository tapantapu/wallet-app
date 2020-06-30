package com.tkp.poc.filter;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tkp.poc.common.factory.ThreadContainer;
import com.tkp.poc.entity.User;
import com.tkp.poc.repository.UserRepository;

public class UserInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserRepository userRepo;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String encodedUserData = request.getHeader("X-Wallet-Current-User");

		byte[] decodedBytes = Base64.getDecoder().decode(encodedUserData.replace("Basic ", ""));
		String[] userDetail = new String(decodedBytes).split(":");

		User user = userRepo.findByUserNameAndPassword(userDetail[0], userDetail[1]);

		if (user != null) {
			ThreadContainer.setContext(user);
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		ThreadContainer.clean();
		super.postHandle(request, response, handler, modelAndView);
	}
}
