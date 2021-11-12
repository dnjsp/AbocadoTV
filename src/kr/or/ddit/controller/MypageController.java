package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.or.ddit.service.BoardServiceImp;
import kr.or.ddit.service.CategoryServiceImp;
import kr.or.ddit.service.MemberServiceImp;
import kr.or.ddit.service.SeedServiceImp;
import kr.or.ddit.service.TagServiceImp;
import kr.or.ddit.vo.BlogCategoryVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SeedVO;
import kr.or.ddit.vo.TagVO;

@WebServlet("/Mypage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024
* 100)
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberServiceImp memberService;
	private SeedServiceImp seedService;
	private BoardServiceImp boardService;
	private CategoryServiceImp categoryService;
	private TagServiceImp tagService;
	
    public MypageController() {
    	memberService = MemberServiceImp.getInstance();
    	seedService = SeedServiceImp.getInstance();
    	boardService = BoardServiceImp.getInstance();
    	categoryService = CategoryServiceImp.getInstance();
    	tagService = TagServiceImp.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String cmd;
		try {
			cmd = request.getParameter("cmd");
		} catch (Exception e) {
			cmd = null;
		}
		HttpSession session = request.getSession();
		
		if(cmd==null||cmd.equals("")) {
			if(session.getAttribute("mail")==null) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인을 해야합니다.');");				
				out.println("location.href='/Main?cmd=login';");			
				out.println("</script>");	
			}else {
				String mail = (String)session.getAttribute("mail");
				
				request.setAttribute("vo", memberService.selectMember(mail));
				request.setAttribute("sum", seedService.SumSales(mail));
				request.setAttribute("myBoard", boardService.getMyBoard(mail));
				request.setAttribute("myComment", boardService.getMyComment(mail));
				request.setAttribute("tagList",tagService.SelectListTag(mail));
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/page/mypage.jsp");
				rd.forward(request, response);				
			}
		}else if(cmd.equals("imageUpload")) {
			String mail = (String)session.getAttribute("mail");
			Collection<Part> parts = request.getParts();
			PrintWriter out = response.getWriter();			
			String result = memberService.ImgUpdate(parts, mail);
			request.setAttribute("json", result);
			RequestDispatcher rd = request.getRequestDispatcher("/page/pro/json-pro.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("passCheck")) {
			MemberVO vo = new MemberVO();
			vo.setMember_mail((String)session.getAttribute("mail"));
			vo.setPassword(request.getParameter("pass"));
			request.setAttribute("json", memberService.PassCheck(vo));
			RequestDispatcher rd = request.getRequestDispatcher("/page/pro/json-pro.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("update")) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("member_mail", (String)session.getAttribute("mail"));
			map.put("selector",request.getParameter("target"));
			map.put("value",request.getParameter("value"));
			request.setAttribute("json", memberService.UpdateMember(map));
			RequestDispatcher rd = request.getRequestDispatcher("/page/pro/json-pro.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("charge")){
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/page/kakaoPayList.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("payment")) {
			String mail = (String)session.getAttribute("mail");
			int price = Integer.parseInt(request.getParameter("cp_item"));
			SeedVO svo = new SeedVO();
			svo.setMember_mail(mail);
			svo.setSeed_state("B");
			MemberVO vo = memberService.selectMember(mail);
			request.setAttribute("name", vo.getFirstname() + vo.getLastname());
			request.setAttribute("email", mail);
			request.setAttribute("totalPrice", price);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/page/kakaoPay.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("pay")) {
			String mail = (String)session.getAttribute("mail");
			String amount = request.getParameter("amount");
			int newAmount = Integer.parseInt(amount);
			SeedVO svo = new SeedVO();
			svo.setMember_mail(mail);
			svo.setSeed(newAmount / 100 + "");
			svo.setSeed_state("B");
			seedService.InsertSeed(mail, svo);
		}else if(cmd.equals("seedList")) {
			String mail = (String)session.getAttribute("mail");			
			List<SeedVO> list = seedService.SelectSeed(mail);
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/page/seedList.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("category")) {
			String mail = (String)session.getAttribute("mail");
			List<BlogCategoryVO> list = categoryService.SelectCategory(mail);
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/page/category.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("tagAdd")) {
			String mail = (String)session.getAttribute("mail");
			TagVO tagVO = new TagVO();
			tagVO.setMember_mail(mail);
			tagVO.setTag(request.getParameter("value"));
			String result = tagService.InsertTag(tagVO);
			request.setAttribute("json", result);
			RequestDispatcher rd = request.getRequestDispatcher("/page/pro/json-pro.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("tagDelete")) {
			String idx = request.getParameter("value");
			String result = tagService.DeleteTag(idx);
			request.setAttribute("json", result);
			RequestDispatcher rd = request.getRequestDispatcher("/page/pro/json-pro.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
