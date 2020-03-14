package io.connector;

import java.awt.Color;

public class Player {
	private String name;
	private Color color;
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Object getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
