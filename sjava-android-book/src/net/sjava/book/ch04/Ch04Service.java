package net.sjava.book.ch04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Ch04Service extends Service {
	public static final String TAG = Ch04Service.class.getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();

		Log.i(TAG, "����");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int i = super.onStartCommand(intent, flags, startId);

		String value = intent.getStringExtra("txt");
		Log.i(TAG, "���� ����Ÿ : " + value);
		
		return i;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		Log.i(TAG, "����");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
