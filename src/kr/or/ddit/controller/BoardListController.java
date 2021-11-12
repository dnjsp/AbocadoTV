package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.or.ddit.dao.BlogCommentDAOImp;
import kr.or.ddit.dao.BoardCommentDAOImp;
import kr.or.ddit.dao.BoardDAOImp;
import kr.or.ddit.dao.BoardLikeDAOImp;
import kr.or.ddit.service.BoardCommentServiceImp;
import kr.or.ddit.service.BoardServiceImp;
import kr.or.ddit.util.GsonUtil;
import kr.or.ddit.util.Paging;
import kr.or.ddit.vo.BoardCommentVO;
import kr.or.ddit.vo.BoardLikeVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.RealBoardVO;
import kr.or.ddit.vo.RealCommentVO;

@WebServlet("/pagingList")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GsonUtil gsonUtil;
	private BoardServiceImp service;
	private BoardCommentServiceImp commentService;
	private Gson gson;
	private BoardDAOImp boardDAO;
	private BoardLikeDAOImp boardLikeDAO;
	
	
	public BoardListController() {
		service = BoardServiceImp.getInstance();
		commentService = BoardCommentServiceImp.getInstance();
		gson = GsonUtil.getGson();
		boardDAO = BoardDAOImp.getInstance();
		boardLikeDAO = BoardLikeDAOImp.getInstance();
		gsonUtil = GsonUtil.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cmd = request.getParameter("cmd");
	
		if (cmd == null || cmd.equals("paging")) {
			int countList = service.countList();
			
			String pageStr = request.getParameter("page");
			int page;
			if(pageStr == null) page = 1;
			else page = Integer.parseInt(pageStr);
			Paging pg = new Paging(countList, 10, page);

			List<RealBoardVO> list = pg.showBoardList();
			
			request.setAttribute("list", list);
			request.setAttribute("pageCnt", pg.getTotalPageCnt());
			request.setAttribute("pagingList", pg.getPagingList());

			RequestDispatcher rd = request.getRequestDispatcher("/page/boardList.jsp");

			rd.forward(request, response);
		} else if(cmd.equals("search")) {
			String search = request.getParameter("search");
			String condition = request.getParameter("condition");
			Map<String,String> map = new HashMap<String,String>();
			map.put("condition", condition);
			map.put("search", search);
			int countList = boardDAO.countListSearch(map);

			String pageStr = request.getParameter("page");
			int page;
			if(pageStr == null) page = 1;
			else page = Integer.parseInt(pageStr);
			Paging pg = new Paging(countList, 10, page);
			
			List<RealBoardVO> list = pg.showSearchList(search,condition);
			request.setAttribute("list", list);
			request.setAttribute("pageCnt", pg.getTotalPageCnt());
			request.setAttribute("pagingList", pg.getPagingList());
			request.setAttribute("search", search);
			request.setAttribute("condition", condition);
			RequestDispatcher rd = request.getRequestDispatcher("/page/boardList.jsp");
			rd.forward(request, response);				
		} else if(cmd.equals("see")) {
			HttpSession session = request.getSession();
			BoardLikeVO boardLikeVO = new BoardLikeVO();
			String idx = request.getParameter("boardIdx");
			String mail = (String)session.getAttribute("mail");
			RealBoardVO seeBoard = service.SeeBoard(idx);
			List<RealCommentVO> list = commentService.SelectListComment(idx);
			service.UpdateCount(idx);
			boardLikeVO.setBoard_index(idx);
			boardLikeVO.setMember_mail(mail);
			
			request.setAttribute("board", seeBoard);
			request.setAttribute("list", list);
			request.setAttribute("like",boardLikeDAO.CheckBoardLike(boardLikeVO));
			RequestDispatcher rd = request.getRequestDispatcher("/page/boardShow.jsp");
			rd.forward(request, response);
		} else if(cmd.equals("delete")) {
			String idx = request.getParameter("boardIdx");
			BoardVO vo = new BoardVO();
			vo.setBoard_index(idx);
			service.DeleteBoard(vo);
			
			response.sendRedirect("/pagingList");
		} else if(cmd.equals("update")) {
			String idx = request.getParameter("boardIdx");
			String title = request.getParameter("title");
			String content = request.getParameter("editordata");
			BoardVO vo = new BoardVO();
			vo.setBoard_index(idx);
			vo.setBoard_title(title);
			vo.setBoard_content(content);
			
			service.UpdateBoard(vo);
			
			response.sendRedirect("/pagingList?cmd=see&boardIdx=" + idx);
		} else if(cmd.equals("insertComment")) {
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			String boardIdx = request.getParameter("boardIdx");
			
			BoardCommentVO vo = new BoardCommentVO();
			vo.setMember_mail(writer);
			vo.setBcomment_content(content);
			vo.setBoard_index(boardIdx);
			vo.setBcomment_state("E");
			
			PrintWriter out = response.getWriter();
			
			JsonObject json = new JsonObject();
			boolean insertComment = commentService.InsertComment(vo);
			if(insertComment) {
				List<RealCommentVO> list = commentService.SelectListComment(boardIdx);
				JsonArray array = new JsonArray();
				for(RealCommentVO commentVO : list) {
					JsonObject sub = new JsonObject();
					sub.addProperty("idx", commentVO.getBcomment_index());
					sub.addProperty("date", commentVO.getBcomment_date());
					sub.addProperty("nickname", commentVO.getNickname());
					sub.addProperty("content", commentVO.getBcomment_content());
					array.add(sub);
				}
				json.addProperty("result", "성공");
				json.add("comment", array);
			} else {
				json.addProperty("result", "실패");
			}
			out.print(gson.toJson(json));
		}else if(cmd.equals("deleteComment")) {
			String idx = request.getParameter("idx");
			BoardCommentDAOImp boardDAO = BoardCommentDAOImp.getInstance();
			boardDAO.DeleteComment(idx);
			request.setAttribute("json", gsonUtil.SwOK());
			RequestDispatcher rd = request.getRequestDispatcher("/page/pro/json-pro.jsp");
			rd.forward(request, response);
		}else if(cmd.equals("like")) {
			HttpSession session = request.getSession();
			String mail = (String)session.getAttribute("mail");
			BoardLikeVO boardLikeVO = new BoardLikeVO();
			boardLikeVO.setBoard_index(request.getParameter("board_idx"));
			boardLikeVO.setMember_mail(mail);
			boardLikeDAO.InsertBoardLike(boardLikeVO);
		}else if(cmd.equals("hate")) {
			HttpSession session = request.getSession();
			String mail = (String)session.getAttribute("mail");
			BoardLikeVO boardLikeVO = new BoardLikeVO();
			boardLikeVO.setBoard_index(request.getParameter("board_idx"));
			boardLikeVO.setMember_mail(mail);
			boardLikeDAO.DeleteBoardLike(boardLikeVO);
		}
	}
}
