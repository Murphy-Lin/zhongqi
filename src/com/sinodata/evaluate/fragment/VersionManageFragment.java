package com.sinodata.evaluate.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sinodata.evaluate.R;
import com.sinodata.evaluate.utils.VersionManage;

public class VersionManageFragment extends Fragment implements OnClickListener{

	private TextView tv_versioncode;
	private TextView tv_copyright;
	private TextView tv_telphone;
	private Button btn_checkupdate;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_versionmanage, container, false);
		tv_versioncode = (TextView) v.findViewById(R.id.tv_versioncode);
		tv_copyright = (TextView) v.findViewById(R.id.tv_copyright);
		tv_telphone = (TextView) v.findViewById(R.id.tv_telphone);
		btn_checkupdate = (Button) v.findViewById(R.id.btn_fragment_version);
		
		btn_checkupdate.setOnClickListener(this);
		String versionname = VersionManage.getAppVersion();
		tv_versioncode.setText(versionname);
		tv_copyright.setText("北京龙欢九合医药科技有限公司");
		tv_telphone.setText("12345678901");
		return v;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
