package com.example.zigi;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.OnRegStateParam;

import android.content.Intent;

public class MyAccount extends Account  {

	private MainActivity mainActivity;

	public MyAccount(MainActivity mainActivity) {
		// TODO Auto-generated constructor stub
		
		this.mainActivity = mainActivity;
	}

	@Override
	  public void onRegState(OnRegStateParam prm) {

	    System.out.println("*** On registration state: " + prm.getCode());

	    if(prm.getExpiration() != 0){
			if((prm.getReason().toString()).equals("OK")){
				mainActivity.sendBroadcast("MyAccountRegState", 1);
			} else{
				mainActivity.sendBroadcast("MyAccountRegState", 0);
			}
	    }
	  }
	
}
