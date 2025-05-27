package cal.exercise;

public class Exercise 
{
	private String exerName;//운동 이름
	private double exerKcal;//운동 소모 칼로리
	
	public Exercise(String exerName, double exerKcal)//생성자에서 매개변수를 받아 변수 초기화
	{
		this.exerName=exerName;
		this.exerKcal=exerKcal;
	}
	
	//아래 4개의 메소드는 Getter와 Setter
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
	public String toString()//toString 오버라이딩
	{
		return "운동이름: "+exerName+" 분당 소비칼로리: "+exerKcal;
	}
	
	
	
}
