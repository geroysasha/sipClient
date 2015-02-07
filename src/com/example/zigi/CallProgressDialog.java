package com.example.zigi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;



public class CallProgressDialog extends ProgressDialog {

	protected android.view.View.OnClickListener btnOcl;
	private Chronometer crmCall;

	public CallProgressDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

	}
	
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		crmCall.stop();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {  
        // TODO Auto-generated method stub  
        super.onCreate(savedInstanceState);  

        setCancelable(false);
   
        setContentView(R.layout.call);  

        Button btnHangup  = (Button) findViewById(R.id.buttonHangup); 
        crmCall = (Chronometer) findViewById(R.id.chronometer);
        crmCall.start();
        //TODO: HangUp and close dialog 
        btnHangup.setOnClickListener(btnOcl);   
          
    }
												
	public void setBtnOcl(android.view.View.OnClickListener ocl){
		btnOcl = ocl;
	}
    
	public void setText(String text){

        TextView textView = (TextView) findViewById(R.id.call_textView); 
        textView.setText(text);
        
	}

	public void statError(){
        
		ImageView imageView = (ImageView) findViewById(R.id.image_break); 
		ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1); 
		Button btnHangup  = (Button) findViewById(R.id.buttonHangup); 
        imageView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        crmCall.setVisibility(View.GONE);
        btnHangup.setText("Close");
        
	}
}
