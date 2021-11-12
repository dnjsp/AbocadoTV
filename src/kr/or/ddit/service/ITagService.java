package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.TagVO;

public interface ITagService {
	public String InsertTag(TagVO vo);
	
	public String DeleteTag(String idx);
	
	public List<TagVO> SelectListTag(String mail);
	
}
