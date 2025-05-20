//package src.Check;
//
//import javax.swing.*;
//
///**
// * 프로그램 실행 진입점 클래스
// */
//public class CheckMain {
//
//    // 생성자 (지금은 특별한 동작 없음)
//    public CheckMain() {
//    }
//
//    // 메인 메서드: 프로그램 실행 시 가장 먼저 호출되는 메서드
//    public static void main(String[] args) {
//        // Swing은 EDT(Event Dispatch Thread)에서 실행해야 안전함
//        SwingUtilities.invokeLater(() -> {
//            // 프레임 생성
//            JFrame frame = new JFrame("건강 상태 체크");
//
//            // CheckPanel을 생성하여 프레임에 부착
//            frame.setContentPane(new CheckPanel(frame));
//
//            // 프레임 설정
//            frame.setSize(800, 600); // 창 크기 설정
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼
//            frame.setLocationRelativeTo(null); // 화면 중앙에 위치
//            frame.setVisible(true); // 창을 화면에 표시
//        });
//    }
//}