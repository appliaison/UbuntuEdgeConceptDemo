package com.appliaison.ubuntuedgeconceptdemo.controllers.snapshots;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.appliaison.ubuntuedgeconceptdemo.R;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

public class SnapshotsActivity extends FragmentActivity
{
	
	private static final Random RANDOM = new Random();

	TestFragmentAdapter mAdapter;
	ViewPager mPager;
	PageIndicator mIndicator;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_titles);

		mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);

		TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
		mIndicator = indicator;
		indicator.setViewPager(mPager);

		final float density = getResources().getDisplayMetrics().density;
		indicator.setBackgroundColor(0xFF33B5E5);
		indicator.setFooterColor(0xFFAA2222);
		indicator.setFooterLineHeight(1 * density); // 1dp
		indicator.setFooterIndicatorHeight(3 * density); // 3dp
		indicator.setFooterIndicatorStyle(IndicatorStyle.Underline);
		indicator.setTextColor(0xAA000000);
		indicator.setSelectedColor(0xFF000000);
		indicator.setSelectedBold(true);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.random:
			final int page = RANDOM.nextInt(mAdapter.getCount());
			Toast.makeText(this, "Changing to page " + page, Toast.LENGTH_SHORT);
			mPager.setCurrentItem(page);
			return true;

		case R.id.add_page:
			if (mAdapter.getCount() < 10)
			{
				mAdapter.setCount(mAdapter.getCount() + 1);
				mIndicator.notifyDataSetChanged();
			}
			return true;

		case R.id.remove_page:
			if (mAdapter.getCount() > 1)
			{
				mAdapter.setCount(mAdapter.getCount() - 1);
				mIndicator.notifyDataSetChanged();
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}