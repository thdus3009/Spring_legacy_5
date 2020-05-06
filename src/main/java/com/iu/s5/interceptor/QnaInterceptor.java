package com.iu.s5.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s5.board.BoardVO;
import com.iu.s5.member.MemberVO;

@Component
public class QnaInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean check =false;
		Object obj = request.getSession().getAttribute("member");
		
		if(obj != null) {
			check = true;
		}else {
			response.sendRedirect("../member/memberLogin");
		}
		return check;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		String method = request.getMethod();
		String path = request.getServletPath();
		path = path.substring(path.lastIndexOf("/"));
		System.out.println(path);
		if (method.equals("GET")&& path.equals("/qnaUpdate")) {

			// list는 누구나 볼 수 있지만 click했을때 내용은 해당 글쓴 사람만 볼 수 있다.
			MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
			BoardVO boardVO = (BoardVO) modelAndView.getModel().get("vo");

			String board = (String) modelAndView.getModel().get("board");
			board = "./" + board + "List";

			if (memberVO != null) {

				if (!memberVO.getId().equals(boardVO.getWriter())) {
					modelAndView.setViewName("redirect:" + board);

				} else {
					modelAndView.addObject("result", "권한이 없음");
					modelAndView.addObject("path", board);

					modelAndView.setViewName("common/result");
				}
			}
		}
	}

}
