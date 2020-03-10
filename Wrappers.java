package com.java.demo1;

public class Wrappers {

	public static void main(String[] args) {
		Boolean b = Boolean.valueOf("dasda");
		System.out.println(b);//prints false
		
		Boolean t = Boolean.valueOf("True");//this is automoxing
		System.out.println(t);//prints false
		boolean b2=t.booleanValue();//this is unboxing
		Boolean b3=true;//autoboxing
		boolean b4=b3;//unboxing		

	}

}
