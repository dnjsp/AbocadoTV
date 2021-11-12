package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import kr.or.ddit.dao.MemberDAOImp;
import kr.or.ddit.dao.SeedDAOImp;
import kr.or.ddit.dao.StreamingDAOImp;
import kr.or.ddit.util.GsonUtil;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SeedVO;
import kr.or.ddit.vo.StreamingVO;

@WebServlet("/Streaming")
public class StreamingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StreamingDAOImp streamingDAO;
	private MemberDAOImp memberDAO;
	private SeedDAOImp seedDAO;
	private GsonUtil gsonUtil;
	
    public StreamingController() {
    	streamingDAO= StreamingDAOImp.getInstance();
    	gsonUtil = GsonUtil.getInstance();
    	memberDAO = MemberDAOImp.getInstance();
    	seedDAO = SeedDAOImp.getInstnace();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		if(cmd.equals("close")) {
			HttpSession session = request.getSession();
			String mail = (String)session.getAttribute("mail");
			StreamingVO streamingVo = new StreamingVO();
			streamingVo.setMember_mail(mail);
			streamingDAO.UpdateStreaming(streamingVo);
			
			request.setAttribute("json", gsonUtil.SwOK());
			RequestDispatcher rd = request.getRequestDispatcher("/page/pro/json-pro.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("see")) {
			HttpSession session = request.getSession();
			String mail = (String)session.getAttribute("mail");
			if(mail == null) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 후 이용하실 수 있습니다.');");
				out.println("location.href='/Main?cmd=login';");
				out.println("</script>");
			}
			String streamer = request.getParameter("memberMail");
			MemberVO mymemVO =  memberDAO.SelectMember(mail);
			MemberVO streamerVO =  memberDAO.SelectMember(streamer);
			request.setAttribute("seed", seedDAO.SumSales(mail));
			request.setAttribute("nickname",mymemVO.getNickname());
			request.setAttribute("master", false);
			request.setAttribute("memberVO", streamerVO);
			RequestDispatcher rd = request.getRequestDispatcher("/page/Streaming.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("donate")) {
			HttpSession session = request.getSession();
			String mail = (String)session.getAttribute("mail");
			String donate = request.getParameter("donate");
			String streamer = request.getParameter("streamer");
			SeedVO seedVO = new SeedVO();
			seedVO.setMember_mail(mail);
			seedVO.setSeed(donate);
			seedVO.setSeed_state("D");
			seedDAO.InsertSeed(seedVO);
			SeedVO seedDonateVO = new SeedVO();
			seedDonateVO.setMember_mail(streamer);
			seedDonateVO.setSeed(donate);
			seedDonateVO.setSeed_state("I");
			seedDAO.InsertSeed(seedDonateVO);
			request.setAttribute("json", gsonUtil.SwOK());
			RequestDispatcher rd = request.getRequestDispatcher("/page/pro/json-pro.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
