package com.appliaison.ubuntuedgeconceptdemo.controllers.step4;

import com.actionbarsherlock.app.SherlockActivity;
import com.appliaison.ubuntuedgeconceptdemo.HomeActivity;
import com.appliaison.ubuntuedgeconceptdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Step4Activity extends SherlockActivity implements
		WheelModel.Listener
{
	
	private Button buttonSkip;
	private Button buttonContinue;
	private TextView headerwelcometext;
	private TextView headertext;
	private TextView headermessagetext;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		this.getSupportActionBar().hide();
		setContentView(R.layout.activity_step4);

		ClickWheel wheel = (ClickWheel) findViewById(R.id.wheel);
		 wheel.getModel().addListener(this);
		buttonContinue = (Button) this.findViewById(R.id.buttonContinue);
		
		headerwelcometext = (TextView) this.findViewById(R.id.headerwelcometext);
		headertext = (TextView) this.findViewById(R.id.headertext);
		headermessagetext = (TextView) this.findViewById(R.id.headermessagetext);
		
		Typeface tf = Typeface.createFromAsset(this.getAssets(),
				"Linotype - FrutigerLTPro-Roman.otf");
		Typeface tf2 = Typeface.createFromAsset(this.getAssets(), "BandaRegular.ttf");
		Typeface tf3 = Typeface.createFromAsset(this.getAssets(), "avenir-65-medium-1361499812.ttf");
		Typeface tf4 = Typeface.createFromAsset(this.getAssets(), "Ubuntu-R.ttf");
		headerwelcometext.setTypeface(tf4);
		headertext.setTypeface(tf4);
		headermessagetext.setTypeface(tf4);

		buttonContinue.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// Intent intent = new Intent(CheckinStep2Activity.this,
				// HomeActivity.class);
				Intent intent = new Intent(Step4Activity.this,
					HomeActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onDialPositionChanged(WheelModel sender, int nicksChanged)
	{
		TextView headertext = (TextView) findViewById(R.id.headertext);
		headertext.setText(sender.getCurrentNick() + "");
	}
}