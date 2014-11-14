package net.sjava.book.ch04;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import net.sjava.book.AbstractActivity;
import net.sjava.book.BitmapHandler;
import net.sjava.book.BookApplication;
import net.sjava.book.R;

public class Ch0401Activity extends AbstractActivity {
	private TextView mTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_ch04_01);
		
		mTextView = (TextView)findViewById(R.id.activity_ch04_01_txtview);
		
		// ���� ������ ����
		

		runUIThread();
		//runNonUIThread();
		
		// �Ϲ� ������� ����
		/*

		*/		
	}
	
	private void runUIThread() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				File f = new File(Environment.getExternalStorageDirectory() +"/net.sjava.book/");
				if(!f.exists())
					f.mkdirs();
				
				Bitmap bitmap = null;
				BitmapHandler handler = BitmapHandler.newInstance();
				try {
					
					// resource�� �ִ� �̹����� �ε��ϰ� �� �̹����� UI �����忡�� �����Ѵ�. 
					for(int i=0; i < 6; i++) {
						bitmap = BitmapFactory.decodeResource(getResources(), BookApplication.imageArray[i]);
						handler.save(bitmap, BookApplication.strArray[i]);
						Thread.sleep(1000);
					}
					
					// ���� �������̱⿡ ȭ�� ���Ű���
					Log.d(CNAME, Thread.currentThread().getName());					
					mTextView.setText("�ʱ�ȭ �Ϸ�");
				} catch(Throwable e) {
					Log.e(CNAME, "runOnUiThread error", e);
				}
			}
		});
	}
	
	private void runNonUIThread() {
		new Thread(new Runnable(){
			@Override
			public void run() {
				File f = new File(Environment.getExternalStorageDirectory() +"/net.sjava.book/");
				if(!f.exists())
					f.mkdirs();
				
				Bitmap bitmap = null;
				BitmapHandler handler = BitmapHandler.newInstance();
				
				try {
					// resource�� �ִ� �̹����� �ε��ϰ� �� �̹����� UI �����忡�� �����Ѵ�.
					for(int i=0; i < 6; i++) {
						bitmap = BitmapFactory.decodeResource(getResources(), BookApplication.imageArray[i]);
						handler.save(bitmap, BookApplication.strArray[i]);
					}
					
					Log.d(CNAME, Thread.currentThread().getName());
					
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// ���� �������̱⿡ ȭ�� ���Ű���
							Log.d(CNAME, Thread.currentThread().getName());
							mTextView.setText("�ʱ�ȭ �Ϸ�");
						}
					});
				}catch(Throwable e) {
					Log.e(CNAME, "runOnUiThread error", e);
				}				
			}
		}).start();
	}
}
