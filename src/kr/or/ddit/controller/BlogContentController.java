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

import kr.or.ddit.service.BlogServiceImp;
import kr.or.ddit.service.CategoryServiceImp;
import kr.or.ddit.service.MemberServiceImp;
import kr.or.ddit.util.Paging;
import kr.or.ddit.vo.BlogCategoryVO;
import kr.or.ddit.vo.BlogContentVO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.RealBlogContentVO;

@WebServlet("/blogContent.do")
public class BlogContentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BlogServiceImp service;
	private MemberServiceImp memberService;
	private CategoryServiceImp categoryService;
	
	public BlogContentController() {
		service = BlogServiceImp.getInstance();
		memberService = MemberServiceImp.getInstance();
		categoryService = CategoryServiceImp.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		
		if(cmd == null || cmd.equals("paging")) {
			String email = request.getParameter("email");
			List<BlogCategoryVO> categoryList = categoryService.SelectCategory(email);
			MemberVO memVO = memberService.selectMember(email);
			request.setAttribute("memberVO", memVO);
			request.setAttribute("categoryList", categoryList);
			int countList = service.countList(email);
			
			String pageStr = request.getParameter("page");
			int page;
			if(pageStr == null) page = 1;
			else page = Integer.parseInt(pageStr);
			Paging pg = new Paging(countList, 10, page);
			
			RealBlogContentVO vo = new RealBlogContentVO();
			vo.setMember_mail(email);
			
			List<BlogContentVO> list = pg.showBlogContentList(vo);
			
			request.setAttribute("list", list);
			request.setAttribute("pageCnt", pg.getTotalPageCnt());
			request.setAttribute("pagingList", pg.getPagingList());
			
			RequestDispatcher rd = request.getRequestDispatcher("/page/blogAllContentList.jsp");
			
			rd.forward(request, response);
			
		} else if(cmd.equals("categoryPaging")) {
			String pageStr = request.getParameter("page");
			String blogCategory = request.getParameter("blogCategory");
			int countCategoryList = service.countCategoryList(blogCategory);
			
			int page;
			if(pageStr == null) page = 1;
			else page = Integer.parseInt(pageStr);
			Paging pg = new Paging(countCategoryList, 10, page);
			RealBlogContentVO vo = new RealBlogContentVO();
			vo.setCategory_index(blogCategory);
			
			List<BlogContentVO> list = pg.showCategoryContentList(vo);
			
			request.setAttribute("list", list);
			request.setAttribute("pageCnt", pg.getTotalPageCnt());
			request.setAttribute("pagingList", pg.getPagingList());
			
			RequestDispatcher rd = request.getRequestDispatcher("/page/blogCategoryList.jsp");
			
			rd.forward(request, response);
		} else if(cmd.equals("see")) {
			String idx = request.getParameter("blogIdx");
			System.out.print(idx);
			BlogContentVO vo = service.seeBlogContent("15");
			vo = service.seeBlogContent(idx);
			request.setAttribute("vo", vo);
			service.updateCount(idx);
			
			RequestDispatcher rd = request.getRequestDispatcher("/page/blogShow.jsp");
			rd.forward(request, response);
			
		} else if(cmd.equals("delete")) {
			String idx = request.getParameter("blogIdx");
			System.out.println(idx);
			BlogContentVO vo = new BlogContentVO();
			vo.setBlog_index(idx);
			
			service.DeleteBoard(idx);
			
			response.sendRedirect("/blogContent.do?cmd=paging&page=1");
			
		} else if(cmd.equals("update")) {
			String idx = request.getParameter("blogIdx");
			String title = request.getParameter("title");
			String content = request.getParameter("editordata");
			
			BlogContentVO vo = new BlogContentVO();
			
			vo.setBlog_index(idx);
			vo.setBlog_title(title);
			vo.setBlog_content(content);
			
			service.UpdateBoard(vo);
			
			response.sendRedirect("/blogContent.do?cmd=see&blogIdx=" + idx);
			
		} else if(cmd.equals("write")) {
			HttpSession session = request.getSession();
			String email = (String)session.getAttribute("mail");
			List<BlogCategoryVO> list = service.getMyCategory(email);
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/page/blogwrite.jsp");
			
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
