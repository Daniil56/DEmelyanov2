package ru.job4j.calculator;

public class MathUtil {
	private static double result = 0;
	
	public static double  add(double first, double second) {
		result = first + second;
		return result;
	}
	public void subtract(double first, double second) {
		this.result = first - second;
	}
	public void div(double first, double second) {
		this.result = (first / second);
	}
	public void multiple(double first, double second) {
		this.result =  first * second;
	}
	public double getResult() {
		return this.result;
	}
}