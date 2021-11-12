package kr.or.ddit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.service.BlogServiceImp;
import kr.or.ddit.vo.BlogContentVO;

@WebServlet("/blogContentWrite.do")
public class BlogContentWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession();
		
		String writer = (String)session.getAttribute("mail");
		String title = request.getParameter("title");
		String content = request.getParameter("editordata");
		String category = request.getParameter("category-list");
		title = title.replaceAll("<", "&lt;");
		title = title.replaceAll(">", "&gt;");
		
		writer = writer.replaceAll("<", "&lt;");
		writer = writer.replaceAll(">", "&gt;");
		
		BlogContentVO vo = new BlogContentVO();
		
		vo.setMember_mail(writer);
		vo.setBlog_title(title);
		vo.setBlog_content(content);
		vo.setBlog_category(category);
		vo.setBlog_authorith("A");
		
		BlogServiceImp service = new BlogServiceImp();
		
		int idx = service.InsertBoard(vo);
		
		RequestDispatcher rd = request.getRequestDispatcher("/blogContent.do?cmd=see&blogIdx=" + idx);
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
