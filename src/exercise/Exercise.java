package exercise;

public class Exercise 
{
	private String exerName;//� �̸�
	private double exerKcal;//� �Ҹ� Į�θ�
	
	public Exercise(String exerName, double exerKcal)//�����ڿ��� �Ű������� �޾� ���� �ʱ�ȭ
	{
		this.exerName=exerName;
		this.exerKcal=exerKcal;
	}
	
	//�Ʒ� 4���� �޼ҵ�� Getter�� Setter
	public String getExerName() {
		return exerName;
	}
	public void setExerName(String exerName) {
		this.exerName = exerName;
	}
	public double getExerKcal() {
		return exerKcal;
	}
	public void setExerKcal(double exerKcal) {
		this.exerKcal = exerKcal;
	}
	
	@Override
	public String toString()//toString �������̵�
	{
		return "��̸�: "+exerName+" �д� �Һ�Į�θ�: "+exerKcal;
	}
	
	
	
}
