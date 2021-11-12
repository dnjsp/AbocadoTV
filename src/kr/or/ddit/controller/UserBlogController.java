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

import kr.or.ddit.dao.FollowDAOImp;
import kr.or.ddit.dao.VisitorDAOImp;
import kr.or.ddit.service.CategoryServiceImp;
import kr.or.ddit.service.MemberServiceImp;
import kr.or.ddit.util.GsonUtil;
import kr.or.ddit.vo.BlogCategoryVO;
import kr.or.ddit.vo.FollowVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.VisitorVO;

@WebServlet("/Blog")
public class UserBlogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberServiceImp memberService;
	private CategoryServiceImp categoryService;
	private VisitorDAOImp visitorDAO;
	private GsonUtil gsonUtil;
	private FollowDAOImp followDAO;
	
    public UserBlogController() {
    	memberService = MemberServiceImp.getInstance();
    	categoryService = CategoryServiceImp.getInstance();
    	visitorDAO = VisitorDAOImp.getInstance();
    	followDAO = FollowDAOImp.getInstance();
    	gsonUtil = GsonUtil.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		if(cmd==null||cmd.equals("")) {
			HttpSession session = request.getSession();
			String mail = (String)session.getAttribute("mail");
			VisitorVO visitorVO = new VisitorVO();
			visitorVO.setMember_mail(mail);
			visitorVO.setVisitor_target(mail);
			if(!visitorDAO.CheckVisitor(visitorVO)) {
				visitorDAO.InsertVisitor(visitorVO);
			}
			MemberVO memVO = memberService.selectMember(mail);
			List<BlogCategoryVO> categoryList = categoryService.SelectCategory(mail);
			session.setAttribute("memberVO", memVO);
			session.setAttribute("categoryList", categoryList);
			session.setAttribute("mailId", mail);
			RequestDispatcher rd = request.getRequestDispatcher("/page/blog.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("blog")) {
			HttpSession session = request.getSession();
			String myMail = (String)session.getAttribute("mail");
			String mail = request.getParameter("mailId");
			if(myMail != null) {
				VisitorVO visitorVO = new VisitorVO();
				visitorVO.setMember_mail(myMail);
				visitorVO.setVisitor_target(mail);
				if(!visitorDAO.CheckVisitor(visitorVO)) {
					visitorDAO.InsertVisitor(visitorVO);
				}
			}
			FollowVO followVO = new FollowVO(); 
			FollowDAOImp followDAO = FollowDAOImp.getInstance();
			followVO.setFollow_mail(mail);
			followVO.setFollower_mail(myMail);
			MemberVO memVO = memberService.selectMember(mail);
			List<BlogCategoryVO> categoryList = categoryService.SelectCategory(mail);
			session.setAttribute("mailId", mail);
			request.setAttribute("followflag",followDAO.CheckFollow(followVO));
			session.setAttribute("memberVO", memVO);
			session.setAttribute("categoryList", categoryList);
			RequestDispatcher rd = request.getRequestDispatcher("/page/blog.jsp");
			rd.forward(request, response);
		}else if (cmd.equals("following")) {
			HttpSession session = request.getSession();
			String mail = (String) session.getAttribute("mail");
			FollowVO followVO = new FollowVO();
			followVO.setFollow_mail(request.getParameter("follow"));
			followVO.setFollower_mail(mail);
			followDAO.InsertFollow(followVO);
			request.getAttribute(gsonUtil.SwOK());
			RequestDispatcher rd = request.getRequestDispatcher("/page/pro/json-pro.jsp");
			rd.forward(request, response);
		}else if (cmd.equals("unfollowing")) {
			HttpSession session = request.getSession();
			String mail = (String) session.getAttribute("mail");
			FollowVO followVO = new FollowVO();
			followVO.setFollow_mail(request.getParameter("follow"));
			followVO.setFollower_mail(mail);
			followDAO.DeleteFollow(followVO);
			request.getAttribute(gsonUtil.SwOK());
			RequestDispatcher rd = request.getRequestDispatcher("/page/pro/json-pro.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
