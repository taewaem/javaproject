package cal.food;

import Login.User;
import store.frame.BackgroundPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.URL;
import java.util.List;

import static UI.frame.CaloriePanel.user;

public class FoodPanel extends JPanel {

	// 음식 정보 저장용 멤버 변수
	public static String foodName = "없습니다";
	public static double foodkcal = 0.0;

	private static String thisFoodName = "없습니다";
	private static double thisFoodkcal = 0.0;

	public static double totalCaloriesBurned = 0.0;
	// 음식 리스트
	private List<Food> foodList = FoodList.getFoodList();

	// 음식 정보를 표시할 JLabel
	private JLabel foodInfoLabel;
	private JLabel foodImageLabel;

	// 총 칼로리 계산
	private double totalCalories = 0.0;

	// 음식의 양을 입력할 텍스트 필드와 버튼
	private JTextField foodAmountField;

	// 버튼을 통한 입력 모드 처리 변수
	private boolean isInputMode = false;
	private BackgroundPanel backgroundPanel;


	// 기본 생성자
	public FoodPanel() {

		backgroundPanel = new BackgroundPanel();
		setLayout(new BorderLayout());
		setSize(800, 600);

		// 타이틀 Label 설정
		JLabel titleLabel = new JLabel();
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setText("음식 화면");

		// main
		JPanel mainfoodInfoPanel = new JPanel();
		mainfoodInfoPanel.setLayout(new GridLayout(1, 2, 10, 10));
		mainfoodInfoPanel.setOpaque(false);
		//왼쪽
		JPanel selectFoodPanel = new JPanel(new GridBagLayout());
		selectFoodPanel.setBackground(Color.WHITE);

		//오른쪽
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.setOpaque(false);
		// 음식 정보를 표시할 JLabel
		foodInfoLabel = new JLabel("<html><h1>음식을 선택하세요!</h1><br><h1>칼로리: 0 Kcal</h1></html>");
		foodInfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		foodInfoLabel.setHorizontalAlignment(JLabel.CENTER);

		// 음식 이미지 표시용 JLabel
		foodImageLabel = new JLabel();
		foodImageLabel.setHorizontalAlignment(JLabel.CENTER); // 가로 가운데 정렬 (이미지 위치를 맞추기 위함)

		// 음식 양 입력 패널
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		inputPanel.setOpaque(false);

		// 음식 양 입력 필드와 버튼
		foodAmountField = new JTextField(5);
		JButton calculateButton = new JButton("음식 양 입력");
//		calculateButton.setPreferredSize(new Dimension(120,30));
//		calculateButton.setBackground(Color.WHITE);
//		calculateButton.setForeground(Color.BLACK);
//		calculateButton.setFont(new Font("휴먼둥근헤드라인",Font.PLAIN,12));
//		calculateButton.setFocusPainted(false);
//		calculateButton.setBorderPainted(false);
		// 버튼 클릭 시 소모 칼로리 계산
		calculateButton.addActionListener(e -> {
			try {
				// 입력된 음식 양 (인분)
				int amount = Integer.parseInt(foodAmountField.getText());

				// 총 칼로리 계산


				totalCalories += thisFoodkcal * amount;
				System.out.println("총 섭취한 칼로리: " + totalCalories);
				user.setTotalFoodKcal(totalCalories);

				JOptionPane.showMessageDialog(this, thisFoodName + "을 " + amount + "인분 먹었을 때\n섭취한 칼로리: " + thisFoodkcal * amount + " Kcal");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "음식 양을 정확하게 입력해주세요.");
			}
		});

		inputPanel.add(new JLabel("음식 양 (인분):"));
		inputPanel.add(foodAmountField);
		inputPanel.add(calculateButton);

		// 오른쪽 패널에 음식 정보 및 이미지 추가
		JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(foodInfoLabel, BorderLayout.NORTH);
		rightPanel.add(foodImageLabel, BorderLayout.CENTER);
		rightPanel.add(inputPanel, BorderLayout.SOUTH);

		// 음식 선택 패널
		JScrollPane inputFoodTypeScroll = new JScrollPane(selectFoodPanel);
		inputFoodTypeScroll.getVerticalScrollBar().setUnitIncrement(12);
		inputFoodTypeScroll.setPreferredSize(new Dimension(800, 400));


		// 음식 목록에서 버튼 생성
		for (int i = 0; i < foodList.size(); i++) {

			// GridBagConstraints 설정 (버튼 위치와 크기 조정)
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0; // 가로 위치 고정
			gbc.gridy = i; // 세로 위치 변경
			gbc.weightx = 1; // 남는 공간의 가로 차지 비율
			gbc.weighty = 0.5; // 세로 차지 비율
			gbc.fill = GridBagConstraints.HORIZONTAL; // 가로로 공간을 채우기
			gbc.insets = new Insets(5, 5, 5, 5); // 버튼 간의 여백 설정

			Food food = foodList.get(i);

			// 음식 아이콘 이미지 설정
			ImageIcon icon = new ImageIcon(getClass().getResource("/cal/Images/" + food.getFoodName() + ".png"));
			Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			icon = new ImageIcon(img);

			// 음식 선택 버튼
			JButton selectButton = new JButton(food.getFoodName(), icon);
			selectButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
			selectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			selectButton.setBorderPainted(false);
			selectButton.setContentAreaFilled(false);
			selectButton.setBackground(new Color(0,0,0,0));
			// 버튼 클릭 시 음식 정보 업데이트
			selectButton.addActionListener(e -> {
				thisFoodName = food.getFoodName();
				thisFoodkcal = food.getFoodKcal();

				// 음식 정보 및 이미지 업데이트

				foodInfoLabel.setText("<html><h2>" + thisFoodName + "</h2><br><h3>칼로리: " + thisFoodkcal + " Kcal</h3></html>");


				// 음식 이미지 업데이트
				ImageIcon foodImageIcon = new ImageIcon(getClass().getResource("/cal/Images/" + thisFoodName + ".png"));
				Image foodImage = foodImageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
				foodImageLabel.setIcon(new ImageIcon(foodImage));


			});

			selectFoodPanel.add(selectButton, gbc);
		}
		// 메인 패널에 레이아웃 추가
		mainfoodInfoPanel.add(inputFoodTypeScroll);
		mainfoodInfoPanel.add(rightPanel);

		// 상단 타이틀 추가
		this.add(titleLabel, BorderLayout.NORTH);
		this.add(mainfoodInfoPanel, BorderLayout.CENTER);

		// 화면 보이기
		this.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // 기본적인 페인팅 작업
		backgroundPanel.paintBackground(g, this); // 배경 그리기
	}
}
