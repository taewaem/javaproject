package cal.exercise;

import Login.User;
import store.frame.BackgroundPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

import javax.swing.*;

import static UI.frame.CaloriePanel.user;

// JFrame과 ActionListener를 상속받은 ExercisePanel 클래스
public class ExercisePanel extends JPanel {

	// 선택된 운동의 이름과 분당 칼로리를 저장하는 멤버 변수
	private static String thisExerName = "없습니다";
	private static double thisExerkcal = 0.0;

	private double totalCalories = 0.0;

	// 현재 운동 종류를 나타내는 List
	private List<Exercise> exerList = ExerciseList.getAllExercise();

	// 운동 정보를 표시할 JLabel (오른쪽 패널에 표시)
	private JLabel exerciseInfoLabel;
	private JLabel exerciseImageLabel;

	private BackgroundPanel backgroundPanel;

	// 기본 생성자
	public ExercisePanel()
	{

		backgroundPanel = new BackgroundPanel();

		setSize(800,600); // 화면 크기 설정
		setLayout(new BorderLayout()); // 보더 레이아웃 설정
		// 이 화면의 제목을 설정하는 JLabel
		JLabel titleLabel = new JLabel();
		titleLabel.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 17)); // 폰트 설정
		titleLabel.setHorizontalAlignment(JLabel.CENTER); // 가로 가운데 정렬
		titleLabel.setText("운동 화면"); // 제목 텍스트 설정

		// 운동을 선택할 컴포넌트를 담을 패널
		JPanel mainExerPanel = new JPanel();
		mainExerPanel.setLayout(new GridLayout(1, 2, 10, 10)); // 가로로 두 개의 패널을 나누기
		mainExerPanel.setOpaque(false);

		// 왼쪽 패널: 운동 이미지 버튼을 나열할 곳
		JPanel selectExerPanel = new JPanel(new GridBagLayout());
		selectExerPanel.setBackground(Color.WHITE);

		// 오른쪽 패널: 선택된 운동 정보(운동 이름, 칼로리)를 표시
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout()); // 정보는 가로로 표시
		infoPanel.setOpaque(false);
		// 운동 정보를 띄울 JLabel
		exerciseInfoLabel = new JLabel("<html><h1>운동을 선택하세요!</h1><br><h2>분당 소모 칼로리: 0 Kcal</h2></html>");
		exerciseInfoLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		exerciseInfoLabel.setHorizontalAlignment(JLabel.CENTER); // 가운데 정렬

		// 운동 이미지 표시용 JLabel
		exerciseImageLabel = new JLabel();
		exerciseImageLabel.setHorizontalAlignment(JLabel.CENTER); // 이미지 가로 가운데 정렬

		// 운동 시간 입력 및 계산 버튼 패널
		JPanel inputTimePanel = new JPanel();
		inputTimePanel.setLayout(new FlowLayout()); // FlowLayout 사용
		inputTimePanel.setOpaque(false);

		// 운동 시간을 입력할 텍스트 필드와 버튼
		JTextField timeInputField = new JTextField(5); // 운동 시간 입력 텍스트 필드
		JButton calculateButton = new JButton("운동 시간 입력");

		// 운동 시간 입력 후 클릭하면 소모 칼로리 계산
		calculateButton.addActionListener(e -> {
			try {
				// 입력된 시간 (분)
				int timeInMinutes = Integer.parseInt(timeInputField.getText());

				totalCalories += thisExerkcal * timeInMinutes;
				System.out.println("총 소모된 칼로리: " + totalCalories);
				user.setTotalExerKcal(totalCalories);

				// 시간에 따른 칼로리 소모 계산
				JOptionPane.showMessageDialog(this, thisExerName + " 운동을 " + timeInMinutes + "분 동안 했을 때\n소모된 칼로리: " + thisExerkcal * timeInMinutes + " Kcal");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "운동 시간을 정확하게 입력해주세요.");
			}
		});

		// 입력 시간 패널에 텍스트 필드와 버튼 추가
		inputTimePanel.add(new JLabel("운동 시간 (분):"));
		inputTimePanel.add(timeInputField);
		inputTimePanel.add(calculateButton);

		// 오른쪽 패널에 JLabel과 운동 시간 입력 패널 추가
		JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(exerciseImageLabel, BorderLayout.CENTER);
		rightPanel.add(exerciseInfoLabel, BorderLayout.NORTH);
		rightPanel.add(inputTimePanel, BorderLayout.SOUTH);

		// 스크롤 기능을 위한 JScrollPane
		JScrollPane inputExerTypeScroll = new JScrollPane(selectExerPanel);
		inputExerTypeScroll.getVerticalScrollBar().setUnitIncrement(12); // 스크롤 속도 조절
		inputExerTypeScroll.setPreferredSize(new Dimension(800, 400)); // 스크롤 페인 크기 설정
		inputExerTypeScroll.setOpaque(false);

		// 운동 종류 리스트를 가져와서 버튼 생성
		for (int i = 0; i < exerList.size(); i++)
		{
			// GridBagConstraints 설정 (버튼 위치와 크기 조정)
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0; // 가로 위치 고정
			gbc.gridy = i; // 세로 위치 변경
			gbc.weightx = 1; // 남는 공간의 가로 차지 비율
			gbc.weighty = 0.5; // 세로 차지 비율
			gbc.fill = GridBagConstraints.HORIZONTAL; // 가로	로 공간을 채우기
			gbc.insets = new Insets(5, 5, 5, 5); // 버튼 간의 여백 설정

			// 운동 객체 가져오기
			Exercise exercise = exerList.get(i);

			// 운동 아이콘 이미지 설정
			URL url = this.getClass().getResource("/cal/Images/" + exercise.getExerName() + ".png");
			ImageIcon icon = new ImageIcon(url);
			Image img = icon.getImage();
			Image fixImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // 이미지 크기 조정
			ImageIcon fixIcon = new ImageIcon(fixImg);

			// 운동 선택 버튼 생성
			JButton selectButton = new JButton(exercise.getExerName(), fixIcon);
			selectButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15)); // 버튼 테두리 설정
			selectButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서 설정
			selectButton.setBorderPainted(false);
			selectButton.setContentAreaFilled(false);
			selectButton.setOpaque(false);

			// 버튼 클릭 시 운동 정보 표시
			selectButton.addActionListener(e -> {
				String exerName = exercise.getExerName(); // 운동 이름
				double exerKcal = exercise.getExerKcal(); // 분당 칼로리

				// 오른쪽 패널에 운동 정보 갱신
				String infoText = "<html><h1>" + exerName + " 운동</h1><br><h2>분당 소모 칼로리: " + exerKcal + " Kcal</h2></html>";
				exerciseInfoLabel.setText(infoText); // JLabel 텍스트 업데이트

				// 운동 이미지 업데이트
				URL imageUrl = this.getClass().getResource("/cal/Images/" + exerName + ".png");
				ImageIcon exerImageIcon = new ImageIcon(imageUrl);
				Image exerImage = exerImageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				exerciseImageLabel.setIcon(new ImageIcon(exerImage));

				// 선택된 운동 정보 저장
				thisExerName = exerName;
				thisExerkcal = exerKcal;
			});

			// 생성한 버튼을 selectExerPanel에 추가
			selectExerPanel.add(selectButton, gbc);
		}

		// 메인 패널에 왼쪽 패널과 오른쪽 패널 추가
		mainExerPanel.add(inputExerTypeScroll);
		mainExerPanel.add(rightPanel);

		// 메인 화면에 각 컴포넌트 추가
		this.add(titleLabel, BorderLayout.NORTH); // 제목 추가
		this.add(mainExerPanel, BorderLayout.CENTER); // 운동 선택 패널 추가

		this.setVisible(true); // 화면을 보이게 설정
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // 기본적인 페인팅 작업
		backgroundPanel.paintBackground(g, this); // 배경 그리기
	}

}