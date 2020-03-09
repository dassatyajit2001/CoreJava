package com.java.demo1;
/**
 *  private static final int JVMTI_THREAD_STATE_ALIVE = 0x0001;
    private static final int JVMTI_THREAD_STATE_TERMINATED = 0x0002;
    private static final int JVMTI_THREAD_STATE_RUNNABLE = 0x0004;
    private static final int JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER = 0x0400;
    private static final int JVMTI_THREAD_STATE_WAITING_INDEFINITELY = 0x0010;
    private static final int JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT = 0x0020;
 * @author satyajitdas
 *
 */
public class MultiThreadingBasics {

	public static void main(String[] args) throws InterruptedException {

		// executingThreadExample();
		printMessageNonSyncronized();
		printMessageSyncronized();

	}

	/**
	 * Method to call non Synchronized methods
	 */
	private static void printMessageNonSyncronized() {
		System.out.println(Thread.currentThread() + " I am main Thread");
		MessageNonSynchronized obj = new MessageNonSynchronized();
		Thread t1 = new Thread(new MessagePrinter(obj, "Hello"));
		Thread t2 = new Thread(new MessagePrinter(obj, "Concurrent"));
		Thread t3 = new Thread(new MessagePrinter(obj, "World"));

		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * method to call Synchronized Message
	 */
	private static void printMessageSyncronized() {
		System.out.println(Thread.currentThread() + " I am main Thread");
		MessageSynchronized obj = new MessageSynchronized();
		Thread t1 = new Thread(new MessagePrinter(obj, "Hello"));
		Thread t2 = new Thread(new MessagePrinter(obj, "Concurrent"));
		Thread t3 = new Thread(new MessagePrinter(obj, "World"));

		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ways to instantiate a thread and call them
	 */
	private static void executingThreadExample() {
		System.out.println(Thread.currentThread() + " I am main Thread");
		Thread t1 = new ExtendsThread();
		t1.start();

		Thread t2 = new Thread(new ImplementsRunnable());

		// t2.setPriority(20000); any range outside the boundaries will throw
		// IllegalThreadStateException
		t2.setPriority(Thread.MAX_PRIORITY);
		t2.start();
		// to check if the thread is alive
		System.out.println(t2.isAlive());

		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// t2.start(); will throw java.lang.IllegalThreadStateException at Runtime.
		// after a thread is
		// terminated it can never be restarted
		System.out.println("Main thread exiting");
	}

}

/**
 * 1st way to create thread implementing Runnable Interface
 * 
 * @author satyajitdas
 *
 */
class ImplementsRunnable implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread() + "  I am in run of ImplementsRunnable");
	}

}

/**
 * 2nd way to create thread extend Thread class
 * 
 * @author satyajitdas
 *
 */
class ExtendsThread extends Thread {

	public void run() {
		System.out.println(Thread.currentThread() + "  I am in run of ExtendsThread");
	}

}

class MessagePrinter implements Runnable {
	MessageNonSynchronized obj = null;
	MessageSynchronized objSync = null;
	String str = null;

	MessagePrinter(MessageNonSynchronized obj, String str) {
		this.obj = obj;
		this.str = str;
	}

	MessagePrinter(MessageSynchronized objSync, String str) {
		this.objSync = objSync;
		this.str = str;
	}

	@Override
	public void run() {
		if (obj != null)
			obj.display(str);
		else
			{
			objSync.display(str);
			objSync.displayWithSyncObject(str);
			}
			
		;
	}

}

class MessageNonSynchronized {
	public void display(String message) {
		System.out.print("**Starting Message-->" + message);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(": End Message***");
	}
}

class MessageSynchronized {
	synchronized public void display(String message) {
		System.out.print("**Starting Message-->" + message);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(": End Message***");
	}

	public void displayWithSyncObject(String message) {
		synchronized (this) {
			System.out.print("**Starting Message-->" + message);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(": End Message***");
		}
	}
}