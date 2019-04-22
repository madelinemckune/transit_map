package project4;

import java.util.ArrayList;

public class Node {
	//name of the city
	private String startCity;
	private String endCity;
	private String color;
	private String type;
	public boolean onStack;
	public Node parent;
	
	
	// EMPTY CONSTRUCTOR
	public Node() {
		
	}
	
	public Node (String start, String end, String color, String type) {
		this.startCity = start;
		this.endCity = end;
		this.color = color;
		this.type = type;
		this.onStack = false;
	}
	
	

    @Override
	public String toString() {
		return "Node [startCity=" + startCity + ", endCity=" + endCity + ", color=" + color + ", type=" + type + "]";
	}


	public String getStartCity() {
		return startCity;
	}


	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}


	public String getEndCity() {
		return endCity;
	}


	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
   	
	
}
