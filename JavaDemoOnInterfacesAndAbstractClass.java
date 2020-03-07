package com.java.demo1;

public class JavaDemoOnInterfacesAndAbstractClass {

	public static void main(String[] args) {
		//example to type inference
		//Parent method reference can call to overridden methods in child class
		var o1 = getObj(1);
		var o2 = getObj(2);
		var o3 = getObj(3);
		o1.f1();
		o2.f1();
		o3.f1();

		
		I1 obj = new Child();
		obj.f1();
		obj.f1(4);
		obj.f3();
		
	}

	public static Parent getObj(int x) {
		switch (x) {
		case 1: {
			return new Parent();
		}
		case 2: {
			return new Child1();
		}
		case 3: {
			return new Child2();
		}

		default: {
			return new Parent();
		}
		}
	}
}

class Parent {
	public void f1() {
		System.out.println("inside parent");
	}
}

class Child1 extends Parent {
	public void f1() {
		System.out.println("inside child1");
	}
}

class Child2 extends Parent {
	public void f1() {
		System.out.println("inside child2");
	}

}

interface I1 {
	int x = 5;

	default void f1() {
		System.out.println("inside default f1" + x);
	}

	/**
	 * default method which can be overridden. Default needs a body
	 * 
	 * @param y
	 */
	default void f1(int y) {
		System.out.println("inside default of Interface f1 " + y);
	}

	/**
	 * method can be made private but need to have a body
	 */
	private void f2() {
		System.out.println("inside private non default f2 ");
	}

	void f3();

}

/**
 * class implements Interface
 * 
 * @author satyajitdas
 *
 */
class Child implements I1 {

	/**
	 * non Default method overridden
	 */
	@Override
	public void f3() {
		System.out.println("in overridden non default f3 ");
	}

	/**
	 * Default overridden
	 */
	@Override
	public void f1() {
		System.out.println("inside default overridden f1 of Child " + x);
	}

	// @Override gives error in compile time
	// non visible
	private void f2() {
		System.out.println("inside private non default f2 ");
	}

}

//If the abstract class doesnt implement all the methods then the class has to be abstract
abstract class AbstractClassParent implements I1{
	
}