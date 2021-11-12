package kr.or.ddit.service;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.or.ddit.dao.BlogCategoryDAOImp;
import kr.or.ddit.util.GsonUtil;
import kr.or.ddit.vo.BlogCategoryVO;

public class CategoryServiceImp implements ICategoryService{
	private static CategoryServiceImp instance;
	public static CategoryServiceImp getInstance() {
		if(instance==null) instance = new CategoryServiceImp();
		return instance;
	}
	
	private BlogCategoryDAOImp categoryDAO;
	private GsonUtil gsonUtil;
	private Gson gson;
	
	private CategoryServiceImp() {
		categoryDAO = BlogCategoryDAOImp.getInstance();
		gsonUtil = GsonUtil.getInstance();
		gson = GsonUtil.getGson();
	}

	@Override
	public String InsertCategory(BlogCategoryVO vo) {
		JsonObject json = new JsonObject();
		int idx = categoryDAO.InsertCategory(vo);
		if(idx != 0) {
			json.addProperty("idx", idx);
			json.addProperty("sw", "ok");
			return gson.toJson(json);
		}
		return gsonUtil.SwNO();
	}

	@Override
	public String DeleteCategory(String idx) {
		if(categoryDAO.DeleteCategory(idx)) return gsonUtil.SwOK();
		return gsonUtil.SwNO();
	}

	@Override
	public String UpdateCategory(BlogCategoryVO vo) {
		if(categoryDAO.UpdateCategory(vo)) return gsonUtil.SwOK();
		return gsonUtil.SwNO();
	}

	@Override
	public List<BlogCategoryVO> SelectCategory(String mail) {
		return categoryDAO.SelectCategory(mail);
	}

}
