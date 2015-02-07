package com.example.zigi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Utilites {

	private String logTag = "ZiGI";
	private ConnectedIndicator connectedIndicator;
	private ZigiAsynkTask zigiAsynkTask;
	private SharedPreferences sPref;
	private MainActivity mainActivity;

	public Utilites(MainActivity mainActivity) {
		super();
		// TODO Auto-generated constructor stub
		SharedPreferences sPref;
		this.mainActivity = mainActivity;
	}

	//shows process of connecting to a server VOIP-GSM
	public void authorizeIndicatorProcess(int zigiAsynktaskSelect){
		
		zigiAsynkTask = new ZigiAsynkTask(zigiAsynktaskSelect);
    	zigiAsynkTask.execute();

	}

	public void stopAuthorizeIndicatorProcess(int returnAuthorizeStatus){

    	zigiAsynkTask.setFlag(returnAuthorizeStatus);
		
	}	
	
	public void availableVoipGsmGateway(int zigiAsynktaskSelect){
		
		zigiAsynkTask = new ZigiAsynkTask(zigiAsynktaskSelect);
    	zigiAsynkTask.execute();		
		
	}
	
	//add process indicator of connecting to a server VOIP-GSM on layout
	public boolean addConnectionIndicator(Object Layout){

		if(Layout.getClass().getName().contains("RelativeLayout")){
			
			connectedIndicator = new ConnectedIndicator(this.mainActivity);
			((RelativeLayout) Layout).addView(connectedIndicator);
			return true;
			
		} else if(Layout.getClass().getName().contains("LinearLayout")){

			connectedIndicator = new ConnectedIndicator(this.mainActivity);
			((LinearLayout) Layout).addView(connectedIndicator);
			return true;
			
		} else {
			Log.d(logTag,"Error method addConnectionIndicator: this object not supported!");
		}
		return false;
	}		
	
	public void appDialogError(String string){
		
	}
	
	public void appDialogSelect(){
		
	}	
	
	public void appDialogList(){
		
	}		
	
	@SuppressWarnings("static-access")
	public void saveSipInfo(String[] info){
		sPref = this.mainActivity.getPreferences(this.mainActivity.MODE_PRIVATE);
	    Editor ed = sPref.edit();
	    ed.putString("server", info[0]);
	    ed.putString("user", info[1]);
	    ed.putString("password", info[2]);	    
	    ed.commit();		
	}
	
	@SuppressWarnings("static-access")
	public String[] loadSipInfo(){
		
		String[] string = new String[3];
	    sPref = this.mainActivity.getPreferences(this.mainActivity.MODE_PRIVATE);
	    string[0] = sPref.getString("server", "");
	    string[1] = sPref.getString("user", "");
	    string[2] = sPref.getString("password", "");
		return string;	    
	    
	}	
	
	private class ConnectedIndicator extends View{

		private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);	 
		private int i;
		private int status;		
		
		public ConnectedIndicator(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
			i = 0;
			status = -1;
			
		}		
		
		@Override
		public void postInvalidate() {
			// TODO Auto-generated method stub
			super.postInvalidate();
			
			if(i == 0){
				i = 1;
			} else{
				i = 0;
			}
			
		}	

	    @Override
	    public void onDraw(Canvas canvas){	
	    	String connText = null;
	    	switch(status){
		    	case -1:connText = "Connection";
		    		break;
		    	case 0:connText = "Connection failed!";
		    		break;
		    	case 1:connText = "Connected";
		    		break;
	    	};	  
	    	
	    	setLed(canvas, connText);
	    	status = -1;
	    }	
	    
	    private void setLed(Canvas canvas, String connText){  
	
    		if(connText.equals("Connection failed!")){
    			
    			setColor(canvas, Color.GRAY, Color.RED);
     			 			
    		} else if(connText.equals("Connection")){
        		if((i%2)==0){
        			
        			setColor(canvas, Color.YELLOW, Color.GRAY);
        			
        		} else{
        			
        			setColor(canvas, Color.GRAY, Color.YELLOW);
        			
        		}     			
    		} else if(connText.equals("Connected")){

    			setColor(canvas, Color.GRAY, Color.GREEN);
    			
    		}
			canvas.drawText(connText, 70, 25, mPaint);
			
	    }

	    private void setColor(Canvas canvas, int colorOne, int colorTwo){
	    	
    	    mPaint.setColor(colorOne);
    	    canvas.drawCircle(20, 20, 10, mPaint);  
    		mPaint.setColor(colorTwo);	 
    		canvas.drawCircle(50, 20, 10, mPaint); 
    		if(colorTwo == Color.GRAY){
    			mPaint.setColor(Color.YELLOW);
    		}

	    }
	    
	    private void setStatus(int statusInternetConnection){
	    	
	    	status = statusInternetConnection;
	    	
	    }	    
	
	    //end class ConnectedIndicator  
	}  
	
	public class ZigiAsynkTask	extends AsyncTask<Object, Object, Object>{
		
		private boolean flag;
		private int status;
		private int zigiAsynktaskSelect;
		
	    public ZigiAsynkTask(int zigiAsynktaskSelect) {
			super();
			// TODO Auto-generated constructor stub
			flag = false;
			this.zigiAsynktaskSelect = zigiAsynktaskSelect;
		}   
	    
		@Override
	    protected void onPreExecute() {
	      super.onPreExecute();
	    }		
		
		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			switch(this.zigiAsynktaskSelect){
			case MainActivity.ZIGI_ASYNCTASK_AUTHORIZE:
				Indicator();
				break;
			case MainActivity.ZIGI_ASYNCTASK_GATEWAY:
				String[] sip_info = getGatewayZigi(mainActivity.textView_number_phone.getText().toString());
				/**
				 * sip_info[0] - sip number GSM gateway
				 * sip_info[1] - name GSM gateway
				 */
				if(sip_info.length > 1){
					mainActivity.sendBroadcast("sipGsmGatewayRegState"
							, "found: " + sip_info[1] + " GSM gateway...");	
					if(setCallerId(mainActivity.textView_number_phone.getText().toString())){
						mainActivity.sendBroadcast("sipGsmGatewayRegState"
								, "Ok:registered " + sip_info[1] + " GSM gateway...");
						mainActivity.sendBroadcast("sipMakeCall", sip_info);
					} else{
						mainActivity.sendBroadcast("sipGsmGatewayRegState"
								, "ERROR:not registered " + sip_info[1] + " GSM gateway!");
					}
				} else{
					mainActivity.sendBroadcast("sipGsmGatewayRegState"
							, "ERROR:not detected GSM gateway!");
				}
				break;
			}
			return null;
		}

		protected void setFlag(int returnAuthorizeStatus){

			status = returnAuthorizeStatus;
			flag = true;
			
		}
		
		private String[] getGatewayZigi(String number) {
			
			String dataResponse = "";
			String[] sip_info = new String[1];
			
			BufferedReader in = null;
			
		    HttpClient httpclient = new DefaultHttpClient();
		    //HttpPost httppost = new HttpPost("http://tinyurl.com/phco5mt");
		    HttpPost httppost = new HttpPost("http://" + mainActivity.getServerIp() + "/rtc/providers_gsm/GSMPS_DB_sip_name.php");
		    Log.d("Network Error", "start ==========");
		    try {
		        // Add data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		        nameValuePairs.add(new BasicNameValuePair("IpCheck", getLocalIpAddress()));
		        nameValuePairs.add(new BasicNameValuePair("number", number));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);
		        in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		        dataResponse = in.readLine();

		        if(!dataResponse.contains("Errore - parameter is not found.")){
			        sip_info = dataResponse.split("&");
			        //temporary solution, example: sip_info[0] = "No1001", delete "No"
			        sip_info[0] = sip_info[0].substring(sip_info[0].length() - 4
			        		, sip_info[0].length());
		        }
		        
		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		    }
			return sip_info;
			
		} 	

		private Boolean setCallerId(String callerid){
			
			BufferedReader in = null;
			
		    HttpClient httpclient = new DefaultHttpClient();
		    //HttpPost httppost = new HttpPost("http://tinyurl.com/phco5mt");
		    HttpPost httppost = new HttpPost("http://" + mainActivity.getServerIp() + "/rtc/lib/zigi_db_set.php");
		    Log.d("Network Error", "start ==========");
		    try {
		        // Add data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		        
		        nameValuePairs.add(new BasicNameValuePair("command", "setCallerId"));
		        nameValuePairs.add(new BasicNameValuePair("name", mainActivity.getUserName()));
		        /**temporary solution, adaptation with  the algorithm of  the web interface
		         * example:callerid = mf0991234567/1422966047116/00.00.00.00
		         * mf - mobile, free call
		         */
		        nameValuePairs.add(new BasicNameValuePair("callerid","mf" + callerid + "/" + System.currentTimeMillis() + "/" + getLocalIpAddress()));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);
		        in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		        if(in.readLine() != null){
		        	return false;
		        }
	        
		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		    }

			return true;

		}
		
 		private String getLocalIpAddress()
	    {
	        try {
	            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	                NetworkInterface intf = en.nextElement();
	                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                    InetAddress inetAddress = enumIpAddr.nextElement();
	                    if (!inetAddress.isLoopbackAddress()) {
	                    	//Log.d("Network Error", "IP ==" + inetAddress.getHostAddress().toString());
	                        return inetAddress.getHostAddress().toString();
	                    }
	                 }
	             }
	         } catch (SocketException ex) {
	             String LOG_TAG = null;
	            Log.e(LOG_TAG, ex.toString());
	         }

	         return null;
	    }		
		
		private void Indicator(){
			
			while(!flag){
				connectedIndicator.postInvalidate();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			flag = false;
			
			connectedIndicator.setStatus(status);
			connectedIndicator.postInvalidate();			
		}

	}

	
}
