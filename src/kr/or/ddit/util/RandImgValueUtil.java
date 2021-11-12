package kr.or.ddit.util;

import java.util.Random;

public class RandImgValueUtil {
	private static RandImgValueUtil instance;
	private RandImgValueUtil(){
		rdn = new Random();
	}
	public static RandImgValueUtil getInstance() {
		if(instance == null) {
			instance = new RandImgValueUtil();
			return instance;
		}
		return instance;
	}
	
	private Random rdn;
	public String makeRand() {
		String result = "";
		char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l',
				'm','n','o','p','q','r','s','t','u','v','w','x','y','z',
				'1','2','3','4','5','6','7','8','9','0'};
		for(int i=0;i<15;i++) {
			result += chars[rdn.nextInt(chars.length)];
		}
		result += ".png";
		return result;
	}
}
