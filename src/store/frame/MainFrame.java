package store.frame;

import store.cart.Cart;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Stack;

public class MainFrame extends JFrame {

    public MainFrame() {
        System.out.println("main frame");

        setLayout(new CardLayout());

        setTitle("상점");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);        //화면 중앙에 띄우기
        setResizable(false);                //화면 사이즈 조절 x
//      mainPanel.setLayout(null);          //래이아웃 없이 좌표로 위치 잡음

        /**
         * 메인 프레임에서 추가될 패널들
         * 상점 패널
         *.....
         */
        JPanel storePanel = new StorePanel();
        add(storePanel);

        setVisible(true);
    }


}
