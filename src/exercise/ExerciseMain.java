package exercise;

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
public class ExerciseMain extends JFrame implements ActionListener
{
	//��� �̸��� �д� �Ҹ� Į�θ��� �����ϴ� static ������(�ܺο��� �����Ͻñ�)
	public static String exerName="�����ϴ�";
	public static double exerKcal=0.0;
	
	//������ �̸��� 1�κ��� Į�θ��� �����ϴ� ��� ������
	private static String thisExerName="�����ϴ�";
	private static double thisExerkcal=0.0;
	
	//���� ��� ������ ��Ÿ���� List
	List<Exercise> exerList = new ArrayList<Exercise>();
    
    //��� ��� �ߴ��� �޴� ������
    public int min=0;
	
    //�̺�Ʈ������ �޼ҵ尡 ����� �� �ְ� ��������� ������ JScrollPane
    JScrollPane inputExerTypeScroll;
    
    //�̺�Ʈ������ �޼ҵ尡 ����� �� �ְ� ��������� ������ JTextField
    JTextField exerAmountField;
    
    //�ٸ� �޼ҵ尡 �̿��� �� �ְ� ��������� ������ JLabel
    JLabel outputExerLabel;
    
    //���� �Է��ϴ� ��ư�� �ؽ�Ʈ�� ���� ���� �Է¹޴� �������� �����ϴ� boolean����
    String inputButtonText="�Է�";
	boolean isInputMode=false;
    
	//�ڷΰ��� ��ư�� �ؽ�Ʈ
    String backButtonText="�ڷ� ����";
	
    //�⺻ ������
	public ExerciseMain()
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
		JPanel mainExerPanal = new JPanel();
	
		//��->��, ��->�Ϸ� �ڵ����� ä��� FlowLayout�� ����
		mainExerPanal.setLayout(new FlowLayout());
		
		//�г��� ������ �˱����� ������
		mainExerPanal.setOpaque(true);
		mainExerPanal.setBackground(Color.blue);
		
		//�Է¹��� ������ ����ϴ� JLabel
	    outputExerLabel = new JLabel();
	    outputExerLabel.setBounds(75, 0, 300, 100);//�� �θ� ������Ʈ�� �������� �ϴ� x,y ��ǥ�� ����,���� ũ�� ����
	    outputExerLabel.setFont(new Font("���� ���",Font.CENTER_BASELINE,12));//��Ʈ ����, ��ġ, ũ�� ����
	    outputExerLabel.setHorizontalAlignment(JLabel.CENTER);//���� ��� ����
	    rewriteTextoutputLabel();//��¹� �ۼ� �޼���
	    
	    //������ �˾ƺ��� ���� ������
	    outputExerLabel.setOpaque(true);
	    outputExerLabel.setBackground(Color.green);
		
		//������ ������ �Է��� ��ư�� ���� JPanel
	    JPanel selectExerPanel = new JPanel();//��¼�� �� �г��� ��ü ��ü�� ����� Ŭ������ ���� ����ؾ� ������?
		selectExerPanel.setLayout(new GridBagLayout());//GridBagLayout���� �г��� ���̾ƿ� ����
		
		//setPreferredSize(new Dimension(550, 1000));
		
		//List�� ������ �Է�
		exerList.add(new Exercise("�޸���", 8.6));
		exerList.add(new Exercise("�ȱ�", 2.4));
		exerList.add(new Exercise("��ܿ���������", 8));
		exerList.add(new Exercise("�ٳѱ�", 10));
		exerList.add(new Exercise("��", 5));
		exerList.add(new Exercise("������Ÿ��", 4.7));
		exerList.add(new Exercise("��������Ű��", 4));
		exerList.add(new Exercise("�ȱ������", 7));
		exerList.add(new Exercise("����Ʈ", 8));
		exerList.add(new Exercise("����", 7));
		
		//�Է��� �� �ִ� ������ ������ ��ũ�� �����ϰ� ���ִ� JScrollPane-250514���� �۵�����.
		inputExerTypeScroll = new JScrollPane(selectExerPanel);
		
		//��ũ�� ���κ��� ������ ���� ������Ʈ�� Ŀ���Ѵ�.
		//�� ��ũ�� ���� �ȿ� ũ�Ⱑ �������� ���� �г�(gridbaglayout)�� �ְ�, �� �гο� ��ư�� �߰� �����μ�, �г��� ũ�Ⱑ ��ũ�� ������ ũ�⺸�� Ŀ����.
		inputExerTypeScroll.setPreferredSize(new Dimension(300, 100));
		
		//inputexerTypeScroll�� selectexerPanel�� ����Ͽ�, ��ũ�� ��� �߰�.
	    inputExerTypeScroll.setViewportView(selectExerPanel);
	    
