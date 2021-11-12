package kr.or.ddit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.service.BoardServiceImp;
import kr.or.ddit.service.NoticeServiceImp;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NoticeVO;

@WebServlet("/noticeWrite.do")
public class NoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession();
		String writer = (String) session.getAttribute("mail");
		
		String title = request.getParameter("title");
		String content = request.getParameter("editordata");
		
		title = title.replaceAll("<", "&lt;");
		title = title.replaceAll(">", "&gt;");
		
		writer = writer.replaceAll("<", "&lt;");
		writer = writer.replaceAll(">", "&gt;");
		
		NoticeVO vo = new NoticeVO();
		
		vo.setNotice_title(title);
		vo.setMember_mail(writer);
		vo.setNotice_content(content);
		
		NoticeServiceImp service = NoticeServiceImp.getInstance();
		
		int index = service.InsertNotice(vo);
		RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath() + "/NoticeController?cmd=see&noticeIdx="+index);
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
