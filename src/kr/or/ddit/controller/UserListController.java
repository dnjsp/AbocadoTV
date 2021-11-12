package kr.or.ddit.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dao.MemberDAOImp;
import kr.or.ddit.dao.TagDAOImp;
import kr.or.ddit.util.UserPaging;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TagVO;

@WebServlet("/UserList")
public class UserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberDAOImp memberDAO;
	private TagDAOImp tagDAO;
	
    public UserListController() {
    	memberDAO = MemberDAOImp.getInstance();
    	tagDAO = TagDAOImp.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("ranking")) {
			String page = request.getParameter("page");
			int pageInt = 1;
			if(page!= null) {
				pageInt = Integer.parseInt(page);
			}
			int countList = memberDAO.MemberCount();
			UserPaging pg = new UserPaging(countList,10,pageInt);
			List<MemberVO> memList = pg.showMemberList();
			Map<String,List<TagVO>> tagMap = new HashMap<String,List<TagVO>>();
			for(MemberVO vo: memList) {
				String eachMail = vo.getMember_mail();
				if(vo.getProfileimg()== null) {
					vo.setProfileimg("default.png");
				}
				tagMap.put(eachMail, tagDAO.SelectListTag(eachMail));
			}
			request.setAttribute("search", false);
			request.setAttribute("pageCnt", pg.getTotalPageCnt());
			request.setAttribute("pagingList", pg.getPagingList());
			request.setAttribute("memList", memList);
			request.setAttribute("tagMap", tagMap);
			RequestDispatcher rd = request.getRequestDispatcher("/page/rank.jsp");
			rd.forward(request, response);
		}else if (cmd.equals("search")) {
			String page = request.getParameter("page");
			String value = request.getParameter("value");
			int pageInt = 1;
			if(page!= null) {
				pageInt = Integer.parseInt(page);
			}
			int countList = memberDAO.MemberCountSearch(value);
			UserPaging pg = new UserPaging(countList,10,pageInt);
			List<MemberVO> memList = pg.showMemberList(value);
			Map<String,List<TagVO>> tagMap = new HashMap<String,List<TagVO>>();
			for(MemberVO vo: memList) {
				String eachMail = vo.getMember_mail();
				if(vo.getProfileimg()== null) {
					vo.setProfileimg("default.png");
				}
				tagMap.put(eachMail, tagDAO.SelectListTag(eachMail));
			}
			request.setAttribute("search", true);
			request.setAttribute("value", value);
			request.setAttribute("pageCnt", pg.getTotalPageCnt());
			request.setAttribute("pagingList", pg.getPagingList());
			request.setAttribute("memList", memList);
			request.setAttribute("tagMap", tagMap);
			RequestDispatcher rd = request.getRequestDispatcher("/page/rank.jsp");
			rd.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
