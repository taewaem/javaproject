package food;

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

//JFrame�� �������̽� ActionListener�� ��ӹ��� Ŭ����
public class FoodMain extends JFrame implements ActionListener
{
	//������ �̸��� 1�κ��� Į�θ��� �����ϴ� static ������(�ܺο��� �����Ͻñ�)
	public static String foodName="�����ϴ�";
	public static double foodkcal=0.0;
	
	//������ �̸��� 1�κ��� Į�θ��� �����ϴ� ��� ������
	private static String thisFoodName="�����ϴ�";
	private static double thisFoodkcal=0.0;
	
	//���� ������ ������ ��Ÿ���� List
    List<Food> foodList = new ArrayList<Food>();
    
    //������ ���κ� �Ծ����� �޴� ������
    public int amountFood=0;
	
    //�̺�Ʈ������ �޼ҵ尡 ����� �� �ְ� ��������� ������ JScrollPane
    JScrollPane inputFoodTypeScroll;
    
    //�̺�Ʈ������ �޼ҵ尡 ����� �� �ְ� ��������� ������ JTextField
    JTextField foodAmountField;
    
    //�ٸ� �޼ҵ尡 �̿��� �� �ְ� ��������� ������ JLabel
    JLabel outputFoodLabel;
    
    //���� �Է��ϴ� ��ư�� �ؽ�Ʈ�� ���� ���� �Է¹޴� �������� �����ϴ� boolean����
    String inputButtonText="�Է�";
	boolean isInputMode=false;
    
	//�ڷΰ��� ��ư�� �ؽ�Ʈ
    String backButtonText="�ڷ� ����";
	
