package frame;

import javax.swing.*;

public class UtilPanel{

    public void goToPage(JPanel newPage) {

        MainFrame.pageStack.push((JPanel) MainFrame.mainFrame.getContentPane()); // 현재 패널 저장
        MainFrame.mainFrame.setContentPane(newPage); // 새로운 패널로 전환
        MainFrame.mainFrame.revalidate();
        MainFrame.mainFrame.repaint();
    }

    public void goBackPage() {
        if (!MainFrame.pageStack.isEmpty()) {
            JPanel backPanel = MainFrame.pageStack.pop();
            MainFrame.mainFrame.setContentPane(backPanel); // 이전 패널로 전환
            MainFrame.mainFrame.revalidate();
            MainFrame.mainFrame.repaint();

        }
    }

}
