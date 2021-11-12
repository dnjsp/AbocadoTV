package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dao.MemberDAOImp;
import kr.or.ddit.dao.StreamingDAOImp;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.StreamingVO;

@WebServlet("/StreamingList")
public class StreamingListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StreamingDAOImp streamingDAO;
	private MemberDAOImp memberDAO; 
	
    public StreamingListController() {
    	streamingDAO = StreamingDAOImp.getInstance();
    	memberDAO = MemberDAOImp.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<StreamingVO> list =streamingDAO.SelectListStreaming();
		MemberVO memVO = null;
		if(!list.isEmpty()) {
			String bjMail = list.get(0).getMember_mail();
			memVO =  memberDAO.SelectMember(bjMail);
		}
		request.setAttribute("streamingList", list);
		request.setAttribute("memberVO", memVO);
		RequestDispatcher rd = request.getRequestDispatcher("/page/streamingList.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
