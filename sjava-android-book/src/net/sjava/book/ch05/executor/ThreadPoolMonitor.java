package net.sjava.book.ch05.executor;

import java.util.concurrent.ThreadPoolExecutor;

import android.util.Log;

public class ThreadPoolMonitor implements Runnable {
	static final String CNAME = ThreadPoolMonitor.class.getSimpleName();
	
	private boolean isRunning = true;
	private ThreadPoolExecutor executor;

	public ThreadPoolMonitor(ThreadPoolExecutor executor) {
		this.executor = executor;
	}

	public void shutdown() {
		this.isRunning = false;
	}

	@Override
	public void run() {
		while (isRunning) {
			Log.d(CNAME, String.format("[%d/%d] Ȱ�� ������ �� : %d, "
							+ "�Ϸ�� �½�ũ : %d, "
							+ "�½�ũ : %d, "
							+ "ThreadPoolExecutor ���Ῡ�� : %s, "
							+ "ThreadPoolExecutor�� �½�ũ�� ���Ῡ�� : %s",
							executor.getPoolSize(), 
							executor.getCorePoolSize(),
							executor.getActiveCount(),
							executor.getCompletedTaskCount(),
							executor.getTaskCount(), executor.isShutdown(),
							executor.isTerminated()));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Log.e(CNAME, "����", e);
			}
		}
	}
}
