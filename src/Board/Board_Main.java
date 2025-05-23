// package Board;

// //Board_Main 클래스: UI / 실행 컨트롤러 역할
// import java.awt.GridLayout;

// import javax.swing.DefaultListModel;
// import javax.swing.JButton;
// import javax.swing.JDialog;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JList;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
// import javax.swing.JTextField;
// import javax.swing.SwingConstants;

// //게시판 메인 화면 클래스 (JFrame을 상속받아 GUI 창을 생성)
// public class Board_Main extends JPanel {
// 	private Board board; // 게시글 데이터를 관리하는 Board 객체
// 	private JFrame parentFrame;

// 	// 생성자 - Board 객체를 받아와서 메인 화면을 구성
// 	public Board_Main(Board board, JFrame parentFrame) {
// 		this.board = board;
// 		this.parentFrame = parentFrame;

// 		setLayout(new GridLayout(4, 1, 10, 10));

// 		// 버튼 생성
// 		JButton listBtn = new JButton("게시글 목록 보기");
// 		JButton writeBtn = new JButton("게시글 작성");
// 		JButton editBtn = new JButton("게시글 수정");
// 		JButton deleteBtn = new JButton("게시글 삭제");
// 		// 버튼 클릭 이벤트 처리
// 		listBtn.addActionListener(e -> showPostList()); // 목록 보기
// 		writeBtn.addActionListener(e -> openPostDialog(null)); // 작성
// 		editBtn.addActionListener(e -> openEditDialog()); // 수정
// 		deleteBtn.addActionListener(e -> openDeleteDialog()); // 삭제
// 		// 버튼을 프레임에 추가
// 		add(listBtn);
// 		add(writeBtn);
// 		add(editBtn);
// 		add(deleteBtn);
// 		// 창을 화면에 보이게 함
// 		// setVisible(true);
// 	}
// 	// 1. 게시글 목록 보기 (목록 창 열기)
// 	private void showPostList() {
// 		JDialog dialog = new JDialog(parentFrame, "게시글 목록", true);
// 		dialog.setSize(400, 300);
// 		dialog.setLocationRelativeTo(this);
// 		// 게시글 목록 모델 가져오기
// 		DefaultListModel<Board.Post> listModel = board.getPostListModel();
// 		// 게시글이 없을 경우 메시지 표시
// 		if (listModel.isEmpty()) {
// 			JLabel emptyLabel = new JLabel("등록된 게시글이 없습니다.", SwingConstants.CENTER);
// 			dialog.add(emptyLabel);
// 		} else {
// 			// 게시글 리스트 보여주기
// 			JList<Board.Post> postJList = new JList<>(listModel);
// 			// 게시글을 더블 클릭하면 상세보기 창 열기
// 			postJList.addMouseListener(new java.awt.event.MouseAdapter() {
// 				@Override
// 				public void mouseClicked(java.awt.event.MouseEvent e) {
// 					if (e.getClickCount() == 2) {
// 						int index = postJList.locationToIndex(e.getPoint());
// 						if (index != -1) {
// 							Board.Post post = listModel.get(index);
// 							openViewDialog(post); // 상세보기 창 띄우기
// 						}
// 					}
// 				}
// 			});
// 			JScrollPane scrollPane = new JScrollPane(postJList);
// 			dialog.add(scrollPane);
// 		}
// 		dialog.setVisible(true); // 창 표시
// 	}
// 	// 2. 게시글 작성 또는 수정 공통 다이얼로그
// 	// post가 null이면 '작성'이고, post가 있으면 '수정'
// 	private void openPostDialog(Board.Post post) {
// 		JDialog dialog = new JDialog(parentFrame, post == null ? "게시글 작성" : "게시글 수정", true);
// 		dialog.setSize(400, 300);
// 		dialog.setLayout(new GridLayout(5, 2));
// 		dialog.setLocationRelativeTo(this);

// 		// TODO: 실제 칼로리 연승님이 구현한 (임시)CalorieManager?에서 받아올 예정
// 		CalorieManager calorieManager = new CalorieManager();
// 		double exerKcal = calorieManager.getExerciseCalories();
// 		double foodKcal = calorieManager.getFoodCalories();
// 		// 칼로리 정보를 보여줄 JLabel 추가
// 		JLabel calorieLabel = new JLabel("운동 칼로리: " + exerKcal + " kcal  |  음식 칼로리: " + foodKcal + " kcal");

// 		// 제목/내용 입력 필드 (수정일 경우 기존 내용 표시)
// 		JTextField titleField = new JTextField(post != null ? post.getTitle() : "");
// 		JTextArea contentArea = new JTextArea(post != null ? post.getContent() : "");

