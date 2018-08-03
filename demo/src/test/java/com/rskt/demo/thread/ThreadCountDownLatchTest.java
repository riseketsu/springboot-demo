/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.thread;

import java.util.concurrent.CountDownLatch;

import com.rskt.demo.utils.DateUtils;

/**
 * Class:ThreadCountDownLatchTest.java<br>
 *
 * Class Summary：<br>
 * 
 * Class Feature:<br>
 *
 * @Author lishijie
 * @CreateDate 2018-08-07
 *
 */
public class ThreadCountDownLatchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 1、 创建CountDownLatch 对象， 设定需要计数的子线程数目
		final CountDownLatch latch = new CountDownLatch(5);

		System.out.println("Main线程 开始执行.");
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
						
						// 2、子线程执行完毕，计数减1
						latch.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			child.start();
		}

		System.out.println("等待子线程执行完毕...");
		//3、 当前线程挂起等待 
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main线程 执行完毕.");
		System.exit(0);
	}

}
