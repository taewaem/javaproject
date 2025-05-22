package store.frame;


import UI.frame.CenterPanel;
import UI.frame.MenuPanel;

import javax.swing.*;
import java.util.Stack;

public class UtilPanel{


    public void switchPanel(JPanel container, JPanel newPanel) {

        container.removeAll();             // 기존 컴포넌트 제거
        container.add(newPanel);          // 새 패널 추가
        container.revalidate();           // 레이아웃 새로 계산
        container.repaint();              // 다시 그리기
    }

}
