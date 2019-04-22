package project4;

public class Line {
	public String startCity;
	public String endCity;
	public String color;
	public String type;
	

	public Line(String startCity, String endCity, String color, String type) {
		super();
		this.startCity = startCity;
		this.endCity = endCity;
		this.color = color;
		this.type = type;
	}


	@Override
	public String toString() {
		return "Line [startCity=" + startCity + ", endCity=" + endCity + ", color=" + color + ", type=" + type + "]";
	}
	
	
	
}
