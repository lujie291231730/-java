package com.seed.interceptor;

import com.seed.annotation.Access;
import com.seed.entity.ReturnValue;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class SeedInterceptor implements HandlerInterceptor {

	public SeedInterceptor() {
		super();
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("start");
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			Access access = ((HandlerMethod) handler).getMethodAnnotation(Access.class);
			if (access == null)
				// 该方法没有注解，不需要进行权限校验
				return true;
			else {
				// 拦截代码，通过验证则返回true，否则返回false
				System.out.println(access.value()[0]);
				//boolean test = !access.value().equals("1");
				System.out.println("登陆日志查看需要权限");
				ReturnValue rtv = new ReturnValue();
				rtv.setMsg("您不具备此操作权限");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = null;
				out = response.getWriter();
				out.append(rtv.toString());
				return false;
			}
		} else
			return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.out.println("pending");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("end");
	}
}
