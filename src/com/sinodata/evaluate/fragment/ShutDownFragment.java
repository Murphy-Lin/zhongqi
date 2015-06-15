package com.sinodata.evaluate.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sinodata.evaluate.R;

public class ShutDownFragment extends Fragment {

	private Button btn_shutdown;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_shutdown, container, false);
		btn_shutdown = (Button) v.findViewById(R.id.btn_fragment_shutdown);
		btn_shutdown.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(getActivity()).setTitle("您确认是否关机么？").setPositiveButton("关机", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						Process proc;
						try {
							proc = Runtime.getRuntime().exec(new String[]{"su","-c","reboot -p"});
							proc.waitFor();
							Toast.makeText(getActivity(), "自动关机", Toast.LENGTH_SHORT).show();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
					}
				}).setNegativeButton("不关机", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				}).show();
			}
		});
		return v;
	}
}
