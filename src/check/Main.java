//package src.Check;
//
//import javax.swing.JFrame;
//
///**
// * 프로그램 실행을 위한 메인 클래스
// */
//public class Main {
//
//    // 메인 프레임을 참조하기 위한 static 변수
//    private static JFrame jFrame;
//
//    public static void main(String[] args) {
//        // 메인 프레임 생성
//        jFrame = new JFrame("Check");
//
//        // CheckPanel을 생성하여 프레임에 추가
//        CheckPanel panel = new CheckPanel(jFrame);
//        jFrame.setContentPane(panel);
//
//        // 프레임 기본 설정
//        jFrame.setSize(800, 600); // 창 크기
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 클릭 시 종료
//        jFrame.setLocationRelativeTo(null); // 화면 중앙에 배치
//        jFrame.setVisible(true); // 창 보이기
//    }
//}