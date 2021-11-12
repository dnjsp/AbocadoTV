package kr.or.ddit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dao.MemberDAOImp;
import kr.or.ddit.dao.StreamingDAOImp;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.StreamingVO;

@WebServlet("/startStreaming.do")
public class StartStreamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberDAOImp memberDAO;
	private StreamingDAOImp streamingDAO;
	
    public StartStreamController() {
    	memberDAO = MemberDAOImp.getInstance();
    	streamingDAO = StreamingDAOImp.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession();
		String mail = (String)session.getAttribute("mail");
		String category = request.getParameter("stream-category");
		StreamingVO streamVO = new StreamingVO();
		streamVO.setMember_mail(mail);
		streamVO.setStreaming_category(category);
		streamVO.setStreaming_state("L");
		streamingDAO.InsertStreaming(streamVO);
		MemberVO memVO =  memberDAO.SelectMember(mail);
		
		request.setAttribute("nickname",memVO.getNickname());
		request.setAttribute("master", true);
		request.setAttribute("memberVO", memVO);
		RequestDispatcher rd = request.getRequestDispatcher("/page/Streaming.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