	    //�׻� ��ũ���� ���̰�
	    inputExerTypeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		for (int i = 0; i < exerList.size(); i++)//exerList ó������ ������
		{
			//gridlayout�� �� ������Ʈ�� ��ġ�� ũ�⸦ �����ϱ� ���� GridBagConstraints
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx=0;//���� ���� ��� �ε��� 0���� ����(��, 1��)
			gbc.gridy=i;//���� ���� i������ ����ϹǷ� ��ĭ�� �Ʒ��� ��������.
			gbc.weightx =1;//���� ������ ���� ���� ����
			gbc.weightx=0.5;//���� ������ ���� ���� ����
			gbc.fill = GridBagConstraints.HORIZONTAL;//���� �г��� ������ ���θ� ä���.
			gbc.insets = new Insets(5,5,5,5);
			
			Exercise exercise = exerList.get(i);//i�� �ش��ϴ� �ε����� exer ��ü�� �����´�.
			//������ ������ �� �ִ� Panel�� �� ��ư
			JButton selectButton = new JButton(exercise.getExerName());
			selectButton.addActionListener(this);//���� ��ư ��ü�� ActionListener ���
			
			selectExerPanel.add(selectButton,gbc);//selectExerPanel ��� ���� ��ư �߰�
		}		
		
		//�Ļ緮�� �ؽ�Ʈ�� �Է¹޴� JTextField
		exerAmountField = new JTextField("�̰��� �� �� ��ϼ̴��� ���ڷ� �����ּ���!");
		exerAmountField.setBounds(0, -200, 300, 100);//�� �θ� ������Ʈ�� �������� �ϴ� x,y ��ǥ�� ����,���� ũ�� ����
		
		//����� ���� �гο� ���� ������Ʈ �Է�
		mainExerPanal.add(outputExerLabel);
		mainExerPanal.add(inputExerTypeScroll);
		mainExerPanal.add(exerAmountField);
	
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
		this.add(mainExerPanal,BorderLayout.CENTER);//mainExerPanal �������̾ƿ� �߰��� ����
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
			
			for (Exercise exercise : exerList)//exerListó������ ������
			{
				if (eventButton.getText().equals(exercise.getExerName()))//eventButton�� text�� exerlist�� name�߿� �־��� ��,
				{
					selectedExer(exercise);//��������濡 �����ϴ� �޼���
				}
				
			}//���ٸ�, �� �Լ��� �ƹ��͵� ó������ ���� ��.
			
			if (eventButton.getText().equals(inputButtonText))//�׷��ٸ�, �� ��ư�� �ؽ�Ʈ�� "�Է�" �ΰ�?
			{
							
				if (isInputMode==false)//�´ٸ�, ���� �Է��� �����ʴ� �����ΰ�?
				{					
					exerAmountField.setText("�̰��� �� �� ��ϼ̴��� ���ڷ� �����ּ���!");
					
					inputComVisibleSet(true);//���̰� �ϴ� �޼ҵ� ȣ��
					
					isInputMode=true;//����� �Է��� �޴� �����̴�.
				}
				
				else if (isInputMode==true)//�´ٸ�, ���� �Է��� �޴� �����ΰ�?
				{
					//���⿡ static �����濡 ���� �Է��ϴ� �Լ� �ۼ�.
					
					try 
					{
						min=Integer.parseInt(exerAmountField.getText());//��������� �ִ� �Ļ緮�� ����
						setStaticVar(thisExerName, thisExerkcal);//����ƽ ���������� �Ѱܹ�����.
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
		exerAmountField.setVisible(set);
		inputExerTypeScroll.setVisible(set);
		
		//ȭ�� �簻��
		this.revalidate();
		this.repaint();
	}
	
	public void selectedExer(Exercise ex)//������ ������ ������ ��� �����濡 �ѱ�� �޼���
	{
		thisExerkcal = ex.getExerKcal();//Į�θ� ����
		thisExerName = ex.getExerName();//�̸� ����
	}

	public void setStaticVar(String exerName, double exerKcal)//������ ������ ������ Static �����濡 �ѱ�� �޼���
	{
		ExerciseMain.exerName=exerName;
		ExerciseMain.exerKcal=exerKcal;
	}
	
	//outputexerLabel�� ���� �޼���
	public void rewriteTextoutputLabel()
	{
		outputExerLabel.setText("<html><body><center>"+
				"����� �ֱٿ��: "+ExerciseMain.exerName+"<br>"+
				"�� �Ҹ��� Į�θ���: "+(ExerciseMain.exerKcal*min)+"Kcal"+
				"</center></body></html>");//��¹�
		
		//ȭ�� �簻��
		this.revalidate();
		this.repaint();
	}

}
