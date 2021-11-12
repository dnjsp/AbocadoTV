package kr.or.ddit.vo;

public class RealBlogContentVO {
	private String blog_index;
	private String member_mail;
	private String blog_date;
	private String blog_count;
	private String blog_content;
	private String blog_title;
	private String blog_category;
	private String blog_authorith;
	private int startPage;
	private String category_index;
	
	public RealBlogContentVO(){}

	public String getBlog_index() {
		return blog_index;
	}
	
	
	public String getCategory_index() {
		return category_index;
	}

	public void setCategory_index(String category_index) {
		this.category_index = category_index;
	}

	public void setBlog_index(String blog_index) {
		this.blog_index = blog_index;
	}

	public String getMember_mail() {
		return member_mail;
	}

	public void setMember_mail(String member_mail) {
		this.member_mail = member_mail;
	}

	public String getBlog_date() {
		return blog_date;
	}

	public void setBlog_date(String blog_date) {
		this.blog_date = blog_date;
	}

	public String getBlog_count() {
		return blog_count;
	}

	public void setBlog_count(String blog_count) {
		this.blog_count = blog_count;
	}

	public String getBlog_content() {
		return blog_content;
	}

	public void setBlog_content(String blog_content) {
		this.blog_content = blog_content;
	}

	public String getBlog_title() {
		return blog_title;
	}

	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}

	public String getBlog_category() {
		return blog_category;
	}

	public void setBlog_category(String blog_category) {
		this.blog_category = blog_category;
	}

	public String getBlog_authorith() {
		return blog_authorith;
	}

	public void setBlog_authorith(String blog_authorith) {
		this.blog_authorith = blog_authorith;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	

}
