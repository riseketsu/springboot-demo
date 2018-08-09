/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.rskt.demo.utils.DateUtils;

/**
 * Class:ThreadCyclicBarrierTest.java<br>
 *
 * Class Summary：<br>
 * 
 * Class Feature:<br>
 *
 * @Author lishijie
 * @CreateDate 2018-08-07
 *
 */
public class ThreadCyclicBarrierTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Main线程 开始执行.");
		final CyclicBarrier barrier = new CyclicBarrier(5);
		
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
						try {
							barrier.await();
						} catch (InterruptedException | BrokenBarrierException e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			child.start();
		}
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("Main线程 执行完毕.");
		System.exit(0);
	}

}
