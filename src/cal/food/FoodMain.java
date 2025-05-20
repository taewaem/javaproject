package cal.food;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cal.exercise.ExerciseMain;

//JFrame과 인터페이스 ActionListener를 상속받은 클래스
public class FoodMain extends JFrame implements ActionListener
{
	//음식의 이름과 1인분의 칼로리를 저장하는 static 변수방(외부에서 참조하시길)
	public static String foodName="없습니다";
	public static double foodkcal=0.0;
	
	//음식의 이름과 1인분의 칼로리를 저장하는 멤버 변수방
	private static String thisFoodName="없습니다";
	private static double thisFoodkcal=0.0;
	
	//현재 음식의 종류를 나타내는 List
    List<Food> foodList = new ArrayList<Food>();
    
    //음식을 몇인분 먹었는지 받는 변수방
    public int amountFood=0;
	
    //이벤트리스너 메소드가 사용할 수 있게 멤버변수로 선언한 JScrollPane
    JScrollPane inputFoodTypeScroll;
    
    //이벤트리스너 메소드가 사용할 수 있게 멤버변수로 선언한 JTextField
    JTextField foodAmountField;
    
    //다른 메소드가 이용할 수 있게 멤버변수로 선언한 JLabel
    JLabel outputFoodLabel;
    
    //값을 입력하는 버튼의 텍스트와 현재 값을 입력받는 상태인지 점검하는 boolean변수
    String inputButtonText="입력";
	boolean isInputMode=false;
    
	//뒤로가는 버튼의 텍스트
    String backButtonText="뒤로 가기";
	