    //�⺻ ������
	public FoodMain()
	{		
		this.setTitle("���� ����");//frame�� Ÿ��Ʋ
		this.setSize(450,800);//9(����):16(����)����
		this.setLocationRelativeTo(null);//��ġ������ null�� �ϸ� ��ġ�� �߾����� ��´�.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�ݱ⸦ ������ ���α׷��� ����ȴ�.
		this.setLayout(new BorderLayout());//�������̾ƿ����� ����
		
		//�� Frame�� ������ ���� JLabel
		JLabel titleLabel = new JLabel();
		titleLabel.setFont(new Font("���� ���",Font.CENTER_BASELINE,17));//��Ʈ ����, ��ġ, ũ�� ����
		titleLabel.setHorizontalAlignment(JLabel.CENTER);//���� ��� ����
		titleLabel.setText("�Ĵ� ȭ��");//���� ����
		
		//������ ������ �Ļ緮�� �Է¹޴� ������Ʈ�� ����ϴ� ������Ʈ�� ���� JPanel
		JPanel mainFoodPanal = new JPanel();
	
		//��->��, ��->�Ϸ� �ڵ����� ä��� FlowLayout�� ����
		mainFoodPanal.setLayout(new FlowLayout());
		
		//�г��� ������ �˱����� ������
		mainFoodPanal.setOpaque(true);
		mainFoodPanal.setBackground(Color.blue);
		
		//�Է¹��� ������ ����ϴ� JLabel
	    outputFoodLabel = new JLabel();
	    outputFoodLabel.setBounds(75, 0, 300, 100);//�� �θ� ������Ʈ�� �������� �ϴ� x,y ��ǥ�� ����,���� ũ�� ����
	    outputFoodLabel.setFont(new Font("���� ���",Font.CENTER_BASELINE,12));//��Ʈ ����, ��ġ, ũ�� ����
	    outputFoodLabel.setHorizontalAlignment(JLabel.CENTER);//���� ��� ����
	    rewriteTextoutputLabel();//��¹� �ۼ� �޼���
	    
	    //������ �˾ƺ��� ���� ������
	    outputFoodLabel.setOpaque(true);
	    outputFoodLabel.setBackground(Color.green);
		
		//������ ������ �Է��� ��ư�� ���� JPanel
	    JPanel selectFoodPanel = new JPanel();//��¼�� �� �г��� ��ü ��ü�� ����� Ŭ������ ���� ����ؾ� ������?
		selectFoodPanel.setLayout(new GridBagLayout());//GridBagLayout���� �г��� ���̾ƿ� ����
		
		//setPreferredSize(new Dimension(550, 1000));
		
		//List�� ������ �Է�
		foodList.add(new Food("�Ķ��̵�ġŲ", 600));
		foodList.add(new Food("���ġŲ", 700));
		foodList.add(new Food("���۷δ�����", 300));
		foodList.add(new Food("�Ұ������", 320));
		foodList.add(new Food("��Ǫ������", 270));
		foodList.add(new Food("�Ͽ��̾�����", 230));
		foodList.add(new Food("�Ұ�����", 250));
		foodList.add(new Food("�Ұ��ġ�����", 350));
		foodList.add(new Food("ġŲ����", 500));
		foodList.add(new Food("�������", 500));
		
		//�Է��� �� �ִ� ������ ������ ��ũ�� �����ϰ� ���ִ� JScrollPane-250514���� �۵�����.
		inputFoodTypeScroll = new JScrollPane(selectFoodPanel);
		
		//��ũ�� ���κ��� ������ ���� ������Ʈ�� Ŀ���Ѵ�.
		//�� ��ũ�� ���� �ȿ� ũ�Ⱑ �������� ���� �г�(gridbaglayout)�� �ְ�, �� �гο� ��ư�� �߰� �����μ�, �г��� ũ�Ⱑ ��ũ�� ������ ũ�⺸�� Ŀ����.
		inputFoodTypeScroll.setPreferredSize(new Dimension(300, 100));
		
		//inputFoodTypeScroll�� selectFoodPanel�� ����Ͽ�, ��ũ�� ��� �߰�.
	    inputFoodTypeScroll.setViewportView(selectFoodPanel);
	    
	    //�׻� ��ũ���� ���̰�
	    inputFoodTypeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		for (int i = 0; i < foodList.size(); i++)//foodList ó������ ������
		{
			//gridlayout�� �� ������Ʈ�� ��ġ�� ũ�⸦ �����ϱ� ���� GridBagConstraints
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx=0;//���� ���� ��� �ε��� 0���� ����(��, 1��)
			gbc.gridy=i;//���� ���� i������ ����ϹǷ� ��ĭ�� �Ʒ��� ��������.
			gbc.weightx =1;//���� ������ ���� ���� ����
			gbc.weightx=0.5;//���� ������ ���� ���� ����
			gbc.fill = GridBagConstraints.HORIZONTAL;//���� �г��� ������ ���θ� ä���.
			gbc.insets = new Insets(5,5,5,5);
			
			Food food = foodList.get(i);//i�� �ش��ϴ� �ε����� Food ��ü�� �����´�.
			//������ ������ �� �ִ� Panel�� �� ��ư
			JButton selectButton = new JButton(food.getFoodName());
			selectButton.addActionListener(this);//���� ��ư ��ü�� ActionListener ���
			
			selectFoodPanel.add(selectButton,gbc);//selectFoodPanel ��� ���� ��ư �߰�
		}		
		
		//�Ļ緮�� �ؽ�Ʈ�� �Է¹޴� JTextField
		foodAmountField = new JTextField("�̰��� �� �κ��� ��̴��� ���ڷ� �����ּ���!");
		foodAmountField.setBounds(0, -200, 300, 100);//�� �θ� ������Ʈ�� �������� �ϴ� x,y ��ǥ�� ����,���� ũ�� ����
		
		//����� ���� �гο� ���� ������Ʈ �Է�
		mainFoodPanal.add(outputFoodLabel);
		mainFoodPanal.add(inputFoodTypeScroll);
		mainFoodPanal.add(foodAmountField);
	
		//��ư ����� ��Ƴ��� JPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0,2));
		
		//������ ������ �Է��� ������ ���ϸ鼭, �Է��Ѵٸ� �� ������ �Է��Ѵٴ� �̺�Ʈ�� ��� ��ư
		JButton inputButton = new JButton();
		//inputButton.setPreferredSize(new Dimension(150, 75));//ũ������
		inputButton.setText(inputButtonText);//�ؽ�Ʈ ����
		
		
		//�ڷΰ��� ��ư
		JButton backButton = new JButton();//ũ������
		//backButton.setPreferredSize(new Dimension(150, 75));
		backButton.setText(backButtonText);//�ؽ�Ʈ ����
		
		//�׼Ǹ����� ���
		backButton.addActionListener(this);
		inputButton.addActionListener(this);
		
		//�� ��ư�� �гο� �Է�
		buttonPanel.add(inputButton);
		buttonPanel.add(backButton);
		
		this.add(titleLabel,BorderLayout.NORTH);//titleLabel�� �������̾ƿ� ���ʿ� ����
		this.add(mainFoodPanal,BorderLayout.CENTER);//mainFoodPanal �������̾ƿ� �߰��� ����
		this.add(buttonPanel,BorderLayout.SOUTH);//buttonPanel �������̾ƿ� �Ʒ��� ����
		
		//ó������ �Է��� ���� �ʴ� �����̹Ƿ�, �Է��� �޴� â���� �Ⱥ��̰�
		inputComVisibleSet(false);
		this.setVisible(true);//�� JFrame ��ü�� ���̰�
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e)//��ư�� ������ ���� �̺�Ʈ�� ȣ���ϸ� ����Ǵ� �޼��� 
	{
		if (e.getSource() instanceof JButton)//���� �Ű����� e�� JButton�� �����Ѵٸ�?
		{
			JButton eventButton =  (JButton)e.getSource();//���� �Ű����� e�� �ٿ�ĳ�����Ͽ� ���ο� JButton��ü(��, eventButton)�� ����
			
			for (Food food : foodList)//foodListó������ ������
			{
				if (eventButton.getText().equals(food.getFoodName()))//eventButton�� text�� foodlist�� name�߿� �־��� ��,
				{
					selectedFood(food);//��������濡 �����ϴ� �޼���
				}
				
			}//���ٸ�, �� �Լ��� �ƹ��͵� ó������ ���� ��.
			
			if (eventButton.getText().equals(inputButtonText))//�׷��ٸ�, �� ��ư�� �ؽ�Ʈ�� "�Է�" �ΰ�?
			{
							
				if (isInputMode==false)//�´ٸ�, ���� �Է��� �����ʴ� �����ΰ�?
				{					
					foodAmountField.setText("�̰��� �� �κ��� ��̴��� ���ڷ� �����ּ���!");
					
					inputComVisibleSet(true);//���̰� �ϴ� �޼ҵ� ȣ��
					
					isInputMode=true;//����� �Է��� �޴� �����̴�.
				}
				
				else if (isInputMode==true)//�´ٸ�, ���� �Է��� �޴� �����ΰ�?
				{
					//���⿡ static �����濡 ���� �Է��ϴ� �Լ� �ۼ�.
					
					try 
					{
						amountFood=Integer.parseInt(foodAmountField.getText());//��������� �ִ� �Ļ緮�� ����
						setStaticVar(thisFoodName, thisFoodkcal);//����ƽ ���������� �Ѱܹ�����.
						rewriteTextoutputLabel();//��¹��� �簻��
					}
					
					catch (Exception ex) 
					{
						//�����߻��� �����޼��� ���
						JOptionPane.showMessageDialog(null, "�Ļ緮�� ���ڷ� �Է��� �ּ���!","����",JOptionPane.ERROR_MESSAGE);
					}
					
					finally 
					{
						inputComVisibleSet(false);//�Ⱥ��̰� �ϴ� �޼ҵ� ȣ��
						isInputMode=false;//����� �Է��� ���� �ʴ� �����̴�.
					}
					
				}
				
			}//���ٸ�, �� �Լ��� �ƹ��͵� ó������ ���� ��.
			
			if (eventButton.getText().equals(backButtonText))//�׷��ٸ�, �� ��ư�� �ؽ�Ʈ�� "�ڷΰ���" �ΰ�?
			{
				if (isInputMode==false)//�´ٸ�, ���� �Է��� �����ʴ� �����ΰ�?
				{
					//���⿡ ���� ȭ������ �̵��ϴ� �޼��带 ȣ���� �ּ���.
				}
				
				else if (isInputMode==true)//�´ٸ�, ���� �Է��� �޴� �����ΰ�?
				{
					//�Է��� ����ϴ� �޼���
					inputComVisibleSet(false);//�Ⱥ��̰� �ϴ� �޼ҵ� ȣ��
					isInputMode=false;//����� �Է��� ���� �ʴ� �����̴�.
				}
				
			}//���ٸ�, �� �Լ��� �ƹ��͵� ó������ ���� ��.
		}
		
	}

