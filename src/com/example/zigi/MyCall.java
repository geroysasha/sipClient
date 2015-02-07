package com.example.zigi;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.AudDevManager;
import org.pjsip.pjsua2.AudioMedia;
import org.pjsip.pjsua2.Call;
import org.pjsip.pjsua2.CallInfo;
import org.pjsip.pjsua2.CallMediaInfo;
import org.pjsip.pjsua2.CallMediaInfoVector;
import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.Media;
import org.pjsip.pjsua2.OnCallMediaStateParam;
import org.pjsip.pjsua2.OnCallStateParam;
import org.pjsip.pjsua2.pjmedia_type;
import org.pjsip.pjsua2.pjsip_inv_state;
import org.pjsip.pjsua2.pjsua_call_media_status;

import android.util.Log;

public class MyCall extends Call{


	private Endpoint endpoint;

	public MyCall(Account acc, Endpoint ep) {
		super(acc);
		// TODO Auto-generated constructor stub
		this.endpoint = ep;
	}

	public void onCallState(OnCallStateParam prm) {
		try {
			CallInfo ci = getInfo();
			if (ci.getState() == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
				Log.d("ZiGi", " ZiGi info - PJSIP_INV_STATE_CONFIRMED" );		
			}
			if (ci.getState() == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED) {
				Log.d("ZiGi", " ZiGi info - PJSIP_INV_STATE_DISCONNECTED" );
			}			
			if (ci.getState() == pjsip_inv_state.PJSIP_INV_STATE_EARLY) {
				Log.d("ZiGi", " ZiGi info - PJSIP_INV_STATE_EARLY" );
			}			
		} catch (Exception e) {
			return;
		}		

	}
	
	@Override
	public void onCallMediaState(OnCallMediaStateParam prm) {
		CallInfo ci;
		try {
			ci = getInfo();
		} catch (Exception e) {
			return;
		}

		CallMediaInfoVector cmiv = ci.getMedia();

		for (int i = 0; i < cmiv.size(); i++) {
			CallMediaInfo cmi = cmiv.get(i);
			if (cmi.getType() == pjmedia_type.PJMEDIA_TYPE_AUDIO
					&& (cmi.getStatus() == pjsua_call_media_status.PJSUA_CALL_MEDIA_ACTIVE || cmi.getStatus() == pjsua_call_media_status.PJSUA_CALL_MEDIA_REMOTE_HOLD)) {
				// unfortunately, on Java too, the returned Media cannot be
				// downcasted to AudioMedia
				Media m = getMedia(i);
				AudioMedia am = AudioMedia.typecastFromMedia(m);
				AudDevManager dm = null;
				try {
					dm = this.endpoint.audDevManager();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// connect ports
				try {
					this.endpoint.audDevManager().getCaptureDevMedia().startTransmit(am);
					am.startTransmit(dm.getPlaybackDevMedia());
				} catch (Exception e) {
					continue;
				}
			}
		}
	}	
	
	
}