    //기본 생성자
	public FoodMain()
	{		
		this.setTitle("음식 선택");//frame의 타이틀
		this.setSize(450,800);//9(가로):16(세로)비율
		this.setLocationRelativeTo(null);//위치설정을 null로 하면 위치를 중앙으로 잡는다.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//닫기를 누르면 프로그램이 종료된다.
		this.setLayout(new BorderLayout());//보더레이아웃으로 설정
		
		//이 Frame의 제목을 적는 JLabel
		JLabel titleLabel = new JLabel();
		titleLabel.setFont(new Font("맑은 고딕",Font.CENTER_BASELINE,17));//폰트 종류, 위치, 크기 설정
		titleLabel.setHorizontalAlignment(JLabel.CENTER);//가로 가운데 정렬
		titleLabel.setText("식단 화면");//적을 내용
		
		//음식의 종류와 식사량을 입력받는 컴포넌트와 출력하는 컴포넌트의 집합 JPanel
		JPanel mainFoodPanal = new JPanel();
	
		//좌->우, 상->하로 자동으로 채우는 FlowLayout을 설정
		mainFoodPanal.setLayout(new FlowLayout());
		
		//패널의 범위를 알기위한 색지정
		mainFoodPanal.setOpaque(true);
		mainFoodPanal.setBackground(Color.blue);
		
		//입력받은 정보를 출력하는 JLabel
	    outputFoodLabel = new JLabel();
	    outputFoodLabel.setBounds(75, 0, 300, 100);//이 부모 컴포넌트를 기준으로 하는 x,y 좌표와 가로,세로 크기 설정
	    outputFoodLabel.setFont(new Font("맑은 고딕",Font.CENTER_BASELINE,12));//폰트 종류, 위치, 크기 설정
	    outputFoodLabel.setHorizontalAlignment(JLabel.CENTER);//가로 가운데 정렬
	    rewriteTextoutputLabel();//출력문 작성 메서드
	    
	    //범위를 알아보기 위한 색지정
	    outputFoodLabel.setOpaque(true);
	    outputFoodLabel.setBackground(Color.green);
		
		//음식의 종류를 입력할 버튼이 들어가는 JPanel
	    JPanel selectFoodPanel = new JPanel();//어쩌면 이 패널의 객체 자체를 만드는 클래스를 따로 등록해야 할지도?
		selectFoodPanel.setLayout(new GridBagLayout());//GridBagLayout으로 패널의 레이아웃 설정
		
		//setPreferredSize(new Dimension(550, 1000));
		
		//List에 데이터 입력
		foodList.add(new Food("후라이드치킨", 600));
		foodList.add(new Food("양념치킨", 700));
		foodList.add(new Food("패퍼로니피자", 300));
		foodList.add(new Food("불고기피자", 320));
		foodList.add(new Food("씨푸드피자", 270));
		foodList.add(new Food("하와이안피자", 230));
		foodList.add(new Food("소고기버거", 250));
		foodList.add(new Food("소고기치즈버거", 350));
		foodList.add(new Food("치킨버거", 500));
		foodList.add(new Food("새우버거", 500));
		
		//입력할 수 있는 음식의 종류를 스크롤 가능하게 해주는 JScrollPane
		inputFoodTypeScroll = new JScrollPane(selectFoodPanel);
		
		//스크롤 페인보다 무조건 내부 컴포넌트가 커야한다.
		//이 스크롤 페인 안에 크기가 지정되지 않은 패널(gridbaglayout)이 있고, 이 패널에 버튼을 추가 함으로서, 패널의 크기가 스크롤 페인의 크기보다 커졌다.
		inputFoodTypeScroll.setPreferredSize(new Dimension(300, 100));
		
		//inputFoodTypeScroll에 selectFoodPanel를 등록하여, 스크롤 기능 추가.
	    inputFoodTypeScroll.setViewportView(selectFoodPanel);
	    
	    //항상 스크롤이 보이게
	    inputFoodTypeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		for (int i = 0; i < foodList.size(); i++)//foodList 처음부터 끝까지
		{
			//gridlayout에 들어갈 컴포넌트의 위치와 크기를 조정하기 위한 GridBagConstraints
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx=0;//가로 열은 모두 인덱스 0으로 고정(즉, 1열)
			gbc.gridy=i;//세로 열은 i값으로 배당하므로 한칸씩 아래로 내려간다.
			gbc.weightx =1;//남는 공간의 가로 차지 비율
			gbc.weightx=0.5;//남는 공간의 세로 차지 비율
			gbc.fill = GridBagConstraints.HORIZONTAL;//남는 패널의 공간은 가로만 채운다.
			gbc.insets = new Insets(5,5,5,5);
			
			Food food = foodList.get(i);//i에 해당하는 인덱스의 Food 객체를 가져온다.
			//음식을 선택할 수 있는 Panel에 들어갈 버튼
			JButton selectButton = new JButton(food.getFoodName());
			selectButton.addActionListener(this);//만든 버튼 객체에 ActionListener 등록
			
			selectFoodPanel.add(selectButton,gbc);//selectFoodPanel 방금 만든 버튼 추가
		}		
		
		//식사량을 텍스트로 입력받는 JTextField
		foodAmountField = new JTextField("이곳에 몇 인분을 드셨는지 숫자로 적어주세요!");
		foodAmountField.setBounds(0, -200, 300, 100);//이 부모 컴포넌트를 기준으로 하는 x,y 좌표와 가로,세로 크기 설정
		
		//입출력 메인 패널에 하위 컴포넌트 입력
		mainFoodPanal.add(outputFoodLabel);
		mainFoodPanal.add(inputFoodTypeScroll);
		mainFoodPanal.add(foodAmountField);
	
		//버튼 기능을 모아놓은 JPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0,2));
		
		//음식의 정보를 입력할 것인지 정하면서, 입력한다면 그 정보를 입력한다는 이벤트를 담는 버튼
		JButton inputButton = new JButton();
		//inputButton.setPreferredSize(new Dimension(150, 75));//크기지정
		inputButton.setText(inputButtonText);//텍스트 지정
		
		
		//뒤로가기 버튼
		JButton backButton = new JButton();//크기지정
		//backButton.setPreferredSize(new Dimension(150, 75));
		backButton.setText(backButtonText);//텍스트 지정
		
		//액션리스너 등록
		backButton.addActionListener(this);
		inputButton.addActionListener(this);
		
		//각 버튼을 패널에 입력
		buttonPanel.add(inputButton);
		buttonPanel.add(backButton);
		
		this.add(titleLabel,BorderLayout.NORTH);//titleLabel을 보더레이아웃 위쪽에 삽입
		this.add(mainFoodPanal,BorderLayout.CENTER);//mainFoodPanal 보더레이아웃 중간에 삽입
		this.add(buttonPanel,BorderLayout.SOUTH);//buttonPanel 보더레이아웃 아래에 삽입
		
		//처음에는 입력을 받지 않는 상태이므로, 입력을 받는 창들을 안보이게
		inputComVisibleSet(false);
		this.setVisible(true);//이 JFrame 객체가 보이게
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e)//버튼을 누르는 등의 이벤트를 호출하면 실행되는 메서드 
	{
		if (e.getSource() instanceof JButton)//받은 매개변수 e가 JButton을 포함한다면?
		{
			JButton eventButton =  (JButton)e.getSource();//받은 매개변수 e를 다운캐스팅하여 새로운 JButton객체(즉, eventButton)에 대입
			
			for (Food food : foodList)//foodList처음부터 끝까지
			{
				if (eventButton.getText().equals(food.getFoodName()))//eventButton의 text가 foodlist의 name중에 있었을 시,
				{
					selectedFood(food);//멤버변수방에 대입하는 메서드
				}
				
			}//없다면, 이 함수는 아무것도 처리하지 않을 것.
			
			if (eventButton.getText().equals(inputButtonText))//그렇다면, 이 버튼의 텍스트가 "입력" 인가?
			{
							
				if (isInputMode==false)//맞다면, 현재 입력을 받지않는 상태인가?
				{					
					foodAmountField.setText("이곳에 몇 인분을 드셨는지 숫자로 적어주세요!");
					
					inputComVisibleSet(true);//보이게 하는 메소드 호출
					
					isInputMode=true;//현재는 입력을 받는 상태이다.
				}
				
				else if (isInputMode==true)//맞다면, 현재 입력을 받는 상태인가?
				{
					//여기에 static 변수방에 값을 입력하는 함수 작성.
					
					try 
					{
						amountFood=Integer.parseInt(foodAmountField.getText());//멤버변수에 있는 식사량에 대입
						setStaticVar(thisFoodName, thisFoodkcal);//스태틱 변수방으로 넘겨버린다.
						rewriteTextoutputLabel();//출력문의 재갱신
					}
					
					catch (Exception ex) 
					{
						//에러발생시 에러메세지 출력
						JOptionPane.showMessageDialog(null, "식사량은 숫자로 입력해 주세요!","에러",JOptionPane.ERROR_MESSAGE);
					}
					
					finally 
					{
						inputComVisibleSet(false);//안보이게 하는 메소드 호출
						isInputMode=false;//현재는 입력을 받지 않는 상태이다.
					}
					
				}
				
			}//없다면, 이 함수는 아무것도 처리하지 않을 것.
			
			if (eventButton.getText().equals(backButtonText))//그렇다면, 이 버튼의 텍스트가 "뒤로가기" 인가?
			{
				if (isInputMode==false)//맞다면, 현재 입력을 받지않는 상태인가?
				{
					//여기에 메인 화면으로 이동하는 메서드를 호출해 주세요.
				}
				
				else if (isInputMode==true)//맞다면, 현재 입력을 받는 상태인가?
				{
					//입력을 취소하는 메서드
					inputComVisibleSet(false);//안보이게 하는 메소드 호출
					isInputMode=false;//현재는 입력을 받지 않는 상태이다.
				}
				
			}//없다면, 이 함수는 아무것도 처리하지 않을 것.
		}
		
	}

