package com.appliaison.ubuntuedgeconceptdemo;

import java.util.Timer;
import java.util.TimerTask;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ImageView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuInflater;
import com.appliaison.ubuntuedgeconceptdemo.controllers.step1.Step1Activity;

public class SplashScreenActivity extends SherlockActivity
{

	private static final String TAG = "SplashScreenActivity";

	private MenuItem mMenuDebug;

	private ImageView imageView1;

	private static final int DEBUG_ID = Menu.FIRST + 0;

	private static final int PROFILE_ID = Menu.FIRST + 1;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		this.getSupportActionBar().hide();

		// imageView1 = (ImageView) this.findViewById(R.id.imageView1);

		TimerTask timerTask;
		Timer timer;

		timerTask = new TimerTask()
		{

			@Override
			public void run()
			{
				
				startActivity(new Intent(SplashScreenActivity.this,
						Step1Activity.class));
				finish();
			
			}
		};

		timer = new Timer();
		timer.schedule(timerTask, 1 * 1000);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		if (FeaturesConfiguration.isDebug)
		{
			// getSupportMenuInflater().inflate(R.menu.splash_menu, menu);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// If this callback does not handle the item click,
		// onPerformDefaultAction
		// of the ActionProvider is invoked. Hence, the provider encapsulates
		// the
		// complete functionality of the menu item.

		switch (item.getItemId())
		{
		case R.id.menu_item_debug_provider_overflow:
		{
			Intent intent = new Intent(SplashScreenActivity.this,
					MainActivity.class);

			startActivity(intent);
			break;
		}
		case R.id.menu_item_profile_provider_overflow:
		{
//			Intent intent = new Intent(SplashScreenActivity.this,
//					ProfileStep1Activity.class);
//			startActivity(intent);
			break;
		}

		case R.id.menu_item_checkin_provider_overflow:
		{
//			startActivity(new Intent(SplashScreenActivity.this,
//					CheckinStep1Activity.class));
		}
		
		case R.id.menu_item_html5_provider_overflow:
		{
//			startActivity(new Intent(SplashScreenActivity.this,
//					HTML5Proto.class));
		}

		}

		return (super.onOptionsItemSelected(item));
	}

}
