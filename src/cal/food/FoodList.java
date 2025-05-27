package cal.food;

import java.util.ArrayList;
import java.util.List;

public class FoodList {

    public static List<Food> getFoodList() {
        List<Food> foodList = new ArrayList<>();

        //List에 데이터 입력
        foodList.add(new Food("치킨", 600));
        foodList.add(new Food("마라탕", 700));
        foodList.add(new Food("닭발", 320));

        foodList.add(new Food("피자", 300));
        foodList.add(new Food("짜장면", 270));
        foodList.add(new Food("라면", 230));

        foodList.add(new Food("냉면", 250));
        foodList.add(new Food("족발", 350));
        foodList.add(new Food("곱창", 500));
        foodList.add(new Food("고기", 500));

        return foodList;
    }
}
