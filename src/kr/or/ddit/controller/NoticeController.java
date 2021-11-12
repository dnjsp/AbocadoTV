package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.service.NoticeServiceImp;
import kr.or.ddit.util.Paging;
import kr.or.ddit.vo.NoticeVO;

@WebServlet("/NoticeController")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoticeServiceImp service;
	
    public NoticeController() {
		service = NoticeServiceImp.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		
		if(cmd == null || cmd.equals("paging")) {
			int countList = service.CountList();
			
			String pageStr = request.getParameter("page");
			int page;
			if(pageStr == null) page = 1;
			else page = Integer.parseInt(pageStr);
			Paging pg = new Paging(countList, 10, page);
		
			List<NoticeVO> list = pg.showNoticeList();
			
			request.setAttribute("list", list);
			request.setAttribute("pageCnt", pg.getTotalPageCnt());
			request.setAttribute("pagingList", pg.getPagingList());
		
			RequestDispatcher rd = request.getRequestDispatcher("/page/noticeList.jsp");
			
			rd.forward(request, response);
		} else if(cmd.equals("delete")) {
			String idx = request.getParameter("noticeIdx");
			service.DeleteNotice(idx);
			
			response.sendRedirect("/NoticeController");
		} else if(cmd.equals("see")) {
			HttpSession session = request.getSession();
			String idx = request.getParameter("noticeIdx");
			NoticeVO vo = service.SeeNotice(idx);
			
			request.setAttribute("vo", vo);
			service.updateCount(idx);
			RequestDispatcher rd = request.getRequestDispatcher("/page/noticeShow.jsp");
			rd.forward(request, response);
			
		} else if(cmd.equals("update")) {
			String idx = request.getParameter("noticeIdx");
			String title = request.getParameter("title");
			String content = request.getParameter("editordata");
			
			NoticeVO vo = new NoticeVO();
			vo.setNotice_index(idx);
			vo.setNotice_title(title);
			vo.setNotice_content(content);
			
			service.UpdateNotice(vo);
			
			response.sendRedirect("/NoticeController?cmd=see&noticeIdx=" + idx);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
