package com.java.demo1;

public class ProducerConsumerExample {

	public static void main(String[] args) {
		MessageHolder obj = new MessageHolder();
		Thread consumer = new Thread(new Consumer(obj));
		consumer.start();
		Thread producer = new Thread(new Producer(obj));
		producer.start();
	}
}

/**
 * Producer class that generates and stores data
 * @author satyajitdas
 *
 */
class Producer implements Runnable {
	MessageHolder obj = null;

	Producer(MessageHolder obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			obj.produce(i);
		}
	}
}

/**
 * Consumer class that reads and removes data
 * @author satyajitdas
 *
 */
class Consumer implements Runnable {
	MessageHolder obj = null;

	Consumer(MessageHolder obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			obj.consume();
		}
	}}

/**
 * Message Holder class on which producer and consumer put and read data
 * respectively
 * 
 * @author satyajitdas
 *
 */
class MessageHolder {
	int value = Integer.MIN_VALUE;

	public void produce(int x) {
		synchronized (this) {
			while (value != Integer.MIN_VALUE) {
				try {
					System.out.println("Nothing to produce so waiting");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (value == Integer.MIN_VALUE) {
				value = x;
				System.out.println("Produced value->" + value);
			}
			notifyAll();
		}
	}

	/**
	 * reads and removes data
	 */
	public void consume() {
		synchronized (this) {
			while (value == Integer.MIN_VALUE) {
				try {
					System.out.println("Nothing to consume so waiting");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (value != Integer.MIN_VALUE) {
				System.out.println("Consumed Value->" + value);
				value = Integer.MIN_VALUE;
			}
			notifyAll();
		}
	}

}
