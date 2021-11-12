package kr.or.ddit.vo;

public class BlogCategoryVO {
	private String member_mail;
	private String category_index;
	private String category_name;
	private String category_order;
	private String blog_category;
	private String commentcount;
	
	public BlogCategoryVO() {}
	public String getMember_mail() {
		return member_mail;
	}
	public void setMember_mail(String member_mail) {
		this.member_mail = member_mail;
	}
	public String getCategory_index() {
		return category_index;
	}
	public void setCategory_index(String category_index) {
		this.category_index = category_index;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_order() {
		return category_order;
	}
	public void setCategory_order(String category_order) {
		this.category_order = category_order;
	}
	public String getBlog_category() {
		return blog_category;
	}
	public void setBlog_category(String blog_category) {
		this.blog_category = blog_category;
	}
	public String getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(String commentcount) {
		this.commentcount = commentcount;
	}
	

}
