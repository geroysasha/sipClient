package com.example.zigi;

import org.pjsip.pjsua2.AccountConfig;
import org.pjsip.pjsua2.AuthCredInfo;
import org.pjsip.pjsua2.CallOpParam;
import org.pjsip.pjsua2.CallSetting;
import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.EpConfig;
import org.pjsip.pjsua2.TransportConfig;
import org.pjsip.pjsua2.pjsip_transport_type_e;

import android.util.Log;

public class ZigiSip {

	private Endpoint ep;
	private CallOpParam prm;
	private MyCall myCall;
	private MyAccount acc;
	private AccountConfig acfg;
	private MainActivity mainActivity;
	
	static {
		System.loadLibrary("pjsua2");
		Log.d("ZiGi", "Library loaded");
	}

	public ZigiSip(MainActivity mainActivity) {
		super();
		// TODO Auto-generated constructor stub
		ep = null;
		acc = null;
		myCall = null;
		
		this.mainActivity = mainActivity;
	}

	private boolean initEndpoint() {
		
		// Create endpoint
		ep = new Endpoint();

		try {
			ep.libCreate();
			
			ep.libInit(epConfig());
			
			// Create SIP transport. Error handling sample is shown
			TransportConfig sipTpConfig = new TransportConfig();
			sipTpConfig.setPort(5060);

			ep.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP,
					sipTpConfig);
			// Start the library
			ep.libStart();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
		
	}

	private EpConfig epConfig(){

		// Initialize endpoint
		EpConfig epConfig = new EpConfig();

		epConfig.getMedConfig().setClockRate(16000);
		epConfig.getMedConfig().setSndClockRate(44100);
		epConfig.getMedConfig().setMaxMediaPorts(4);
		epConfig.getMedConfig().setNoVad(true);
		epConfig.getMedConfig().setQuality(10);
		epConfig.getMedConfig().setPtime(0);
		epConfig.getMedConfig().setEcOptions(0);
		epConfig.getMedConfig().setEcTailLen(0);	
		return epConfig;		
		
	}
	
	private void callOpParam(){
		
		prm = new CallOpParam(true);
		CallSetting opt = prm.getOpt();
		opt.setAudioCount(1);
		opt.setVideoCount(0);		
		
	}	
	
	public boolean createAccount(String server, String user, String password) {

		if(initEndpoint()){
			acfg = new AccountConfig();
			acfg.setIdUri("sip:" + user + "@" + server);
			acfg.getNatConfig().setIceEnabled(true);
			acfg.getRegConfig().setRegistrarUri("sip:" + server);
			AuthCredInfo cred = new AuthCredInfo("digest", "*", user, 0, password);
			acfg.getSipConfig().getAuthCreds().add(cred);
			acc = new MyAccount(this.mainActivity);			
			try {
				acc.create(acfg);
				myCall = new MyCall(acc, ep);	
				callOpParam();							
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}  else{
			return false;
		}	
		
		return true;

	}
	
	/*private MyCall createMyCall() {
		MyCall myCall = new MyCall(acc, ep);	
		callOpParam();
		return myCall;		
	}*/
	
 	public void sipMakeCall(String sipName) {
		try {
			myCall.makeCall("sip:" + sipName + "@"+ this.mainActivity.getServerIp() + "", prm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void sipHangupCall(){
		try {
			myCall.hangup(prm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sipClear() {

        //acc.delete();
        //acc = null;
        // Explicitly destroy and delete endpoint
        try {
			ep.libDestroy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ep.delete();
	}	
	
}
