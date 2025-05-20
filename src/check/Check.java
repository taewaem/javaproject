package check;


/**
 * 건강 상태와 추천 영양제를 표현하는 클래스
 */
public class Check {

	private String name;         // 건강 상태 이름 (예: 피로감, 눈 건강 등)
	private String description;  // 추천 영양제 이름 (예: 우루샷, 루테인 등)

	// 생성자: 객체 생성 시 name과 description을 초기화
	public Check(String name, String description) {
		this.name = name;
		this.description = description;
	}

	// getter: name 반환
	public String getName() {
		return name;
	}

	// setter: name 설정
	public void setName(String name) {
		this.name = name;
	}

	// getter: description 반환
	public String getDescription() {
		return description;
	}

	// setter: description 설정
	public void setDescription(String description) {
		this.description = description;
	}
}