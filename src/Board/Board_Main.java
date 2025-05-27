
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