package kr.or.ddit.vo;

public class VisitorVO {
	private String visitor_index;
	private String visitor_date;
	private String visitor_target;
	private String member_mail;
	
	public VisitorVO() {}

	public String getVisitor_index() {
		return visitor_index;
	}

	public void setVisitor_index(String visitor_index) {
		this.visitor_index = visitor_index;
	}

	public String getVisitor_date() {
		return visitor_date;
	}

	public void setVisitor_date(String visitor_date) {
		this.visitor_date = visitor_date;
	}

	public String getVisitor_target() {
		return visitor_target;
	}

	public void setVisitor_target(String visitor_target) {
		this.visitor_target = visitor_target;
	}

	public String getMember_mail() {
		return member_mail;
	}

	public void setMember_mail(String member_mail) {
		this.member_mail = member_mail;
	}
	
}
