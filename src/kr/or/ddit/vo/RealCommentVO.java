package kr.or.ddit.vo;

public class RealCommentVO {
	private String member_mail;
	private String board_index;
	private String bcomment_index;
	private String bcomment_content;
	private String bcomment_state;
	private String bcomment_date;
	private String nickname;
	
	public RealCommentVO() {}

	public String getMember_mail() {
		return member_mail;
	}

	public void setMember_mail(String member_mail) {
		this.member_mail = member_mail;
	}

	public String getBoard_index() {
		return board_index;
	}

	public void setBoard_index(String board_index) {
		this.board_index = board_index;
	}

	public String getBcomment_index() {
		return bcomment_index;
	}

	public void setBcomment_index(String bcomment_index) {
		this.bcomment_index = bcomment_index;
	}

	public String getBcomment_content() {
		return bcomment_content;
	}

	public void setBcomment_content(String bcomment_content) {
		this.bcomment_content = bcomment_content;
	}

	public String getBcomment_state() {
		return bcomment_state;
	}

	public void setBcomment_state(String bcomment_state) {
		this.bcomment_state = bcomment_state;
	}

	public String getBcomment_date() {
		return bcomment_date;
	}

	public void setBcomment_date(String bcomment_date) {
		this.bcomment_date = bcomment_date;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
