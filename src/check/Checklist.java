package check;

import java.util.ArrayList;
import java.util.List;

/**
 * 건강 상태별 추천 영양제 데이터를 저장하고 반환하는 클래스
 */
public class Checklist {
	
	// 전체 체크 항목(건강 상태 및 추천 영양제)을 리스트로 반환하는 정적 메서드
	public static List<Check> getAllCheck() {
		List<Check> checklist = new ArrayList<Check>();

		// 건강 상태와 추천 영양제를 쌍으로 추가
		checklist.add(new Check("피로감", "우루샷"));
		checklist.add(new Check("눈 건강", "루테인"));
		checklist.add(new Check("피부 건강", "히알루론산"));
		checklist.add(new Check("체지방", "다이어트"));
		checklist.add(new Check("혈관혈액순환", "폴릭애시드"));
		checklist.add(new Check("혈중 콜레스테롤", "레시틴"));
		checklist.add(new Check("간 건강", "밀크씨슬"));
		checklist.add(new Check("장 건강", "유산균"));
		checklist.add(new Check("수면", "슬림나이트"));
		checklist.add(new Check("면역 기능", "비타민C"));

		// 완성된 리스트 반환
		return checklist;
	}
}