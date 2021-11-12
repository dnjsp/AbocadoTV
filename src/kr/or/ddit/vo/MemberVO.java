package kr.or.ddit.vo;

public class MemberVO {
	
	private String member_mail; 
	private String firstname; 
	private String lastname; 
	private String password;
	private String nickname;
	private String member_date;
	private String regedent_num;
	private String introduction;
	private String freezedate;
	private String profileimg;
	private String likeCount;
	private String followcount;
	private String todaycount;
	private String totalcount;
	
	public MemberVO(){}

	public String getMember_mail() {
		return member_mail;
	}

	public void setMember_mail(String member_mail) {
		this.member_mail = member_mail;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMember_date() {
		return member_date;
	}

	public void setMember_date(String member_date) {
		this.member_date = member_date;
	}

	public String getRegedent_num() {
		return regedent_num;
	}

	public void setRegedent_num(String regedent_num) {
		this.regedent_num = regedent_num;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getFreezedate() {
		return freezedate;
	}

	public void setFreezedate(String freezedate) {
		this.freezedate = freezedate;
	}

	public String getProfileimg() {
		return profileimg;
	}

	public void setProfileimg(String profileimg) {
		this.profileimg = profileimg;
	}

	public String getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}

	public String getFollowcount() {
		return followcount;
	}

	public void setFollowcount(String followcount) {
		this.followcount = followcount;
	}

	public String getTodaycount() {
		return todaycount;
	}

	public void setTodaycount(String todaycount) {
		this.todaycount = todaycount;
	}

	public String getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}
	
	
}