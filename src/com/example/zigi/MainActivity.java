package com.example.zigi;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private String logTag = "ZiGI";
	private ZigiSip zigiSip;
	private BroadcastReceiver bcr;
	private String serverIp;
	private String userName;
	private CallProgressDialog callProcess;
    protected RelativeLayout mainRelativeLayout;
    protected TextView textView_number_phone;
    protected AuthorizeServerDialog authorizeServerDialog; 
    protected int[] idButtonDialKeyboard;
    protected Utilites utilites;
    
	
    public final static String BROADCAST_ZIGI_SIP_STATE = "broadcast.zigi.sip.state";
    public final static int ZIGI_ASYNCTASK_AUTHORIZE = 0;
    public final static int ZIGI_ASYNCTASK_GATEWAY = 1;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		utilites = new Utilites(this);
		
		zigiSip = new ZigiSip(this);
				
		authorizeServerDialog = new AuthorizeServerDialog(MainActivity.this);	
		authorizeServerDialog.setBtnOcl(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] info = authorizeServerDialog.getAuthInfo();
				
				if(authorizeServerDialog.getStateCheckBox()){
					utilites.saveSipInfo(info);
				}
				authorizeServerDialog.dismiss();

				/* info[0] contains server ip
				 * info[1] contains user name
				 * info[0] contains password
				 */
				serverIp = info[0];
				userName = info[1];
				zigiSip.createAccount(info[0], info[1], info[2]);
				utilites.authorizeIndicatorProcess(ZIGI_ASYNCTASK_AUTHORIZE);
			}});
		
		bcr = new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
				String event = intent.getStringExtra("event");
				
				switch(event){
				case "MyAccountRegState":
					int myAccountRegState = intent.getIntExtra("MyAccountRegState", 0);
					utilites.stopAuthorizeIndicatorProcess(myAccountRegState);
					break;
				case "sipGsmGatewayRegState":
					String str = intent.getStringExtra("sipGsmGatewayRegState");
					if(str.contains("ERROR")){
						callProcess.statError();
					}
					callProcess.setText(str);	
					break;
				case "sipMakeCall":
					String[] sip_info = intent.getStringArrayExtra("sipMakeCall");
					/**
					 * sip_info[0] - sip number GSM gateway
					 * sip_info[1] - name GSM gateway
					 */					
					callProcess.setText("Make call..." + sip_info[1]);
					zigiSip.sipMakeCall(sip_info[0]);
										
					break;
				}		
				
			}};
		
		IntentFilter intFilt = new IntentFilter(BROADCAST_ZIGI_SIP_STATE);
		registerReceiver(bcr, intFilt);
		
    	mainRelativeLayout = (RelativeLayout) this.findViewById(R.id.mainRelativeLayout);
		textView_number_phone = (TextView) this.findViewById(R.id.textView_number_phone);

		idButtonDialKeyboard = new int[] {R.id.button_zero
				, R.id.button_one
				, R.id.button_two
				, R.id.button_three
				, R.id.button_four
				, R.id.button_five
				, R.id.button_six
				, R.id.button_seven
				, R.id.button_eight
				, R.id.button_nine
				, R.id.button_plus
				, R.id.button_grid
				, R.id.button_backspace
				, R.id.button_call};
		
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
    		if(utilites.addConnectionIndicator(mainRelativeLayout)){	

				String[] info = utilites.loadSipInfo();  			
    			authorizeServerDialog.show();
				authorizeServerDialog.setAuthInfo(info);      			
    			
    		} else{ 
    			
    			Log.d(logTag,"Error method addConnectionIndicator!!");
    			
    		}        	
        } else {
        	
            Log.d("Network Error", "No network connection available.");
            
        }
	
		findButtonMain(this);

	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      // TODO Auto-generated method stub, RelativeLayout mainRelativeLayout

      menu.add("server settings");
      menu.add("menu2");
      menu.add("menu3");
      menu.add("menu4");
      
      return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      // TODO Auto-generated method stub
      //Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();

      switch(item.getTitle().toString()){
      case "server settings":
    	  zigiSip.sipClear();
    	  authorizeServerDialog.show();
    	  break;
      }
      return super.onOptionsItemSelected(item);
    }
   	
	private void findButtonMain(MainActivity mainActivity){
		
		Button findButton = null;
		for(int i = 0; i < idButtonDialKeyboard.length; i++){
			findButton = (Button) mainActivity.findViewById(idButtonDialKeyboard[i]);
			findButton.setOnClickListener(mainActivity);		
		}
		
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.button_call: 
			
			callProcess = new CallProgressDialog(MainActivity.this);	
			OnClickListener oclHangUp = new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					zigiSip.sipHangupCall();
					callProcess.dismiss();
				}};
			callProcess.setBtnOcl(oclHangUp);

			//Intent callActivityIntent = new Intent(MainActivity.this, CallActivity.class);
			//startActivity(callActivityIntent);
			callProcess.show();	
			utilites.availableVoipGsmGateway(ZIGI_ASYNCTASK_GATEWAY);
			break;		
		case R.id.button_plus: 
			setTextView_number_phone("*");	
			break;
		case R.id.button_grid: 
			//connectProcess();
			setTextView_number_phone("#");	
			break;
		case R.id.button_backspace: 
			backspaceTextView_number_phone();
			break;		
		default:
			setTextView_number_phone( ((Button) this.findViewById(arg0.getId())).getText().toString() );
			break;
		}
		
	}	

	//TODO: adds simple a digit in field number mobile
    public void setTextView_number_phone(String text){
    	textView_number_phone.setText(textView_number_phone.getText() + text);
    }
    
    //TODO: deletes simple a digit out field number mobile
    public void backspaceTextView_number_phone(){
    	String text = (String) textView_number_phone.getText();
    	if(!text.equals(""))
    		textView_number_phone.setText(text.substring(0, text.length() - 1));
    }  

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		zigiSip.sipClear();			
		this.unregisterReceiver(bcr);
		super.onStop();
	}

	protected String getServerIp(){
		return serverIp;
	}
	
	protected String getUserName(){
		return userName;
	}
	
	protected void sendBroadcast(String state, Object sendParameter){
		Intent intent = new Intent(MainActivity.BROADCAST_ZIGI_SIP_STATE);
		intent.putExtra("event", state);
		if(sendParameter instanceof String){
			intent.putExtra(state, (String) sendParameter);
		} else if(sendParameter instanceof String[]){
			intent.putExtra(state, (String[]) sendParameter);
		} else {
			intent.putExtra(state, (int) sendParameter);			
		}

		MainActivity.this.sendBroadcast(intent);
	}

}
