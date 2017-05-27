package com.tracybrother.test;

public class A extends SuperA {
	public void testA(){
		System.out.println("I'm aaaaaaaaa");
	}
	public static void main(String[] args) {
		SuperA a = new A();
		a.testA();
	}
}
