package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.TagVO;

public interface ITagDAO {
	public String InsertTag(TagVO vo);
	
	public boolean DeleteTag(String idx);
	
	public List<TagVO> SelectListTag(String mail);
	
}
