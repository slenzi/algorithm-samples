package org.lenzi.playground.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class QueueTest {

	public static void main(String[] args){
		new QueueTest().doTest();
	}
	
	public QueueTest() {
		
	}
	
	public void doTest(){
		
		doBlockingQueueTest();
		
	}

	public void doBlockingQueueTest(){
		
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(100);
		
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		
		new Thread(producer).start();
		new Thread(consumer).start();
		
		System.out.println("Producer and consumer started");
		
		try {
			Thread.sleep(1000L * 30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Flagging producer to stop...");
		producer.stop();
		
		try {
			Thread.sleep(1000L * 30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Flagging consumer to stop...");
		consumer.stop();
		
		System.out.println("Done");
		
		System.exit(0);
		
	}
	
	/**
	 * Produce elements and add them to the queue
	 * 
	 * @author slenzi
	 */
	private class Producer implements Runnable {

		private BlockingQueue<String> queue = null;
		
		private boolean doRun = true;
		
		public Producer(BlockingQueue<String> queue){
			this.queue = queue;
		}
		
		public void stop(){
			doRun = false;
		}
		
		@Override
		public void run() {
			System.out.println("Producer started");
			long count = 0L;
			try {
				String next = null;
				while(doRun){
					
					next = String.valueOf(count);
					System.out.println("producing to queue " + next);
					queue.add(next);
					count++;
					
					Thread.sleep(500l);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			doRun = true;
			System.out.println("Producer stopped");
		}
		
	}
	
	/**
	 * Consume elements from the queue
	 * 
	 * @author slenzi
	 */
	private class Consumer implements Runnable {

		private BlockingQueue<String> queue = null;
		
		private boolean doRun = true;
		
		public Consumer(BlockingQueue<String> queue){
			this.queue = queue;
		}
		
		public void stop(){
			doRun = false;
		}
		
		@Override
		public void run() {
			System.out.println("Consumer started");
			try {
				String next = null;
				while(doRun){
					
					next = queue.poll(2, TimeUnit.SECONDS);
					
					Thread.sleep(750L);
					
					System.out.println("consuming from queue = " + next);
				
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			doRun = true;
			System.out.println("Consumer stopped.");
		}
		
		
	}
	
}
