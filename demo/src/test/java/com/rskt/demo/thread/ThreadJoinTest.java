/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.thread;

import java.util.Vector;

import com.rskt.demo.utils.DateUtils;

/**
 * Class:ThreadJoinTest.java<br>
 *
 * Class Summary：<br>
 * 
 * Class Feature:<br>
 *
 * @Author lishijie
 * @CreateDate 2018-08-07
 *
 */
public class ThreadJoinTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main线程 开始执行.");
		Vector<Thread> threads = new Vector<Thread>();
		for (int i = 0; i < 5; i++) {
			Thread child = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String timeStr = DateUtils.getSystemDateTime(null);
						System.out.println(Thread.currentThread().getName() + "  开始执行." + timeStr);
						Thread.sleep(2000);
						timeStr = DateUtils.getSystemDateTime(null);
						System.out.println(Thread.currentThread().getName() + "  执行完毕." + timeStr);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			threads.add(child);
			child.start();
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Main线程 执行完毕.");
		System.exit(0);
	}

}
