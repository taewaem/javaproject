package cal.exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseList {
    public static List<Exercise> getAllExercise() {
        List<Exercise> exerList = new ArrayList<Exercise>();

        //List에 데이터 입력
        exerList.add(new Exercise("달리기", 8.6));
        exerList.add(new Exercise("걷기", 2.4));
        exerList.add(new Exercise("계단오르내리기", 8));
        exerList.add(new Exercise("줄넘기", 10));
        exerList.add(new Exercise("댄스", 5));
        exerList.add(new Exercise("자전거타기", 4.7));
        exerList.add(new Exercise("윗몸일으키기", 4));
        exerList.add(new Exercise("팔굽혀펴기", 7));
        exerList.add(new Exercise("스쿼트", 8));
        exerList.add(new Exercise("수영", 7));
        // 완성된 리스트 반환
        return exerList;
    }
}
