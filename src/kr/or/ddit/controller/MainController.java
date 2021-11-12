package kr.or.ddit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		HttpSession session = request.getSession();
		
		if(cmd.equals("login")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/page/login.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("signup")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/page/signup.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("logout")) {
			session.invalidate();
			response.sendRedirect("/page/index.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
