package kr.or.ddit.service;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import kr.or.ddit.dao.MemberDAOImp;
import kr.or.ddit.util.GsonUtil;
import kr.or.ddit.util.RandImgValueUtil;
import kr.or.ddit.util.SHA256Util;
import kr.or.ddit.util.SendMail;
import kr.or.ddit.util.TemporaryPasswordUtil;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.UploadFileVO;

public class MemberServiceImp implements IMemberService{
	private static MemberServiceImp instance;
	public static MemberServiceImp getInstance() {
		if(instance==null) instance = new MemberServiceImp();
		return instance;
	}
	
	private MemberDAOImp memberdao;
	private SendMail sendmail;
	private GsonUtil gsonUtil;
	private SHA256Util sha256Util;
	private RandImgValueUtil randUtil;
	private TemporaryPasswordUtil temUtil;
	
	private MemberServiceImp() {
		memberdao = MemberDAOImp.getInstance();
		sendmail = SendMail.getInstance();
		gsonUtil = GsonUtil.getInstance();
		sha256Util = SHA256Util.getInstance();
		randUtil = RandImgValueUtil.getInstance();
		temUtil = TemporaryPasswordUtil.getInstance();
	}
	
	@Override
	public String CheckMember(String mail) {
		if(memberdao.CheckMember(mail)) {
			try {
				sendmail.SendCertification(mail);
				return gsonUtil.SwOK();
			} catch (Exception e) {
			}
		}
		return gsonUtil.SwNO();
	}
	
	@Override
	public String Resend(String mail) {
		try {
			sendmail.SendCertification(mail);
			return gsonUtil.SwOK();			
		} catch (Exception e) {
			return gsonUtil.SwNO();
		}
	}

	@Override
	public String InsertMember(MemberVO vo,String certification) {
		vo.setPassword(sha256Util.encrypt(vo.getPassword()));
		if(certification.equals(sendmail.getFromMap(vo.getMember_mail()))) {
			if(memberdao.InsertMember(vo)) {
				return gsonUtil.SwOK();
			}
		}
		return gsonUtil.SwNO();
	}
	
	@Override
	public boolean LoginMember(MemberVO vo) {
		try {
			vo.setPassword(sha256Util.encrypt(vo.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberdao.LoginMember(vo);
	}

	@Override
	public String ImgUpdate(Collection<Part> parts,String mail) {
		String uploadPath = "C:\\Users\\show5\\eclipse-workspace\\AbocadoTV\\WebContent\\memImg";
		File fileUploadDir = new File(uploadPath);
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
		String fileName = "";
		for (Part part : parts) {
			String contentDisposition = part.getHeader("Content-Disposition");
			String[] items = contentDisposition.split(";");
			for(String item : items) {
				if(item.trim().startsWith("filename")) {
					fileName = item.substring(item.indexOf("=") + 2, item.length()-1);
				}
			}
			if(!"".equals(fileName)) {
				UploadFileVO vo = new UploadFileVO();
				fileName = randUtil.makeRand();
				vo.setFileSize((long)Math.ceil(	 part.getSize()/1024.0));
				try {
					part.write(uploadPath + File.separator + fileName);
					Map<String,String> map = new HashMap<String,String>();
					map.put("value", fileName);
					map.put("member_mail", mail);
					map.put("selector", "PROFILEIMG");
					if(memberdao.UpdateMember(map)) {
						return gsonUtil.SwOK();
					}
				} catch (Exception e) {
					return gsonUtil.SwNO();
				}
			}
		}
		return gsonUtil.SwNO();
	}

	@Override
	public String UpdateMember(Map<String, String> map) {
		if(map.get("selector").equals("password")) {
			map.replace("value", sha256Util.encrypt(map.get("value")));
		}
		if(memberdao.UpdateMember(map)) return gsonUtil.SwOK();
		return gsonUtil.SwNO();
	}

	@Override
	public MemberVO selectMember(String mail) {
		MemberVO vo = memberdao.SelectMember(mail);
		if(vo.getProfileimg()==null) {
			vo.setProfileimg("default.png");			
		}
		return vo;
	}

	@Override
	public String PassCheck(MemberVO vo) {
		vo.setPassword(sha256Util.encrypt(vo.getPassword()));
		if(memberdao.LoginMember(vo)) return gsonUtil.SwOK();
		return gsonUtil.SwNO();
	}

	@Override
	public String temporaryPassword(String mail) {
		String tem = temUtil.getTemporaryPassword();
		String newPass = sha256Util.encrypt(tem);
		Map<String,String> map = new HashMap<String,String>();
		map.put("member_mail", mail);
		map.put("selector", "PASSWORD");
		map.put("value", newPass);
		if(memberdao.UpdateMember(map)) {
			sendmail.SendTemporary(mail, tem);			
			return gsonUtil.SwOK();
		}
		return gsonUtil.SwNO();			
	}
	
	@Override
	public List<MemberVO> SelectListMember(MemberVO vo) {
		return memberdao.SelectListMember();
	}

	@Override
	public boolean UpdateMemberState(Map<String,String> map) {
		
		return memberdao.UpdateMemberState(map);
	}

	@Override
	public int CheckFreezedate(String mail) {
		
		return memberdao.CheckFreezedate(mail);
	}

}
