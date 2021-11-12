package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.EarningDAOImp;
import kr.or.ddit.dao.MemberDAOImp;
import kr.or.ddit.dao.SeedDAOImp;
import kr.or.ddit.vo.EarningVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SeedVO;

public class SeedServiceImp implements ISeedService{
	private static SeedServiceImp instance;
	public static SeedServiceImp getInstance() {
		if(instance == null) {
			instance = new SeedServiceImp();
			return instance;
		}
		return instance;
	}
	
	private SeedDAOImp seedDao;
	private MemberDAOImp memberDao;
	private EarningDAOImp earningDao;
	
	private SeedServiceImp() {
		seedDao = SeedDAOImp.getInstnace();
		memberDao = MemberDAOImp.getInstance();
		earningDao = EarningDAOImp.getInstance();
	}
	
	@Override
	public String InsertSeed(String mail,SeedVO vo) {
		MemberVO memvo = memberDao.SelectMember(mail);
		EarningVO evo = new EarningVO();
		
		evo.setCharge(Integer.parseInt(vo.getSeed()) * 100 +"");
		evo.setMember_mail(vo.getMember_mail());
		
		seedDao.InsertSeed(vo);
		earningDao.insertEarning(evo);
		return memvo.getFirstname() + memvo.getLastname();
	}

	@Override
	public List<SeedVO> SelectSeed(String mail) {
		return seedDao.SelectSeed(mail);
	}

	@Override
	public int SumSales(String mail) {
		return seedDao.SumSales(mail);
	}

}
