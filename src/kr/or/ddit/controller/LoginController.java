package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dao.VisitorDAOImp;
import kr.or.ddit.service.MemberServiceImp;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.VisitorVO;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberServiceImp service = MemberServiceImp.getInstance();
	
	private VisitorDAOImp visitorDAO;
	
	public LoginController(){
		visitorDAO = VisitorDAOImp.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String mail = (request.getParameter("mail")).toLowerCase();
		String password = request.getParameter("password");
		String saveMail = request.getParameter("saveMail");
		
		MemberVO vo = new MemberVO();
		vo.setMember_mail(mail);
		vo.setPassword(password);
		
		HttpSession session = request.getSession();

		if(service.LoginMember(vo)) {
			if (mail.equals("lim3617@naver.com") || mail.equals("yyj161091@gmail.com")) {
				session.setAttribute("authority", true);
			}else {
				session.setAttribute("authority", false);
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date time = new Date();

			String nowTime = format.format(time);

			int blockDate = service.CheckFreezedate(mail);
			if (blockDate != 0) {
				if (Integer.parseInt(nowTime) < blockDate) {
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert(`회원님은 " + blockDate + "까지 로그인이 제한되었습니다.`)");
					out.println("location.href=`/Main?cmd=login`");
					out.println("</script>");
					return;
				}
			}
			session.setAttribute("mail", mail);
			
			if (saveMail != null) {
				Cookie c = new Cookie("saveMail", mail);
				c.setMaxAge(60 * 60 * 24 * 30);
				response.addCookie(c);
			} else {
				Cookie c = new Cookie("saveMail", mail);
				c.setMaxAge(0);
				response.addCookie(c);
			}
			
			VisitorVO visitorVO = new VisitorVO();
			visitorVO.setMember_mail(mail);
			visitorVO.setVisitor_target("home");
			if(!visitorDAO.CheckVisitor(visitorVO)) {
				visitorDAO.InsertVisitor(visitorVO);
			}
			response.sendRedirect("/page/index.html");
		} else {
			session.setAttribute("errMsg", "아이디 또는 비밀번호가 잘못 입력 되었습니다.");
			response.sendRedirect("/Main?cmd=login"); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
