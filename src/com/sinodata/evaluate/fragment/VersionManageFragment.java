package com.sinodata.evaluate.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sinodata.evaluate.R;
import com.sinodata.evaluate.beans.Version;
import com.sinodata.evaluate.utils.FastJsonTools;
import com.sinodata.evaluate.utils.HttpUtil;
import com.sinodata.evaluate.utils.VersionManage;

public class VersionManageFragment extends Fragment implements OnClickListener{

	private TextView tv_versioncode;
	private TextView tv_copyright;
	private TextView tv_telphone;
	private Button btn_checkupdate;
	private VersionManage vm;
	//版本号检测url
	private String versionUrl = "http://123.57.4.158:8080/Assessment/crypto?serviceType=MBS0000050";
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_versionmanage, container, false);
		tv_versioncode = (TextView) v.findViewById(R.id.tv_versioncode);
		tv_copyright = (TextView) v.findViewById(R.id.tv_copyright);
		tv_telphone = (TextView) v.findViewById(R.id.tv_telphone);
		btn_checkupdate = (Button) v.findViewById(R.id.btn_fragment_version);
		vm = new VersionManage(getActivity());
		btn_checkupdate.setOnClickListener(this);
		tv_versioncode.setText(VersionManage.getAppVersionName());
		tv_copyright.setText("北京龙欢九和医药科技有限公司");
		tv_telphone.setText("longhuanjiuhe@126.com");
		return v;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		HttpUtil.get(versionUrl, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, String content) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, content);
				//JSONObject obj = JSON.parseObject(content);
				List<Version> list = FastJsonTools.getBeans(content, Version.class);
				String versionCode = list.get(0).getVersion();
				if(vm.getAppVersion() < Integer.valueOf(versionCode)){
					vm.checkUpdateInfo(list.get(0).getUrl());
				}else{
					Toast.makeText(getActivity(), "此版本已是最新版本！", Toast.LENGTH_LONG).show();
				}
			}
			@Override
			public void onFailure(Throwable error, String content) {
				// TODO Auto-generated method stub
				super.onFailure(error, content);
				Toast.makeText(getActivity(), "网络请求异常！", Toast.LENGTH_LONG).show();
			}
		});
		
		
	}
	
}
