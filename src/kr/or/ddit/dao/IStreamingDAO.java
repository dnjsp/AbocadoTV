package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.StreamingVO;

public interface IStreamingDAO {
	public boolean InsertStreaming(StreamingVO vo);
	
	public boolean UpdateStreaming(StreamingVO vo);
	
	public List<StreamingVO> SelectListStreaming();
	
}
