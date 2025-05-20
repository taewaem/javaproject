package src.Check;

import javax.swing.JFrame;

//import src.store.frame.MainFrame;

public class Main {
    public static void main(String[] args) {
        //new MainFrame(); // ✅ 객체 생성은 main 메서드 안에서 해야 함
           JFrame frame = new JFrame("건강 상태 체크");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 테스트용
        frame.setSize(800, 600);
        frame.setContentPane(new CheckPanel(frame));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); //여기까지 테스트용
    }
}