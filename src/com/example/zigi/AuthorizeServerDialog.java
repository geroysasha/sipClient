package com.example.zigi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TabHost;

public class AuthorizeServerDialog extends CallProgressDialog{

	public AuthorizeServerDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
        // TODO Auto-generated method stub  
        super.onCreate(savedInstanceState);  

        setCancelable(false);
   
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        
        setContentView(R.layout.authorize);  
        
        TabHost tabHost = (TabHost) this.findViewById(android.R.id.tabhost);

        tabHost.setup();
        
        TabHost.TabSpec tabSpec;
        
        tabSpec = tabHost.newTabSpec("tab1");
        tabSpec.setIndicator("Own server");
        tabSpec.setContent(tabContentFactory);
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec("tab2");
        tabSpec.setIndicator("ZiGi server");
        tabSpec.setContent(tabContentFactory);        
        tabHost.addTab(tabSpec);
        
        tabHost.setCurrentTabByTag("tab1");        
       
        Button button = (Button) findViewById(R.id.auth_tab_one_button);
        
        button.setOnClickListener(btnOcl);
        
    }


	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();	
	}

	private TabHost.TabContentFactory tabContentFactory = new TabHost.TabContentFactory() {
		
		@Override
		public View createTabContent(String tag) {
			switch(tag){
			case "tab1":
				return getLayoutInflater().inflate(R.layout.authorize_tab_one, null);
			case "tab2":
				return getLayoutInflater().inflate(R.layout.authorize_tab_two, null);
			};		
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	public String[] getAuthInfo(){
		
		String[] string = new String[] {((EditText) findViewById(R.id.auth_tab_one_editText_server_ip)).getText().toString()
			, ((EditText) findViewById(R.id.auth_tab_one_editText_users)).getText().toString()
			, ((EditText) findViewById(R.id.auth_tab_one_editText_password)).getText().toString()};
		
		return string;	
	}

	public void setAuthInfo(String[] info){
		
		if(info[0].equals(null)){
			((EditText) findViewById(R.id.auth_tab_one_editText_server_ip)).setText(info[0]);
		}
		if(info[1].equals(null)){
			((EditText) findViewById(R.id.auth_tab_one_editText_users)).setText(info[1]);
		}
		if(info[2].equals(null)){
			((EditText) findViewById(R.id.auth_tab_one_editText_password)).setText(info[2]);	
		}
		
	}	
	
	public boolean getStateCheckBox(){
        
        CheckBox checkBox = (CheckBox) findViewById(R.id.auth_tab_one_checkBox);
        return checkBox.isChecked();
        
	}
}
