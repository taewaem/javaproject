package store.frame;

import javax.swing.*;

public class UtilPanel{

    public void goToPage(JPanel newPage) {

        frame.MainFrame.pageStack.push((JPanel) frame.MainFrame.mainFrame.getContentPane()); // 현재 패널 저장
        frame.MainFrame.mainFrame.setContentPane(newPage); // 새로운 패널로 전환
        frame.MainFrame.mainFrame.revalidate();
        frame.MainFrame.mainFrame.repaint();
    }

    public void goBackPage() {
        if (!frame.MainFrame.pageStack.isEmpty()) {
            JPanel backPanel = frame.MainFrame.pageStack.pop();
            frame.MainFrame.mainFrame.setContentPane(backPanel); // 이전 패널로 전환
            frame.MainFrame.mainFrame.revalidate();
            frame.MainFrame.mainFrame.repaint();

        }
    }

}
