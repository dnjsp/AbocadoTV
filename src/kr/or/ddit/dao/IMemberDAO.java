package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDAO {
	public boolean CheckMember(String mail);
	
	public boolean InsertMember(MemberVO vo);
	
	public List<MemberVO> SelectListMember(Map<String,String>map);
	
	public List<MemberVO> SelectSearchListMember(Map<String,String> map);

	public int MemberCount();
	
	public int MemberCountSearch(String mail);
	
	public MemberVO SelectMember(String mail);
	
	public boolean LoginMember(MemberVO vo);
	
	public boolean UpdateMember(Map<String,String> map);
	
	
	public boolean UpdateMemberState(Map<String, String> map);
	
	public int CheckFreezedate(String mail);
	
	public List<MemberVO> SelectListMember();
}
