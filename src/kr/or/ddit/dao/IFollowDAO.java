package kr.or.ddit.dao;

import kr.or.ddit.vo.FollowVO;

public interface IFollowDAO {
	public boolean InsertFollow(FollowVO vo);
	
	public boolean DeleteFollow(FollowVO vo);

	public int CountFollow(String mail);
	
	public boolean CheckFollow(FollowVO vo);
}
