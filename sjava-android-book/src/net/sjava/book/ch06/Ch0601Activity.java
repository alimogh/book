package net.sjava.book.ch06;

import net.sjava.book.AbstractActivity;
import net.sjava.book.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ch0601Activity extends AbstractActivity {
	
	private TextView mTextView;
	private Button mBtn01;
	private Button mBtn02;
	private Button mBtn03;
	private Button mBtn04;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_ch06_01);
		
		mTextView = (TextView)findViewById(R.id.activity_ch06_01_txtview);
		
		mBtn01 = (Button)findViewById(R.id.activity_ch06_01_btn_01);
		mBtn02 = (Button)findViewById(R.id.activity_ch06_01_btn_02);
		mBtn03 = (Button)findViewById(R.id.activity_ch06_01_btn_03);
		mBtn04 = (Button)findViewById(R.id.activity_ch06_01_btn_04);
	
		
		mBtn01.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new UpdateUIThread(mTextView).start();
			}
		});
		
		mBtn02.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Handler(new UpdateHandlerCallback()).sendMessageDelayed(new Message(), 1 * 1000);
			}
		});
				
		mBtn03.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Handler().post(new Runnable() {
					@Override
					public void run() {
						mTextView.setText("Handler post(Runnable r) ���� ��");
					}
				});
			}
		});
		
		mBtn04.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new UpdateLooperThread(mTextView).start();
			}
		});
	}

	class UpdateHandlerCallback implements Handler.Callback {
		@Override
		public boolean handleMessage(Message msg) {
			mTextView.setText("Handler.Callback ���� ��");
			return true;
		}
	}
	
	class UpdateUIThread extends Thread{
		private TextView tv;
		public UpdateUIThread(TextView tv) {
			this.tv = tv;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(1 * 1000);
				
				// ���� �����尡 �ƴϱ� ������ ����..
				tv.setText("���� ��");
				
			} catch(Exception e) {
				Log.e(CNAME, "run error", e);
			}
		}
	}
	
	
	class UpdateLooperThread extends Thread {
		private TextView tv;
		public UpdateLooperThread(TextView tv) {
			this.tv = tv;
		}
		
		@Override
		public void run() {
			try {					
				Thread.sleep(1000);
				
				// �Ϲ� ������� ���۰� ���ε��Ǿ� ���� �ʾƼ�, �ڵ鷯�� ��������� �Ѱ���� �Ѵ�
				new Handler(Looper.getMainLooper()).post(new Runnable() {
					@Override
					public void run() {
						tv.setText("�Ϲݽ����忡�� Handler ��� ���� ��");
					}
				});
				
			} catch(Exception e) {
				Log.e(CNAME, "run error", e);
			}
		}
	}
	


}