// 		JButton submitBtn = new JButton("완료");
// 		JButton cancelBtn = new JButton("취소");
// 		// 완료 버튼 클릭: 작성 또는 수정 처리-> 메인화면 돌아감
// 		submitBtn.addActionListener(e -> {
// 			String title = titleField.getText();
// 			String content = contentArea.getText();
// 			// 새 게시글 작성
// 			if (post == null) {
// 				board.addPost(title, content);
// 			} else {
// 				// 기존 게시글 수정
// 				post.setTitle(title);
// 				post.setContent(content);
// 				board.updatePost(post.getPostNo(), post.getTitle(), post.getContent());
// 			}
// 			board.saveToFile("board.txt");// 변경된 내용을 파일에 저장
// 			dialog.dispose();// 다이얼로그 닫기
// 		});
// 		// 취소 버튼 : 창 닫기
// 		cancelBtn.addActionListener(e -> dialog.dispose());
// 		// 다이얼로그에 컴포넌트 배치
// 		dialog.add(calorieLabel); // 칼로리 표시 (첫 줄)
// 		dialog.add(new JLabel("")); // 빈 공간
// 		dialog.add(new JLabel("제목:")); // 제목 라벨
// 		dialog.add(titleField); // 제목 작성
// 		dialog.add(new JLabel("내용:")); // 내용 라벨
// 		dialog.add(new JScrollPane(contentArea)); // 내용 작성
// 		dialog.add(submitBtn);
// 		dialog.add(cancelBtn);

// 		dialog.setVisible(true);
// 	}
// 	// 3. 게시글 수정: 게시글을 선택 후 수정 창으로 이동
// 	// 게시글 리스트를 보여주고, 선택한 게시글을 openPostDialog()에 넘겨 수정 화면을 연다
// 	private void openEditDialog() {
// 		// 게시글 리스트 가져오기
// 		DefaultListModel<Board.Post> listModel = board.getPostListModel();
// 		// 없으면 없다는 메시지 출력
// 		if (listModel.isEmpty()) {
// 			// 수정할 게시글이 없으면 알림 메시지 출력
// 			JOptionPane.showMessageDialog(this, "수정할 게시글이 없습니다.");
// 			return;
// 		}
// 		// 게시글 리스트 보여주고 선택
// 		JList<Board.Post> postJList = new JList<>(listModel);
// 		int result = JOptionPane.showConfirmDialog(this, new JScrollPane(postJList), "수정할 게시글 선택",
// 				JOptionPane.OK_CANCEL_OPTION);

// 		if (result == JOptionPane.OK_OPTION) {
// 			Board.Post selected = postJList.getSelectedValue();
// 			if (selected != null) {
// 				int postNo = selected.getPostNo(); // 실제 게시글 번호 가져오기
// 				openPostDialog(selected); // 수정 창 열기
// 			} else {
// 				JOptionPane.showMessageDialog(this, "게시글을 선택해주세요.");
// 			}
// 		}
// 	}
// 	// 4. 게시글 삭제: 게시글 선택 후 삭제
// 	private void openDeleteDialog() {
// 		DefaultListModel<Board.Post> listModel = board.getPostListModel();
// 		if (listModel.isEmpty()) {
// 			JOptionPane.showMessageDialog(this, "삭제할 게시글이 없습니다.");
// 			return;
// 		}
// 		JList<Board.Post> postJList = new JList<>(listModel);
// 		int result = JOptionPane.showConfirmDialog(this, new JScrollPane(postJList), "삭제할 게시글 선택",
// 				JOptionPane.OK_CANCEL_OPTION);
// 		if (result == JOptionPane.OK_OPTION) {
// 			Board.Post selected = postJList.getSelectedValue(); // 선택된 게시글 객체 가져오기
// 			if (selected != null) {
// 				int postNo = selected.getPostNo(); // 실제 게시글 번호
// 				board.deletePost(postNo); // 정확한 게시글 삭제
// 				board.saveToFile("board.dat"); // 변경사항 저장
// 				JOptionPane.showMessageDialog(this, "게시글이 삭제되었습니다.");
// 			} else {
// 				JOptionPane.showMessageDialog(this, "게시글을 선택해주세요.");
// 			}
// 		}
// 	}
// 	// 게시글 상세보기 창
// 	private void openViewDialog(Board.Post post) {
// 		JDialog viewDialog = new JDialog(parentFrame, "게시글 보기", true);
// 		viewDialog.setSize(400, 300);
// 		viewDialog.setLayout(new GridLayout(4, 1));
// 		viewDialog.setLocationRelativeTo(this);

