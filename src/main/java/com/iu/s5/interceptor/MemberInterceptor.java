package com.iu.s5.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s5.member.MemberVO;

@Component
public class MemberInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// memberPage 요청시 로그인 유무 판단 , 로그인이 안되있으면 로그인 폼으로 이동
		
		boolean check = false;
		
		//MemberVO도 가능하지만 Object타입으로 받아도 가능 (로그인 됬나안됬나 확인하려고 하니까 null만 아니면된다.)
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		if(memberVO!=null) {
			check = true;
		}else {
			request.setAttribute("result", "로그인 후 이용가능합니다");
			request.setAttribute("path", "../member/memberLogin");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			view.forward(request, response);
		}
		
		return check;
	}
	
}