	//�Է��� ����ϴ� �г��� �� ������Ʈ�� ���̰� ���� ���� ���ϴ� �޼ҵ�
	public void inputComVisibleSet(boolean set)
	{
		//������Ʈ ����ȭ �缳��
		foodAmountField.setVisible(set);
		inputFoodTypeScroll.setVisible(set);
		
		//ȭ�� �簻��
		this.revalidate();
		this.repaint();
	}
	
	public void selectedFood(Food f)//������ ������ ������ ��� �����濡 �ѱ�� �޼���
	{
		thisFoodkcal = f.getFoodKcal();//Į�θ� ����
		thisFoodName = f.getFoodName();//�̸� ����
	}

	public void setStaticVar(String foodName, double foodKcal)//������ ������ ������ Static �����濡 �ѱ�� �޼���
	{
		FoodMain.foodName=foodName;
		FoodMain.foodkcal=foodKcal;
	}
	
	//outputFoodLabel�� ���� �޼���
	public void rewriteTextoutputLabel()
	{
		outputFoodLabel.setText("<html><body><center>"+
				"����� �ֱٽĻ��: "+FoodMain.foodName+"<br>"+
				"�Ļ��� �� Į�θ���: "+(FoodMain.foodkcal*amountFood)+"Kcal"+
				"</center></body></html>");//��¹�
		
		//ȭ�� �簻��
		this.revalidate();
		this.repaint();
	}
	

}
