package kr.or.ddit.service;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.or.ddit.dao.TagDAOImp;
import kr.or.ddit.util.GsonUtil;
import kr.or.ddit.vo.TagVO;

public class TagServiceImp implements ITagService{
	private static TagServiceImp instance;
	public static TagServiceImp getInstance() {
		if(instance==null) instance = new TagServiceImp();
		return instance;
	}
	
	private TagDAOImp tagDAO;
	private Gson gson;
	private GsonUtil gsonUtil;
	
	private TagServiceImp () {
		tagDAO = TagDAOImp.getInstance();
		gson = GsonUtil.getGson();
		gsonUtil = GsonUtil.getInstance();
	}
	
	@Override
	public String InsertTag(TagVO vo) {
		JsonObject json = new JsonObject();
		json.addProperty("idx", tagDAO.InsertTag(vo));
		return gson.toJson(json);
	}

	@Override
	public String DeleteTag(String idx) {
		if(tagDAO.DeleteTag(idx)) {
			return gsonUtil.SwOK();		
		}
		return gsonUtil.SwNO();
	}

	@Override
	public List<TagVO> SelectListTag(String mail) {
		return tagDAO.SelectListTag(mail);
	}

}
