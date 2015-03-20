package org.snow.test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TestShutdownHook {
	static Timer timer = new Timer("job-timer");
	static AtomicInteger count = new AtomicInteger(0);
	static class CleanWorkThread extends Thread{
		@Override
		public void run() {
			System.out.println("clean some work.");
			timer.cancel();
			try {
				Thread.sleep(2*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new CleanWorkThread());
		System.out.println("main class start ...");
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				count.getAndIncrement();
				System.out.println("doing job "+count);
				if(count.get()==10){
					System.exit(0);
				}
			}
		}, 0, 2*1000);
	}
}