	//입력을 담당하는 패널의 두 컴포넌트를 보이게 할지 말지 정하는 메소드
	public void inputComVisibleSet(boolean set)
	{
		//컴포넌트 투명화 재설정
		foodAmountField.setVisible(set);
		inputFoodTypeScroll.setVisible(set);
		
		//화면 재갱신
		this.revalidate();
		this.repaint();
	}
	
	public void selectedFood(Food f)//선택한 음식의 정보를 멤버 변수방에 넘기는 메서드
	{
		thisFoodkcal = f.getFoodKcal();//칼로리 대입
		thisFoodName = f.getFoodName();//이름 대입
	}

	public void setStaticVar(String foodName, double foodKcal)//선택한 음식의 정보를 Static 변수방에 넘기는 메서드
	{
		FoodMain.foodName=foodName;
		FoodMain.foodkcal=foodKcal;
	}
	
	//outputFoodLabel의 갱신 메서드
	public void rewriteTextoutputLabel()
	{
		outputFoodLabel.setText("<html><body><center>"+
				"당신의 최근식사는: "+FoodMain.foodName+"<br>"+
				"식사의 총 칼로리는: "+(FoodMain.foodkcal*amountFood)+"Kcal"+
				"</center></body></html>");//출력문
		
		//화면 재갱신
		this.revalidate();
		this.repaint();
	}
	
		public static void main(String[] args)//테스트 용 Main 입니다.
	{
		FoodMain obj = new FoodMain();
	}


}
