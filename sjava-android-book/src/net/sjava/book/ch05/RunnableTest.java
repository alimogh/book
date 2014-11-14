package net.sjava.book.ch05;

import android.os.Process;
import android.util.Log;

public class RunnableTest implements Runnable {
	static final String CNAME = RunnableTest.class.getSimpleName();

	@Override
	public void run() {
		int tid = Process.myTid();

		Log.d(CNAME, Thread.currentThread().getName() + " �켱���� ["
				+ Thread.currentThread().getPriority() + "] �� ����");
		Log.d(CNAME,
				Thread.currentThread().getName() + " �켱���� ["
						+ Process.getThreadPriority(tid) + "] �� ����");
	}
}
