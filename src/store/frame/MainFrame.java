package store.frame;

import check.CheckPanel;
import store.cart.Cart;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Stack;

public class MainFrame extends JFrame {

    // 화면 전환을 위한 패널 스택
    public static Stack<JPanel> pageStack = new Stack<>();


    // 메인 프레임에 접근하기 위한 전역 변수
    public static JFrame mainFrame;

    public static DecimalFormat df = new DecimalFormat("###,###");

    private Cart cart = new Cart();
    public MainFrame() {
        System.out.println("main frame");

        mainFrame = this;
        mainFrame.setLayout(new CardLayout());

        mainFrame.setTitle("상점");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);        //화면 중앙에 띄우기
        mainFrame.setResizable(false);                //화면 사이즈 조절 x
//      mainPanel.setLayout(null);          //래이아웃 없이 좌표로 위치 잡음

//        //x버튼 클릭 시 시스템 종료
//        WindowListener windowListener = new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
//                System.exit(0);
//            }
//        };
//        addWindowListener(windowListener);
        /**
         * 메인 프레임에서 추가될 패널들
         * 상점 패널
         *.....
         */
        JPanel storePanel = new StorePanel();
        JPanel checkPanel = new CheckPanel();

//        mainFrame.add(checkPanel);
        mainFrame.add(storePanel);

        mainFrame.setVisible(true);
    }


}
