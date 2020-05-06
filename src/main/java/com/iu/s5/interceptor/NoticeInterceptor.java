package com.iu.s5.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s5.member.MemberVO;

@Component
public class NoticeInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//관리자면 통과, 아니면 다른곳으로 보내기
		//파라미터를 통해 아무나 들어오려고 하는것을 막아주는 역할을 한다.
		boolean check = false;
		
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		if(memberVO != null && memberVO.getId().equals("admin")) {
			check = true;
			System.out.println("관리자");
		}else {
			System.out.println("관리자가 아니다");
//방법1.		response.sendRedirect("../member/memberLogin");
			
			request.setAttribute("result", "권한이 없습니다.");
			request.setAttribute("path", "../notice/noticeList");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			//internalview reselve까지 안간다. > 주소전체다 적어야함
			view.forward(request, response);
		}
		return check;
	}
	
}
