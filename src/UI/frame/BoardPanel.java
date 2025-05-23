// package UI.frame;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.awt.image.*;
// import javax.imageio.ImageIO;
// import java.io.IOException;
// import java.net.URL;

// public class BoardPanel extends JPanel {
    
//     private Image background;

//     private JPanel listPanel;
//     private JPanel btnPanel;
//     private JButton writeBtn;
//     private JButton editBtn;
//     private JButton deleteBtn;
//     private Color themeColor = new Color(34, 139, 34);
//     private Dimension btnSize = new Dimension(220, 70);
    
//     public BoardPanel() {

//         //배경이미지 불러오기
//         URL imageURL = getClass().getResource("/img/Background.jpg");
//         try{
//             background = ImageIO.read(imageURL);
//         }catch(IOException e){
//             e.printStackTrace();
//         }

//         setLayout(new GridBagLayout());
//         GridBagConstraints c = new GridBagConstraints(); 

//         //listPanel(Board 첫 화면 가장 크게 보이는 패널)
//         listPanel = new JPanel();
//         listPanel.setBorder(BorderFactory.createLineBorder(themeColor, 3));
//         listPanel.setForeground(Color.BLACK);
//         listPanel.setBackground(Color.WHITE);
//         listPanel.setPreferredSize(new Dimension(300, 300));
//         c.gridx = 0;    // 첫 번째 열
//         c.gridy = 0;    // 첫 번째 행
//         c.gridheight = 3;   // 3개의 행 차지지
//         c.insets = new Insets(10, 40, 10, 10);  //여백
//         c.anchor = GridBagConstraints.CENTER;   // 중앙 정렬
//         c.fill = GridBagConstraints.NONE;   //크기 조절 안함
//         add(listPanel, c);

//         //btnPanel
//         btnPanel = new JPanel();
//         btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
//         btnPanel.setOpaque(false);  //투명배경

//         writeBtn = createBtn("게시물 작성");
//         editBtn = createBtn("게시물 수정");
//         deleteBtn = createBtn("게시물 삭제");

//         btnPanel.add(Box.createVerticalGlue()); //위 여백
//         btnPanel.add(writeBtn);
//         btnPanel.add(Box.createRigidArea(new Dimension(0, 30)));    //간격
//         btnPanel.add(editBtn);
//         btnPanel.add(Box.createRigidArea(new Dimension(0, 30)));
//         btnPanel.add(deleteBtn);
//         btnPanel.add(Box.createVerticalGlue()); //아래 여백

//         c.gridx = 1;
//         c.gridy = 0;
//         c.gridheight = 3;
//         c.anchor = GridBagConstraints.CENTER;
//         c.fill = GridBagConstraints.NONE;
//         add(btnPanel, c);
//     }

//     private JButton createBtn(String text){
//         JButton btn = new JButton(text);
//         btn.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 30));
//         btn.setPreferredSize(btnSize);
//         btn.setBackground(themeColor);
//         btn.setForeground(Color.WHITE);
//         btn.setFocusPainted(false);
//         btn.setBorderPainted(false);

//         return btn;
//     }
//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         if(background != null){
//             g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
//         }
//     }

    
// }

package UI.frame;

import Board.Board;
import Board.Board_Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class BoardPanel extends JPanel {

    private Image background;

    private JPanel listPanel;
    private JPanel btnPanel;
    private JButton writeBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JList<Board.Post> postList;
    private DefaultListModel<Board.Post> listModel;

    private Board board;
    private Board_Main boardMain;

    private Color themeColor = new Color(34, 139, 34);
    private Dimension btnSize = new Dimension(220, 70);

    public BoardPanel() {
        // 배경 이미지 로드
        URL imageURL = getClass().getResource("/img/Background.jpg");
        try {
            background = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        board = new Board();
        listModel = board.getPostListModel(); // 초기 게시글 모델 (빈 상태)
        boardMain = new Board_Main(board, listModel);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // listPanel (게시글 목록 표시)
        listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createLineBorder(themeColor, 3));
        listPanel.setBackground(Color.WHITE);
        listPanel.setPreferredSize(new Dimension(500, 400));

        postList = new JList<>(listModel);
        postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        postList.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        postList.setFixedCellHeight(40);

        postList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = postList.locationToIndex(e.getPoint());
                    Board.Post post = listModel.get(index);
                    boardMain.openViewDialog(BoardPanel.this, post);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(postList);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 3;
        c.insets = new Insets(10, 40, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(listPanel, c);

        // btnPanel
        btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
        btnPanel.setOpaque(false);

        writeBtn = createBtn("게시물 작성");
        editBtn = createBtn("게시물 수정");
        deleteBtn = createBtn("게시물 삭제");

        writeBtn.addActionListener(e -> boardMain.openPostDialog(BoardPanel.this));

        editBtn.addActionListener(e -> {
            int index = postList.getSelectedIndex();
            if (index != -1) {
                boardMain.openEditDialog(BoardPanel.this, index);
            } else {
                JOptionPane.showMessageDialog(this, "수정할 게시물을 선택하세요.");
            }
        });

        deleteBtn.addActionListener(e -> {
            int index = postList.getSelectedIndex();
            if (index != -1) {
                boardMain.openDeleteDialog(BoardPanel.this, index);
            } else {
                JOptionPane.showMessageDialog(this, "삭제할 게시물을 선택하세요.");
            }
        });

        btnPanel.add(Box.createVerticalGlue());
        btnPanel.add(writeBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        btnPanel.add(editBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        btnPanel.add(deleteBtn);
        btnPanel.add(Box.createVerticalGlue());

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
        c.insets = new Insets(10, 10, 10, 40);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.0;
        c.weighty = 1.0;
        add(btnPanel, c);
    }

    private JButton createBtn(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        btn.setForeground(Color.WHITE);
        btn.setBackground(themeColor);
        btn.setPreferredSize(btnSize);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
