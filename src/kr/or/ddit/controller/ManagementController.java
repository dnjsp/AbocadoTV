package kr.or.ddit.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import kr.or.ddit.service.MemberServiceImp;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/management.do")
public class ManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//    public ManagementController() {
//    	super();
//    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String cmd = request.getParameter("cmd");
			if(cmd==null||cmd.equals("list")) {
				MemberServiceImp service = MemberServiceImp.getInstance();
				
				String userMail = request.getParameter("member_mail");
				String userState = request.getParameter("state");
				
				Map<String,String> map = new HashMap<String,String>();
				map.put("member_mail",userMail);
				map.put("day",userState);
				service.UpdateMemberState(map);

				RequestDispatcher rd = request.getRequestDispatcher("/memberList.do");
				rd.forward(request, response);	
				
			}else if (cmd.equals("manage")) {
				HttpSession session = request.getSession();
				String mail = (String)session.getAttribute("mail");
				String email = request.getParameter("mem_mail");
				
				request.setAttribute("email", email);
				request.setAttribute("mail", mail);
				RequestDispatcher rd = request.getRequestDispatcher("/page/management.jsp");
				rd.forward(request, response);								
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
