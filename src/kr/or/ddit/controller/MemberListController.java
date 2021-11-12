package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import kr.or.ddit.service.ChatBlockServiceImp;
import kr.or.ddit.service.MemberServiceImp;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberServiceImp memberService = MemberServiceImp.getInstance();
	MemberVO memberVO = new MemberVO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		List<MemberVO> list = null;
		
		list = memberService.SelectListMember(memberVO);
		request.setAttribute("memberList", list);
		for (MemberVO memberVO : list) {
			System.out.println(memberVO.getFreezedate());			
		}
		RequestDispatcher rd = request.getRequestDispatcher("/page/memberList.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
