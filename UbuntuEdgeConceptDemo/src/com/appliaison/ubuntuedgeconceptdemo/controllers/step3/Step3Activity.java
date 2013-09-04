package com.appliaison.ubuntuedgeconceptdemo.controllers.step3;

import com.actionbarsherlock.app.SherlockActivity;
import com.appliaison.ubuntuedgeconceptdemo.R;
import com.appliaison.ubuntuedgeconceptdemo.controllers.step4.Step4Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Step3Activity extends SherlockActivity implements
		CustomSlider.CustomSliderPositionListener
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
		setContentView(R.layout.activity_step3);

		CustomSlider customSlider = (CustomSlider) this
				.findViewById(R.id.slider_vertical);

		customSlider.setPositionListener(this);

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
				Intent intent = new Intent(Step3Activity.this,
						Step4Activity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public void onPositionChange(float newPosition)
	{
		// TODO Auto-generated method stub
		TextView text = (TextView) findViewById(R.id.text);
		// text.setText(String.valueOf(newPosition));
	}

}