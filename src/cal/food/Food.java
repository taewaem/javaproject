package cal.food;

public class Food 
{

	private String foodName;//음식 이름
	private double foodKcal;//음식 칼로리
	
	public Food(String foodName, double foodKcal)//생성자에서 매개변수를 받아 변수 초기화
	{
		this.foodName=foodName;
		this.foodKcal=foodKcal;
	}
	
	//아래 4개의 메소드는 Getter와 Setter
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
	public String toString()//toString 오버라이딩
	{
		return "음식 이름: "+foodName+" 음식의 1인분 당 칼로리: "+foodKcal;
	}
	
	
	
}
