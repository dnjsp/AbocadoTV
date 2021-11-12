package kr.or.ddit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.util.SendMail;

@WebServlet("/serviceCenter")
public class ServiceCenterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SendMail sendMail = SendMail.getInstance();
	
    public ServiceCenterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String category = request.getParameter("category");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		String checkinfo = request.getParameter("chk_info");
		
		sendMail.SendConsult(category, content, checkinfo, answer);
		response.sendRedirect("/page/servicecenter.html");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
