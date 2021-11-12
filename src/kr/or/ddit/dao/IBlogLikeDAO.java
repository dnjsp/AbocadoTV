package kr.or.ddit.dao;

import kr.or.ddit.vo.BlogLikeVO;

public interface IBlogLikeDAO {
	public boolean InsertBlogLike(BlogLikeVO vo);
	
	public boolean DeleteBlogLike(BlogLikeVO vo);
	
	public boolean CheckBlogLike(String mail);
	
	public int CountBlogLike(String idx);
}
