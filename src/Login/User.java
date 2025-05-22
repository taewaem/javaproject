package Login;

public class User {

	private String id;// 아이디
	private String pw;// 비밀번호
	private String nickname;// 닉네임
	private String birthday;// 생년월일
	private String phnumber;// 전화번호
	private double totalFoodKcal;
	private double totalExerKcal;

	public User(){

	}
	public User(String id, String pw, String nickname, String birthday, String phnumber) {
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		this.birthday = birthday;
		this.phnumber = phnumber;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhnumber() {
		return phnumber;
	}

	public void setPhnumber(String phnumber) {
		this.phnumber = phnumber;
	}

	public double getTotalFoodKcal() {
		return totalFoodKcal;
	}

	public void setTotalFoodKcal(double totalFoodKcal) {
		this.totalFoodKcal = totalFoodKcal;
	}

	public double getTotalExerKcal() {
		return totalExerKcal;
	}

	public void setTotalExerKcal(double totalExerKcal) {
		this.totalExerKcal = totalExerKcal;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", nickname=" + nickname + ", birthday=" + birthday + ", phnumber="
				+ phnumber + "]";
	}

}
