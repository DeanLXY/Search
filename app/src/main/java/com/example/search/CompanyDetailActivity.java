package com.example.search;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.search.sql.Devices;

public class CompanyDetailActivity extends Activity implements OnClickListener{

	private TextView mDish;
	private TextView mCount;
	private TextView mPrice;
	private TextView mNumber;
	private TextView mContent;
	private TextView mPriceUnit;
	private ImageView mImg;
	private Button mAdd;
	private Button mReduce;
	private RelativeLayout mShoppingCart;
	
	
	private int numTag = 0;
	private float sale = 19.0f;
	
     @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.company_fragment);
    	
    	mDish = (TextView) findViewById(R.id.dish);
    	mCount = (TextView) findViewById(R.id.count);
    	mPrice = (TextView) findViewById(R.id.price);
    	mNumber = (TextView) findViewById(R.id.number);
    	mContent = (TextView) findViewById(R.id.content);
    	mImg = (ImageView) findViewById(R.id.img);
    	mAdd = (Button) findViewById(R.id.addNum);
    	mReduce = (Button) findViewById(R.id.reduceNum);
    	mPriceUnit = (TextView) findViewById(R.id.price_unit);
    	mShoppingCart = (RelativeLayout) findViewById(R.id.shopping_cart);
    	
    	mAdd.setOnClickListener(this);
    	mReduce.setOnClickListener(this);
    	
    	Intent intent = getIntent();
    	Bundle bundle = intent.getBundleExtra("com");
    	Devices de = (Devices) bundle.get("com");
    	Log.e("ggj", de.toString());
    	mDish.setText(de.getName());
    	mCount.setText("月售   88");
    	mPrice.setText("￥  "+Float.toString(sale));
    	//mContent.setText(de.getIntroduciton());
    	

    }
    @Override
    public void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	Log.e("ggj", "ggj onResume");
    }
     
    public void getInfo(Devices devices){

    	
    	Log.e("ggj", "ggj getInfo");
    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addNum:
			if (mNumber.getVisibility() != View.VISIBLE ||  mReduce.getVisibility() != View.VISIBLE) {
				mNumber.setVisibility(View.VISIBLE);
				mReduce.setVisibility(View.VISIBLE);
				//mShoppingCart.setBackgroundResource(getResources().getColor(android.R.color.white));
			}
			numTag++;
			mNumber.setText(numTag+"");
			mPriceUnit.setText("￥  "+Float.toString(sale*numTag));
			break;
			
         case R.id.reduceNum:
        	 numTag --;
			if (numTag >= 1) {
				mNumber.setText(numTag+"");
				mPriceUnit.setText("￥  "+Float.toString((float)sale*numTag));
			}else{
				mNumber.setVisibility(View.GONE);
				mReduce.setVisibility(View.GONE);
			//	mShoppingCart.setBackgroundResource(getResources().getColor(android.R.color.darker_gray));
				
			}
			
			break;

		default:
			break;
		}
		
	}
}
