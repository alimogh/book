package net.sjava.book.ch03;

import net.sjava.book.AbstractActivity;
import net.sjava.book.R;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Ch0301Activity extends AbstractActivity {
	private TextView txtView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_ch03_01);

		txtView = (TextView)findViewById(R.id.activity_ch03_01_txtView_01);		
		// ��Ʈ�� Ȯ���� ���� �κ�
		View v = txtView.getRootView();
	}
}
