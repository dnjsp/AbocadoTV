package kr.or.ddit.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import kr.or.ddit.vo.MemberVO;

public interface IMemberService {
	public String CheckMember(String mail);
	
	public String Resend(String mail);
	
	public boolean LoginMember(MemberVO vo);
	
	public String InsertMember(MemberVO vo,String certification);
	
	public String PassCheck(MemberVO vo);
	
	public String ImgUpdate(Collection<Part> parts,String mail);
	
	public String UpdateMember(Map<String,String> map);
	
	public MemberVO selectMember(String mail);
	
	public String temporaryPassword(String mail);
	
	public List<MemberVO> SelectListMember(MemberVO vo);
	
	public boolean UpdateMemberState(Map<String,String> map);
	
	public int CheckFreezedate(String mail);
}
