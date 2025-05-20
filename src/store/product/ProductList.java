package store.product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {

    public static List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        //클래스 처음 참조시 자동 초기화, 따로 호출 X
        productList.add(new Product("우루샷", 15000, new ProductDescription( "체력 개선",
                "피로 회복 및 에너지 증진에 도움을 줄 수 있는 건강기능식품",
                "하루 1회 섭취",
                "임산부는 복용 전 전문의 상담 필요")));
        productList.add(new Product("루테인", 20000, new ProductDescription("눈 건강",
                "눈 피로와 시력 보호에 도움",
                "하루 1회 식후 섭취",
                "과다 복용 시 부작용 가능")));
        productList.add(new Product("히알루론산", 25000, new ProductDescription("피부 건강",
                "피부 보습과 탄력 개선 도움",
                "하루 2회 섭취",
                "알레르기 체질은 주의")));
        productList.add(new Product("다이어트", 30000, new ProductDescription( "체지방 감소",
                "건강한 체중 감량 보조제",
                "하루 1정 식전 섭취",
                "임산부 및 수유부는 섭취 금지")));
        productList.add(new Product("폴릭애시드", 25000, new ProductDescription( "비타민B6",
                "빈혈 예방과 세포 성장 지원",
                "하루 1회 섭취",
                "권장량 초과 금지")));
        productList.add(new Product("밀크씨슬", 15000, new ProductDescription(   "간 건강",
                "간 기능 개선 및 해독 지원, 간 세포 보호",
                "하루 1회 섭취, 식사 후 섭취 권장",
                "권장량 초과 금지")));
        productList.add(new Product("유산균", 20000, new ProductDescription( "장 건강",
                "장내 유익균 증식 도움",
                "하루 1회 식전 섭취",
                "과다 복용 시 복통 유발 가능")));
        productList.add(new Product("슬림나이트", 30000, new ProductDescription(  "수면 개선",
                "숙면 유도와 수면 질 개선 도움",
                "취침 30분 전 섭취",
                "운전 전 복용 금지")));
        productList.add(new Product("비타민C", 35000, new ProductDescription(  "면역 기능",
                "면역력 증진 및 피로 회복 도움",
                "하루 1회 섭취",
                "과다 복용 시 위장 장애 발생 가능")));
        productList.add(new Product("레시틴", 20000, new ProductDescription("혈중 콜레스테롤",
                "혈액 건강 및 뇌 기능 지원",
                "하루 2회 섭취",
                "특이 체질은 주의")));

        return productList;
    }


}
