package kr.or.ddit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dao.BlogCategoryDAOImp;
import kr.or.ddit.service.CategoryServiceImp;
import kr.or.ddit.vo.BlogCategoryVO;
import kr.or.ddit.vo.BlogVO;

@WebServlet("/Category")
public class CategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryServiceImp categoryService;
	private BlogCategoryDAOImp categoryDAO;
    
	public CategoriesController() {
		categoryService = CategoryServiceImp.getInstance();
		categoryDAO = BlogCategoryDAOImp.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		BlogCategoryVO blogVO = new BlogCategoryVO();
		HttpSession session = request.getSession();
		String mail = (String)session.getAttribute("mail");
		String result = "";
		if(cmd.equals("start")) {
			result = categoryDAO.start();
		}else if(cmd.equals("end")) {
			result = categoryDAO.end();
		}else if(cmd.equals("insert")) {
			blogVO.setBlog_category(request.getParameter("BS"));
			blogVO.setCategory_name(request.getParameter("value"));
			blogVO.setCategory_order(request.getParameter("order"));
			blogVO.setMember_mail(mail);
			result = categoryService.InsertCategory(blogVO);
		}else if(cmd.equals("delete")) {
			result = categoryService.DeleteCategory(request.getParameter("idx"));
		}else if(cmd.equals("update")) {
			blogVO.setCategory_index(request.getParameter("idx"));
			blogVO.setBlog_category(request.getParameter("BS"));
			blogVO.setCategory_name(request.getParameter("value"));
			blogVO.setCategory_order(request.getParameter("order"));
			blogVO.setMember_mail(mail);
			result = categoryService.UpdateCategory(blogVO);
		}	
		request.setAttribute("json", result);
		RequestDispatcher rd = request.getRequestDispatcher("/page/pro/json-pro.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
