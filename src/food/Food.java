package food;

public class Food 
{

	private String foodName;//���� �̸�
	private double foodKcal;//���� Į�θ�
	
	public Food(String foodName, double foodKcal)//�����ڿ��� �Ű������� �޾� ���� �ʱ�ȭ
	{
		this.foodName=foodName;
		this.foodKcal=foodKcal;
	}
	
	//�Ʒ� 4���� �޼ҵ�� Getter�� Setter
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public double getFoodKcal() {
		return foodKcal;
	}
	public void setFoodKcal(double foodKcal) {
		this.foodKcal = foodKcal;
	}
	
	@Override
	public String toString()//toString �������̵�
	{
		return "���� �̸�: "+foodName+" ������ 1�κ� �� Į�θ�: "+foodKcal;
	}
	
	
	
}
