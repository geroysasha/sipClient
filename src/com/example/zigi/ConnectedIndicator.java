package com.example.zigi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class ConnectedIndicator extends View{

	private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);	 

	private String logTag = "ZiGI";
	
	private Canvas mCanvas;  
	private int i = 0;
	
	
	public ConnectedIndicator(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

    @Override
    public void onDraw(Canvas canvas) 
    {
    	Log.d(logTag, String.valueOf(i));
    	mCanvas = canvas;    	
    	if(i == 0){
	    	mPaint.setColor(Color.GRAY);
	    	canvas.drawCircle(50, 20, 10, mPaint); 
	    	mPaint.setColor(Color.YELLOW);
	    	canvas.drawCircle(20, 20, 10, mPaint); 
	    	i++;
    	} else{
    		if((i%2)==0){
	    		mPaint.setColor(Color.GRAY);
	    		canvas.drawCircle(50, 20, 10, mPaint); 
	    	    mPaint.setColor(Color.YELLOW);
	    	    canvas.drawCircle(20, 20, 10, mPaint);
    		} else{
	    		mPaint.setColor(Color.YELLOW);
	    		canvas.drawCircle(50, 20, 10, mPaint); 
	    	    mPaint.setColor(Color.GRAY);
	    	    canvas.drawCircle(20, 20, 10, mPaint);    
    		}    		
    	}
    	if(i == 11){
    		canvas.drawText("Connected", 70, 25, mPaint);
    	}
    }	
    
    public void setStatus(){
	    i++;    		
	    postInvalidate();	
    }
    
    

}   