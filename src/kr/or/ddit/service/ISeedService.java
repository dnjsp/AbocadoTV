package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.SeedVO;

public interface ISeedService {
	public String InsertSeed(String mail,SeedVO vo);
	
	public List<SeedVO> SelectSeed(String mail);
	
	public int SumSales(String mail);
}