// 		JLabel titleLabel = new JLabel("제목: " + post.getTitle()); // 제목 표시
// 		JTextArea contentArea = new JTextArea(post.getContent()); // 내용 표시
// 		contentArea.setEditable(false);

// 		JScrollPane scrollPane = new JScrollPane(contentArea);

// 		JButton closeButton = new JButton("닫기");
// 		closeButton.addActionListener(e -> viewDialog.dispose()); // 닫기 버튼

// 		viewDialog.add(titleLabel);
// 		viewDialog.add(scrollPane);
// 		viewDialog.add(closeButton);

// 		viewDialog.setVisible(true);
// 	}
// 	public static void main(String[] args) {
// 		Board board = new Board(); // 게시글 관리 객체 생성
// 		board.loadFromFile("board.dat"); // 파일에서 게시글 불러오기
// 		// new Board_Main(board); // 메인 프레임 실행

// 		JFrame frame = new JFrame("게시판 메인 메뉴");
// 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// 		frame.setSize(400, 300);
// 		frame.setLocationRelativeTo(null);

// 		Board_Main mainPanel = new Board_Main(board, frame);
// 		frame.setContentPane(mainPanel);

// 		frame.setVisible(true);
// 	}
// }

package Board;

import javax.swing.*;
import java.awt.*;

public class Board_Main {

    private Board board;
    private DefaultListModel<Board.Post> listModel;
	private Font font = new Font("맑은고딕", Font.PLAIN, 30);

    public Board_Main(Board board, DefaultListModel<Board.Post> listModel) {
        this.board = board;
        this.listModel = listModel;
    }

    // 게시글 작성 다이얼로그
    public void openPostDialog(Component parent) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "게시물 작성", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        JTextField titleField = new JTextField(40);
        JTextArea contentArea = new JTextArea(10, 40);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

		//폰트 설정
		titleField.setFont(font);
		contentArea.setFont(font);

        JPanel inputPanel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(new JLabel("제목: "), BorderLayout.WEST);
        titlePanel.add(titleField, BorderLayout.CENTER);
        inputPanel.add(titlePanel, BorderLayout.NORTH);
        inputPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("등록");
		// okButton.setOpaque(false);
		// okButton.setBackground((Color.WHITE));
        JButton cancelButton = new JButton("취소");

        okButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();
            if (title.isEmpty() || content.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "제목과 내용을 모두 입력해주세요.");
            } else {
                board.addPostAndUpdateModel(title, content, listModel);
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // 게시글 수정 다이얼로그
    public void openEditDialog(Component parent, int index) {
        Board.Post post = listModel.get(index);

        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "게시물 수정", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        JTextField titleField = new JTextField(post.getTitle(), 40);
        JTextArea contentArea = new JTextArea(post.getContent(), 10, 40);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

		//폰트 설정
		titleField.setFont(font);
		contentArea.setFont(font);

        JPanel inputPanel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(new JLabel("제목: "), BorderLayout.WEST);
        titlePanel.add(titleField, BorderLayout.CENTER);
        inputPanel.add(titlePanel, BorderLayout.NORTH);
        inputPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("수정");
        JButton cancelButton = new JButton("취소");

        okButton.addActionListener(e -> {
            String newTitle = titleField.getText().trim();
            String newContent = contentArea.getText().trim();
            if (newTitle.isEmpty() || newContent.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "제목과 내용을 모두 입력해주세요.");
            } else {
                board.updatePostAndModel(index, newTitle, newContent, listModel);
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // 게시글 삭제 확인 다이얼로그
    public void openDeleteDialog(Component parent, int index) {
        Board.Post post = listModel.get(index);

        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "게시물 삭제", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        JLabel confirmLabel = new JLabel("정말 삭제하시겠습니까?\n제목: " + post.getTitle(), SwingConstants.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton("삭제");
        JButton noButton = new JButton("취소");

        yesButton.addActionListener(e -> {
            board.deletePostAndUpdateModel(index, listModel);
            dialog.dispose();
        });

        noButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        dialog.add(confirmLabel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // 게시글 조회 다이얼로그
    public void openViewDialog(Component parent, Board.Post post) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), post.getTitle(), Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(parent);

        JTextArea contentArea = new JTextArea(post.getContent());
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

		//폰트 설정
		contentArea.setFont(font);

        JScrollPane scrollPane = new JScrollPane(contentArea);
        dialog.add(scrollPane);

        dialog.setVisible(true);		
	}
}	
