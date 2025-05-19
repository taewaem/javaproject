package product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {

    public static List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        //클래스 처음 참조시 자동 초기화, 따로 호출 X
        productList.add(new Product("우루샷", 15000, "체력 개선, UCDA"));
        productList.add(new Product("루테인", 20000, "눈 겅강"));
        productList.add(new Product("히알루론산", 25000, "피부 건강"));
        productList.add(new Product("다이어트", 30000, "체지방 감소"));
        productList.add(new Product("폴릭애시드", 25000, "비타민B6"));
        productList.add(new Product("밀크씨슬", 15000, "간 건강"));
        productList.add(new Product("유산균", 20000, "장 건강"));
        productList.add(new Product("슬림나이트", 30000, "수면 개선"));
        productList.add(new Product("비타민C", 35000, "면역 기능"));
        productList.add(new Product("레시틴", 20000, "혈중 콜레스테롤"));

        return productList;
    }


}
