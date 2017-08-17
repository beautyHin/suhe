package com.common.interceptor;

import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class SessionInterceptor extends MethodFilterInterceptor{

	private static final long serialVersionUID = 1L;


	@Override
	protected String doIntercept(ActionInvocation action) throws Exception {
		
		Map<String, Object> map = action.getInvocationContext().getSession();
		if(map.get("user")== null) {
			return "/jsp/loginTip.jsp";
		}
		return action.invoke();
	}


	

	
}
